package com.isdc.simulations;

import java.net.UnknownHostException;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.DBRef;
import com.mongodb.MongoClient;
import com.mongodb.MongoException;
import com.mongodb.util.JSON;
import com.munsi.pojo.master.Customer;
import com.munsi.pojo.master.MainAccount;
import com.munsi.util.CommonUtil;
import com.munsi.util.Constants;

public class MongoDemo {
	public static DB getDB(){
		MongoClient mongoClient = null;
		try {
			mongoClient = new MongoClient(Constants.DB_HOST, Constants.DB_PORT);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		return mongoClient.getDB(Constants.DB_NAME);
	}

	public static void main(String[] args) {
		//insert1();
		//readCus();
		//readCus2();
		insertMAC();
	}
	
	public static MainAccount readMainAC() {
		DBCollection collection = getDB().getCollection("mainaccount");
		DBObject dbObject = new BasicDBObject("_id","2");
		DBCursor dbc = collection.find(dbObject);
		MainAccount ma = (MainAccount) CommonUtil.jsonToObject( JSON.serialize( dbc.next() ),MainAccount.class.getCanonicalName());
		return ma;
	}
	
	
	public static void readCus2(){
		
		DBCollection collection = getDB().getCollection( "customer");
		DBObject dbKey = new BasicDBObject("name",1);
		
		DBCursor dbCursor = collection.find(new BasicDBObject(), dbKey);
		
		while ( dbCursor.hasNext() ) {
			
			BasicDBObject dbObject = (BasicDBObject) dbCursor.next();
			
			String _id = dbObject.getString("_id");
			String name = dbObject.getString("name");
			
			String [] idName = new String[]{ _id, name };
			System.out.println(idName.length);
			System.out.println(idName[0]+" : "+idName[1]);
		}
		
		
	}
	
	public static void readCus() {
		
		DBCollection collection = getDB().getCollection("customer");
		DBObject dbObject = new BasicDBObject("_id","2");
		
		DBObject db =  collection.findOne(dbObject);
		
		DBObject db2 =  ((DBRef)db.get("mainAccountXid")).fetch();
		
		//db.put("mainAccount", db2);
		
		db.removeField("mainAccountXid");
		String jsonString = JSON.serialize(db);
		Customer cus =  (Customer) CommonUtil.jsonToObject( jsonString, Customer.class.getName() );
		
	}
	
	public static void insertMAC() {
		DBCollection collection = getDB().getCollection("mainaccount");
		DBObject dbObject = (DBObject) JSON.parse( getJSONStringMainAC() ); 
		collection.insert(dbObject );
	}
	
	public static void insert1() {
		DBCollection collection = getDB().getCollection("customer");
		String json = getJSONStringCus();
		System.out.println(json);
		DBObject dbObject = (DBObject) JSON.parse( json );
		DBRef ref = new DBRef(getDB(), "mainaccount", "2");
		dbObject.put("mainAccountXid", ref);
		dbObject.removeField("mainAccount");
		dbObject.removeField("area");
		dbObject.removeField("beat");
		System.out.println(dbObject);
		collection.insert(dbObject );
		System.out.println("insert completed");
	}

	
	public static String getJSONStringMainAC() {
		MainAccount ma = new MainAccount();
		//Integer id = MongoUtil.getNextSequence(Constants.DBCollectionEnum.MAST_MAIN_ACCOUNT);
		String strid = getNextSequence("mainaccount").toString();
		System.out.println("strid "+strid);
		ma.set_id(strid);
		ma.setCode("ma1");
		ma.setName("SUNDRY CREDITORS");
		return CommonUtil.objectToJson(ma);
	}
	
	
	public static String getJSONStringCus() {
				
		MainAccount ma = new MainAccount();
		ma.set_id("2");
		ma.setCode("ma1");
		ma.setName("SUNDRY CREDITORS");
		
		Customer customer = new Customer();
		
		customer.set_id("2");
		customer.setName("Harindra");
		
		String jsonString = CommonUtil.objectToJson(customer);
		return jsonString;
		
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
	
	public static Integer getNextSequence(String collectionStr) {

		String collectionName = collectionStr;

		DB db = getDB();
		DBCollection collection = db.getCollection("counters");
		DBObject query = new BasicDBObject("_id", collectionName);
		DBObject update = (DBObject) JSON.parse("{ $inc: { seq: 1 } }");

		synchronized (query) {

			DBObject dbObject = collection.findAndModify(query, update);

			if (dbObject == null) {
				dbObject = new BasicDBObject("_id", collectionName).append("seq", 1);
				collection.insert(dbObject);
			}

			dbObject = collection.findOne(query);
			if (dbObject == null) {
				throw new MongoException("Problem to find next sequence");
			}
			System.out.println( "Seq: "+dbObject.get("seq") );
			return Integer.valueOf( dbObject.get("seq").toString() );
		}

	}

	
}
