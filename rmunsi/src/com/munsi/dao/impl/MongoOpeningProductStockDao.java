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
import com.munsi.dao.OpeningProductStockDao;
import com.munsi.pojo.master.OpeningProductStock;
import com.munsi.pojo.master.inventory.ProductInventory;
import com.munsi.util.CommonUtil;
import com.munsi.util.Constants.DBCollectionEnum;
import com.munsi.util.MongoUtil;

public class MongoOpeningProductStockDao implements OpeningProductStockDao {
	private static final Logger LOG = Logger.getLogger( MongoOpeningProductStockDao.class );
	
	public static final String KEY_PRODUCT_XID = "productXid";
	
	public static final String KEY_PRODUCT = "product";
	
	
	private String collProduct = DBCollectionEnum.MAST_PRODUCT.toString();
	private String collOpeningProductStock = DBCollectionEnum.OPENING_PRODUCT_STOCK.toString();
	private String collProductInventory = DBCollectionEnum.PRODUCT_INVENTORY.toString();
	
	private DB mongoDB = MongoUtil.getDB();
	
	@Override
	public Boolean create(OpeningProductStock openingProductStock) {
		try{
			Date date = new Date();
			openingProductStock.setCtime( date );
			openingProductStock.setUtime( date );
			String _id = MongoUtil.getNextSequence(DBCollectionEnum.OPENING_PRODUCT_STOCK).toString();
			openingProductStock.set_id(_id);
			
			DBCollection collection = mongoDB.getCollection( collOpeningProductStock );
			String jsonString = CommonUtil.objectToJson(openingProductStock);
			
			DBObject dbObject = (DBObject) JSON.parse( jsonString );
			
			DBRef productRef = new DBRef(mongoDB, collProduct, openingProductStock.getProduct().get_id() );
			dbObject.put( KEY_PRODUCT_XID, productRef);
			
			dbObject.removeField(KEY_PRODUCT);
			
			collection.insert(dbObject );
			
				DBCollection collectionInventry = mongoDB.getCollection( collProductInventory );
				ProductInventory inventory = new ProductInventory();
				String _id2 = MongoUtil.getNextSequence(DBCollectionEnum.PRODUCT_INVENTORY).toString();
				
				inventory.set_id(_id2);
				inventory.setAvailableQuantity(openingProductStock.getQuantity());
				inventory.setBatchNumber(openingProductStock.getBatchNumber());
				inventory.setExpDate( openingProductStock.getExpDate() );
				inventory.setMfgDate( openingProductStock.getMfgDate() );
				inventory.setProduct( openingProductStock.getProduct() );
			
				jsonString = CommonUtil.objectToJson(inventory);
				
				DBObject invenToryDBObject = (DBObject) JSON.parse( jsonString );
				
				productRef = new DBRef(mongoDB, collProduct, openingProductStock.getProduct().get_id() );
				invenToryDBObject.put( KEY_PRODUCT_XID, productRef);
				invenToryDBObject.removeField(KEY_PRODUCT);
				
				collectionInventry.insert( invenToryDBObject );
				
				return true;
			
		}catch( Exception exception ){
			LOG.equals(exception);
		}
		return false;
	}
	
	@Override
	public Boolean update(OpeningProductStock openingProductStock) {
		try{
			Date date = new Date();
			openingProductStock.setUtime( date );
			
			DBCollection collection = mongoDB.getCollection( collOpeningProductStock );
			String jsonString = CommonUtil.objectToJson(openingProductStock);
			
			DBObject dbObject = (DBObject) JSON.parse( jsonString );
			if( openingProductStock.getProduct() != null ){
				DBRef productRef = new DBRef(mongoDB, collProduct, openingProductStock.getProduct().get_id() );
				dbObject.put( KEY_PRODUCT_XID, productRef);
				dbObject.removeField(KEY_PRODUCT);
			}
			dbObject.removeField("_id");
			DBObject updateObj = new BasicDBObject("$set", dbObject); 
			DBObject query = new BasicDBObject("_id", openingProductStock.get_id());
			collection.update(query, updateObj);
			return true;
			
		}catch( Exception exception ){
			LOG.equals(exception);
		}
		return false;
		
	}
	
