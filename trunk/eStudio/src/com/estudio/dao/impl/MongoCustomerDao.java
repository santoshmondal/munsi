package com.estudio.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.async.util.CommonUtil;
import com.async.util.Constants.DBCollectionEnum;
import com.async.util.MongoUtil;
import com.estudio.dao.AccessUserDao;
import com.estudio.dao.CustomerDao;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.WriteResult;
import com.mongodb.util.JSON;
import com.estudio.pojo.Customer;

public class MongoCustomerDao implements CustomerDao{
	private static final Logger LOG = Logger.getLogger(MongoCustomerDao.class);

	private final String collCustomer = DBCollectionEnum.CUSTOMER.toString();
	private final DB mongoDB = MongoUtil.getDB();

	@Override
	public Boolean create(Customer customer) {
		try {
			Date date = new Date();
			customer.setCtime(date);
			customer.setUtime(date);
			Integer _id = MongoUtil.getNextSequence(DBCollectionEnum.CUSTOMER);
			customer.set_id(_id+"");

			DBCollection collection = mongoDB.getCollection(collCustomer);
			String jsonString = CommonUtil.objectToJson(customer);

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
	public Boolean update(Customer customer) {
		try {
			Date date = new Date();
			customer.setUtime(date);

			DBCollection collection = mongoDB.getCollection(collCustomer);
			String jsonString = CommonUtil.objectToJson(customer);

			DBObject dbObject = (DBObject) JSON.parse(jsonString);
			dbObject.removeField("_id");
			
			DBObject query = new BasicDBObject("_id", customer.get_id());

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
			DBCollection collection = mongoDB.getCollection(collCustomer);

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
	public Customer get(String _id) {
		try {
			DBCollection collection = mongoDB.getCollection(collCustomer);
			DBObject query = new BasicDBObject("_id", _id);
			DBObject dbObject = collection.findOne(query);
			String jsonString = JSON.serialize(dbObject);
			Customer customer = (Customer) CommonUtil.jsonToObject(jsonString, Customer.class.getName());

			return customer;

		} catch (Exception exception) {
			LOG.equals(exception);
		}
		return null;
	}


	@Override
	public List<Customer> getAll() {
		try {
			DBCollection collection = mongoDB.getCollection(collCustomer);
			DBCursor dbCursor = collection.find();

			List<Customer> areaList = new ArrayList<>();

			while (dbCursor.hasNext()) {
				DBObject dbObject = dbCursor.next();
				String jsonString = JSON.serialize(dbObject);
				Customer customer = (Customer) CommonUtil.jsonToObject(jsonString, AccessUserDao.class.getName());
				areaList.add(customer);
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
			DBCollection collection = mongoDB.getCollection(collCustomer);
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
	
	public static void main(String[] args) {
		MongoCustomerDao mcd = new MongoCustomerDao();
		Customer customer = new Customer();
		customer.setName("ajay devgan");
		
		mcd.create(customer);
	}
}
