package com.estudio.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.async.util.CommonUtil;
import com.async.util.Constants.DBCollectionEnum;
import com.async.util.MongoUtil;
import com.estudio.dao.AccessUserDao;
import com.estudio.dao.InvoiceDao;
import com.estudio.pojo.Invoice;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.DBRef;
import com.mongodb.util.JSON;

public class MongoInvoiceDao implements InvoiceDao{
	private static final Logger LOG = Logger.getLogger(MongoInvoiceDao.class);

	private final String collInvoice = DBCollectionEnum.INVOICE.toString();
	private final String collCustomer = DBCollectionEnum.CUSTOMER.toString();
	
	private final String KEY_CUSTOMER = "customer";
	private final String KEY_CUSTOMER_XID = "customeXid";
	private final DB mongoDB = MongoUtil.getDB();

	@Override
	public Boolean create(Invoice invoice) {
		try {
			Date date = new Date();
			invoice.setCtime(date);
			invoice.setUtime(date);
			Integer _id = MongoUtil.getNextSequence(DBCollectionEnum.INVOICE);
			invoice.set_id(_id+"");

			DBCollection collection = mongoDB.getCollection(collInvoice);
			String jsonString = CommonUtil.objectToJson(invoice);

			DBObject dbObject = (DBObject) JSON.parse(jsonString);
			dbObject.removeField(KEY_CUSTOMER);
			DBRef custRef = new DBRef(mongoDB, collCustomer, invoice.getCustomer().get_id());
			dbObject.put(KEY_CUSTOMER_XID, custRef);
			collection.insert(dbObject);

		} catch (Exception exception) {
			LOG.equals(exception);
		}
		return false;
	}

	@Override
	public Boolean update(Invoice invoice) {
		try {
			Date date = new Date();
			invoice.setUtime(date);

			DBCollection collection = mongoDB.getCollection(collInvoice);
			String jsonString = CommonUtil.objectToJson(invoice);

			DBObject dbObject = (DBObject) JSON.parse(jsonString);
			dbObject.removeField("_id");
			if(invoice.getCustomer() != null){
				dbObject.removeField(KEY_CUSTOMER);
				DBRef custRef = new DBRef(mongoDB, collCustomer, invoice.getCustomer().get_id());
				dbObject.put(KEY_CUSTOMER_XID, custRef);
			}
			
			DBObject query = new BasicDBObject("_id", invoice.get_id());

			DBObject update = new BasicDBObject("$set", dbObject);

			collection.update(query, update);

		} catch (Exception exception) {
			LOG.equals(exception);
		}
		return false;

	}
	
	@Override
	public Boolean updateStatus(String _id, String status) {
		try {
			Date date = new Date();
			
			DBCollection collection = mongoDB.getCollection(collInvoice);
			
			DBObject dbObject = new BasicDBObject("status",status)
								.append("utime", date);
			
			DBObject query = new BasicDBObject("_id", _id);

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
			DBCollection collection = mongoDB.getCollection(collInvoice);

			DBObject query = new BasicDBObject("_id", _id);
			DBObject update = new BasicDBObject("deleted", true).append("utime", new Date());
			DBObject updateObj = new BasicDBObject("$set", update);
			collection.update(query, updateObj);
			
		} catch (Exception exception) {
			LOG.equals(exception);
		}
		return false;

	}

	@Override
	public Invoice get(String _id) {
		try {
			DBCollection collection = mongoDB.getCollection(collInvoice);
			DBObject query = new BasicDBObject("_id", _id);
			DBObject dbObject = collection.findOne(query);
			
			DBObject custRef =  ( (DBRef)dbObject.get(KEY_CUSTOMER_XID) ).fetch();
			dbObject.put(KEY_CUSTOMER, custRef);
			
			String jsonString = JSON.serialize(dbObject);
			Invoice invoice = (Invoice) CommonUtil.jsonToObject(jsonString, Invoice.class.getName());

			return invoice;

		} catch (Exception exception) {
			LOG.equals(exception);
		}
		return null;
	}


	@Override
	public List<Invoice> getAll() {
		try {
			DBCollection collection = mongoDB.getCollection(collInvoice);
			DBCursor dbCursor = collection.find();

			List<Invoice> areaList = new ArrayList<>();

			while (dbCursor.hasNext()) {
				DBObject dbObject = dbCursor.next();

				DBObject custRef =  ( (DBRef)dbObject.get(KEY_CUSTOMER_XID) ).fetch();
				dbObject.put(KEY_CUSTOMER, custRef);

				String jsonString = JSON.serialize(dbObject);
				Invoice invoice = (Invoice) CommonUtil.jsonToObject(jsonString, AccessUserDao.class.getName());
				areaList.add(invoice);
			}

			return areaList;

		} catch (Exception exception) {
			LOG.equals(exception);
		}
		return null;
	}

}
