/* Copyright (c) 2009 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package main.java.com.analytic.reports.jdo.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.jdo.FetchGroup;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;


import main.java.com.analytic.reports.jdo.PMF;
import main.java.com.analytic.reports.jdo.model.Customer;
import main.java.com.analytic.reports.jdo.model.CustomerAnalyticInfo;
import main.java.com.analytic.reports.servlets.SmsForGoogleAnalyticLoginServlet;


public class CustomerDAO {

	private static final Logger log = Logger.getLogger(CustomerDAO.class.getName());

	/**
	 * 
	 * @Author: Moshe Herskovits
	 * @Date: Jun 8, 2014
	 * @Description: Insert new Record into Customer DB
	 */

	public static String insertNewCustomer(Customer customer) {

		PersistenceManager pm = PMF.get().getPersistenceManager();
		try {
			pm.makePersistent(customer);
			log.info("The ID of the new entry is: " + customer.getUserId());
		} catch (Exception ex) 
		{
			log.severe("CustomerUtils insertNewCustomer failed for " + customer.getUserId() + "!!!" + ex.getMessage());
		}
		return customer.getUserId();
	}

	/**
	 * 
	 * @Author: Moshe Herskovits
	 * @Date: May 13, 2014
	 * @Description: Get ALL Customer List from DB
	 */

	public static List<Customer> getCustomerList() {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Query query = pm.newQuery(Customer.class);
		return (List<Customer>) query.execute();
	}

	/**
	 * 
	 * @Author: Moshe Herskovits
	 * @Date: May 13, 2014
	 * @Description: Get Customer Informatio nBy User ID
	 */

	public static Customer getCustomerInformationByUserID(String userID) 
	{
		Customer customer = null;
		PersistenceManager pm = PMF.get().getPersistenceManager();
		try {
			//pm.getFetchPlan().setGroup(FetchGroup.ALL);
			customer = pm.getObjectById(Customer.class, userID);
			ArrayList<CustomerAnalyticInfo> customerAnalyticList = customer.getCustomerAnalyticList();
			if (customerAnalyticList.size() > 0)
			{
				customerAnalyticList.get(0).getProfileId();
			}
			
		}catch (Exception ex) 
		{
			log.severe("CustomerUtils getCustomerInformationByUserID failed for " + userID + "!!!" + ex.getMessage());
		} finally {
			pm.close();
		}
		return customer;
	}

	/**
	 * @Author: Moshe Herskovits
	 * @Date: May 13, 2014
	 * @Description: update Customer Token In DB
	 */
	public static void updateCustomerTokenInDB(Customer cust, String accessToken, String refreshToken, String authorizationCode) 
	{
		cust.setAccessToken(accessToken);
		cust.setRefreshToken(refreshToken);
		cust.setAuthorizationCode(authorizationCode);
		updateCustomerInDB(cust);

	}
	
	/**
	 * @Author: Moshe Herskovits
	 * @Date: May 13, 2014
	 * @Description: update Customer Token In DB
	 */
	public static void updateCustomerBalanceInDB(Customer cust, String balance) 
	{
		//cust.getCustomerFinancial().setBalance(balance);
		cust.setBalance(balance);
		updateCustomerInDB(cust);

	}


	/**
	 * @Author: Moshe Herskovits
	 * @Date: June 7, 2014
	 * @Description: update Customer Token In DB
	 */
	public static void updateCustomerAnalyticInfo(Customer cust,
			String accountName, String profileId, String goalId, String goalName, String timeToSendSMS) {

		CustomerAnalyticInfo customerAnalyticInfo = new CustomerAnalyticInfo();
		customerAnalyticInfo.setGoalId(goalId);
		customerAnalyticInfo.setGoalName(goalName);
		customerAnalyticInfo.setProfileId(profileId);
		customerAnalyticInfo.setTimeToSendSMS(timeToSendSMS);
		cust.getCustomerAnalyticList().add(customerAnalyticInfo);
		updateCustomerInDB(cust);

	}

	/**
	 * @Author: Moshe Herskovits
	 * @Date: June 06, 2014
	 * @Description: Update Customer In DB
	 */

	public static void updateCustomerInDB(Customer cust) 
	{
		PersistenceManager pm = PMF.get().getPersistenceManager();
		try {
			pm.makePersistent(cust);
		} catch (Exception ex) {
			log.severe("CustomerUtils updateCustomerInDB failed for " + cust.getUserId() + "!!!" + ex.getMessage());
		} finally {
			pm.close();
		}
	}

	/**
	 *@Author:      Moshe Herskovits
	 *@Date:        Aug 7, 2014
	 *@Description: Check If Customer existsi in ourDB
	 */
	public static boolean isUserExists(Customer cust) 
	{
		if (cust!= null && cust.getUserId() != null && cust.getUserId().length() > 0)
		{
			return true;
		}
		return false;	
	}

	/**
	 *@Author:      Moshe Herskovits
	 *@Date:        Aug 7, 2014
	 *@Description: Does user has same password as store in DB
	 */
	public static boolean doesUserHasSameCredential(String passwordFromLoginForm, Customer cust) 
	{
		String passwordFromDatabase = cust.getPassword();
		if (passwordFromDatabase != null && passwordFromDatabase.equals(passwordFromLoginForm))
		{
			return true;
		}else
		{
			return false;
		}
	}
	
	/**
	 *@Author:      Moshe Herskovits
	 *@Date:        Aug 7, 2014
	 *@Description: Does user has same password as store in DB
	 */
	public static void cleanUpCustomerTable() 
	{
		PersistenceManager pm = PMF.get().getPersistenceManager();
		try {
			Query q = pm.newQuery(Customer.class);
			q.deletePersistentAll();
		} catch (Exception ex) {
			log.severe("CustomerUtils cleanupAllTable failed for !!! " + ex.getMessage());
		} finally {
			pm.close();
		}
		
	}
	
	
}
