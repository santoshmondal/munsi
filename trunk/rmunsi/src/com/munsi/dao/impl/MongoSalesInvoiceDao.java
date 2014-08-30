package com.munsi.dao.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.DBRef;
import com.mongodb.QueryOperators;
import com.mongodb.util.JSON;
import com.munsi.dao.SalesInvoiceDao;
import com.munsi.pojo.invoice.sales.SalesInvoice;
import com.munsi.util.CommonUtil;
import com.munsi.util.Constants.DBCollectionEnum;
import com.munsi.util.MongoUtil;

public class MongoSalesInvoiceDao implements SalesInvoiceDao {
	private static final Logger LOG = Logger.getLogger(MongoSalesInvoiceDao.class);

	private String sInvoiceCollection = DBCollectionEnum.SALES_INVOICE.toString();

	// ReferecenCustomer collection, Reference customer xid, and reference  customer
	private String refCustomerCollection = DBCollectionEnum.MAST_CUSTOMER.toString();
	private static final String KEY_CUSTOMER_XID = "customerXid";
	private static final String KEY_CUSTOMER = "customer";

	private DB mongoDB = MongoUtil.getDB();

	@Override
	public Boolean create(SalesInvoice sInvoice) {
		Boolean sReturn = false;
		try {

			Date date = new Date();
			sInvoice.setCtime(date);
			sInvoice.setUtime(date);
			String _id = MongoUtil.getNextSequence(DBCollectionEnum.SALES_INVOICE).toString();
			sInvoice.set_id(_id);
			sInvoice.setInvoiceNumber(CommonUtil.getFormatedDate(CommonUtil.DATE_FORMAT_YY_MM_DD) + _id);

			DBCollection collection = mongoDB.getCollection(sInvoiceCollection);
			String jsonString = CommonUtil.objectToJson(sInvoice);

			DBObject dbObject = (DBObject) JSON.parse(jsonString);

			// HANDLING Customer reference
			DBRef refCustomrObject = new DBRef(mongoDB, refCustomerCollection, sInvoice.getCustomer().get_id());
			dbObject.put(KEY_CUSTOMER_XID, refCustomrObject);
			dbObject.removeField(KEY_CUSTOMER);

			collection.insert(dbObject);

			sReturn = true;
		} catch (Exception e) {
			LOG.error(e);
		}

		return sReturn;
	}

	@Override
	public Boolean update(SalesInvoice sInvoice) {
		Boolean sReturn = false;
		try {
			Date date = new Date();
			sInvoice.setUtime(date);

			DBCollection collection = mongoDB.getCollection(sInvoiceCollection);
			String jsonString = CommonUtil.objectToJson(sInvoice);

			DBObject dbObject = (DBObject) JSON.parse(jsonString);
			dbObject.removeField("_id");

			// Handling customer reference
			if (sInvoice.getCustomer() != null) {
				DBRef refCustomrObject = new DBRef(mongoDB, refCustomerCollection, sInvoice.getCustomer().get_id());
				dbObject.put(KEY_CUSTOMER_XID, refCustomrObject);
				dbObject.removeField(KEY_CUSTOMER);
			}

			DBObject query = new BasicDBObject("_id", sInvoice.get_id());
			DBObject updateObj = new BasicDBObject("$set", dbObject);

			collection.update(query, updateObj);
			sReturn = true;
		} catch (Exception e) {
			LOG.error(e);
		}

		return sReturn;
	}

	@Override
	public Boolean delete(String _id) {
		Boolean sReturn = false;
		try {
			SalesInvoice sInvoice = new SalesInvoice();
			sInvoice.set_id(_id);
			sInvoice.setDeleted(true);
			sInvoice.setUtime(new Date());
			update(sInvoice);

			sReturn = true;
		} catch (Exception e) {
			LOG.error(e);
		}

		return sReturn;
	}

	@Override
	public SalesInvoice get(String _id) {
		SalesInvoice sInvoice = null;
		try {
			sInvoice = get(_id, true);
		} catch (Exception e) {
			LOG.error(e);
		}

		return sInvoice;
	}

