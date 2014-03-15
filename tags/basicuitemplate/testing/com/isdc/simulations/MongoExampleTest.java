package com.isdc.simulations;

import java.io.IOException;
import java.net.UnknownHostException;

import org.apache.log4j.Logger;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.util.JSON;

public class MongoExampleTest {
	private static Logger log = Logger.getLogger(MongoExampleTest.class);
	public static final String MONGO_HOST = "localhost";
	public static final int MONGO_PORT = 27017;
	public static final String MONGO_DB = "subscription";
	public static final String MONGO_COLLECTION_SUBSCRIPTION = "subscription_details";

	public static void main(String args[]) {
		// insert();
		search();
	}

	public static void insert() {
		DBCollection subscriptionCollection = null;
		try {
			MongoClient mongo = new MongoClient(MONGO_HOST, MONGO_PORT);
			DB db = mongo.getDB(MONGO_DB);

			subscriptionCollection = db.getCollection(MONGO_COLLECTION_SUBSCRIPTION);

			if (subscriptionCollection == null) {
				subscriptionCollection = db.createCollection(MONGO_COLLECTION_SUBSCRIPTION, null);
			}

			// Convert object to JSON
			UserSubscription obj = new UserSubscription();
			obj.setId(1);
			UserInfo uobj = new UserInfo();
			uobj.setUsername("user11");
			obj.setUinfo(uobj);
			obj.setSinfo(new SubscribeInfo("user topic", "user section", "mumbai"));

			ObjectMapper mapper = new ObjectMapper();
			String json = mapper.writeValueAsString(obj);

			// Convert JSON to MongoDB object
			DBObject dbObject = (DBObject) JSON.parse(json);

			subscriptionCollection.insert(dbObject);

			log.info("Successful Insertion::" + json);
		} catch (UnknownHostException e) {
			e.printStackTrace();
			log.error("Unknown Host Exception", e);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
			log.error("JSON GENERATION Exception", e);
		} catch (JsonMappingException e) {
			e.printStackTrace();
			log.error("JSON MAPPING Exception", e);
		} catch (IOException e) {
			e.printStackTrace();
			log.error("JSONIO Exception", e);
		}
	}

	public static void search() {
		DBCollection subscriptionCollection = null;
		try {
			MongoClient mongo = new MongoClient(MONGO_HOST, MONGO_PORT);
			DB db = mongo.getDB(MONGO_DB);

			subscriptionCollection = db.getCollection(MONGO_COLLECTION_SUBSCRIPTION);

			BasicDBObject query = new BasicDBObject();
			query.put("uinfo.username", "user11");
			DBCursor cursor = subscriptionCollection.find(query);
			while (cursor.hasNext()) {
				DBObject next = cursor.next();
				System.out.println(next);
			}

			log.info("Query Result::");
		} catch (UnknownHostException e) {
			e.printStackTrace();
			log.error("Unknown Host Exception", e);
		}
	}
}
