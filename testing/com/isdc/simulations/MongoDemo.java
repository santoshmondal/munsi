package com.isdc.simulations;

import java.net.UnknownHostException;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.munsi.util.Constants;

public class MongoDemo {
	public static void main(String[] args) {
		insert();
	}

	public static void insert1() {

	}

	public static void insert() {
		try {
			MongoClient mongoClient = new MongoClient(Constants.DB_HOST, Constants.DB_PORT);
			DB db = mongoClient.getDB(Constants.DB_NAME);

			DBCollection collection = db.getCollection("user");

			BasicDBObject doc = new BasicDBObject("name", "MongoDB").append("_id", "jaishree").append("type", "database").append("count", 1)
					.append("info", new BasicDBObject("x", 203).append("y", 102));

			// ObjectMapper om = new ObjectMapper();

			collection.insert(doc);

		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}
}