	@Override
	public SalesInvoice get(String _id, Boolean withReferences) {
		SalesInvoice sInvoice = null;
		try {
			DBObject queryObject = new BasicDBObject("_id", _id);
			sInvoice = getSalesInvoiceByQuery(queryObject, withReferences);
			sInvoice.setSctime(CommonUtil.longToStringDate(sInvoice.getCtime().getTime()));
			sInvoice.setSutime(CommonUtil.longToStringDate(sInvoice.getUtime().getTime()));
		} catch (Exception e) {
			LOG.error(e);
		}

		return sInvoice;
	}

	@Override
	public List<SalesInvoice> getAll(Boolean withReferences) {
		List<SalesInvoice> sInvoiceList = new ArrayList<SalesInvoice>();
		try {
			DBCollection collection = mongoDB.getCollection(sInvoiceCollection);
			DBObject deletedQuery = MongoUtil.getQueryToCheckDeleted();
			DBCursor dbCursor = collection.find(deletedQuery);

			while (dbCursor.hasNext()) {
				DBObject dbObject = dbCursor.next();

				if (withReferences == true) {
					// Handle customer reference
					DBObject refCustomrObject = ((DBRef) dbObject.get(KEY_CUSTOMER_XID)).fetch();
					dbObject.put(KEY_CUSTOMER, refCustomrObject);
				}

				// customer ref continues.
				dbObject.removeField(KEY_CUSTOMER_XID);

				String jsonString = JSON.serialize(dbObject);
				SalesInvoice sInvoice = (SalesInvoice) CommonUtil.jsonToObject(jsonString, SalesInvoice.class.getName());
				sInvoiceList.add(sInvoice);
			}

		} catch (Exception e) {
			LOG.error(e);
		}

		return sInvoiceList;
	}

	private SalesInvoice getSalesInvoiceByQuery(DBObject queryObject, Boolean withReferences) {
		SalesInvoice sInvoice = null;
		try {
			DBCollection collection = mongoDB.getCollection(sInvoiceCollection);
			DBObject dbObject = collection.findOne(queryObject);

			if (withReferences == true) {
				// Handle customer reference
				DBObject refCustomrObject = ((DBRef) dbObject.get(KEY_CUSTOMER_XID)).fetch();
				dbObject.put(KEY_CUSTOMER, refCustomrObject);
			}

			// customer ref continues.
			dbObject.removeField(KEY_CUSTOMER_XID);

			String jsonString = JSON.serialize(dbObject);
			sInvoice = (SalesInvoice) CommonUtil.jsonToObject(jsonString, SalesInvoice.class.getName());

		} catch (Exception exception) {
			LOG.equals(exception);
		}

		return sInvoice;
	}

	@Override
	public List<SalesInvoice> getAllByDate(String startDate, String endDate) {
		List<SalesInvoice> sInvoiceList = new ArrayList<SalesInvoice>();
		try {
			DBCollection collection = mongoDB.getCollection(sInvoiceCollection);

			DBObject deletedQuery = MongoUtil.getQueryToCheckDeleted();

			String pattern = "dd-MM-yy";
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

			DBObject dateQuery = new BasicDBObject("invoiceDate", new BasicDBObject("$lt", simpleDateFormat.parse(endDate).getTime()).append("$gte", simpleDateFormat.parse(startDate).getTime()));

			BasicDBList queryList = new BasicDBList();
			queryList.add(deletedQuery);
			//queryList.add(dateQuery);

			DBObject finalQuery = new BasicDBObject(QueryOperators.AND, queryList);

			DBCursor dbCursor = collection.find(finalQuery);

			while (dbCursor.hasNext()) {
				DBObject dbObject = dbCursor.next();

				// Handle customer reference
				DBObject refCustomrObject = ((DBRef) dbObject.get(KEY_CUSTOMER_XID)).fetch();
				dbObject.put(KEY_CUSTOMER, refCustomrObject);
				// customer ref continues.
				dbObject.removeField(KEY_CUSTOMER_XID);

				String jsonString = JSON.serialize(dbObject);
				SalesInvoice sInvoice = (SalesInvoice) CommonUtil.jsonToObject(jsonString, SalesInvoice.class.getName());
				sInvoiceList.add(sInvoice);
			}

		} catch (Exception e) {
			LOG.error(e);
		}

		return sInvoiceList;
	}

}
