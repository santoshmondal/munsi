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
import com.munsi.dao.SupplierDao;
import com.munsi.pojo.master.Supplier;
import com.munsi.util.CommonUtil;
import com.munsi.util.Constants.DBCollectionEnum;
import com.munsi.util.MongoUtil;

public class MongoSupplierDao implements SupplierDao {
	private static final Logger LOG = Logger.getLogger( MongoSupplierDao.class );
	
	public static final String KEY_AREA_XID = "areaXid";
	public static final String KEY_BEAT_XID = "beatXid";
	
	public static final String KEY_AREA = "area";
	public static final String KEY_BEAT = "beat";
	
	private String collSupplier = DBCollectionEnum.MAST_SUPPLIER.toString();
	private String collArea = DBCollectionEnum.MAST_AREA.toString();
	private String collBeat = DBCollectionEnum.MAST_BEAT.toString();
	
	private DB mongoDB = MongoUtil.getDB();
	
	@Override
	public Boolean create(Supplier supplier) {
		try{
			Date date = new Date();
			supplier.setCtime( date );
			supplier.setUtime( date );
			String _id = MongoUtil.getNextSequence(DBCollectionEnum.MAST_SUPPLIER).toString();
			supplier.set_id( _id );
			
			DBCollection collection = mongoDB.getCollection( collSupplier );
			String jsonString = CommonUtil.objectToJson(supplier);
			
			DBObject dbObject = (DBObject) JSON.parse( jsonString );
			
			DBRef areaRef = new DBRef(mongoDB, collArea, supplier.getArea().get_id());
			DBRef beatRef = new DBRef(mongoDB, collBeat, supplier.getBeat().get_id());
			
			dbObject.put( KEY_AREA_XID, areaRef );
			dbObject.put( KEY_BEAT_XID, beatRef );
			
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
	public Boolean update(Supplier supplier) {
		try{
			Date date = new Date();
			supplier.setUtime( date );
			
			DBCollection collection = mongoDB.getCollection( collSupplier );
			String jsonString = CommonUtil.objectToJson(supplier);
			
			DBObject dbObject = (DBObject) JSON.parse( jsonString );
			
			DBRef areaRef = new DBRef(mongoDB, collArea, supplier.getArea().get_id());
			DBRef beatRef = new DBRef(mongoDB, collBeat, supplier.getBeat().get_id());
			
			dbObject.put( KEY_AREA_XID, areaRef );
			dbObject.put( KEY_BEAT_XID, beatRef );
			
			dbObject.removeField(KEY_AREA);
			dbObject.removeField(KEY_BEAT);
			dbObject.removeField("_id");
			DBObject updateObj = new BasicDBObject("$set", dbObject); 
			
			DBObject query = new BasicDBObject("_id", supplier.get_id()); 
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
			DBCollection collection = mongoDB.getCollection( collSupplier );
			
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
	public Supplier get(String _id) {
		return get(_id, false); // _id of supplier, withReferences - false 
	}
	
	@Override
	public Supplier get(String _id, Boolean withReferences) {
		try{
			DBCollection collection = mongoDB.getCollection( collSupplier );
			DBObject query = new BasicDBObject("_id", _id);
			DBObject dbObject = collection.findOne(query);
			
			if( withReferences == true ){
				DBObject areaDB =  ((DBRef)dbObject.get(KEY_AREA_XID)).fetch();
				DBObject beatDB =  ((DBRef)dbObject.get(KEY_BEAT_XID)).fetch();
				
				dbObject.put(KEY_AREA, areaDB);
				dbObject.put(KEY_BEAT, beatDB);
			}
			
			dbObject.removeField(KEY_AREA_XID);
			dbObject.removeField(KEY_BEAT_XID);
			
			String jsonString = JSON.serialize(dbObject);
			Supplier supplier = (Supplier) CommonUtil.jsonToObject( jsonString, Supplier.class.getName() );
			
			return supplier;
			
		}catch( Exception exception ){
			LOG.equals(exception);
		}
		return null;
	}
	
	@Override
	public List<Supplier> getAll() {
		return getAll(false);
	}
	
	@Override
	public List<Supplier> getAll(Boolean withReferences) {
		try{
			DBCollection collection = mongoDB.getCollection( collSupplier );
			DBObject queryCheckDeleted = MongoUtil.getQueryToCheckDeleted();
			DBCursor dbCursor = collection.find(queryCheckDeleted);
			
			List<Supplier> supplierList = new ArrayList<>();
			
			while ( dbCursor.hasNext() ) {
				DBObject dbObject = dbCursor.next();
				
				if( withReferences == true ){
					DBObject areaDB =  ((DBRef)dbObject.get(KEY_AREA_XID)).fetch();
					DBObject beatDB =  ((DBRef)dbObject.get(KEY_BEAT_XID)).fetch();
					
					dbObject.put(KEY_AREA, areaDB);
					dbObject.put(KEY_BEAT, beatDB);
				}
				
				dbObject.removeField(KEY_AREA_XID);
				dbObject.removeField(KEY_BEAT_XID);
				
				String jsonString = JSON.serialize(dbObject);
				Supplier supplier = (Supplier) CommonUtil.jsonToObject( jsonString, Supplier.class.getName() );
				supplierList.add(supplier);
			}
			
			return supplierList;
			
		}catch( Exception exception ){
			LOG.equals(exception);
		}
		return null;
	}
	
	@Override
	public List<String[]> getIdName() {
		try{
			DBCollection collection = mongoDB.getCollection( collSupplier );
			DBObject dbKey = new BasicDBObject("name",1);
			DBObject queryCheckDeleted = MongoUtil.getQueryToCheckDeleted();
			
			DBCursor dbCursor = collection.find(queryCheckDeleted, dbKey);
			
			List<String[]> supplierList = new ArrayList<>();
			
			while ( dbCursor.hasNext() ) {
				
				BasicDBObject dbObject = (BasicDBObject) dbCursor.next();
				
				String _id = dbObject.getString("_id");
				String name = dbObject.getString("name");
				
				String [] idName = new String[]{ _id, name };
				supplierList.add(idName);
			}
			
			return supplierList;
			
		}catch( Exception exception ){
			LOG.equals(exception);
		}
		return null;
	}
	
	
}
