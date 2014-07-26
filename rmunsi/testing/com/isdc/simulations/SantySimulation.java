package com.isdc.simulations;

import java.io.IOException;
import java.io.Serializable;
import java.net.UnknownHostException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.DBRef;
import com.mongodb.MongoClient;
import com.mongodb.util.JSON;
import com.munsi.util.CommonUtil;
import com.munsi.util.Constants;

public class SantySimulation {
	public static void main(String[] args) throws Exception {
		jsonIgnoreNull();
	}

	public static void jsonIgnoreNull() {
		SUser bean = new SUser();
		bean.set_id(1234l);
		
		System.out.println(CommonUtil.objectToJson(bean));
	}
	
	public static void insert() throws UnknownHostException {
		MongoClient mongoClient = new MongoClient(Constants.DB_HOST, Constants.DB_PORT);
		DB db = mongoClient.getDB(Constants.DB_NAME);

		DBCollection collection = db.getCollection("user");

		Muser user = new Muser();
		user.set_id(2);
		user.setName("hsv");
		collection.insert(user);
	}

	public static void jsonSimulation() throws JsonGenerationException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		JsonBean jBean = new JsonBean();
		jBean.set_id(2);
		jBean.setName("hsv");

		String jsonStr = mapper.writeValueAsString(jBean);
		System.out.println(jsonStr);

		jsonStr = "{\"_id\":2,\"name\":\"hsv\", \"temp\":333}";
		JsonBean readValue = mapper.readValue(jsonStr, JsonBean.class);
		System.out.println(readValue);
	}

	public static void mongoReferenceInsertSimulation() throws Exception {

		MongoClient mongoClient = new MongoClient(Constants.DB_HOST, Constants.DB_PORT);
		DB db = mongoClient.getDB(Constants.DB_NAME);

		ObjectMapper jsonMapper = new ObjectMapper();

		SAddress addr = new SAddress();
		addr.set_id(100l);
		addr.setAddress("Kharghar, Sector-3.");
		String jsonAddr = jsonMapper.writeValueAsString(addr);
		DBObject mbAddr = (DBObject) JSON.parse(jsonAddr);
		try {
			DBCollection collection = db.getCollection("santy_address");
			collection.insert(mbAddr);
		} catch (Exception e) {
			e.printStackTrace();
		}

		SUser sUser = new SUser();
		sUser.set_id(1l);
		sUser.setName("raj here!!");
		String jsonUser = jsonMapper.writeValueAsString(sUser);

		DBObject mdUser = (DBObject) JSON.parse(jsonUser);
		DBRef xAddr = new DBRef(db, "santy_address", 100l);
		mdUser.put("xAddressId", xAddr);

		try {
			DBCollection collection = db.getCollection("santy_user");
			collection.insert(mdUser);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void mongoReferenceFetchSimulation() throws Exception {
		MongoClient mongoClient = new MongoClient(Constants.DB_HOST, Constants.DB_PORT);
		DB db = mongoClient.getDB(Constants.DB_NAME);

		DBCollection collection = db.getCollection("santy_user");
		DBObject dbUser = collection.findOne(1l);

		DBRef addrRef = (DBRef) dbUser.get("xAddressId");
		DBObject mdAddr = addrRef.fetch();
		dbUser.put("address", mdAddr);
		dbUser.removeField("xAddressId");

		String json = JSON.serialize(dbUser);
		SUser joUser = (SUser) CommonUtil.jsonToObject(json, SUser.class.getName());
		System.out.println(joUser);
	}
}

@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class SUser implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long _id;
	private String name;
	private Long xAddressId;
	private SAddress address;

	public long get_id() {
		return _id;
	}

	public void set_id(long _id) {
		this._id = _id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getxAddressId() {
		return xAddressId;
	}

	public void setxAddressId(Long xAddressId) {
		this.xAddressId = xAddressId;
	}

	public SAddress getAddress() {
		return address;
	}

	public void setAddress(SAddress address) {
		this.address = address;
	}

}

@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class SAddress implements Serializable {
	private static final long serialVersionUID = 1L;
	private long _id;
	private String address;

	public long get_id() {
		return _id;
	}

	public void set_id(long _id) {
		this._id = _id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}

@JsonIgnoreProperties(ignoreUnknown = true)
class JsonBean {
	private int _id;
	private String name;

	public int get_id() {
		return _id;
	}

	public void set_id(int _id) {
		this._id = _id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}

class Muser extends BasicDBObject {
	private static final long serialVersionUID = 1L;
	private int _id;
	private String name;

	public int get_id() {
		return _id;
	}

	public void set_id(int _id) {
		this._id = _id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}