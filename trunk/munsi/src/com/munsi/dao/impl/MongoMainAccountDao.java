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
import com.munsi.dao.MainAccountDao;
import com.munsi.pojo.master.Customer;
import com.munsi.pojo.master.MainAccount;
import com.munsi.util.CommonUtil;
import com.munsi.util.Constants.DBCollectionEnum;
import com.munsi.util.MongoUtil;

public class MongoMainAccountDao implements MainAccountDao {
	private static final Logger LOG = Logger.getLogger( MongoMainAccountDao.class );
	
	
	private String collMAinAC = DBCollectionEnum.MAST_MAIN_ACCOUNT.toString();
	
	private DB mongoDB = MongoUtil.getDB();
	
	@Override
	public Boolean create(MainAccount mainAccount) {
		try{
			Date date = new Date();
			mainAccount.setCtime( date );
			mainAccount.setUtime( date );
			String _id = MongoUtil.getNextSequence(DBCollectionEnum.MAST_MAIN_ACCOUNT).toString();
			mainAccount.set_id( _id );
			
			DBCollection collection = mongoDB.getCollection( collMAinAC );
			String jsonString = CommonUtil.objectToJson(mainAccount);
			
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
	public Boolean update(MainAccount mainAccount) {
		try{
			Date date = new Date();
			mainAccount.setUtime( date );
			
			DBCollection collection = mongoDB.getCollection( collMAinAC );
			String jsonString = CommonUtil.objectToJson(mainAccount);
			
			DBObject dbObject = (DBObject) JSON.parse( jsonString );
			
			DBObject query = new BasicDBObject("_id", mainAccount.get_id());
			
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
			DBCollection collection = mongoDB.getCollection( collMAinAC );
			
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
	public MainAccount get(String _id) {
		return get(_id, false); // _id of customer, withReferences - false 
	}
	
	@Override
	public MainAccount get(String _id, Boolean withReferences) {
		try{
			DBCollection collection = mongoDB.getCollection( collMAinAC );
			DBObject query = new BasicDBObject("_id", _id);
			DBObject dbObject = collection.findOne(query);
			String jsonString = JSON.serialize(dbObject);
			MainAccount mainAccount = (MainAccount) CommonUtil.jsonToObject( jsonString, Customer.class.getName() );
			
			return mainAccount;
			
		}catch( Exception exception ){
			LOG.equals(exception);
		}
		return null;
	}
	
	@Override
	public List<MainAccount> getAll() {
		return getAll(false);
	}
	
	@Override
	public List<MainAccount> getAll(Boolean withReferences) {
		try{
			DBCollection collection = mongoDB.getCollection( collMAinAC );
			DBCursor dbCursor = collection.find();
			
			List<MainAccount> mainAccountList = new ArrayList<>();
			
			while ( dbCursor.hasNext() ) {
				DBObject dbObject = dbCursor.next();
				String jsonString = JSON.serialize(dbObject);
				MainAccount mainAccount = (MainAccount) CommonUtil.jsonToObject( jsonString, Customer.class.getName() );
				mainAccountList.add(mainAccount);
			}
			
			return mainAccountList;
			
		}catch( Exception exception ){
			LOG.equals(exception);
		}
		return null;
	}
	
	@Override
	public List<String[]> getIdName() {
		try{
			DBCollection collection = mongoDB.getCollection( collMAinAC );
			DBObject dbKey = new BasicDBObject("name",1);
			
			DBCursor dbCursor = collection.find(new BasicDBObject(), dbKey);
			
			List<String[]> mainAccountList = new ArrayList<>();
			
			while ( dbCursor.hasNext() ) {
				
				BasicDBObject dbObject = (BasicDBObject) dbCursor.next();
				
				String _id = dbObject.getString("_id");
				String name = dbObject.getString("name");
				
				String [] idName = new String[]{ _id, name };
				mainAccountList.add(idName);
			}
			
			return mainAccountList;
			
		}catch( Exception exception ){
			LOG.equals(exception);
		}
		return null;
	}
	
	
}
