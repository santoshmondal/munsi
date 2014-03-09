package com.isdc.simulations;

import java.net.UnknownHostException;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;

public class MongoDemo {

	private static final String DB = "isdcdb";
	private static final String HOST = "localhost";
	private static final int PORT = 27017;

	public static void main(String[] args) {
		insert();
	}

	public static void insert() {
		try {
			MongoClient mongoClient = new MongoClient(HOST, PORT);
			DB db = mongoClient.getDB(DB);
			DBCollection collection = db.getCollection("user");

			BasicDBObject doc = new BasicDBObject("name", "MongoDB").append("_id", "jaishree").append("type", "database").append("count", 1)
					.append("info", new BasicDBObject("x", 203).append("y", 102));
			collection.insert(doc);

		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}
}
