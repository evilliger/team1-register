package com.address.register.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.address.register.daos.AddressEntryDAO;
import com.address.register.models.AddressEntry;
import com.google.appengine.api.datastore.*;

@Controller
@RequestMapping("/addressentry")
public class AddressEntryController {

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String getAddAddressEntryPage(ModelMap model) {
		return "add";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ModelAndView add(HttpServletRequest request, ModelMap model) {
		
		AddressEntry ae = new AddressEntry();
		ae.setName(request.getParameter("name"));
		ae.setEmail(request.getParameter("email"));
		ae.setDate(new Date());
		(new AddressEntryDAO()).Add(ae);

		return new ModelAndView("redirect:list");
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public ModelAndView update(HttpServletRequest request, ModelMap model) {
		String key = request.getParameter("key");
		AddressEntry ae = new AddressEntry();
		ae.setKey(KeyFactory.stringToKey(key));
		
		ae.setName(request.getParameter("name"));
		ae.setEmail(request.getParameter("email"));
		ae.setDate(new Date());
		(new AddressEntryDAO()).Update(ae);

		return new ModelAndView("redirect:list");
	}

	@RequestMapping(value = "/update/{key}", method = RequestMethod.GET)
	public String getUpdateAddressEntryPage(@PathVariable String key,
			HttpServletRequest request, ModelMap model) {
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		
		Entity e = null;
		try {
			e = datastore.get(KeyFactory.stringToKey(key));
		} catch (EntityNotFoundException exception) {
			exception.printStackTrace();
		}
		model.addAttribute("addressentry", e);
		
		return "update";

	}

	@RequestMapping(value = "/delete/{key}", method = RequestMethod.GET)
	public ModelAndView delete(@PathVariable String key, ModelMap model) {
		
		AddressEntry ae = new AddressEntry();
		ae.setKey(KeyFactory.stringToKey(key));
		
		(new AddressEntryDAO()).Delete(ae);
		
		// return to list
		return new ModelAndView("redirect:../list");

	}

	// get all AddressEntrys
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String listAddressEntry(ModelMap model) {
		List<AddressEntry> results = (new AddressEntryDAO()).LoadAll();
		
		if (results.isEmpty()) {
			model.addAttribute("addressentryList", new ArrayList<AddressEntry>());
		} else {
			model.addAttribute("addressentryList", results);
		}

		return "list";
	}
	
	// get all AddressEntrys plus the bottom form
	@RequestMapping(value = "/list/{key}", method = RequestMethod.GET)
	public String listAddressEntry(@PathVariable String key, ModelMap model) {
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		
		List<AddressEntry> results = (new AddressEntryDAO()).LoadAll();
		
		if (results.isEmpty()) {
			model.addAttribute("addressentryList", new ArrayList<AddressEntry>());
		} else {
			model.addAttribute("addressentryList", results);
		}

		
		Entity e = null;
		try {
			e = datastore.get(KeyFactory.stringToKey(key));
		} catch (EntityNotFoundException exception) {
			exception.printStackTrace();
		}
		model.addAttribute("updateEntity", new AddressEntry(e));

		return "list";
	}
}