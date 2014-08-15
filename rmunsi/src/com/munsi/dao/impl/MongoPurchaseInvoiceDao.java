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
import com.mongodb.DBRef;
import com.mongodb.util.JSON;
import com.munsi.dao.PurchaseInvoiceDao;
import com.munsi.pojo.invoice.purchase.PurchaseInvoice;
import com.munsi.util.CommonUtil;
import com.munsi.util.Constants.DBCollectionEnum;
import com.munsi.util.MongoUtil;

public class MongoPurchaseInvoiceDao implements PurchaseInvoiceDao {
	private static final Logger LOG = Logger.getLogger(MongoPurchaseInvoiceDao.class);

	private String pInvoiceCollection = DBCollectionEnum.PURCHASE_INVOICE.toString();

	// ReferecenCustomer collection, Reference supplier xid, and reference  customer
	private String refSupplierCollection = DBCollectionEnum.MAST_SUPPLIER.toString();
	private static final String KEY_SUPPLIER_XID = "supplierXid";
	private static final String KEY_SUPPLIER = "supplier";

	private DB mongoDB = MongoUtil.getDB();

	@Override
	public Boolean create(PurchaseInvoice pInvoice) {
		Boolean sReturn = false;

		try {

			Date date = new Date();
			pInvoice.setCtime(date);
			pInvoice.setUtime(date);
			String _id = MongoUtil.getNextSequence(DBCollectionEnum.PURCHASE_INVOICE).toString();
			pInvoice.set_id(_id);

			DBCollection collection = mongoDB.getCollection(pInvoiceCollection);
			String jsonString = CommonUtil.objectToJson(pInvoice);

			DBObject dbObject = (DBObject) JSON.parse(jsonString);

			// HANDLING Supplier reference
			DBRef refCustomrObject = new DBRef(mongoDB, refSupplierCollection, pInvoice.getSupplier().get_id());
			dbObject.put(KEY_SUPPLIER_XID, refCustomrObject);
			dbObject.removeField(KEY_SUPPLIER);

			collection.insert(dbObject);

			sReturn = true;
		} catch (Exception e) {
			LOG.error(e);
		}

		return sReturn;
	}

	@Override
	public Boolean update(PurchaseInvoice pInvoice) {
		Boolean sReturn = false;

		try {

			Date date = new Date();
			pInvoice.setUtime(date);

			DBCollection collection = mongoDB.getCollection(pInvoiceCollection);
			String jsonString = CommonUtil.objectToJson(pInvoice);

			DBObject dbObject = (DBObject) JSON.parse(jsonString);
			dbObject.removeField("_id");

			// Handling supplier reference
			if (pInvoice.getSupplier() != null) {
				DBRef refCustomrObject = new DBRef(mongoDB, refSupplierCollection, pInvoice.getSupplier().get_id());
				dbObject.put(KEY_SUPPLIER_XID, refCustomrObject);
				dbObject.removeField(KEY_SUPPLIER);
			}

			DBObject query = new BasicDBObject("_id", pInvoice.get_id());
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
			PurchaseInvoice pInvoice = new PurchaseInvoice();
			pInvoice.set_id(_id);
			pInvoice.setDeleted(true);
			pInvoice.setUtime(new Date());
			update(pInvoice);

			sReturn = true;
		} catch (Exception e) {
			LOG.error(e);
		}

		return sReturn;
	}

	@Override
	public PurchaseInvoice get(String _id) {
		PurchaseInvoice pInvoice = null;
		try {
			pInvoice = get(_id, false);
		} catch (Exception e) {
			LOG.error(e);
		}

		return pInvoice;
	}

	@Override
	public PurchaseInvoice get(String _id, Boolean withReferences) {
		PurchaseInvoice pInvoice = null;
		try {
			DBObject queryObject = new BasicDBObject("_id", _id);
			pInvoice = getSalesInvoiceByQuery(queryObject, withReferences);
		} catch (Exception e) {
			LOG.error(e);
		}

		return pInvoice;
	}

	@Override
	public List<PurchaseInvoice> getAll(Boolean withReferences) {
		List<PurchaseInvoice> pInvoiceList = new ArrayList<PurchaseInvoice>();
		try {
			DBCollection collection = mongoDB.getCollection(pInvoiceCollection);
			DBObject deletedQuery = MongoUtil.getQueryToCheckDeleted();
			DBCursor dbCursor = collection.find(deletedQuery);

			while (dbCursor.hasNext()) {
				DBObject dbObject = dbCursor.next();

				if (withReferences == true) {
					// Handle customer reference
					DBObject refCustomrObject = ((DBRef) dbObject.get(KEY_SUPPLIER_XID)).fetch();
					dbObject.put(KEY_SUPPLIER, refCustomrObject);
				}

				// supplier ref continues.
				dbObject.removeField(KEY_SUPPLIER_XID);

				String jsonString = JSON.serialize(dbObject);
				PurchaseInvoice pInvoice = (PurchaseInvoice) CommonUtil.jsonToObject(jsonString, PurchaseInvoice.class.getName());
				pInvoiceList.add(pInvoice);
			}

		} catch (Exception e) {
			LOG.error(e);
		}

		return pInvoiceList;
	}

	private PurchaseInvoice getSalesInvoiceByQuery(DBObject queryObject, Boolean withReferences) {
		PurchaseInvoice pInvoice = null;
		try {
			DBCollection collection = mongoDB.getCollection(pInvoiceCollection);
			DBObject dbObject = collection.findOne(queryObject);

			if (withReferences == true) {
				// Handle supplier reference
				DBObject refCustomrObject = ((DBRef) dbObject.get(KEY_SUPPLIER_XID)).fetch();
				dbObject.put(KEY_SUPPLIER, refCustomrObject);
			}

			// supplier ref continues.
			dbObject.removeField(KEY_SUPPLIER_XID);

			String jsonString = JSON.serialize(dbObject);
			pInvoice = (PurchaseInvoice) CommonUtil.jsonToObject(jsonString, PurchaseInvoice.class.getName());

		} catch (Exception exception) {
			LOG.equals(exception);
		}

		return pInvoice;
	}
}
