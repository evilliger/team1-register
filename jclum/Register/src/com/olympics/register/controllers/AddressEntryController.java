package com.olympics.register.controllers;

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

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.KeyFactory;
import com.olympics.register.daos.AddressEntryDAO;
import com.olympics.register.models.AddressEntry;

@Controller
@RequestMapping("/addressentry")
public class AddressEntryController {

	/**
	 * Gets all the Address Entries
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String listCustomer(ModelMap model) {
		
		
		List<AddressEntry> results = new AddressEntryDAO().LoadAll();

		if (results == null || results.isEmpty()) {
			model.addAttribute("aeList", new ArrayList<AddressEntry>());
		} else {
			model.addAttribute("aeList", results);
		}

		return "list";

	}
	
	
	@RequestMapping(value = "/list/{key}", method = RequestMethod.GET)
	public String listCustomerKey(@PathVariable String key,ModelMap model) {
		
		AddressEntryDAO dao = new AddressEntryDAO();

		List<AddressEntry> results = dao.LoadAll();

		if (results == null || results.isEmpty()) {
			model.addAttribute("aeList", new ArrayList<AddressEntry>());
		} else {
			model.addAttribute("aeList", results);
		}
	
		AddressEntry ae = dao.Update(key);

		model.addAttribute("updateEntity", ae);
	
		return "list";
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String getAddCustomerPage(ModelMap model) {
		return "add";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ModelAndView add(HttpServletRequest request, ModelMap model) {

		String name = request.getParameter("name");
		String email = request.getParameter("email");
		
		AddressEntry ae = new AddressEntry();
		ae.setName(name);
		ae.setEmail(email);
		
		new AddressEntryDAO().Create(ae);

		return new ModelAndView("redirect:list");
	}

	@RequestMapping(value = "/update/{key}", method = RequestMethod.GET)
	public String getUpdateCustomerPage(@PathVariable String key,
			HttpServletRequest request, ModelMap model) {

		AddressEntry ae = new AddressEntryDAO().Update(key);

		model.addAttribute("entity", ae);

		return "update";

	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public ModelAndView update(HttpServletRequest request, ModelMap model) {
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();

		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String key = request.getParameter("key");
		Date date = new Date();
		
		AddressEntry ae = new AddressEntry();
		ae.setName(name);
		ae.setEmail(email);
		ae.setKey(KeyFactory.stringToKey(key));
		ae.setDate(date);
		
		new AddressEntryDAO().Update(ae);

		return new ModelAndView("redirect:list");
	}

	@RequestMapping(value = "/delete/{key}", method = RequestMethod.GET)
	public ModelAndView delete(@PathVariable String key,
			HttpServletRequest request, ModelMap model) {

		AddressEntry ae = new AddressEntry();
		ae.setKey(KeyFactory.stringToKey(key));
		new AddressEntryDAO().Delete(ae);
		// return to list
		return new ModelAndView("redirect:../list");

	}

}