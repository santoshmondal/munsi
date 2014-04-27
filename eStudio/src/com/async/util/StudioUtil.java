package com.async.util;

import java.net.UnknownHostException;

import com.estudio.pojo.master.Master;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.util.JSON;

public class StudioUtil {

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
	
	public static Master get() {
		try {
			db=getDB();
			DBCollection collection = db.getCollection(Constants.DBCollectionEnum.INVOICE.toString());
			/*//DBObject query = new BasicDBObject("_id", _id);
			DBObject dbObject = collection.findOne(query);
			String jsonString = JSON.serialize(dbObject);
			Master master = (Master) CommonUtil.jsonToObject(jsonString, Master.class.getName());*/

			//return master;

		} catch (Exception exception) {
		}
		return null;
	}
	
	
	public static void main(String[] args) {
		StudioUtil sUtil = new StudioUtil();
		sUtil.get();
	}
}
