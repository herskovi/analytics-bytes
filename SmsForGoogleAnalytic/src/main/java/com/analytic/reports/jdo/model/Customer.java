/**
 * 
 */
package main.java.com.analytic.reports.jdo.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.jdo.annotations.Element;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.Key;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;
import javax.jdo.annotations.Sequence;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.SequenceGenerator;

import main.java.com.analytic.reports.interfaces.IEntity;
import main.java.com.analytic.reports.utils.DateUtils;

/**
 * @author moshehe
 * May 10, 2014
 */
@PersistenceCapable(identityType=IdentityType.APPLICATION, detachable="false")
//@Sequence(name = "CUSTOMER_ID_SEQ", strategy = null)
@SequenceGenerator(name="CUSTOMER_ID_SEQ", sequenceName="CUSTOMER_ID_SEQ")

public class Customer  implements IEntity
{
	@PrimaryKey
    @Persistent
	private String uniqueAccountNumber;
	@Persistent
	@Key
	private String emailAddress;  
	@Persistent
	private String userId;
	@Persistent
	private String name;  
	@Persistent
	private String companyName; 
	@Persistent
	private String password;
	@Persistent
	private String accessToken;
	@Persistent
	private String refreshToken;
	@Persistent
	private String authorizationCode; 
	@Persistent
	private String countryCode;
	@Persistent
	private String telephoneNumber;
	@Persistent
	private String profileId;
	@Persistent
	private String plan;
	@Persistent
	private String comments;
	@Persistent
	private String userSts;  // 0 = User was just Created but did not approve GA;
	// 1 = User Created and Approved GA but still inactive 
	// 2 = User Clicked On Confirmation mail; 	
	// 3 = User is Suspended due to payments.
	// 4 = User is Resumed from Suspention
	// 5 = User is Cancelled
	@Persistent
	private String wasSMSRecieved; 
    
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CUSTOMER_ID_SEQ") 
    private long sequenceValue;

    @Persistent
	private String balance;


	@Persistent(mappedBy = "customer", defaultFetchGroup = "true")
	private ArrayList<CustomerAnalyticInfo> customerAnalyticList = new ArrayList<CustomerAnalyticInfo>();


	@Persistent(defaultFetchGroup = "true")
	private List<String> telNoForSMS = new ArrayList<String>();

	@Persistent(defaultFetchGroup = "true")
	private List<String> metrics = new ArrayList<String>();

	@Persistent
	private Date creationDate;

	@Persistent
	private String timeZone;


	/**
	 * @param emailAddress
	 * @param firstName
	 * @param lastName
	 * @param telephoneNumber
	 */
	public Customer(String uniqueAccountNumber, String plan,String userId, String name, String companyName,
			String emailAddress, String countryCode, String telephoneNumber,
			String password, String comments,String balance) 
	{
		super();
		this.uniqueAccountNumber = uniqueAccountNumber;
		this.plan = plan;
		this.userId = userId;
		this.name = name;
		this.companyName = companyName;
		this.emailAddress = emailAddress;
		this.countryCode = countryCode;
		this.telephoneNumber = telephoneNumber;
		this.password = password;
		this.comments = comments;
		this.balance = balance;
		//getCustomerFinancial().setBalance(balance);
		telNoForSMS.add(telephoneNumber);
		this.userSts= "0";
		this.creationDate = DateUtils.getCurrentDateObject();
	}
	
	public Customer(String uniqueAccountNumber,String userId,  String countryCode, String telephoneNumber) 
	{
		super();
		this.uniqueAccountNumber=uniqueAccountNumber;
		this.userId = userId;
		this.countryCode = countryCode;
		this.telephoneNumber = telephoneNumber;
	}
	

	public String getUniqueAccountNumber() {
		return uniqueAccountNumber;
	}
	public void setUniqueAccountNumber(String uniqueAccountNumber) {
		this.uniqueAccountNumber = uniqueAccountNumber;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAccessToken() {
		return accessToken;
	}
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	public String getAuthorizationCode() {
		return authorizationCode;
	}
	public void setAuthorizationCode(String authorizationCode) {
		this.authorizationCode = authorizationCode;
	}
	public String getCountryCode() {
		return countryCode;
	}
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
	public String getTelephoneNumber() {
		return telephoneNumber;
	}
	public void setTelephoneNumber(String telephoneNumber) {
		this.telephoneNumber = telephoneNumber;
	}
	public List<String> getTelNoForSMS() {
		return telNoForSMS;
	}
	public void setTelNoForSMS(List<String> telNoForSMS) {
		this.telNoForSMS = telNoForSMS;
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	public String getRefreshToken() {
		return refreshToken;
	}
	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getProfileId() {
		return profileId;
	}

	public void setProfileId(String profileId) {
		this.profileId = profileId;
	}

	public String getPlan() {
		return plan;
	}

	public void setPlan(String plan) {
		this.plan = plan;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public List<String> getMetrics() {
		return metrics;
	}

	public void setMetrics(List<String> metrics) {
		this.metrics = metrics;
	}	

	public ArrayList<CustomerAnalyticInfo> getCustomerAnalyticList() {
		return customerAnalyticList;
	}

	public void setCustomerAnalyticList(
			ArrayList<CustomerAnalyticInfo> customerAnalyticList) {
		this.customerAnalyticList = customerAnalyticList;
	}

	public String getTimeZone() {
		return timeZone;
	}

	public void setTimeZone(String timeZone) {
		this.timeZone = timeZone;
	}


	//	public CustomerFinancial getCustomerFinancial() {
	//		return customerFinancial;
	//	}
	//
	//	public void setCustomerFinancial(CustomerFinancial customerFinancial) {
	//		this.customerFinancial = customerFinancial;
	//	}
	//

	public String getBalance() {
		return balance;
	}

	public void setBalance(String balance) {
		this.balance = balance;
	}

	public String getUserSts() {
		return userSts;
	}

	public void setUserSts(String userSts) {
		this.userSts = userSts;
	}

	public String getWasSMSRecieved() {
		return wasSMSRecieved;
	}

	public void setWasSMSRecieved(String wasSMSRecieved) {
		this.wasSMSRecieved = wasSMSRecieved;
	}
	
	public long getSequenceValue(){
	      return sequenceValue;
	}


}
