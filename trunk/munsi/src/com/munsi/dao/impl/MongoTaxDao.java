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
import com.mongodb.util.JSON;
import com.munsi.dao.TaxDao;
import com.munsi.pojo.master.Tax;
import com.munsi.util.CommonUtil;
import com.munsi.util.Constants.DBCollectionEnum;
import com.munsi.util.MongoUtil;

public class MongoTaxDao implements TaxDao {
	private static final Logger LOG = Logger.getLogger( MongoTaxDao.class );
	
	
	private String collTax = DBCollectionEnum.MAST_TAX.toString();
	
	private DB mongoDB = MongoUtil.getDB();
	
	@Override
	public Boolean create(Tax tax) {
		try{
			Date date = new Date();
			tax.setCtime( date );
			tax.setUtime( date );
			String _id = MongoUtil.getNextSequence(DBCollectionEnum.MAST_TAX).toString();
			tax.set_id( _id );
			
			DBCollection collection = mongoDB.getCollection( collTax );
			String jsonString = CommonUtil.objectToJson(tax);
			
			DBObject dbObject = (DBObject) JSON.parse( jsonString );
			
			collection.insert(dbObject );
			return true;
			
		}catch( Exception exception ){
			LOG.equals(exception);
		}
		return false;
	}
	
	@Override
	public Boolean update(Tax tax) {
		try{
			Date date = new Date();
			tax.setUtime( date );
			
			DBCollection collection = mongoDB.getCollection( collTax );
			String jsonString = CommonUtil.objectToJson(tax);
			
			DBObject dbObject = (DBObject) JSON.parse( jsonString );
			dbObject.removeField("_id");
			
			DBObject updateObje = new BasicDBObject("$set",dbObject);
			
			DBObject query = new BasicDBObject("_id", tax.get_id());
			
			collection.update(query, updateObje);
			return true;
		}catch( Exception exception ){
			LOG.equals(exception);
		}
		return false;
		
	}
	
	@Override
	public Boolean delete(String _id) {
		Tax tax = new Tax();
		tax.set_id(_id);
		tax.setDeleted(true);
		return update(tax);
	}
	
	@Override
	public Tax get(String _id) {
		try{
			DBCollection collection = mongoDB.getCollection( collTax );
			DBObject query = new BasicDBObject("_id", _id);
			DBObject dbObject = collection.findOne(query);
			String jsonString = JSON.serialize(dbObject);
			Tax tax = (Tax) CommonUtil.jsonToObject( jsonString, Tax.class.getName() );
			
			return tax;
			
		}catch( Exception exception ){
			LOG.equals(exception);
		}
		return null;
	}
	
	@Override
	public List<Tax> getAll() {
		try{
			DBCollection collection = mongoDB.getCollection( collTax );
			DBObject queryCheckDeleted = MongoUtil.getQueryToCheckDeleted();
			DBCursor dbCursor = collection.find(queryCheckDeleted);
			
			List<Tax> taxList = new ArrayList<>();
			
			while ( dbCursor.hasNext() ) {
				DBObject dbObject = dbCursor.next();
				String jsonString = JSON.serialize(dbObject);
				Tax tax = (Tax) CommonUtil.jsonToObject( jsonString, Tax.class.getName() );
				taxList.add(tax);
			}
			
			return taxList;
			
		}catch( Exception exception ){
			LOG.equals(exception);
		}
		return null;
	}
	
}
