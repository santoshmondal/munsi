package com.estudio.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.async.util.CommonUtil;
import com.async.util.Constants.DBCollectionEnum;
import com.async.util.MongoUtil;
import com.estudio.dao.AccessUserDao;
import com.estudio.pojo.AccessUser;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.util.JSON;

public class MongoAccessUserDao implements AccessUserDao{
	private static final Logger LOG = Logger.getLogger(MongoAccessUserDao.class);

	private final String collAccessUser = DBCollectionEnum.ACCESS_USER.toString();
	private final DB mongoDB = MongoUtil.getDB();

	@Override
	public AccessUser authenticate(String userName, String password) {
		try {
			DBCollection collection = mongoDB.getCollection(collAccessUser);
			DBObject query = new BasicDBObject("userName", userName)
								.append("password", "password");
			
			DBObject dbObject = collection.findOne(query);
			String jsonString = JSON.serialize(dbObject);
			AccessUser accessUser = (AccessUser) CommonUtil.jsonToObject(jsonString, AccessUser.class.getName());
			return accessUser;

		} catch (Exception exception) {
			LOG.equals(exception);
		}
		return null;
	}
	
	@Override
	public Boolean create(AccessUser accessUser) {
		try {
			Date date = new Date();
			accessUser.setCtime(date);
			accessUser.setUtime(date);
			String _id = MongoUtil.getNextSequence(DBCollectionEnum.ACCESS_USER).toString();
			accessUser.set_id(_id);

			DBCollection collection = mongoDB.getCollection(collAccessUser);
			String jsonString = CommonUtil.objectToJson(accessUser);

			DBObject dbObject = (DBObject) JSON.parse(jsonString);
			collection.insert(dbObject);
			return true;
		} catch (Exception exception) {
			LOG.equals(exception);
		}
		return false;
	}

	@Override
	public Boolean update(AccessUser accessUser) {
		try {
			Date date = new Date();
			accessUser.setUtime(date);

			DBCollection collection = mongoDB.getCollection(collAccessUser);
			String jsonString = CommonUtil.objectToJson(accessUser);

			DBObject dbObject = (DBObject) JSON.parse(jsonString);
			dbObject.removeField("_id");
			
			DBObject query = new BasicDBObject("_id", accessUser.get_id());

			DBObject update = new BasicDBObject("$set", dbObject);

			collection.update(query, update);
			return true;
			
		} catch (Exception exception) {
			LOG.equals(exception);
		}
		return false;

	}

	@Override
	public Boolean delete(String _id) {
		AccessUser accessUser = new AccessUser();
		accessUser.set_id(_id);
		accessUser.setDeleted(true);
		return update(accessUser);
	}

	@Override
	public AccessUser get(String _id) {
		try {
			DBCollection collection = mongoDB.getCollection(collAccessUser);
			DBObject query = new BasicDBObject("_id", _id);
			DBObject dbObject = collection.findOne(query);
			String jsonString = JSON.serialize(dbObject);
			AccessUser accessUser = (AccessUser) CommonUtil.jsonToObject(jsonString, AccessUser.class.getName());

			return accessUser;

		} catch (Exception exception) {
			LOG.equals(exception);
		}
		return null;
	}


	@Override
	public List<AccessUser> getAll() {
		try {
			DBCollection collection = mongoDB.getCollection(collAccessUser);
			DBCursor dbCursor = collection.find();

			List<AccessUser> areaList = new ArrayList<>();

			while (dbCursor.hasNext()) {
				DBObject dbObject = dbCursor.next();
				String jsonString = JSON.serialize(dbObject);
				AccessUser accessUser = (AccessUser) CommonUtil.jsonToObject(jsonString, AccessUser.class.getName());
				areaList.add(accessUser);
			}

			return areaList;

		} catch (Exception exception) {
			LOG.equals(exception);
		}
		return null;
	}

	@Override
	public Long getCount() {
		try {
			DBCollection collection = mongoDB.getCollection(collAccessUser);
			Long count = collection.count();
			return count;

		} catch (Exception exception) {
			LOG.equals(exception);
		}
		return -1l;
	}

}
