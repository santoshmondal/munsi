package com.async.simulations;

import java.io.Serializable;

public class SantoshCommonJson implements Serializable {

	private static final long serialVersionUID = 1L;

	private String id;
	private String text;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String query) {
		this.text = query;
	}

}
