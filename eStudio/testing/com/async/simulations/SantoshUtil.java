package com.async.simulations;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.async.util.CommonUtil;
import com.async.util.Constants;
import com.async.util.Constants.DBCollectionEnum;
import com.async.util.Constants.MasterTypeEnum;
import com.async.util.MongoUtil;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.QueryOperators;
import com.mongodb.util.JSON;

public class SantoshUtil {

	private static DB db = null;

	public static DB getDB() {
		if (db == null) {
			MongoClient mongoClient = null;
			try {
				mongoClient = new MongoClient(Constants.DB_HOST, Constants.DB_PORT);
			} catch (UnknownHostException e) {
				e.printStackTrace();
			}

			db = mongoClient.getDB(Constants.DB_NAME);
		}
		return db;
	}

	public static void main(String[] args) {
		System.out.println(getIDTextFormat(MasterTypeEnum.PHOTO.toString(), "size"));
		Map<String, Object> whereFields = new HashMap<String, Object>();
		whereFields.put("type", MasterTypeEnum.PHOTO.toString());
		whereFields.put("size", "6x7");
		whereFields.put("quality", "metal");
		System.out.println(getValue(whereFields, "price"));
	}

	public static String getIDTextFormat(String type, String queryField) {
		String sReturn = null;
		try {
			String collectionName = DBCollectionEnum.MASTER.toString();
			DB db = getDB();
			DBObject query = new BasicDBObject("type", type);
			DBObject fetch = new BasicDBObject("_id", 0).append(queryField, 1);

			DBCollection collection = db.getCollection(collectionName);
			DBCursor results = collection.find(query, fetch);

			Set<String> sQResponseSet = new HashSet<String>();
			while (results.hasNext()) {
				DBObject document = results.next();
				String queryResponse = (String) document.get(queryField);
				if(queryResponse != null && !queryResponse.trim().equalsIgnoreCase(""))
					sQResponseSet.add(queryResponse);
			}

			List<SantoshCommonJson> list = new ArrayList<SantoshCommonJson>();
			for (String sTemp : sQResponseSet) {
				SantoshCommonJson sObj = new SantoshCommonJson();
				sObj.setId(sTemp);
				sObj.setText(sTemp);

				list.add(sObj);
			}

			sReturn = CommonUtil.objectToJson(list);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return sReturn;
	}

	public static String getIdLabelJSON(DBCollectionEnum dbCollectionEnum, String idKey, String lableKey, String jsonQuery) {
		try {
			DB mongoDB = MongoUtil.getDB();

			DBCollection collection = mongoDB.getCollection(dbCollectionEnum.toString());
			DBObject dbKey = new BasicDBObject(idKey, 1).append(lableKey, 1);

			DBObject deletedQuery = MongoUtil.getQueryToCheckDeleted();
			DBObject finalQuery = deletedQuery;

			if (jsonQuery != null && jsonQuery.length() > 0) {
				DBObject optionalQuery = (DBObject) JSON.parse(jsonQuery);
				BasicDBList queryList = new BasicDBList();
				//queryList.add(deletedQuery);
				queryList.add(optionalQuery);
				finalQuery = new BasicDBObject(QueryOperators.AND, queryList);
			}

			DBCursor dbCursor = collection.find(finalQuery, dbKey);

			String sb = "[]";
			if (dbCursor.hasNext()) {

				sb = JSON.serialize(dbCursor).toString();
			}

			return sb;

		} catch (Exception exception) {
			exception.printStackTrace();
		}

		return "";
	}

	public static String getValue(Map<String, Object> whereField, String queryField) {
		String sReturn = null;
		try {
			String collectionName = DBCollectionEnum.MASTER.toString();
			DB db = getDB();

			DBObject query = new BasicDBObject();//"type", type

			for (Map.Entry<String, Object> entry : whereField.entrySet()) {
				query.put(entry.getKey(), entry.getValue());
			}
			//query.put("size", size);
			//query.put("quality", quality);
			DBObject fetch = new BasicDBObject("_id", 0).append(queryField, 1);

			DBCollection collection = db.getCollection(collectionName);
			DBCursor results = collection.find(query, fetch);

			Set<String> sQResponseSet = new HashSet<String>();
			while (results.hasNext()) {
				DBObject document = results.next();
				String queryResponse = document.get(queryField).toString();

				sQResponseSet.add(queryResponse);
			}

			List<SantoshCommonJson> list = new ArrayList<SantoshCommonJson>();
			for (String sTemp : sQResponseSet) {
				SantoshCommonJson sObj = new SantoshCommonJson();
				sObj.setId(sTemp);
				sObj.setText(sTemp);

				list.add(sObj);
			}

			sReturn = CommonUtil.objectToJson(list);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return sReturn;
	}

	public static String getAllValue(Map<String, Object> whereField) {
		String sReturn = null;
		try {
			String collectionName = DBCollectionEnum.CUSTOMER.toString();
			DB db = getDB();

			DBObject query = new BasicDBObject();//"type", type

			for (Map.Entry<String, Object> entry : whereField.entrySet()) {
				query.put(entry.getKey(), entry.getValue());
			}
			//query.put("size", size);
			//query.put("quality", quality);

			DBCollection collection = db.getCollection(collectionName);
			DBCursor results = collection.find(query);
			if (results.hasNext()) {
				DBObject dbo = results.next();
				String strDate = CommonUtil.longToStringDate((Long) dbo.get("dob"));
				dbo.put("dob", strDate);

				strDate = CommonUtil.longToStringDate((Long) dbo.get("marriageDate"));
				dbo.put("marriageDate", strDate);

				sReturn = JSON.serialize(dbo).toString();
				sReturn = "[" + sReturn + "]";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return sReturn;
	}
}
