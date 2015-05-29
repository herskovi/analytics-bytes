/**
 * 
 */
package main.java.com.analytic.reports.jdo.model;

import java.util.Date;
import java.util.List;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;
import com.google.appengine.api.datastore.Key;
import main.java.com.analytic.reports.interfaces.IEntity;

/**
 * @author moshehe
 * May 10, 2014
 */
@PersistenceCapable(identityType=IdentityType.APPLICATION, detachable="false")
//@Sequence(name = "CUSTOMER_ID_SEQ", strategy = null)
public class Subscriber  implements IEntity
{
	
	/**
	 * @param telephoneNumber
	 */
	public Subscriber(String telephoneNumber , String countryCode) {
		super();
		this.telephoneNumber = telephoneNumber;
		this.countryCode = countryCode;
	}
	 @PrimaryKey
	 @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	 private Key key;
	@Persistent
	private String telephoneNumber;
	@Persistent
	private String name;   
	@Persistent
	private String countryCode;
	@Persistent
	private String wasSMSRecieved; 
	@Persistent	
	private String subscriberSts; /** 0 = User was just Created but did not approve GA;
	// 1 = User Created and Approved GA but still inactive 
	// 2 = User Clicked On Confirmation mail; 	
	// 3 = User is Suspended due to payments.
	// 4 = User is Resumed from Suspention
	// 5 = User is Cancelled 
	 **/
	
    @Persistent(embeddedElement = "true", defaultFetchGroup = "true")
	private List<SMS> smsList=null;	
	@Persistent
	private Date creationDate;
	private Customer customer;
		
	public Key getKey() {
		return key;
	}

	public void setKey(Key key) {
		this.key = key;
	}

	public String getTelephoneNumber() {
		return telephoneNumber;
	}

	public void setTelephoneNumber(String telephoneNumber) {
		this.telephoneNumber = telephoneNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
	
	public List<SMS> getSmsList() {
		return smsList;
	}

	public void setSmsList(List<SMS> smsList) {
		this.smsList = smsList;
	}



	
	

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public String getWasSMSRecieved() {
		return wasSMSRecieved;
	}

	public void setWasSMSRecieved(String wasSMSRecieved) {
		this.wasSMSRecieved = wasSMSRecieved;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	

	/**
	 * @return the subscriberSts
	 */
	public String getSubscriberSts() {
		return subscriberSts;
	}

	/**
	 * @param subscriberSts the subscriberSts to set
	 */
	public void setSubscriberSts(String subscriberSts) {
		this.subscriberSts = subscriberSts;
	}


	


}
