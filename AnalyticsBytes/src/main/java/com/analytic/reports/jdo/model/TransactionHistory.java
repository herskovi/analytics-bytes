/**
 * 
 */
package main.java.com.analytic.reports.jdo.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.jdo.annotations.Element;
import javax.jdo.annotations.Extension;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import main.java.com.analytic.reports.interfaces.IEntity;

/**
 * @author moshehe
 * May 10, 2014
 */
@PersistenceCapable(identityType=IdentityType.APPLICATION, detachable="false")
public class TransactionHistory  implements IEntity
{

	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	@Extension(vendorName="datanucleus", key="gae.encoded-pk", value="true")
	private String encodedKey;
	@Persistent
	private String emailAddress;  
	@Persistent
	private String date;
	@Persistent
	private String description;
	@Persistent
	private String amount;
	@Persistent
	private String transactionId;
	
	
	public String getEmailAddress() {
		return emailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
	
	   


}
