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
import com.munsi.dao.AreaDao;
import com.munsi.pojo.master.Customer;
import com.munsi.pojo.master.Area;
import com.munsi.util.CommonUtil;
import com.munsi.util.Constants.DBCollectionEnum;
import com.munsi.util.MongoUtil;

public class MongoAreaDao implements AreaDao {
	private static final Logger LOG = Logger.getLogger( MongoAreaDao.class );
	
	
	private String collArea = DBCollectionEnum.MAST_AREA.toString();
	
	private DB mongoDB = MongoUtil.getDB();
	
	@Override
	public Boolean create(Area area) {
		try{
			Date date = new Date();
			area.setCtime( date );
			area.setUtime( date );
			String _id = MongoUtil.getNextSequence(DBCollectionEnum.MAST_AREA).toString();
			area.set_id( _id );
			
			DBCollection collection = mongoDB.getCollection( collArea );
			String jsonString = CommonUtil.objectToJson(area);
			
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
	public Boolean update(Area area) {
		try{
			Date date = new Date();
			area.setUtime( date );
			
			DBCollection collection = mongoDB.getCollection( collArea );
			String jsonString = CommonUtil.objectToJson(area);
			
			DBObject dbObject = (DBObject) JSON.parse( jsonString );
			
			DBObject query = new BasicDBObject("_id", area.get_id());
			
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
			DBCollection collection = mongoDB.getCollection( collArea );
			
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
	public Area get(String _id) {
		return get(_id, false); // _id of customer, withReferences - false 
	}
	
	@Override
	public Area get(String _id, Boolean withReferences) {
		try{
			DBCollection collection = mongoDB.getCollection( collArea );
			DBObject query = new BasicDBObject("_id", _id);
			DBObject dbObject = collection.findOne(query);
			String jsonString = JSON.serialize(dbObject);
			Area area = (Area) CommonUtil.jsonToObject( jsonString, Customer.class.getName() );
			
			return area;
			
		}catch( Exception exception ){
			LOG.equals(exception);
		}
		return null;
	}
	
	@Override
	public List<Area> getAll() {
		return getAll(false);
	}
	
	@Override
	public List<Area> getAll(Boolean withReferences) {
		try{
			DBCollection collection = mongoDB.getCollection( collArea );
			DBCursor dbCursor = collection.find();
			
			List<Area> areaList = new ArrayList<>();
			
			while ( dbCursor.hasNext() ) {
				DBObject dbObject = dbCursor.next();
				String jsonString = JSON.serialize(dbObject);
				Area area = (Area) CommonUtil.jsonToObject( jsonString, Customer.class.getName() );
				areaList.add(area);
			}
			
			return areaList;
			
		}catch( Exception exception ){
			LOG.equals(exception);
		}
		return null;
	}
	
	@Override
	public List<String[]> getIdName() {
		try{
			DBCollection collection = mongoDB.getCollection( collArea );
			DBObject dbKey = new BasicDBObject("name",1);
			
			DBCursor dbCursor = collection.find(new BasicDBObject(), dbKey);
			
			List<String[]> areaList = new ArrayList<>();
			
			while ( dbCursor.hasNext() ) {
				
				BasicDBObject dbObject = (BasicDBObject) dbCursor.next();
				
				String _id = dbObject.getString("_id");
				String name = dbObject.getString("name");
				
				String [] idName = new String[]{ _id, name };
				areaList.add(idName);
			}
			
			return areaList;
			
		}catch( Exception exception ){
			LOG.equals(exception);
		}
		return null;
	}
	
	
}
