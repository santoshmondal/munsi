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
import com.munsi.dao.ProductGroupDao;
import com.munsi.pojo.master.ProductGroup;
import com.munsi.util.CommonUtil;
import com.munsi.util.Constants.DBCollectionEnum;
import com.munsi.util.MongoUtil;

public class MongoProductGroupDao implements ProductGroupDao {
	private static final Logger LOG = Logger.getLogger( ProductGroupDao.class );
	
	private String collProductGroup = DBCollectionEnum.MAST_PRODUCT_GROUP.toString();
	
	private DB mongoDB = MongoUtil.getDB();
	
	@Override
	public Boolean create(ProductGroup productGroup) {
		try{
			Date date = new Date();
			productGroup.setCtime( date );
			productGroup.setUtime( date );
			String _id = MongoUtil.getNextSequence(DBCollectionEnum.MAST_PRODUCT_GROUP).toString();
			productGroup.set_id( _id );
			
			DBCollection collection = mongoDB.getCollection( collProductGroup );
			String jsonString = CommonUtil.objectToJson(productGroup);
			
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
	public Boolean update(ProductGroup productGroup) {
		try{
			Date date = new Date();
			productGroup.setUtime( date );
			
			DBCollection collection = mongoDB.getCollection( collProductGroup );
			String jsonString = CommonUtil.objectToJson(productGroup);
			
			DBObject dbObject = (DBObject) JSON.parse( jsonString );
			
			DBObject query = new BasicDBObject("_id", productGroup.get_id()); 
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
			DBCollection collection = mongoDB.getCollection( collProductGroup );
			
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
	public ProductGroup get(String _id) {
		try{
			DBCollection collection = mongoDB.getCollection( collProductGroup );
			DBObject query = new BasicDBObject("_id", _id);
			DBObject dbObject = collection.findOne(query);
		
			String jsonString = JSON.serialize(dbObject);
			ProductGroup productGroup = (ProductGroup) CommonUtil.jsonToObject( jsonString, ProductGroup.class.getName() );
			
			return productGroup;
			
		}catch( Exception exception ){
			LOG.equals(exception);
		}
		return null;
	}
	
	@Override
	public List<ProductGroup> getAll() {
		return getAll("1");
	}

	@Override
	public List<ProductGroup> getAll(String level) {
		try{
			DBCollection collection = mongoDB.getCollection( collProductGroup );
			DBObject query = new BasicDBObject("level", level);
			DBCursor dbCursor = collection.find(query);
			
			List<ProductGroup> productGroupList = new ArrayList<>();
			
			while ( dbCursor.hasNext() ) {
				DBObject dbObject = dbCursor.next();

				String jsonString = JSON.serialize(dbObject);
				ProductGroup productGroup = (ProductGroup) CommonUtil.jsonToObject( jsonString, ProductGroup.class.getName() );
				productGroupList.add(productGroup);
			}
			
			return productGroupList;
			
		}catch( Exception exception ){
			LOG.equals(exception);
		}
		return null;
	}

	
	
}
