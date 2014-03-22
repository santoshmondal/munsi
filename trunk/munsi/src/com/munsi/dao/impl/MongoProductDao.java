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
import com.mongodb.WriteResult;
import com.mongodb.util.JSON;
import com.munsi.dao.ProductDao;
import com.munsi.pojo.master.Product;
import com.munsi.pojo.master.Tax;
import com.munsi.util.CommonUtil;
import com.munsi.util.Constants.DBCollectionEnum;
import com.munsi.util.MongoUtil;

public class MongoProductDao implements ProductDao {
	private static final Logger LOG = Logger.getLogger( MongoProductDao.class );
	
	public static final String KEY_MANUFACTURER_XID = "manufacturerXid";
	public static final String KEY_TAX_LIST_XID = "taxListXid";
	public static final String KEY_PRODUCT_GROUP_XID = "productGroupXid";
	public static final String KEY_PRODUCT_SUBGROUP_XID = "productSubGroupXid";
	
	public static final String KEY_MANUFACTURER = "manufacturer";
	public static final String KEY_PRODUCT_GROUP = "productGroup";
	public static final String KEY_PRODUCT_SUBGROUP = "productSubGroup";
	public static final String KEY_TAX_LIST= "taxList";
	
	
	private String collProduct = DBCollectionEnum.MAST_PRODUCT.toString();
	private String collManufacturer = DBCollectionEnum.MAST_MANUFACTURER.toString();
	private String collTax = DBCollectionEnum.MAST_TAX.toString();
	private String collProductGroup = DBCollectionEnum.MAST_PRODUCT_GROUP.toString();
	
	private DB mongoDB = MongoUtil.getDB();
	
