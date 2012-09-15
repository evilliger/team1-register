package com.address.register.daos;

import java.util.ArrayList;
import java.util.List;

import com.address.register.models.AddressEntry;

import com.google.appengine.api.datastore.*;

public class AddressEntryDAO {
	
	private static String entityTypeName = "AddressBook";
	private static String addressBookName = "MyAddressBook";
	private static String itemTypeName = "AddressEntry";
	
	public List<AddressEntry> LoadAll() {
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		Key addressBookKey = KeyFactory.createKey(entityTypeName, addressBookName);
		
		Query query = new Query(itemTypeName, addressBookKey)
        	.setAncestor(addressBookKey)
        	.addSort("date", Query.SortDirection.DESCENDING);
		List<Entity> entityResults = datastore.prepare(query).asList(FetchOptions.Builder.withLimit(10));
		List<AddressEntry> results = new ArrayList<AddressEntry>();
		for (int i = 0; i < entityResults.size(); ++i) {
			Entity e = entityResults.get(i);
			AddressEntry ae = new AddressEntry(e);
			results.add(ae);
		}
		return results;
	}

	public void Add(AddressEntry ae) {
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		Key addressBookKey = KeyFactory.createKey(entityTypeName, addressBookName);
		
		Entity e = new Entity(itemTypeName, addressBookKey);
		e.setProperty("name", ae.getName());
		e.setProperty("email", ae.getEmail());
		e.setProperty("date", ae.getDate());
		datastore.put(e);
	}
	
	public void Update(AddressEntry ae) {
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		
		Entity e = new Entity(ae.getKey());
		e.setProperty("name", ae.getName());
		e.setProperty("email", ae.getEmail());
		e.setProperty("date", ae.getDate());
		datastore.put(e);
	}
	
	public void Delete(AddressEntry ae) {
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		
		datastore.delete(ae.getKey());
	}
}
