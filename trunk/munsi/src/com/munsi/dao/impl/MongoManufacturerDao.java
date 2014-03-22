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
			String _id = MongoUtil.getNextSequence(DBCollectionEnum.MAST_MANUFACTURER).toString();
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
			dbObject.removeField("_id");
			
			DBObject query = new BasicDBObject("_id", manufacturer.get_id());
			
			DBObject updateObj = new BasicDBObject("$set", dbObject);
			
			WriteResult writeResult = collection.update(query, updateObj);
			
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
			
			DBObject updateObj = new BasicDBObject("$set", update);
			
			WriteResult writeResult = collection.update(query, updateObj);
			
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
		try{
			DBCollection collection = mongoDB.getCollection( collManufacturer );
			DBObject deletedQuery = MongoUtil.getQueryToCheckDeleted();
			DBCursor dbCursor = collection.find(deletedQuery);
			
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
	

	
}
