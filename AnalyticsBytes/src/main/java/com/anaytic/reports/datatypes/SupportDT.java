/**
 * 
 */
package main.java.com.anaytic.reports.datatypes;

import java.io.Serializable;
import java.util.List;

import com.google.api.services.analytics.Analytics.Management.Webproperties;
import com.google.api.services.analytics.model.Accounts;

/**
 * @author admin
 * Jun 6, 2014
 */
public class SupportDT implements Serializable
{
	private String firstName = null;
	private String lastName="";
	private String email="";
	private String company="";
	private String description ="";
	
	/**
	 * @param firstName2
	 * @param lastName2
	 * @param email2
	 * @param company2
	 * @param description2
	 */
	public SupportDT(String firstName, String lastName, String email,
			String company, String description) 
	{
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.company = company;
		this.description = description;
	}
	
	
	/**
	 * 
	 */
	public SupportDT() 
	{
	}


	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
		
	


}
