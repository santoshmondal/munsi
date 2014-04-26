package com.estudio.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.async.util.CommonUtil;
import com.async.util.Constants.DBCollectionEnum;
import com.async.util.MongoUtil;
import com.estudio.dao.MasterDao;
import com.estudio.pojo.master.Master;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.util.JSON;

public class MongoMasterDao implements MasterDao{
	private static final Logger LOG = Logger.getLogger(MongoMasterDao.class);

	private final String collMaster = DBCollectionEnum.MASTER.toString();
	private final DB mongoDB = MongoUtil.getDB();

	@Override
	public Boolean create(Master master) {
		try {
			Date date = new Date();
			master.setCtime(date);
			master.setUtime(date);
			
			DBCollection collection = mongoDB.getCollection(collMaster);
			Integer id = MongoUtil.getNextSequence(DBCollectionEnum.MASTER);
			master.set_id(id+"");
			String jsonString = CommonUtil.objectToJson(master);
			
			DBObject dbObject = (DBObject) JSON.parse(jsonString);

			collection.insert(dbObject);

		} catch (Exception exception) {
			LOG.equals(exception);
		}
		return false;
	}

	@Override
	public Boolean update(Master master) {
		try {
			Date date = new Date();
			master.setUtime(date);

			DBCollection collection = mongoDB.getCollection(collMaster);
			String jsonString = CommonUtil.objectToJson(master);

			DBObject dbObject = (DBObject) JSON.parse(jsonString);
			dbObject.removeField("_id");
			
			DBObject query = new BasicDBObject("_id", master.get_id());

			DBObject update = new BasicDBObject("$set", dbObject);

			collection.update(query, update);

		} catch (Exception exception) {
			LOG.equals(exception);
		}
		return false;

	}

	@Override
	public Boolean delete(String _id) {
		try {
			DBCollection collection = mongoDB.getCollection(collMaster);

			DBObject query = new BasicDBObject("_id", _id);
			DBObject update = new BasicDBObject("deleted", true).append("utime", new Date());
			DBObject updateObj = new BasicDBObject("$set", update);
			collection.update(query, updateObj);
		} catch (Exception exception) {
			LOG.equals(exception);
		}
		return false;

	}

	
	
	//@Override
	public List<Master> getPhotoAll() {
		return getByType("photo");
	}

	//@Override
	public List<Master> getLaminationAll() {
		return getByType("photo");
	}
	
	//@Override
	public List<Master> getFrameAll() {
		return getByType("photo");
	}
	
	public List<Master> getByType(String type) {
		try {
			DBCollection collection = mongoDB.getCollection(collMaster);
			DBObject query = new BasicDBObject("type", type);
			DBCursor dbCursor = collection.find(query);
			List<Master> masterList = new ArrayList<>();

			while (dbCursor.hasNext()) {
				DBObject dbObject = dbCursor.next();
				String jsonString = JSON.serialize(dbObject);
				Master master = (Master) CommonUtil.jsonToObject(jsonString, Master.class.getName());
				masterList.add(master);
			}

			
			return masterList;

		} catch (Exception exception) {
			LOG.equals(exception);
		}
		return null;
	}
	
	
	
	
	
	
	@Override
	public Master get(String _id) {
		try {
			DBCollection collection = mongoDB.getCollection(collMaster);
			DBObject query = new BasicDBObject("_id", _id);
			DBObject dbObject = collection.findOne(query);
			String jsonString = JSON.serialize(dbObject);
			Master master = (Master) CommonUtil.jsonToObject(jsonString, Master.class.getName());

			return master;

		} catch (Exception exception) {
			LOG.equals(exception);
		}
		return null;
	}


	@Override
	public List<Master> getAll() {
		try {
			DBCollection collection = mongoDB.getCollection(collMaster);
			DBCursor dbCursor = collection.find();

			List<Master> areaList = new ArrayList<>();

			while (dbCursor.hasNext()) {
				DBObject dbObject = dbCursor.next();
				String jsonString = JSON.serialize(dbObject);
				Master master = (Master) CommonUtil.jsonToObject(jsonString, Master.class.getName());
				areaList.add(master);
			}

			return areaList;

		} catch (Exception exception) {
			LOG.equals(exception);
		}
		return null;
	}

	
	public static void main(String[] args) {
		MongoMasterDao mmd = new MongoMasterDao();
		Master master = new Master();
		master.setSize("6x7");
		master.setType("photo");
		master.setPrice(100.00f);
		master.setQuality("glass");
		
		mmd.create(master);
		
		
		master.setSize("5x8");
		master.setType("photo");
		master.setPrice(200.00f);
		master.setQuality("glass");
		
		mmd.create(master);
		
		
		master.setSize("6x7");
		master.setType("photo");
		master.setPrice(150.00f);
		master.setQuality("metal");
		
		mmd.create(master);
	}
}
