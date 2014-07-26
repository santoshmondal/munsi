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
import com.mongodb.util.JSON;
import com.munsi.dao.ProductSchemeDao;
import com.munsi.pojo.master.ProductScheme;
import com.munsi.util.CommonUtil;
import com.munsi.util.Constants.DBCollectionEnum;
import com.munsi.util.MongoUtil;

public class MongoProductSchemeDao implements ProductSchemeDao {
	private static final Logger LOG = Logger.getLogger( MongoProductSchemeDao.class );
	
	public static final String KEY_PRODUCT_XID = "productXid";
	
	public static final String KEY_PRODUCT = "product";
	
	
	private String collProduct = DBCollectionEnum.MAST_PRODUCT.toString();
	private String collProductScheme = DBCollectionEnum.MAST_PRODUCT_SCHEME.toString();
	
	private DB mongoDB = MongoUtil.getDB();
	
	@Override
	public Boolean create(ProductScheme productScheme) {
		try{
			Date date = new Date();
			productScheme.setCtime( date );
			productScheme.setUtime( date );
			String _id = MongoUtil.getNextSequence(DBCollectionEnum.MAST_PRODUCT_SCHEME).toString();
			productScheme.set_id(_id);
			
			DBCollection collection = mongoDB.getCollection( collProductScheme );
			String jsonString = CommonUtil.objectToJson(productScheme);
			
			DBObject dbObject = (DBObject) JSON.parse( jsonString );
			
			DBRef productRef = new DBRef(mongoDB, collProduct, productScheme.getProduct().get_id() );
			dbObject.put( KEY_PRODUCT_XID, productRef);
			
			dbObject.removeField(KEY_PRODUCT);
			
			collection.insert(dbObject );
			return true;
			
		}catch( Exception exception ){
			LOG.equals(exception);
		}
		return false;
	}
	
	@Override
	public Boolean update(ProductScheme productScheme) {
		try{
			Date date = new Date();
			productScheme.setUtime( date );
			
			DBCollection collection = mongoDB.getCollection( collProductScheme);
			String jsonString = CommonUtil.objectToJson(productScheme);
			
			DBObject dbObject = (DBObject) JSON.parse( jsonString );
			if( productScheme.getProduct() != null ){
				DBRef productRef = new DBRef(mongoDB, collProduct, productScheme.getProduct().get_id() );
				dbObject.put( KEY_PRODUCT_XID, productRef);
				dbObject.removeField(KEY_PRODUCT);
			}
			dbObject.removeField("_id");
			DBObject updateObj = new BasicDBObject("$set", dbObject); 

			DBObject query = new BasicDBObject("_id", productScheme.get_id());
			
			collection.update(query, updateObj);
			return true;
			
		}catch( Exception exception ){
			LOG.equals(exception);
		}
		return false;
		
	}
	
	@Override
	public Boolean delete(String _id) {
		ProductScheme productScheme = new ProductScheme();
		productScheme.set_id(_id);
		productScheme.setDeleted(true);
		return update(productScheme);
		
	}
	
	@Override
	public ProductScheme get(String _id) {
		return get(_id, false); // _id of product, withReferences - false 
	}
	
	@Override
	public ProductScheme get(String _id, Boolean withReferences) {
		try{
			DBCollection collection = mongoDB.getCollection( collProductScheme );
			DBObject query = new BasicDBObject("_id", _id);
			DBObject dbObject = collection.findOne(query);
			
			if( withReferences == true ){
				DBObject areaDB =  ((DBRef)dbObject.get(KEY_PRODUCT_XID)).fetch();
				dbObject.put(KEY_PRODUCT, areaDB);
			}
			
			dbObject.removeField(KEY_PRODUCT_XID);
			
			
			String jsonString = JSON.serialize(dbObject);
			ProductScheme productScheme = (ProductScheme) CommonUtil.jsonToObject( jsonString, ProductScheme.class.getName() );
			
			return productScheme;
			
		}catch( Exception exception ){
			LOG.equals(exception);
		}
		return null;
	}
	
	@Override
	public List<ProductScheme> getSchemeByProduct(String product_id) {
		return getSchemeByProduct(product_id, false);
	}
	
	@Override
	public List<ProductScheme> getSchemeByProduct(String product_id,Boolean withReferences) {
		try{
			DBCollection collection = mongoDB.getCollection( collProductScheme );
			
			DBRef productRef = new DBRef(mongoDB, collProduct, product_id);
			
			DBObject query = new BasicDBObject(KEY_PRODUCT_XID, productRef);
			
			DBObject deletedQuery = MongoUtil.getQueryToCheckDeleted();
			
			BasicDBList queryList = new BasicDBList();
			queryList.add(deletedQuery);
			queryList.add(query);
			
			DBObject finalQuery = new BasicDBObject(QueryOperators.AND, queryList);
			
			DBCursor dbCursor = collection.find(finalQuery);
			
			List<ProductScheme> openingPproductStockList = new ArrayList<>();
			
			while ( dbCursor.hasNext() ) {
				DBObject dbObject = dbCursor.next();
				
				if( withReferences == true ){
					DBObject productDB =  ((DBRef)dbObject.get(KEY_PRODUCT_XID)).fetch();
					dbObject.put(KEY_PRODUCT, productDB);
				}
				
				dbObject.removeField(KEY_PRODUCT_XID);
				
				String jsonString = JSON.serialize(dbObject);
				ProductScheme productScheme = (ProductScheme) CommonUtil.jsonToObject( jsonString, ProductScheme.class.getName() );
				openingPproductStockList.add(productScheme);
			}
			
			return openingPproductStockList;
			
		}catch( Exception exception ){
			LOG.equals(exception);
		}
		return null;
	}
	
	
	@Override
	public List<ProductScheme> getAll() {
		return getAll(false);
	}
	
	@Override
	public List<ProductScheme> getAll(Boolean withReferences) {
		try{
			DBCollection collection = mongoDB.getCollection( collProductScheme );
			DBObject deletedQuery = MongoUtil.getQueryToCheckDeleted();
			DBCursor dbCursor = collection.find(deletedQuery);
			
			List<ProductScheme> openingPproductStockList = new ArrayList<>();
			
			while ( dbCursor.hasNext() ) {
				DBObject dbObject = dbCursor.next();
				
				if( withReferences == true ){
					DBObject productDB =  ((DBRef)dbObject.get(KEY_PRODUCT_XID)).fetch();
					dbObject.put(KEY_PRODUCT, productDB);
				}
				
				dbObject.removeField(KEY_PRODUCT_XID);
				
				String jsonString = JSON.serialize(dbObject);
				ProductScheme productStock = (ProductScheme) CommonUtil.jsonToObject( jsonString, ProductScheme.class.getName() );
				openingPproductStockList.add(productStock);
			}
			
			return openingPproductStockList;
			
		}catch( Exception exception ){
			LOG.equals(exception);
		}
		return null;
	}
	
	
	
}
