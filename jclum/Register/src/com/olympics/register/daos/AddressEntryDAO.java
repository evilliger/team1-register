package com.olympics.register.daos;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.Query;
import com.olympics.register.models.AddressEntry;

public class AddressEntryDAO {
	
	public static final String modelType = "AddressBook";
	public static final String modalName = "RegisterAB";
	public static final String modalEntityName = "AddressEntry";

	DatastoreService ds;

	public AddressEntryDAO() {
		this.ds = DatastoreServiceFactory.getDatastoreService();;
	}

	public List<AddressEntry> LoadAll() {

		Key addressBookKey = KeyFactory.createKey(modelType, modalName);

		Query query = new Query(modalEntityName, addressBookKey).setAncestor(addressBookKey).addSort("date", Query.SortDirection.DESCENDING);

		List<Entity> tmpresults = ds.prepare(query).asList(
				FetchOptions.Builder.withLimit(10));

		List<AddressEntry> results = new ArrayList<AddressEntry>();
		
		for (Entity e : tmpresults) {
			AddressEntry ae = new AddressEntry();
			Map<String, Object> tmp = e.getProperties();
			ae.setName((String) tmp.get("name"));
			ae.setEmail((String) tmp.get("email"));
			ae.setDate((Date) tmp.get("date"));
			ae.setKey(e.getKey());
			results.add(ae);
		}

		return results;
	}

	public void Update(AddressEntry ae) {
		Entity e = new Entity(ae.getKey());
		e.setProperty("name", ae.getName());
		e.setProperty("email", ae.getEmail());
		e.setProperty("date", ae.getDate());

		ds.put(e);
	}

	public AddressEntry Update(String key) {

		Entity e = null;

		try {
			e = ds.get(KeyFactory.stringToKey(key));
		} catch (EntityNotFoundException exception) {
			exception.printStackTrace();
		}
		
		AddressEntry ae = new AddressEntry();
		ae.setKey(e.getKey());
		ae.setName((String)e.getProperty("name"));
		ae.setEmail((String)e.getProperty("email"));
		ae.setDate((Date)e.getProperty("date"));
		
		return ae;
	}

	public void Create(AddressEntry ae) {

		Key addressBookKey = KeyFactory.createKey(modelType, modalName);
		Date date = new Date();

		Entity e = new Entity(modalEntityName, addressBookKey);
		e.setProperty("name", ae.getName());
		e.setProperty("email", ae.getEmail());
		e.setProperty("date", date);

		ds.put(e);
	}

	public void Delete(AddressEntry ae) {
		ds.delete(ae.getKey());
	}

	public AddressEntry Read(String key) {
		
		Key aeKey = KeyFactory.createKey("Register", "AE");
		Query query = new Query("people", aeKey).setAncestor(aeKey).addSort("date",
				Query.SortDirection.DESCENDING);
		
		//query.addFilter("key", FilterOperator.EQUAL,key);

		List<Entity> tmpresults = ds.prepare(query).asList(
				FetchOptions.Builder.withLimit(1000));

		AddressEntry ae = new AddressEntry();
		Map<String, Object> tmp = tmpresults.get(0).getProperties();
		ae.setName((String) tmp.get("name"));
		ae.setEmail((String) tmp.get("email"));
		ae.setDate((Date) tmp.get("date"));
		ae.setKey(tmpresults.get(0).getKey());
	
		
		return ae;
	}

	public void Close() {
	}

}