	@Override
	public Boolean create(Product product) {
		try{
			Date date = new Date();
			product.setCtime( date );
			product.setUtime( date );
			String _id = MongoUtil.getNextSequence(DBCollectionEnum.MAST_PRODUCT).toString();
			product.set_id( _id );
			
			DBCollection collection = mongoDB.getCollection( collProduct );
			String jsonString = CommonUtil.objectToJson(product);
			
			DBObject dbObject = (DBObject) JSON.parse( jsonString );
			
			BasicDBList basicDBList = new BasicDBList();
			if( product.getTaxList() != null ){
				for(Tax tax : product.getTaxList() ){
					DBRef taxRef = new DBRef(mongoDB, collTax, tax.get_id() );
					basicDBList.add(taxRef);
				}
			}
			
			
			DBRef manufacturerRef = new DBRef(mongoDB, collManufacturer, product.getManufacturar().get_id() );
			DBRef productGroupRef = new DBRef(mongoDB, collProductGroup, product.getProductGroup().get_id());
			DBRef productSubGroupRef = new DBRef(mongoDB, collProductGroup, product.getProductSubGroup().get_id());
			
			dbObject.put( KEY_TAX_LIST_XID, basicDBList);
			dbObject.put( KEY_MANUFACTURER_XID, manufacturerRef );
			dbObject.put( KEY_PRODUCT_GROUP_XID, productGroupRef );
			dbObject.put( KEY_PRODUCT_SUBGROUP_XID, productSubGroupRef );
			
			dbObject.removeField(KEY_MANUFACTURER);
			dbObject.removeField(KEY_TAX_LIST);
			dbObject.removeField(KEY_PRODUCT_GROUP);
			dbObject.removeField(KEY_PRODUCT_SUBGROUP);
			
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
	public Boolean update(Product product) {
		try{
			Date date = new Date();
			product.setUtime( date );
			
			DBCollection collection = mongoDB.getCollection( collProduct );
			String jsonString = CommonUtil.objectToJson(product);
			
			DBObject dbObject = (DBObject) JSON.parse( jsonString );
			BasicDBList basicDBList = new BasicDBList();
			if( product.getTaxList() != null ){
				for(Tax tax : product.getTaxList() ){
					DBRef taxRef = new DBRef(mongoDB, collTax, tax.get_id() );
					basicDBList.add(taxRef);
				}
			}
			
			
			DBRef manufacturerRef = new DBRef(mongoDB, collManufacturer, product.getManufacturar().get_id() );
			DBRef productGroupRef = new DBRef(mongoDB, collProductGroup, product.getProductGroup().get_id());
			DBRef productSubGroupRef = new DBRef(mongoDB, collProductGroup, product.getProductSubGroup().get_id());
			
			dbObject.put( KEY_TAX_LIST_XID, basicDBList);
			dbObject.put( KEY_MANUFACTURER_XID, manufacturerRef );
			dbObject.put( KEY_PRODUCT_GROUP_XID, productGroupRef );
			dbObject.put( KEY_PRODUCT_SUBGROUP_XID, productSubGroupRef );
			
			dbObject.removeField(KEY_MANUFACTURER);
			dbObject.removeField(KEY_TAX_LIST);
			dbObject.removeField(KEY_PRODUCT_GROUP);
			dbObject.removeField(KEY_PRODUCT_SUBGROUP);
			
			DBObject query = new BasicDBObject("_id", product.get_id()); 
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
			DBCollection collection = mongoDB.getCollection( collProduct );
			
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
	public Product get(String _id) {
		return get(_id, false); // _id of product, withReferences - false 
	}
	
	@Override
	public Product get(String _id, Boolean withReferences) {
		try{
			DBCollection collection = mongoDB.getCollection( collProduct );
			DBObject query = new BasicDBObject("_id", _id);
			DBObject dbObject = collection.findOne(query);
			
			if( withReferences == true ){
				BasicDBList basicDBList = (BasicDBList) dbObject.get(KEY_TAX_LIST_XID);
				
				BasicDBList taxDBOList =  new BasicDBList();
				if( basicDBList != null ){
					DBRef [] taxDBRefList = (DBRef[]) basicDBList.toArray();
					
					for(DBRef taxDBRef : taxDBRefList ){
						DBObject taxDBObject = taxDBRef.fetch();
						taxDBOList.add(taxDBObject);
					}
				}
				
				DBObject manufacturerDBO =  ( (DBRef)dbObject.get(KEY_MANUFACTURER_XID) ).fetch();
				DBObject productGroupDBO =  ( (DBRef)dbObject.get(KEY_PRODUCT_GROUP_XID) ).fetch();
				DBObject productSubGroupDBO =  ( (DBRef)dbObject.get(KEY_PRODUCT_SUBGROUP_XID) ).fetch();
				
				dbObject.put(KEY_TAX_LIST, taxDBOList);
				dbObject.put(KEY_MANUFACTURER, manufacturerDBO);
				dbObject.put(KEY_PRODUCT_GROUP, productGroupDBO);
				dbObject.put(KEY_PRODUCT_SUBGROUP, productSubGroupDBO);
			}
			
			dbObject.removeField(KEY_TAX_LIST_XID);
			dbObject.removeField(KEY_MANUFACTURER_XID);
			dbObject.removeField(KEY_PRODUCT_GROUP_XID);
			dbObject.removeField(KEY_PRODUCT_SUBGROUP_XID);
			
			String jsonString = JSON.serialize(dbObject);
			Product product = (Product) CommonUtil.jsonToObject( jsonString, Product.class.getName() );
			
			return product;
			
		}catch( Exception exception ){
			LOG.equals(exception);
		}
		return null;
	}
	
	@Override
	public List<Product> getAll() {
		return getAll(false);
	}
	
	@Override
	public List<Product> getAll(Boolean withReferences) {
		try{
			DBCollection collection = mongoDB.getCollection( collProduct );
			DBCursor dbCursor = collection.find();
			
			List<Product> productList = new ArrayList<>();
			
			while ( dbCursor.hasNext() ) {
				DBObject dbObject = dbCursor.next();
				
				if( withReferences == true ){
					
					BasicDBList basicDBList = (BasicDBList) dbObject.get(KEY_TAX_LIST_XID);
					
					BasicDBList taxDBOList =  new BasicDBList();
					if( basicDBList != null ){
						DBRef [] taxDBRefList = (DBRef[]) basicDBList.toArray();
						
						for(DBRef taxDBRef : taxDBRefList ){
							DBObject taxDBObject = taxDBRef.fetch();
							taxDBOList.add(taxDBObject);
						}
					}
					
					DBObject manufacturerDBO =  ( (DBRef)dbObject.get(KEY_MANUFACTURER_XID) ).fetch();
					DBObject productGroupDBO =  ( (DBRef)dbObject.get(KEY_PRODUCT_GROUP_XID) ).fetch();
					DBObject productSubGroupDBO =  ( (DBRef)dbObject.get(KEY_PRODUCT_SUBGROUP_XID) ).fetch();
					
					dbObject.put(KEY_TAX_LIST, taxDBOList);
					dbObject.put(KEY_MANUFACTURER_XID, manufacturerDBO);
					dbObject.put(KEY_PRODUCT_GROUP, productGroupDBO);
					dbObject.put(KEY_PRODUCT_SUBGROUP, productSubGroupDBO);
					
				}
				
				dbObject.removeField(KEY_TAX_LIST_XID);
				dbObject.removeField(KEY_MANUFACTURER_XID);
				dbObject.removeField(KEY_PRODUCT_GROUP_XID);
				dbObject.removeField(KEY_PRODUCT_SUBGROUP_XID);
				
				String jsonString = JSON.serialize(dbObject);
				Product product = (Product) CommonUtil.jsonToObject( jsonString, Product.class.getName() );
				productList.add(product);
			}
			
			return productList;
			
		}catch( Exception exception ){
			LOG.equals(exception);
		}
		return null;
	}
	
	
	
}
