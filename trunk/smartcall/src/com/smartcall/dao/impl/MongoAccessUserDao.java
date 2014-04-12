package com.smartcall.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.async.util.CommonUtil;
import com.async.util.Constants.DBCollectionEnum;
import com.async.util.MongoUtil;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.WriteResult;
import com.mongodb.util.JSON;
import com.smartcall.dao.AccessUserDao;
import com.smartcall.pojo.AccessUser;

public class MongoAccessUserDao implements AccessUserDao{
	private static final Logger LOG = Logger.getLogger(MongoAccessUserDao.class);

	private final String collAccessUser = DBCollectionEnum.ACCESS_USER.toString();
	private final DB mongoDB = MongoUtil.getDB();

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

			WriteResult writeResult = collection.insert(dbObject);

			if (writeResult.getN() > 0) {
				return true;
			}

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

			WriteResult writeResult = collection.update(query, update);

			if (writeResult.getN() > 0) {
				return true;
			}

		} catch (Exception exception) {
			LOG.equals(exception);
		}
		return false;

	}

	@Override
	public Boolean delete(String _id) {
		try {
			DBCollection collection = mongoDB.getCollection(collAccessUser);

			DBObject query = new BasicDBObject("_id", _id);
			DBObject update = new BasicDBObject("deleted", true).append("utime", new Date());
			DBObject updateObj = new BasicDBObject("$set", update);
			WriteResult writeResult = collection.update(query, updateObj);

			if (writeResult.getN() > 0) {
				return true;
			}
		} catch (Exception exception) {
			LOG.equals(exception);
		}
		return false;

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
				AccessUser accessUser = (AccessUser) CommonUtil.jsonToObject(jsonString, AccessUserDao.class.getName());
				areaList.add(accessUser);
			}

			return areaList;

		} catch (Exception exception) {
			LOG.equals(exception);
		}
		return null;
	}

	/*
	@Override
	public List<String[]> getIdName() {
		try {
			DBCollection collection = mongoDB.getCollection(collAccessUser);
			DBObject dbKey = new BasicDBObject("name", 1);

			DBCursor dbCursor = collection.find(new BasicDBObject(), dbKey);

			List<String[]> areaList = new ArrayList<>();

			while (dbCursor.hasNext()) {

				BasicDBObject dbObject = (BasicDBObject) dbCursor.next();

				String _id = dbObject.getString("_id");
				String name = dbObject.getString("name");

				String[] idName = new String[] { _id, name };
				areaList.add(idName);
			}

			return areaList;

		} catch (Exception exception) {
			LOG.equals(exception);
		}
		return null;
	}
	*/
}
