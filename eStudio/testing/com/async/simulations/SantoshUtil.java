package com.async.simulations;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.async.util.CommonUtil;
import com.async.util.Constants;
import com.async.util.Constants.DBCollectionEnum;
import com.async.util.Constants.MasterTypeEnum;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

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
		System.out.println(getQuery(MasterTypeEnum.PHOTO.toString(), "size"));
	}

	private static String getQuery(String type, String queryField) {
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

				sQResponseSet.add(queryResponse);
			}

			List<SantoshCommonJson> list = new ArrayList<SantoshCommonJson>();
			for (String sTemp : sQResponseSet) {
				SantoshCommonJson sObj = new SantoshCommonJson();
				sObj.setId(sTemp);
				sObj.setQuery(sTemp);

				list.add(sObj);
			}

			sReturn = CommonUtil.objectToJson(list);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return sReturn;
	}
}
