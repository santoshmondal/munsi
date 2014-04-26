package com.estudio.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.async.util.CommonUtil;
import com.async.util.Constants.DBCollectionEnum;
import com.async.util.MongoUtil;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

public class EstudioUtil {

	/**
	 * @param collName
	 * @param type - photo | frame | lamination
	 * @param key - size | quality | framno 
	 * @return
	 */
	public static String getJSONArrayFromMaster( String type, String key) {
		
		try{
			DB mongoDB = MongoUtil.getDB();
			
			DBCollection collection = mongoDB.getCollection(DBCollectionEnum.MASTER.toString());
			DBObject query = new BasicDBObject("type", type);
			
			DBObject fetch = new BasicDBObject(key, 1).append("_id", 0);
			
			DBCursor dbCursor = collection.find(query, fetch);
			List<CommonJson> commonJsons = new ArrayList<>();
			while ( dbCursor.hasNext() ) {
				BasicDBObject dbObject = (BasicDBObject) dbCursor.next();
				String value = dbObject.getString(key);
				CommonJson cj = new CommonJson(value,value); 
				commonJsons.add(cj);
			}

				return CommonUtil.objectToJson(commonJsons);
			}catch( Exception exception )
			{
				exception.printStackTrace();
			}

		return "";

		
		
	}
	
	public static void main(String[] args) {
		System.out.println( getJSONArrayFromMaster("photo","size") );
	}
}

class CommonJson implements Serializable{
	 String id;
	 String text;
	 CommonJson(){}
	 
	 CommonJson(String id, String value){
		 this.id = id ;
		 this.text = value;
	 }
	 
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getValue() {
		return text;
	}
	public void setValue(String text) {
		this.text = text;
	}
	 
	 
	 
}