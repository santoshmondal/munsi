package com.munsi.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.DBRef;
import com.mongodb.WriteResult;
import com.mongodb.util.JSON;
import com.munsi.dao.CustomerDao;
import com.munsi.pojo.master.Customer;
import com.munsi.util.CommonUtil;
import com.munsi.util.Constants.DBCollectionEnum;
import com.munsi.util.MongoUtil;

public class MongoCustomerDao implements CustomerDao {
	private static final Logger LOG = Logger.getLogger( MongoCustomerDao.class );
	
	public static final String KEY_MAIN_ACCOUNT_XID = "mainAccountXid";
	public static final String KEY_AREA_XID = "areaXid";
	public static final String KEY_BEAT_XID = "beatXid";
	
	public static final String KEY_MAIN_ACCOUNT = "mainAccount";
	public static final String KEY_AREA = "area";
	public static final String KEY_BEAT = "beat";
	
	private String collCustomer = DBCollectionEnum.MAST_CUSTOMER.toString();
	private String collMAinAC = DBCollectionEnum.MAST_MAIN_ACCOUNT.toString();
	private String collArea = DBCollectionEnum.MAST_AREA.toString();
	private String collBeat = DBCollectionEnum.MAST_BEAT.toString();
	
	private DB mongoDB = MongoUtil.getDB();
	
	@Override
	public Boolean create(Customer customer) {
		try{
			Date date = new Date();
			customer.setCtime( date );
			customer.setUtime( date );
			String _id = MongoUtil.getNextSequence(DBCollectionEnum.MAST_CUSTOMER).toString();
			customer.set_id( _id );
			
			DBCollection collection = mongoDB.getCollection( collCustomer );
			String jsonString = CommonUtil.objectToJson(customer);
			
			DBObject dbObject = (DBObject) JSON.parse( jsonString );
			
			DBRef mainACRef = new DBRef(mongoDB, collMAinAC, customer.getMainAccount().get_id());
			DBRef areaRef = new DBRef(mongoDB, collArea, customer.getArea().get_id());
			DBRef beatRef = new DBRef(mongoDB, collBeat, customer.getBeat().get_id());
			
			dbObject.put( KEY_MAIN_ACCOUNT_XID, mainACRef );
			dbObject.put( KEY_AREA_XID, areaRef );
			dbObject.put( KEY_BEAT_XID, beatRef );
			
			dbObject.removeField(KEY_MAIN_ACCOUNT);
			dbObject.removeField(KEY_AREA);
			dbObject.removeField(KEY_BEAT);
			
			WriteResult writeResult = collection.insert(dbObject );
			
			if ( writeResult.getN() > 0 ){
				return true;
			}
			
		}catch( Exception exception ){
			LOG.equals(exception);
		}
		return false;
	}
	
	@Override
	public Boolean update(Customer customer) {
		try{
			Date date = new Date();
			customer.setUtime( date );
			
			DBCollection collection = mongoDB.getCollection( collCustomer );
			String jsonString = CommonUtil.objectToJson(customer);
			
			DBObject dbObject = (DBObject) JSON.parse( jsonString );
			String mainAC_id = customer.getMainAccount().get_id();
			
			DBRef mainACRef = new DBRef(mongoDB, collMAinAC, mainAC_id);
			DBRef areaRef = new DBRef(mongoDB, collArea, customer.getArea().get_id());
			DBRef beatRef = new DBRef(mongoDB, collBeat, customer.getBeat().get_id());
			
			dbObject.put( KEY_MAIN_ACCOUNT_XID, mainACRef );
			dbObject.put( KEY_AREA_XID, areaRef );
			dbObject.put( KEY_BEAT_XID, beatRef );
			
			dbObject.removeField(KEY_MAIN_ACCOUNT);
			dbObject.removeField(KEY_AREA);
			dbObject.removeField(KEY_BEAT);
			dbObject.removeField("_id");
			
			DBObject query = new BasicDBObject("_id", customer.get_id()); 
			
			DBObject update = new BasicDBObject("$set", dbObject); 
			
			WriteResult writeResult = collection.update(query, update);
			
			if ( writeResult.getN() > 0 ){
				return true;
			}
			
		}catch( Exception exception ){
			LOG.equals(exception);
		}
		return false;
		
	}
	
