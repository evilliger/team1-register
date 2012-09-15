package com.olympics.register.models;

import java.util.Date;

import com.google.appengine.api.datastore.Key;

public class AddressEntry {
	
	private Key key;

    private String name;
	
    private String email;
	
    private Date date;

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

	public AddressEntry() {
		super();
	}

	
}