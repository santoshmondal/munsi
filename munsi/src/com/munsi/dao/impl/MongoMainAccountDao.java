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
import com.munsi.dao.MainAccountDao;
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
			
			collection.insert(dbObject );
			return true;
			
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
			dbObject.removeField("_id");
			
			DBObject query = new BasicDBObject("_id", mainAccount.get_id());
			
			DBObject update = new BasicDBObject("$set", dbObject); 
			
			collection.update(query, update);
			return true;
			
		}catch( Exception exception ){
			LOG.equals(exception);
		}
		return false;
		
	}
	
	@Override
	public Boolean delete(String _id) {
		MainAccount mainAccount = new MainAccount();
		mainAccount.set_id(_id);
		mainAccount.setDeleted(true);
		return update(mainAccount);
		
	}
	
	@Override
	public MainAccount get(String _id) {
		try{
			DBCollection collection = mongoDB.getCollection( collMAinAC );
			DBObject query = new BasicDBObject("_id", _id);
			DBObject dbObject = collection.findOne(query);
			String jsonString = JSON.serialize(dbObject);
			MainAccount mainAccount = (MainAccount) CommonUtil.jsonToObject( jsonString, MainAccount.class.getName() );
			
			return mainAccount;
			
		}catch( Exception exception ){
			LOG.equals(exception);
		}
		return null;
	}
	
	@Override
	public List<MainAccount> getAll() {
		try{
			DBCollection collection = mongoDB.getCollection( collMAinAC );
			DBObject finalQuery = MongoUtil.getQueryToCheckDeleted();
			DBCursor dbCursor = collection.find( finalQuery);
			
			List<MainAccount> mainAccountList = new ArrayList<>();
			
			while ( dbCursor.hasNext() ) {
				DBObject dbObject = dbCursor.next();
				String jsonString = JSON.serialize(dbObject);
				MainAccount mainAccount = (MainAccount) CommonUtil.jsonToObject( jsonString, MainAccount.class.getName() );
				mainAccountList.add(mainAccount);
			}
			
			return mainAccountList;
			
		}catch( Exception exception ){
			LOG.equals(exception);
		}
		return null;
	}
	
	
	
}