	@Override
	public Boolean delete(String _id) {
		try{
			DBCollection collection = mongoDB.getCollection( collCustomer );
			
			DBObject query = new BasicDBObject("_id", _id);
			DBObject updateKey = new BasicDBObject("deleted", true)
							.append("utime", new Date());
			DBObject update = new BasicDBObject("$set",updateKey);
			
			WriteResult writeResult = collection.update(query, update);
			
			if ( writeResult.getN() > 0 ){
				return true;
			}
		}catch( Exception exception ){
			LOG.equals(exception);
		}
		return false;
		
	}
	
	@Override
	public Customer get(String _id) {
		return get(_id, false); // _id of customer, withReferences - false 
	}
	
	@Override
	public Customer get(String _id, Boolean withReferences) {
		try{
			DBCollection collection = mongoDB.getCollection( collCustomer );
			DBObject query = new BasicDBObject("_id", _id);
			DBObject dbObject = collection.findOne(query);
			
			if( withReferences == true ){
				DBObject mainACdDB =  ((DBRef)dbObject.get(KEY_MAIN_ACCOUNT_XID)).fetch();
				DBObject areaDB =  ((DBRef)dbObject.get(KEY_AREA_XID)).fetch();
				DBObject beatDB =  ((DBRef)dbObject.get(KEY_BEAT_XID)).fetch();
				
				dbObject.put(KEY_MAIN_ACCOUNT, mainACdDB);
				dbObject.put(KEY_AREA, areaDB);
				dbObject.put(KEY_BEAT, beatDB);
			}
			
			dbObject.removeField(KEY_MAIN_ACCOUNT_XID);
			dbObject.removeField(KEY_AREA_XID);
			dbObject.removeField(KEY_BEAT_XID);
			
			String jsonString = JSON.serialize(dbObject);
			Customer customer = (Customer) CommonUtil.jsonToObject( jsonString, Customer.class.getName() );
			
			return customer;
			
		}catch( Exception exception ){
			LOG.equals(exception);
		}
		return null;
	}
	
	@Override
	public List<Customer> getAll() {
		return getAll(false);
	}
	
	@Override
	public List<Customer> getAll(Boolean withReferences) {
		try{
			DBCollection collection = mongoDB.getCollection( collCustomer );
			DBObject deletedQuery = MongoUtil.getQueryToCheckDeleted();
			DBCursor dbCursor = collection.find(deletedQuery);
			
			List<Customer> customerList = new ArrayList<>();
			
			while ( dbCursor.hasNext() ) {
				DBObject dbObject = dbCursor.next();
				
				if( withReferences == true ){
					DBObject mainACdDB =  ((DBRef)dbObject.get(KEY_MAIN_ACCOUNT_XID)).fetch();
					DBObject areaDB =  ((DBRef)dbObject.get(KEY_AREA_XID)).fetch();
					DBObject beatDB =  ((DBRef)dbObject.get(KEY_BEAT_XID)).fetch();
					
					dbObject.put(KEY_MAIN_ACCOUNT, mainACdDB);
					dbObject.put(KEY_AREA, areaDB);
					dbObject.put(KEY_BEAT, beatDB);
				}
				
				dbObject.removeField(KEY_MAIN_ACCOUNT_XID);
				dbObject.removeField(KEY_AREA_XID);
				dbObject.removeField(KEY_BEAT_XID);
				
				String jsonString = JSON.serialize(dbObject);
				Customer customer = (Customer) CommonUtil.jsonToObject( jsonString, Customer.class.getName() );
				customerList.add(customer);
			}
			
			return customerList;
			
		}catch( Exception exception ){
			LOG.equals(exception);
		}
		return null;
	}
	
	@Override
	public List<String[]> getIdName() {
		try{
			DBCollection collection = mongoDB.getCollection( collCustomer );
			DBObject dbKey = new BasicDBObject("name",1);
			
			DBObject deletedQuery = MongoUtil.getQueryToCheckDeleted();
			
			DBCursor dbCursor = collection.find(deletedQuery, dbKey);
			
			List<String[]> customerList = new ArrayList<>();
			
			while ( dbCursor.hasNext() ) {
				
				BasicDBObject dbObject = (BasicDBObject) dbCursor.next();
				
				String _id = dbObject.getString("_id");
				String name = dbObject.getString("name");
				
				String [] idName = new String[]{ _id, name };
				customerList.add(idName);
			}
			
			return customerList;
			
		}catch( Exception exception ){
			LOG.equals(exception);
		}
		return null;
	}
	
	
}
