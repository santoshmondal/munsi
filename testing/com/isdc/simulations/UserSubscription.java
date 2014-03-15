package com.isdc.simulations;

import java.io.Serializable;

import org.codehaus.jackson.map.ObjectMapper;

public class UserSubscription implements Serializable {

	private static final long serialVersionUID = 1L;
	private int id;
	private UserInfo uinfo;
	private SubscribeInfo sinfo;

	public UserSubscription() {
		super();
	}

	public UserSubscription(int id, UserInfo uinfo, SubscribeInfo sinfo) {
		super();
		this.id = id;
		this.uinfo = uinfo;
		this.sinfo = sinfo;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public UserInfo getUinfo() {
		return uinfo;
	}

	public void setUinfo(UserInfo uinfo) {
		this.uinfo = uinfo;
	}

	public SubscribeInfo getSinfo() {
		return sinfo;
	}

	public void setSinfo(SubscribeInfo sinfo) {
		this.sinfo = sinfo;
	}

	/**
	 * DUMMY TEST
	 * @param args
	 */
	public static void main(String args[]) throws Exception {
		UserSubscription obj = new UserSubscription();
		obj.setId(1);
		obj.setUinfo(new UserInfo("user2@abc.com", "99309930"));
		obj.setSinfo(new SubscribeInfo("user topic", "user section", "mumbai"));

		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(obj);

		System.out.println("JSON::" + json);
	}
}
