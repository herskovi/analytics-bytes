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
import main.java.com.analytic.reports.jdo.model.UserStatus;


public class UserStatusDAO {

	private static final Logger log = Logger.getLogger(UserStatusDAO.class.getName());

	/**
	 * 
	 * @Author:      Moshe Herskovits
	 * @Date:        Aug 31, 2014
	 * @Description: Insert new Record into User Status DB
	 */

	public static String insertOrUpdateUserStatus(UserStatus userStatus) 
	{

		PersistenceManager pm = PMF.get().getPersistenceManager();
		try {
			pm.makePersistent(userStatus);
			log.info("The ID of the new entry is: " + userStatus.getEmailAddress());

		} catch (Exception ex) 
		{
			log.severe("UserStatusDAO insertNewTicket failed for " + userStatus.getEmailAddress() + "!!!" + ex.getMessage());
		}
		return userStatus.getEmailAddress();
	}

	/**
	 * 
	 * @Author:      Moshe Herskovits
	 * @Date:        Aug 31, 2014
	 * @Description: Get ALL Tickets List from User Status DB
	 */

	public static List<UserStatus> getUserStatusList() 
	{
		PersistenceManager pm = PMF.get().getPersistenceManager();

		Query query = pm.newQuery(UserStatus.class);
		return (List<UserStatus>) query.execute();
	}

	/**
	 * 
	 * @Author: Moshe Herskovits
	 * @Date: May 13, 2014
	 * @Description: Get Customer Informatio By User ID
	 */

	public static UserStatus getUserStatusInformationByEmail(String email) 
	{
		UserStatus userStatusRecord = null;
		PersistenceManager pm = PMF.get().getPersistenceManager();
		try {
			userStatusRecord = pm.getObjectById(UserStatus.class, email);
		}catch (Exception ex) 
		{
			log.severe("UserStatusDAO getUserStatusInformationByEmail failed for " + email + "!!!" + ex.getMessage());
		} finally {
			pm.close();
		}
		return userStatusRecord;
	}
	
	/**
	 * 
	 * @Author: Moshe Herskovits
	 * @Date: May 13, 2014
	 * @Description: Get Customer Informatio By User ID
	 */

	public static String getUniquKeyInformationByEmail(String email) 
	{	
		UserStatus uStatus = getUserStatusInformationByEmail(email);
		return (uStatus != null ? uStatus.getUniqueKey() : "");
	}

	
}
