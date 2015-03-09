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
import main.java.com.analytic.reports.jdo.PMF;
import main.java.com.analytic.reports.jdo.model.Support;
import main.java.com.analytic.reports.jdo.model.TransactionHistory;


public class TransactionHistoryDAO 
{

	private static final Logger log = Logger.getLogger(TransactionHistoryDAO.class.getName());

	/**
	 * 
	 * @Author:      Moshe Herskovits
	 * @Date:        Aug 31, 2014
	 * @Description: Insert new Record into Support DB
	 */

	public static String insertNewTransaction(TransactionHistory transactionHistory) 
	{

		PersistenceManager pm = PMF.get().getPersistenceManager();
		try {
			pm.makePersistent(transactionHistory);
			log.info("The ID of the new entry is: " + transactionHistory.getEmailAddress());

		} catch (Exception ex) 
		{
			log.severe("TransactionHistory insertNewTransaction failed for " + transactionHistory.getEmailAddress() + "!!!" + ex.getMessage());
		}
		return transactionHistory.getEmailAddress();
	}

	/**
	 * 
	 * @Author:      Moshe Herskovits
	 * @Date:        Aug 31, 2014
	 * @Description: Get ALL Tickets List from Support DB
	 */

	public static List<TransactionHistory> getAllTransactionHistoryList() {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Query query = pm.newQuery(TransactionHistory.class);
		return (List<TransactionHistory>) query.execute();
	}

	/**
	 * 
	 * @Author: Moshe Herskovits
	 * @Date: May 13, 2014
	 * @Description: Get Customer Informatio By User ID
	 */

	public static List<TransactionHistory> getTransactionHistorynByEmail(String email) {
		
		List<TransactionHistory> transactionHistoryResults = null;
	
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Query query = pm.newQuery(TransactionHistory.class);
		try 
		{
		query.setFilter("emailAddress == emailAddressParam");
		query.setOrdering("date desc");
		query.declareParameters("String emailAddressParam");		
		transactionHistoryResults = (List<TransactionHistory>) query.execute(email);
		}catch (Exception ex) 
		{
			log.severe("TransactionHistoryDAO getTransactionHistorynByEmail failed for " + email + "!!!" + ex.getMessage());
		} finally {
		  pm.close();
		}

		return transactionHistoryResults;
	}

	
}
