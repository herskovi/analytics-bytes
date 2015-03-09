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

import java.util.List;
import java.util.logging.Logger;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.jdo.annotations.Key;

import com.google.appengine.api.datastore.KeyFactory;

import main.java.com.analytic.reports.jdo.PMF;
import main.java.com.analytic.reports.jdo.model.SmsHistory;
import main.java.com.analytic.reports.jdo.model.Support;
import main.java.com.analytic.reports.jdo.model.TransactionHistory;


public class SmsHistoryDAO 
{

	private static final Logger log = Logger.getLogger(SmsHistoryDAO.class.getName());

	/**
	 * 
	 * @Author:      Moshe Herskovits
	 * @Date:        Aug 31, 2014
	 * @Description: Insert new Record into Support DB
	 */

	public static String insertNewSMS(SmsHistory smsHistory) 
	{

		PersistenceManager pm = PMF.get().getPersistenceManager();
		try {
			pm.makePersistent(smsHistory);
			log.info("The ID of the new entry is: " + smsHistory.getEmail());

		} catch (Exception ex) 
		{
			log.severe("SMS insertNewSMS failed for " +  smsHistory.getEmail() + "!!!" + ex.getMessage());
		}
		return  smsHistory.getEmail();
	}

	/**
	 * 
	 * @Author:      Moshe Herskovits
	 * @Date:        Aug 31, 2014
	 * @Description: Get ALL Tickets List from Support DB
	 */

	public static List<SmsHistory> getAllSMSHistoryList() 
	{
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Query query = pm.newQuery(SmsHistory.class);
		return (List<SmsHistory>) query.execute();
	}

	/**
	 * 
	 * @Author: Moshe Herskovits
	 * @Date: May 13, 2014
	 * @Description: Get Customer Informatio By User ID
	 */

	public static List<SmsHistory> getSMSHistorynByUser(String userId) 
	{

		List<SmsHistory> smsHistoryResults = null;
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Query query = pm.newQuery(SmsHistory.class);
		try 
		{
			query.setFilter("email == emailAddressParam");
			query.setOrdering("date desc");
			query.declareParameters("String emailAddressParam");		
			smsHistoryResults = (List<SmsHistory>) query.execute(userId);

		}catch (Exception ex) 
		{
			log.severe("TransactionHistoryDAO getSMSHistorynByUser failed for " + userId + "!!!" + ex.getMessage());
		} finally {
			pm.close();
		}

		return smsHistoryResults;
	}


}
