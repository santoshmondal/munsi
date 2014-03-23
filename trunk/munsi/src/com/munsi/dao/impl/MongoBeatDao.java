package com.munsi.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.DBRef;
import com.mongodb.QueryOperators;
import com.mongodb.WriteResult;
import com.mongodb.util.JSON;
import com.munsi.dao.BeatDao;
import com.munsi.pojo.master.Beat;
import com.munsi.util.CommonUtil;
import com.munsi.util.Constants.DBCollectionEnum;
import com.munsi.util.MongoUtil;

public class MongoBeatDao implements BeatDao {
	private static final Logger LOG = Logger.getLogger( MongoBeatDao.class );
	
	
	private String collBeat = DBCollectionEnum.MAST_BEAT.toString();
	private String collArea = DBCollectionEnum.MAST_AREA.toString();

	public static final String KEY_AREA_XID = "areaXid";
	
	public static final String KEY_AREA = "area";
	
	private DB mongoDB = MongoUtil.getDB();
	
	@Override
	public Boolean create(Beat beat) {
		try{
			Date date = new Date();
			beat.setCtime( date );
			beat.setUtime( date );
			String _id = MongoUtil.getNextSequence(DBCollectionEnum.MAST_BEAT).toString();
			beat.set_id( _id );
			
			DBCollection collection = mongoDB.getCollection( collBeat );
			String jsonString = CommonUtil.objectToJson(beat);
			
			DBObject dbObject = (DBObject) JSON.parse( jsonString );
			
			DBRef areaRef = new DBRef(mongoDB, collArea, beat.getArea().get_id());
			dbObject.put( KEY_AREA_XID, areaRef );
			dbObject.removeField(KEY_AREA);
			
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
	public Boolean update(Beat beat) {
		try{
			Date date = new Date();
			beat.setUtime( date );
			
			DBCollection collection = mongoDB.getCollection( collBeat );
			
			String jsonString = CommonUtil.objectToJson(beat);
			
			DBObject dbObject = (DBObject) JSON.parse( jsonString );
			
			DBRef areaRef = new DBRef(mongoDB, collArea, beat.getArea().get_id());
			dbObject.put( KEY_AREA_XID, areaRef );
			dbObject.removeField(KEY_AREA);
			dbObject.removeField("_id");
			DBObject update = new BasicDBObject("$set", dbObject);
			
			DBObject query = new BasicDBObject("_id", beat.get_id());
			
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
			DBCollection collection = mongoDB.getCollection( collBeat );
			
			DBObject query = new BasicDBObject("_id", _id);
			DBObject updateObj = new BasicDBObject("deleted", true)
							.append("utime", new Date());
			DBObject update = new BasicDBObject("$set", updateObj);
			
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
	public Beat get(String _id) {
		return get(_id, false);
	}
	
	@Override
	public Beat get(String _id, Boolean withReferences) {
		try{
			DBCollection collection = mongoDB.getCollection( collBeat );
			DBObject query = new BasicDBObject("_id", _id);
			DBObject dbObject = collection.findOne(query);
			if( withReferences == true ){
				DBObject areaDB =  ((DBRef)dbObject.get(KEY_AREA_XID)).fetch();
				dbObject.put(KEY_AREA, areaDB);
			}
			
			dbObject.removeField(KEY_AREA_XID);
			
			
			String jsonString = JSON.serialize(dbObject);
			Beat beat = (Beat) CommonUtil.jsonToObject( jsonString, Beat.class.getName() );
			
			return beat;
			
		}catch( Exception exception ){
			LOG.equals(exception);
		}
		return null;
	}
	
	@Override
	public List<Beat> getAll() {
		return getAll(false);
	}
	
	@Override
	public List<Beat> getAll(Boolean withReferences) {
		try{
			DBCollection collection = mongoDB.getCollection( collBeat );
			
			DBObject deletedQuery = MongoUtil.getQueryToCheckDeleted();
			
			DBCursor dbCursor = collection.find( deletedQuery );
			
			List<Beat> beatList = new ArrayList<>();
			
			while ( dbCursor.hasNext() ) {
				DBObject dbObject = dbCursor.next();
				
				if( withReferences == true ){
					DBObject areaDB =  ((DBRef)dbObject.get(KEY_AREA_XID)).fetch();
					dbObject.put(KEY_AREA, areaDB);
				}
				
				dbObject.removeField(KEY_AREA_XID);
				
				String jsonString = JSON.serialize(dbObject);
				Beat beat = (Beat) CommonUtil.jsonToObject( jsonString, Beat.class.getName() );
				beatList.add(beat);
			}
			
			return beatList;
			
		}catch( Exception exception ){
			LOG.equals(exception);
		}
		return null;
	}
	
	@Override
	public List<Beat> getBeatListByArea(String area_id) {
		return getBeatListByArea(area_id, false);
	}
	@Override
	public List<Beat> getBeatListByArea(String area_id, Boolean withReferences) {
		try{
			DBCollection collection = mongoDB.getCollection( collBeat );
			
			DBRef areaRef = new DBRef(mongoDB, collArea, area_id);
			
			DBObject query = new BasicDBObject(KEY_AREA_XID, areaRef);
			
			DBObject deletedQuery = MongoUtil.getQueryToCheckDeleted();
			
			BasicDBList queryList = new BasicDBList();
			queryList.add(deletedQuery);
			queryList.add(query);
			
			DBObject finalQuery = new BasicDBObject(QueryOperators.AND, queryList);
			
			DBCursor dbCursor = collection.find( finalQuery );
			
			List<Beat> beatList = new ArrayList<>();
			
			while ( dbCursor.hasNext() ) {
				DBObject dbObject = dbCursor.next();
				
				if( withReferences == true ){
					DBObject areaDB =  ((DBRef)dbObject.get(KEY_AREA_XID)).fetch();
					dbObject.put(KEY_AREA, areaDB);
				}
				
				dbObject.removeField(KEY_AREA_XID);
				
				String jsonString = JSON.serialize(dbObject);
				Beat beat = (Beat) CommonUtil.jsonToObject( jsonString, Beat.class.getName() );
				beatList.add(beat);
			}
			
			return beatList;
			
		}catch( Exception exception ){
			LOG.equals(exception);
		}
		return null;
	}


	
}
