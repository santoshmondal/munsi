package com.estudio.dao.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.async.util.CommonUtil;
import com.async.util.Constants;
import com.async.util.Constants.DBCollectionEnum;
import com.async.util.MongoUtil;
import com.estudio.dao.InvoiceDao;
import com.estudio.pojo.Invoice;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.DBRef;
import com.mongodb.util.JSON;

public class MongoInvoiceDao implements InvoiceDao {
	private static final Logger LOG = Logger.getLogger(MongoInvoiceDao.class);

	private final String collInvoice = DBCollectionEnum.INVOICE.toString();
	private final String collCustomer = DBCollectionEnum.CUSTOMER.toString();

	private final String KEY_CUSTOMER = "customer";
	private final String KEY_CUSTOMER_XID = "customeXid";
	private final DB mongoDB = MongoUtil.getDB();

	@Override
	public Invoice create(Invoice invoice) {
		Invoice toReturn = null;
		try {
			Date date = new Date();
			invoice.setCtime(date);
			invoice.setUtime(date);
			Integer _id = MongoUtil.getNextSequence(DBCollectionEnum.INVOICE);
			invoice.set_id(_id + "");
			invoice.setInvoiceNumber(_id + "");
			invoice.setInvoiceDate(date);

			invoice.setStatus(Constants.OrderStatuEnum.RAW_DATA.toString());

			DBCollection collection = mongoDB.getCollection(collInvoice);
			String jsonString = CommonUtil.objectToJson(invoice);

			DBObject dbObject = (DBObject) JSON.parse(jsonString);
			dbObject.removeField(KEY_CUSTOMER);
			DBRef custRef = new DBRef(mongoDB, collCustomer, invoice.getCustomer().get_id());
			dbObject.put(KEY_CUSTOMER_XID, custRef);
			collection.insert(dbObject);

			toReturn = get(_id + "");

		} catch (Exception exception) {
			LOG.equals(exception);
		}
		return toReturn;
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
			if (invoice.getCustomer() != null) {
				dbObject.removeField(KEY_CUSTOMER);
				DBRef custRef = new DBRef(mongoDB, collCustomer, invoice.getCustomer().get_id());
				dbObject.put(KEY_CUSTOMER_XID, custRef);
			}

			DBObject query = new BasicDBObject("_id", invoice.get_id());

			DBObject update = new BasicDBObject("$set", dbObject);

			collection.update(query, update);

			return true;
		} catch (Exception exception) {
			LOG.equals(exception);
		}
		return false;

	}

	@Override
	public Boolean updateStatus(String _id, String status) {
		Invoice invoice = new Invoice();
		invoice.set_id(_id);
		invoice.setStatus(status);

		return update(invoice);

	}

	@Override
	public Boolean delete(String _id) {
		Invoice sRef = new Invoice();
		sRef.set_id(_id);
		sRef.setDeleted(true);

		return update(sRef);

	}

	@Override
	public Invoice get(String _id) {
		try {
			DBCollection collection = mongoDB.getCollection(collInvoice);
			DBObject query = new BasicDBObject("_id", _id);
			DBObject dbObject = collection.findOne(query);

			DBObject custRef = ((DBRef) dbObject.get(KEY_CUSTOMER_XID)).fetch();
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
		return getAllByField(null);
	}

	@Override
	public List<Invoice> getAllByField(Map<String, String> map) {
		try {
			DBCollection collection = mongoDB.getCollection(collInvoice);

			DBObject query = new BasicDBObject();
			if (map != null) {
				for (Map.Entry<String, String> entry : map.entrySet()) {
					query.put(entry.getKey(), entry.getValue());
				}
				DBObject checkExists = new BasicDBObject("$exists", false);
				query.put("deleted", checkExists);
			}

			//DBObject excludeProjection = new BasicDBObject("photoDetailsList", 0);
			//excludeProjection.put("frameDetailsList", 0);
			DBObject excludeProjection = new BasicDBObject("frameDetailsList", 0);
			excludeProjection.put("laminationDetailsList", 0);

			DBObject orderBy = new BasicDBObject("ctime", -1);

			DBCursor dbCursor = collection.find(query, excludeProjection).sort(orderBy);

			List<Invoice> areaList = new ArrayList<>();

			while (dbCursor.hasNext()) {
				DBObject dbObject = dbCursor.next();

				DBObject custRef = ((DBRef) dbObject.get(KEY_CUSTOMER_XID)).fetch();
				dbObject.put(KEY_CUSTOMER, custRef);

				String jsonString = JSON.serialize(dbObject);
				Invoice invoice = (Invoice) CommonUtil.jsonToObject(jsonString, Invoice.class.getName());
				areaList.add(invoice);
			}

			return areaList;

		} catch (Exception exception) {
			LOG.equals(exception);
		}
		return null;
	}

	public List<Invoice> getAllByField(Map<String, String> map,String sortBy) {
		try {
			DBCollection collection = mongoDB.getCollection(collInvoice);

			DBObject query = new BasicDBObject();
			if (map != null) {
				for (Map.Entry<String, String> entry : map.entrySet()) {
					query.put(entry.getKey(), entry.getValue());
				}
				DBObject checkExists = new BasicDBObject("$exists", false);
				query.put("deleted", checkExists);
			}

			//DBObject excludeProjection = new BasicDBObject("photoDetailsList", 0);
			//excludeProjection.put("frameDetailsList", 0);
			DBObject excludeProjection = new BasicDBObject("frameDetailsList", 0);
			excludeProjection.put("laminationDetailsList", 0);

			DBObject orderBy = new BasicDBObject(sortBy, -1);

			DBCursor dbCursor = collection.find(query, excludeProjection).sort(orderBy);

			List<Invoice> areaList = new ArrayList<>();

			while (dbCursor.hasNext()) {
				DBObject dbObject = dbCursor.next();

				DBObject custRef = ((DBRef) dbObject.get(KEY_CUSTOMER_XID)).fetch();
				dbObject.put(KEY_CUSTOMER, custRef);

				String jsonString = JSON.serialize(dbObject);
				Invoice invoice = (Invoice) CommonUtil.jsonToObject(jsonString, Invoice.class.getName());
				areaList.add(invoice);
			}

			return areaList;

		} catch (Exception exception) {
			LOG.equals(exception);
		}
		return null;
	}
	
	public List<Invoice> getAllByFieldByDate(String startDate,String endDate,String sortBy) {
		try {
			DBCollection collection = mongoDB.getCollection(collInvoice);
			String pattern = "dd-MM-yy";
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
			
			DBObject query = new BasicDBObject("invoiceDate", new BasicDBObject("$lt", simpleDateFormat.parse(endDate).getTime()).append("$gte", simpleDateFormat.parse(startDate).getTime()));
			DBObject checkExists = new BasicDBObject("$exists", false);
			query.put("deleted", checkExists);
		

			//DBObject excludeProjection = new BasicDBObject("photoDetailsList", 0);
			//excludeProjection.put("frameDetailsList", 0);
			DBObject excludeProjection = new BasicDBObject("frameDetailsList", 0);
			excludeProjection.put("laminationDetailsList", 0);

			DBObject orderBy = new BasicDBObject(sortBy, -1);

			DBCursor dbCursor = collection.find(query, excludeProjection).sort(orderBy);

			List<Invoice> areaList = new ArrayList<>();

			while (dbCursor.hasNext()) {
				DBObject dbObject = dbCursor.next();

				DBObject custRef = ((DBRef) dbObject.get(KEY_CUSTOMER_XID)).fetch();
				dbObject.put(KEY_CUSTOMER, custRef);

				String jsonString = JSON.serialize(dbObject);
				Invoice invoice = (Invoice) CommonUtil.jsonToObject(jsonString, Invoice.class.getName());
				areaList.add(invoice);
			}

			return areaList;

		} catch (Exception exception) {
			LOG.equals(exception);
		}
		return null;
	}
}
