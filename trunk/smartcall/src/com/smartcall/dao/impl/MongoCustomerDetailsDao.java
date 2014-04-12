package com.smartcall.dao.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;

import com.async.util.CommonUtil;
import com.async.util.Constants.DBCollectionEnum;
import com.async.util.MongoUtil;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.DBRef;
import com.mongodb.QueryOperators;
import com.mongodb.WriteResult;
import com.mongodb.util.JSON;
import com.smartcall.dao.CustomerDetailsDao;
import com.smartcall.pojo.CustomerDetails;
import com.smartcall.pojo.Service;

public class MongoCustomerDetailsDao implements CustomerDetailsDao {
	private static final Logger LOG = Logger.getLogger( MongoCustomerDetailsDao.class );
	private DB mongoDB = MongoUtil.getDB();
	private String collCustomerDetails = DBCollectionEnum.CUSTOMER_DETAILS.toString();
	private String collAccessUser = DBCollectionEnum.ACCESS_USER.toString();
	
	public static final String KEY_ASSIGNED_CALLER_XID = "assignedCallerXid";
	public static final String KEY_ASSIGNED_CALLER = "assignedCaller";
	
	public static final String KEY_LASTCONTACTEDBY_XID = "lastContactedByXid";
	public static final String KEY_LASTCONTACTED_BY = "lastContactedBy";
	
	@Override
	public Boolean create(CustomerDetails customerDetails){
		try{
			Date date = new Date();
			customerDetails.setCtime( date );
			customerDetails.setUtime( date );
			String _id = customerDetails.getVehicleInfo().getVin();
			if( CommonUtil.isEmptyString(_id) ){
				throw new Exception("Vehicle VIN is empty");
			}
			customerDetails.set_id( _id );
			DBCollection collection = mongoDB.getCollection( collCustomerDetails );
			String jsonString = CommonUtil.objectToJson(customerDetails);
			
			DBObject dbObject = (DBObject) JSON.parse( jsonString );
			dbObject.removeField(KEY_ASSIGNED_CALLER);
			dbObject.removeField(KEY_LASTCONTACTED_BY);
			
			WriteResult writeResult = collection.insert(dbObject );
			
			if ( writeResult.getN() > 0 ){
				return true;
			}
			
		}catch( Exception exception ){
			LOG.error("",exception);
		}
		return false;
	}

	@Override
	public Boolean update(CustomerDetails customerDetails) {
		try{
			Date date = new Date();
			customerDetails.setUtime( date );
			
			DBCollection collection = mongoDB.getCollection( collCustomerDetails );
			String jsonString = CommonUtil.objectToJson(customerDetails);
			
			DBObject dbObject = (DBObject) JSON.parse( jsonString );
			dbObject.removeField("_id");
			dbObject.removeField(KEY_ASSIGNED_CALLER);
			dbObject.removeField(KEY_LASTCONTACTED_BY);
			
			if( customerDetails.getLastContactedBy() != null ){
				DBRef accesssUserRef = new DBRef(mongoDB, collAccessUser, customerDetails.getLastContactedBy().get_id());
				dbObject.put(KEY_LASTCONTACTEDBY_XID, accesssUserRef);
			}
			if( customerDetails.getAssignedCaller() != null ){
				DBRef accesssUserRef = new DBRef(mongoDB, collAccessUser, customerDetails.getAssignedCaller().get_id());
				dbObject.put(KEY_ASSIGNED_CALLER_XID, accesssUserRef);
			}
			
			DBObject updateObj = new BasicDBObject("$set", dbObject);
			
			DBObject query = new BasicDBObject("_id", customerDetails.get_id());
			
			WriteResult writeResult = collection.update(query, updateObj);
			
			if ( writeResult.getN() > 0 ){
				return Boolean.TRUE;
			}
			
		}catch( Exception exception ){
			LOG.error("",exception);
			//exception.printStackTrace();
		}
		return Boolean.FALSE;
	}

	@Override
	public Boolean delete(String _id) {
		try{
			DBCollection collection = mongoDB.getCollection( collCustomerDetails );
			
			DBObject query = new BasicDBObject("_id", _id);
			DBObject update = new BasicDBObject("deleted", true)
							.append("utime", new Date());
			
			DBObject updateObj = new BasicDBObject("$set", update);
			WriteResult writeResult = collection.update(query, updateObj);
			
			if ( writeResult.getN() > 0 ){
				return true;
			}
		}catch( Exception exception ){
			LOG.equals(exception);
		}
		return false;
		
	}
	
	@Override
	public Boolean assignCaller(Set<String> vinList, String coller_xid ) {
		try{
			DBCollection collection = mongoDB.getCollection( collCustomerDetails );
			
			DBRef accesssUserRef = new DBRef(mongoDB, collAccessUser, coller_xid);
			DBObject dbObject = new BasicDBObject(KEY_ASSIGNED_CALLER_XID, accesssUserRef );
			DBObject updateObj = new BasicDBObject("$set", dbObject);
			
			BasicDBList list = new BasicDBList();
			list.addAll(vinList);
			DBObject query = new BasicDBObject(QueryOperators.IN, list);
			
			WriteResult writeResult = collection.update(query, updateObj, false, true);
			
			if ( writeResult.getN() > 0 ){
				return Boolean.TRUE;
			}
			
		}catch( Exception exception ){
			LOG.error("",exception);
			//exception.printStackTrace();
		}
		return Boolean.FALSE;
	}
	
