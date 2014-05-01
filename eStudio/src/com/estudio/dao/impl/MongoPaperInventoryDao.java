package com.estudio.dao.impl;

import java.util.Date;

import org.apache.log4j.Logger;

import com.async.util.CommonUtil;
import com.async.util.Constants.DBCollectionEnum;
import com.async.util.MongoUtil;
import com.estudio.pojo.inventory.PaperPurchase;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.util.JSON;

public class MongoPaperInventoryDao {

	private static final Logger LOG = Logger.getLogger(MongoMasterDao.class);

	private final String collPaperPurchase = DBCollectionEnum.PAPER_PURCHASE.toString();
	private final DB mongoDB = MongoUtil.getDB();

	// @Override
	public Boolean create(PaperPurchase paperPurchase) {
		try {
			Date date = new Date();
			paperPurchase.setCtime(date);
			paperPurchase.setUtime(date);
			
			DBCollection collection = mongoDB.getCollection(collPaperPurchase);
			Integer id = MongoUtil.getNextSequence(DBCollectionEnum.PAPER_PURCHASE);
			paperPurchase.set_id(id+"");
			String jsonString = CommonUtil.objectToJson(paperPurchase);
			DBObject dbObject = (DBObject) JSON.parse(jsonString);
			collection.insert(dbObject);

			//collPaperInventory
			
		} catch (Exception exception) {
			LOG.equals(exception);
		}
		return false;
	}

	//@Override
	public Boolean update( PaperPurchase paperPurchase ) {
		try {
			Date date = new Date();
			paperPurchase.setUtime(date);

			DBCollection collection = mongoDB.getCollection(collPaperPurchase);
			String jsonString = CommonUtil.objectToJson(paperPurchase);

			DBObject dbObject = (DBObject) JSON.parse(jsonString);
			dbObject.removeField("_id");
			
			DBObject query = new BasicDBObject("_id", paperPurchase.get_id());

			DBObject update = new BasicDBObject("$set", dbObject);

			collection.update(query, update);

		} catch (Exception exception) {
			LOG.equals(exception);
		}
		return false;

	}

	public static void main(String[] args) {
		//MongoPaperInventoryDao new MongoPaperInventoryDao();
		DBCollection collPaperInventory = MongoUtil.getDB().getCollection(DBCollectionEnum.PAPER_INVENTORY.toString());
		//DBObject dbObject = collection.findAndModify(query, update);
		DBObject update2 = new BasicDBObject("quantity",5);
		DBObject update3 = new BasicDBObject("$inc", update2);
		//DBObject update = (DBObject) JSON.parse("{ $inc: { quantity: 1 } }");
		DBObject query = new BasicDBObject("quality","glass");
		
		collPaperInventory.update(query, update3);
	}
}
