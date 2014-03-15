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
import com.mongodb.WriteResult;
import com.mongodb.util.JSON;
import com.munsi.dao.ManufacturerDao;
import com.munsi.pojo.master.Manufacturer;
import com.munsi.util.CommonUtil;
import com.munsi.util.Constants.DBCollectionEnum;
import com.munsi.util.MongoUtil;

public class MongoManufacturerDao implements ManufacturerDao {
	private static final Logger LOG = Logger.getLogger( MongoManufacturerDao.class );
	
	private String collManufacturer = DBCollectionEnum.MAST_MANUFACTURER.toString();
	
	private DB mongoDB = MongoUtil.getDB();
	
	@Override
	public Boolean create(Manufacturer manufacturer) {
		try{
			Date date = new Date();
			manufacturer.setCtime( date );
			manufacturer.setUtime( date );
			String _id = MongoUtil.getNextSequence(DBCollectionEnum.MAST_CUSTOMER).toString();
			manufacturer.set_id( _id );
			
			DBCollection collection = mongoDB.getCollection( collManufacturer );
			String jsonString = CommonUtil.objectToJson(manufacturer);
			
			DBObject dbObject = (DBObject) JSON.parse( jsonString );
			
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
	public Boolean update(Manufacturer manufacturer) {
		try{
			Date date = new Date();
			manufacturer.setUtime( date );
			
			DBCollection collection = mongoDB.getCollection( collManufacturer );
			String jsonString = CommonUtil.objectToJson(manufacturer);
			
			DBObject dbObject = (DBObject) JSON.parse( jsonString );
			
			DBObject query = new BasicDBObject("_id", manufacturer.get_id()); 
			WriteResult writeResult = collection.update(query, dbObject);
			
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
			DBCollection collection = mongoDB.getCollection( collManufacturer );
			
			DBObject query = new BasicDBObject("_id", _id);
			DBObject update = new BasicDBObject("deleted", true)
							.append("utime", new Date());
			
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
	public Manufacturer get(String _id) {
		return get(_id, false); // _id of manufacturer, withReferences - false 
	}
	
	@Override
	public Manufacturer get(String _id, Boolean withReferences) {
		try{
			DBCollection collection = mongoDB.getCollection( collManufacturer );
			DBObject query = new BasicDBObject("_id", _id);
			DBObject dbObject = collection.findOne(query);
		
			String jsonString = JSON.serialize(dbObject);
			Manufacturer manufacturer = (Manufacturer) CommonUtil.jsonToObject( jsonString, Manufacturer.class.getName() );
			
			return manufacturer;
			
		}catch( Exception exception ){
			LOG.equals(exception);
		}
		return null;
	}
	
	@Override
	public List<Manufacturer> getAll() {
		return getAll(false);
	}
	
	@Override
	public List<Manufacturer> getAll(Boolean withReferences) {
		try{
			DBCollection collection = mongoDB.getCollection( collManufacturer );
			DBCursor dbCursor = collection.find();
			
			List<Manufacturer> manufacturerList = new ArrayList<>();
			
			while ( dbCursor.hasNext() ) {
				DBObject dbObject = dbCursor.next();

				String jsonString = JSON.serialize(dbObject);
				Manufacturer manufacturer = (Manufacturer) CommonUtil.jsonToObject( jsonString, Manufacturer.class.getName() );
				manufacturerList.add(manufacturer);
			}
			
			return manufacturerList;
			
		}catch( Exception exception ){
			LOG.equals(exception);
		}
		return null;
	}
	
	@Override
	public List<String[]> getIdName() {
		try{
			DBCollection collection = mongoDB.getCollection( collManufacturer );
			DBObject dbKey = new BasicDBObject("name",1);
			
			DBCursor dbCursor = collection.find(new BasicDBObject(), dbKey);
			
			List<String[]> manufacturerList = new ArrayList<>();
			
			while ( dbCursor.hasNext() ) {
				
				BasicDBObject dbObject = (BasicDBObject) dbCursor.next();
				
				String _id = dbObject.getString("_id");
				String name = dbObject.getString("name");
				
				String [] idName = new String[]{ _id, name };
				manufacturerList.add(idName);
			}
			
			return manufacturerList;
			
		}catch( Exception exception ){
			LOG.equals(exception);
		}
		return null;
	}
	
	
}
