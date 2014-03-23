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
import com.munsi.pojo.master.Area;
import com.munsi.pojo.master.Customer;
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
			dbObject.removeField("_id");
			DBObject query = new BasicDBObject("_id", area.get_id());
			
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
			DBCollection collection = mongoDB.getCollection( collArea );
			
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
	public Area get(String _id) {
		try{
			DBCollection collection = mongoDB.getCollection( collArea );
			DBObject query = new BasicDBObject("_id", _id);
			DBObject dbObject = collection.findOne(query);
			String jsonString = JSON.serialize(dbObject);
			Area area = (Area) CommonUtil.jsonToObject( jsonString, Area.class.getName() );
			
			return area;
			
		}catch( Exception exception ){
			LOG.equals(exception);
		}
		return null;
	}
	
	@Override
	public List<Area> getAll() {
		try{
			DBCollection collection = mongoDB.getCollection( collArea );
			DBObject finalQuery = MongoUtil.getQueryToCheckDeleted();
			DBCursor dbCursor = collection.find( finalQuery);
			
			List<Area> areaList = new ArrayList<>();
			
			while ( dbCursor.hasNext() ) {
				DBObject dbObject = dbCursor.next();
				String jsonString = JSON.serialize(dbObject);
				Area area = (Area) CommonUtil.jsonToObject( jsonString, Area.class.getName() );
				areaList.add(area);
			}
			
			return areaList;
			
		}catch( Exception exception ){
			LOG.equals(exception);
		}
		return null;
	}
	
	
	
}
