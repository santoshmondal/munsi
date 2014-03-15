package com.isdc.simulations;

public class SubscribeInfo {

	private String topic;
	private String section;
	private String location;

	public SubscribeInfo() {
		super();
	}

	public SubscribeInfo(String topic, String section, String location) {
		super();
		this.topic = topic;
		this.section = section;
		this.location = location;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public String getSection() {
		return section;
	}

	public void setSection(String section) {
		this.section = section;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

}
