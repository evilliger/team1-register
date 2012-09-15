package com.address.register.models;

import java.util.Date;

import com.google.appengine.api.datastore.*;

public class AddressEntry {
	private Key key;
	
    private String name;
	
    private String email;
	
    private Date date;
    
    public AddressEntry() {
    	super();
    }

    public AddressEntry(Entity e) {
    	super();
		setDate((Date) e.getProperty("date"));
		setEmail((String) e.getProperty("email"));
		setName((String) e.getProperty("name"));
		setKey((Key) e.getKey());
    }

	public Key getKey() {
		return key;
	}

	public void setKey(Key key) {
		this.key = key;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	
}