	@Override
	public Boolean delete(String _id) {
		OpeningProductStock openingProductStock = new OpeningProductStock();
		openingProductStock.set_id(_id);
		openingProductStock.setDeleted(true);
		return update(openingProductStock);
	}
	
	@Override
	public OpeningProductStock get(String _id) {
		return get(_id, false); // _id of product, withReferences - false 
	}
	
	@Override
	public OpeningProductStock get(String _id, Boolean withReferences) {
		try{
			DBCollection collection = mongoDB.getCollection( collOpeningProductStock );
			DBObject query = new BasicDBObject("_id", _id);
			DBObject dbObject = collection.findOne(query);
			
			if( withReferences == true ){
				DBObject areaDB =  ((DBRef)dbObject.get(KEY_PRODUCT_XID)).fetch();
				dbObject.put(KEY_PRODUCT, areaDB);
			}
			
			dbObject.removeField(KEY_PRODUCT_XID);
			
			
			String jsonString = JSON.serialize(dbObject);
			OpeningProductStock openingProductStock = (OpeningProductStock) CommonUtil.jsonToObject( jsonString, OpeningProductStock.class.getName() );
			
			return openingProductStock;
			
		}catch( Exception exception ){
			LOG.equals(exception);
		}
		return null;
	}
	
	@Override
	public List<OpeningProductStock> getOpeningStockByProduct(String product_id) {
		return getOpeningStockByProduct(product_id, false);
	}
	
	@Override
	public List<OpeningProductStock> getOpeningStockByProduct(String product_id,Boolean withReferences) {
		try{
			DBCollection collection = mongoDB.getCollection( collOpeningProductStock );
			
			DBRef productRef = new DBRef(mongoDB, collProduct, product_id);
			
			DBObject query = new BasicDBObject(KEY_PRODUCT_XID, productRef);
			
			DBObject deletedQuery = MongoUtil.getQueryToCheckDeleted();
			
			BasicDBList queryList = new BasicDBList();
			queryList.add(deletedQuery);
			queryList.add(query);
			
			DBObject finalQuery = new BasicDBObject(QueryOperators.AND, queryList);
			
			DBCursor dbCursor = collection.find(finalQuery);
			
			List<OpeningProductStock> openingPproductStockList = new ArrayList<>();
			
			while ( dbCursor.hasNext() ) {
				DBObject dbObject = dbCursor.next();
				
				if( withReferences == true ){
					DBObject productDB =  ((DBRef)dbObject.get(KEY_PRODUCT_XID)).fetch();
					dbObject.put(KEY_PRODUCT, productDB);
				}
				
				dbObject.removeField(KEY_PRODUCT_XID);
				
				String jsonString = JSON.serialize(dbObject);
				OpeningProductStock openingProductStock = (OpeningProductStock) CommonUtil.jsonToObject( jsonString, OpeningProductStock.class.getName() );
				openingPproductStockList.add(openingProductStock);
			}
			
			return openingPproductStockList;
			
		}catch( Exception exception ){
			LOG.equals(exception);
		}
		return null;
	}
	
	
	@Override
	public List<OpeningProductStock> getAll() {
		return getAll(false);
	}
	
	@Override
	public List<OpeningProductStock> getAll(Boolean withReferences) {
		try{
			DBCollection collection = mongoDB.getCollection( collOpeningProductStock );
			DBObject deletedQuery = MongoUtil.getQueryToCheckDeleted();
			DBCursor dbCursor = collection.find(deletedQuery);
			
			List<OpeningProductStock> openingPproductStockList = new ArrayList<>();
			
			while ( dbCursor.hasNext() ) {
				DBObject dbObject = dbCursor.next();
				
				if( withReferences == true ){
					DBObject productDB =  ((DBRef)dbObject.get(KEY_PRODUCT_XID)).fetch();
					dbObject.put(KEY_PRODUCT, productDB);
				}
				
				dbObject.removeField(KEY_PRODUCT_XID);
				
				String jsonString = JSON.serialize(dbObject);
				OpeningProductStock productStock = (OpeningProductStock) CommonUtil.jsonToObject( jsonString, OpeningProductStock.class.getName() );
				openingPproductStockList.add(productStock);
			}
			
			return openingPproductStockList;
			
		}catch( Exception exception ){
			LOG.equals(exception);
		}
		return null;
	}
	
	
	
}
