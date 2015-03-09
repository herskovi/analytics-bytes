/**
 * 
 */
package main.java.com.anaytic.reports.datatypes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.google.api.services.analytics.Analytics.Management.Webproperties;
import com.google.api.services.analytics.model.Accounts;

/**
 * @author admin
 * Jun 6, 2014
 */
public class NumberDT implements Serializable
{

	private String telephoneNumber = "";
	private String profileName = "";
	private String goalName = "";


	public String getTelephoneNumber() {
		return telephoneNumber;
	}

	public void setTelephoneNumber(String telephoneNumber) {
		this.telephoneNumber = telephoneNumber;
	}

	/**
	 * @return the profileName
	 */
	public String getProfileName() {
		return profileName;
	}

	/**
	 * @param profileName the profileName to set
	 */
	public void setProfileName(String profileName) {
		this.profileName = profileName;
	}

	/**
	 * @return the goalName
	 */
	public String getGoalName() {
		return goalName;
	}

	/**
	 * @param goalName the goalName to set
	 */
	public void setGoalName(String goalName) {
		this.goalName = goalName;
	}

	


}
