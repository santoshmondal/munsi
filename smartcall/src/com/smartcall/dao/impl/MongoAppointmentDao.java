package com.smartcall.dao.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.async.util.CommonUtil;
import com.async.util.Constants.DBCollectionEnum;
import com.async.util.Constants.UIOperationsEnum;
import com.async.util.MongoUtil;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.DBRef;
import com.mongodb.WriteResult;
import com.mongodb.util.JSON;
import com.smartcall.dao.AppointmentDao;
import com.smartcall.pojo.Appointment;

public class MongoAppointmentDao implements AppointmentDao {
	private static final Logger LOG = Logger.getLogger( MongoAppointmentDao.class );
	private DB mongoDB = MongoUtil.getDB();
	private String collAppointment = DBCollectionEnum.APPOINTMENT.toString();
	private String collCustomerDetails = DBCollectionEnum.CUSTOMER_DETAILS.toString();
	
	public static final String KEY_CUSTOMER_DETAILS_XID = "customerDetailsXid";
	public static final String KEY_CUSTOMER_DETAILS = "customerDetails";
	
	@Override
	public Boolean create(Appointment appointment){
		try{
			Date date = new Date();
			appointment.setCtime( date );
			appointment.setUtime( date );
			Integer _id = MongoUtil.getNextSequence(DBCollectionEnum.APPOINTMENT);
			appointment.set_id( _id+"" );
			DBCollection collection = mongoDB.getCollection( collAppointment );
			String jsonString = CommonUtil.objectToJson( appointment );
			
			DBObject dbObject = (DBObject) JSON.parse( jsonString );
			dbObject.removeField(KEY_CUSTOMER_DETAILS);
			DBRef custDetailsRef = new DBRef(mongoDB, collCustomerDetails, appointment.getCustomerDetails().get_id());
			dbObject.put(KEY_CUSTOMER_DETAILS_XID, custDetailsRef);
			
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
	public Boolean update(Appointment appointment) {
		try{
			Date date = new Date();
			appointment.setUtime( date );
			
			DBCollection collection = mongoDB.getCollection( collAppointment );
			String jsonString = CommonUtil.objectToJson(appointment);
			
			DBObject dbObject = (DBObject) JSON.parse( jsonString );
			dbObject.removeField("_id");
			dbObject.removeField(KEY_CUSTOMER_DETAILS);
			
			if( appointment.getCustomerDetails() != null ){
				DBRef custDetailsRef = new DBRef(mongoDB, collCustomerDetails, appointment.getCustomerDetails().get_id());
				dbObject.put(KEY_CUSTOMER_DETAILS_XID, custDetailsRef);
			}
			
			DBObject updateObj = new BasicDBObject("$set", dbObject);
			
			DBObject query = new BasicDBObject("_id", appointment.get_id());
			
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
			DBCollection collection = mongoDB.getCollection( collAppointment );
			
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
	public Boolean updateStatus(String _id, UIOperationsEnum pUIOperationsEnum) {
		try{
			DBCollection collection = mongoDB.getCollection( collAppointment );
			
			DBObject query = new BasicDBObject("_id", _id);
			DBObject update = new BasicDBObject("status", pUIOperationsEnum.toString() )
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
	
	// ==================== ========================//
	@Override
	public List<Appointment> getAll() {
		try{
			DBCollection collection = mongoDB.getCollection( collCustomerDetails );
			DBObject finalQuery = MongoUtil.getQueryToCheckDeleted();
			DBCursor dbCursor = collection.find( finalQuery);
			
			List<Appointment> appointmentList = new ArrayList<>();
			
			while ( dbCursor.hasNext() ) {
				DBObject dbObject = dbCursor.next();
				
				DBObject custDetailsRef =  ( (DBRef)dbObject.get(KEY_CUSTOMER_DETAILS_XID) ).fetch();
				
				dbObject.put(KEY_CUSTOMER_DETAILS, custDetailsRef);
				
				String jsonString = JSON.serialize(dbObject);
				Appointment appointment = (Appointment) CommonUtil.jsonToObject( jsonString, Appointment.class.getName() );
				appointmentList.add(appointment);
				
			}
			
			Collections.sort(appointmentList, new Comparator<Appointment>() {
				public int compare(Appointment o1, Appointment o2) {
					Date date1 = o1.getAppimentDate();
					Date date2 = o2.getAppimentDate();
					return date1.compareTo(date2);
				}
			});
			
			return appointmentList;
			
		}catch( Exception exception ){
			LOG.error("",exception);
			//exception.printStackTrace();
		}
		return null;
	}


	@Override
	public Appointment get(String _id) {
		try{
			DBCollection collection = mongoDB.getCollection( collAppointment );
			DBObject query = new BasicDBObject("_id", _id);
			DBObject dbObject = collection.findOne(query);
			
			DBObject custDetailsRef =  ( (DBRef)dbObject.get(KEY_CUSTOMER_DETAILS_XID) ).fetch();
			
			dbObject.put(KEY_CUSTOMER_DETAILS, custDetailsRef);
			
			String jsonString = JSON.serialize(dbObject);
			Appointment appointment = (Appointment) CommonUtil.jsonToObject( jsonString, Appointment.class.getName() );
			
			return appointment;
			
		}catch( Exception exception ){
			LOG.error("",exception);
			//exception.printStackTrace();
		}
		return null;
	}
	

}
