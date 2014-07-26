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
import com.munsi.dao.AccesssUserDao;
import com.munsi.pojo.master.AccessUser;
import com.munsi.pojo.master.Customer;
import com.munsi.util.CommonUtil;
import com.munsi.util.Constants.DBCollectionEnum;
import com.munsi.util.MongoUtil;

public class MongoAccessUserDao implements AccesssUserDao {
	private static final Logger LOG = Logger.getLogger(MongoAccessUserDao.class);

	private final String collAccessUser = DBCollectionEnum.MAST_ACCESS_USER.toString();

	private final DB mongoDB = MongoUtil.getDB();

	@Override
	public Boolean create(AccessUser accessUser) {
		try {
			Date date = new Date();
			accessUser.setCtime(date);
			accessUser.setUtime(date);
			String _id = MongoUtil.getNextSequence(DBCollectionEnum.MAST_ACCESS_USER).toString();
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
		return get(_id, false); // _id of customer, withReferences - false
	}

	@Override
	public AccessUser get(String _id, Boolean withReferences) {
		try {
			DBCollection collection = mongoDB.getCollection(collAccessUser);
			DBObject query = new BasicDBObject("_id", _id);
			DBObject dbObject = collection.findOne(query);
			String jsonString = JSON.serialize(dbObject);
			AccessUser accessUser = (AccessUser) CommonUtil.jsonToObject(jsonString, Customer.class.getName());

			return accessUser;

		} catch (Exception exception) {
			LOG.equals(exception);
		}
		return null;
	}

	@Override
	public List<AccessUser> getAll() {
		return getAll(false);
	}

	@Override
	public List<AccessUser> getAll(Boolean withReferences) {
		try {
			DBCollection collection = mongoDB.getCollection(collAccessUser);
			DBCursor dbCursor = collection.find();

			List<AccessUser> areaList = new ArrayList<>();

			while (dbCursor.hasNext()) {
				DBObject dbObject = dbCursor.next();
				String jsonString = JSON.serialize(dbObject);
				AccessUser accessUser = (AccessUser) CommonUtil.jsonToObject(jsonString, Customer.class.getName());
				areaList.add(accessUser);
			}

			return areaList;

		} catch (Exception exception) {
			LOG.equals(exception);
		}
		return null;
	}

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

}
