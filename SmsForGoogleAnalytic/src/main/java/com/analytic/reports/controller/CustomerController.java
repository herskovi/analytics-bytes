/**
 * 
 */
package main.java.com.analytic.reports.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Produces;

import main.java.com.analytic.reports.controller.response.NumberResponse;
import main.java.com.analytic.reports.datatypes.NumberDT;
import main.java.com.analytic.reports.jdo.dao.CustomerDAO;
import main.java.com.analytic.reports.jdo.model.Customer;
import main.java.com.analytic.reports.jdo.model.SMS;
import main.java.com.analytic.reports.jdo.model.Subscriber;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
 
@Controller
@RequestMapping("/crud")
public class CustomerController {
 
	@RequestMapping(value="/addCustomerPage", method = RequestMethod.GET)
	public String getAddCustomerPage(ModelMap model) {
 
		return "add";
 
	}
 
	@RequestMapping(value="/add", method = RequestMethod.POST)
	
	public ModelAndView add(HttpServletRequest request, ModelMap model) {
 
		String name = request.getParameter("name");
		String email = request.getParameter("email");
 
	        Key customerKey = KeyFactory.createKey("CustomerSpring", name);
 
		Date date = new Date();
                Entity customerSpring = new Entity("CustomerSpring", customerKey);
                customerSpring.setProperty("name", name);
                customerSpring.setProperty("email", email);
                customerSpring.setProperty("date", date);
 
                DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
                datastore.put(customerSpring);
 
                return new ModelAndView("redirect:list");
 
	}
 
	@RequestMapping(value="/update/{name}", method = RequestMethod.GET)
	public String getUpdateCustomerPage(@PathVariable String name, 
			HttpServletRequest request, ModelMap model) 
	{
 
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		Query query = new Query("CustomerSpring");
		query.addFilter("name", FilterOperator.EQUAL, name);
		PreparedQuery pq = datastore.prepare(query);
 
		Entity e = pq.asSingleEntity();
		model.addAttribute("CustomerSpring",  e);
 
		return "update";
 
	}
 
	@RequestMapping(value="/update", method = RequestMethod.POST)
	public ModelAndView update(HttpServletRequest request, ModelMap model) {
 
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
 
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String originalName =  request.getParameter("originalName");
 
		Query query = new Query("CustomerSpring");
		query.addFilter("name", FilterOperator.EQUAL, originalName);
		PreparedQuery pq = datastore.prepare(query);
		Entity customerSpring = pq.asSingleEntity();
 
		customerSpring.setProperty("name", name);
		customerSpring.setProperty("email", email);
		customerSpring.setProperty("date", new Date());
 
                datastore.put(customerSpring);
 
               //return to list
               return new ModelAndView("redirect:list");
 
	}
 
	@RequestMapping(value="/delete/{name}", method = RequestMethod.GET)
	public ModelAndView delete(@PathVariable String name,
			HttpServletRequest request, ModelMap model) {
 
                DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
 
                Query query = new Query("CustomerSpring");
		query.addFilter("name", FilterOperator.EQUAL, name);
		PreparedQuery pq = datastore.prepare(query);
		Entity customer = pq.asSingleEntity();
 
                datastore.delete(customer.getKey());
 
                //return to list
                return new ModelAndView("redirect:../list");
 
	}
 
	//get all subscribers per customer
	@RequestMapping(value="/list/{userid:.+}", method = RequestMethod.POST )
	@Produces("application/json")
	public String listAllSubscribersUnderCustomer(@PathVariable String userid,
												HttpServletRequest request, 
												ModelMap model)
	{
 
		//FIXME - Get userId include .com
		Customer cust = CustomerDAO.getCustomerInformationByUserID(userid);
		 //Get Numbers from Databse and display it. 
		 //List<String> telNoForSMSList = cust.getTelNoForSMS();
		ArrayList <NumberDT>  numberList = new ArrayList <NumberDT>();
		if (cust!= null)
		{
			List<Subscriber> subscriberList = null; //FIXME!!!!!!!!!!!!!! Correct to cust.getSubscriberList();
			int subscriberListSize = (subscriberList != null ? subscriberList.size() : 0);
	
			 for( int subIndex = 0; subIndex <subscriberListSize; subIndex++)
			 {
				 Subscriber sub = subscriberList.get(subIndex);
				 List <SMS> smsList = sub.getSmsList();
				 
				 for (SMS sms : smsList)
				 {
	
					 NumberDT numberDT = new NumberDT();
					 numberDT.setTelephoneNumber(sub.getTelephoneNumber());
					 numberDT.setProfileName(sms.getProfileName());
					 numberDT.setGoalName(sms.getGoalName());
					 numberList.add(numberDT);
				 }
			 }
		}		
		
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String jsonArray = gson.toJson(numberList);
		jsonArray = "{\"Result\":\"OK\",\"Records\":"+ jsonArray + "}";
		//String json = gson.toJson(numberList);
		model.addAttribute("json", jsonArray);
		return "number";
	}
 
}