	// ==================== ========================//
	@Override
	public List<CustomerDetails> getAll() {
		try{
			DBCollection collection = mongoDB.getCollection( collCustomerDetails );
			DBObject finalQuery = MongoUtil.getQueryToCheckDeleted();
			DBCursor dbCursor = collection.find( finalQuery);
			
			List<CustomerDetails> customerDetailsList = new ArrayList<>();
			
			while ( dbCursor.hasNext() ) {
				DBObject dbObject = dbCursor.next();
				
				DBObject callerRef =  ( (DBRef)dbObject.get(KEY_ASSIGNED_CALLER_XID) ).fetch();
				DBObject contactedByRef =  ( (DBRef)dbObject.get(KEY_LASTCONTACTEDBY_XID) ).fetch();
				
				dbObject.put(KEY_ASSIGNED_CALLER, callerRef);
				dbObject.put(KEY_LASTCONTACTED_BY, contactedByRef);
				
				String jsonString = JSON.serialize(dbObject);
				CustomerDetails customerDetails = (CustomerDetails) CommonUtil.jsonToObject( jsonString, CustomerDetails.class.getName() );
				customerDetailsList.add(customerDetails);
				
			}
			
			Collections.sort(customerDetailsList, new Comparator<CustomerDetails>() {
				public int compare(CustomerDetails o1, CustomerDetails o2) {
					Date date1 = o1.getLastServiceDate();
					Date date2 = o2.getLastServiceDate();
					return date1.compareTo(date2);
				}
			});
			
			return customerDetailsList;
			
		}catch( Exception exception ){
			LOG.error("",exception);
			//exception.printStackTrace();
		}
		return null;
	}


	@Override
	public CustomerDetails get(String _id) {
		try{
			DBCollection collection = mongoDB.getCollection( collCustomerDetails );
			DBObject query = new BasicDBObject("_id", _id);
			DBObject dbObject = collection.findOne(query);
			
			DBObject callerRef =  ( (DBRef)dbObject.get(KEY_ASSIGNED_CALLER_XID) ).fetch();
			DBObject contactedByRef =  ( (DBRef)dbObject.get(KEY_LASTCONTACTEDBY_XID) ).fetch();
			
			dbObject.put(KEY_ASSIGNED_CALLER, callerRef);
			dbObject.put(KEY_LASTCONTACTED_BY, contactedByRef);
			
			String jsonString = JSON.serialize(dbObject);
			CustomerDetails customerDetails = (CustomerDetails) CommonUtil.jsonToObject( jsonString, CustomerDetails.class.getName() );
			
			return customerDetails;
			
		}catch( Exception exception ){
			LOG.error("",exception);
			//exception.printStackTrace();
		}
		return null;
	}
	

	@Override
	public Boolean addService(String _id, Service service ) {
		try{
			DBCollection collection = mongoDB.getCollection( collCustomerDetails );
			
			String jsonString = CommonUtil.objectToJson(service);
			
			DBObject dbObjectService = (DBObject) JSON.parse( jsonString );
			
			DBObject dbObjServiceList = new BasicDBObject("serviceList", dbObjectService );
			
			DBObject updateDate = new BasicDBObject( "utime", new Date() )
			.append("lastServiceDate", service.getServiceDate() );
			
			DBObject updateQuery = new BasicDBObject("$set",updateDate)
										.append("$push", dbObjServiceList);
			
			DBObject query = new BasicDBObject("_id", _id);
			
			WriteResult writeResult = collection.update(query, updateQuery);
			
			if ( writeResult.getN() > 0 ){
				return Boolean.TRUE;
			}

			
		}catch( Exception exception ){
			LOG.error("",exception);
			//exception.printStackTrace();
		}
		return Boolean.FALSE;
	}

	
	@Override
	public Boolean updateRating(String _id, Integer rating ) {
		try{
			
			DBCollection collection = mongoDB.getCollection( collCustomerDetails );
			
			DBObject dbObjRating = new BasicDBObject("rating", rating ).append( "utime", new Date());
			
			DBObject updateQuery = new BasicDBObject("$set", dbObjRating);
			
			DBObject query = new BasicDBObject("_id", _id);
			
			WriteResult writeResult = collection.update(query, updateQuery);
			
			if ( writeResult.getN() > 0 ){
				return Boolean.TRUE;
			}

			
		}catch( Exception exception ){
			LOG.error("",exception);
			//exception.printStackTrace();
		}
		return Boolean.FALSE;
	}

	@Override
	public Boolean updateLastCalling( String _id, String lastContactedByXID, String lastCallResponse, String remark ) {
		
		try{
			Date date = new Date();
			DBCollection collection = mongoDB.getCollection( collCustomerDetails );
			
			DBRef accesssUserRef = new DBRef(mongoDB, collAccessUser, lastContactedByXID);
			
			DBObject dbObject = new BasicDBObject("lastCallResponse", lastCallResponse )
									.append("remark", remark)
									.append(KEY_LASTCONTACTEDBY_XID, accesssUserRef)
									.append("lastCallingDate", date)
									.append( "utime", date);
			
			DBObject updateQuery = new BasicDBObject("$set", dbObject);
			
			DBObject query = new BasicDBObject("_id", _id);
			
			WriteResult writeResult = collection.update(query, updateQuery);
			
			if ( writeResult.getN() > 0 ){
				return Boolean.TRUE;
			}

			
		}catch( Exception exception ){
			LOG.error("",exception);
			//exception.printStackTrace();
		}
		return Boolean.FALSE;
	}	
	
	public static void main(String[] args) {
		String id = "MDHFBUK13AA004425";
		MongoCustomerDetailsDao obj = new MongoCustomerDetailsDao();
		obj.updateLastCalling(id, null, "Ringing", "Hellooooo");
	}
	
}
