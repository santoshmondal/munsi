package com.isdc.simulations;

import java.io.IOException;
import java.net.UnknownHostException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.munsi.util.Constants;

public class SantySimulation {
	public static void main(String[] args) throws JsonGenerationException, JsonMappingException, IOException {
		jsonSimulation();
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