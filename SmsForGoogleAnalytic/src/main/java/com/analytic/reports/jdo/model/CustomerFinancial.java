/**
 * 
 */
package main.java.com.analytic.reports.jdo.model;

import javax.jdo.annotations.IdGeneratorStrategy; 
import com.google.appengine.api.datastore.Key;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;
import javax.jdo.annotations.IdentityType;
import javax.persistence.Entity;
import javax.persistence.Id;

import main.java.com.analytic.reports.interfaces.IEntity;
import main.java.com.analytic.reports.utils.consts.BalanceConsts;


/**
 * @author admin
 * May 17, 2014
 */
@PersistenceCapable
public class CustomerFinancial implements IEntity 
{ 
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7417579342749846578L;
	@PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    private Key key;	
	@Persistent
	private String balance; 
	@Persistent
    private String spent;
	@Persistent
	private String original;
	@Persistent
	private Customer customer;
	
	
	/**
	 * @param balance2
	 */
	public CustomerFinancial(String balance)
	{
		this.balance = balance;
	}
	
	/**
	 * 
	 */
	public CustomerFinancial() 
	{
		this.balance = BalanceConsts.DEFAULT_BALANCE;
	}
	
	public String getBalance() {
		return balance;
	}
	public void setBalance(String balance) {
		this.balance = balance;
	}
	public String getSpent() {
		return spent;
	}
	public void setSpent(String spent) {
		this.spent = spent;
	}
	public String getOriginal() {
		return original;
	}
	public void setOriginal(String original) {
		this.original = original;
	}
	
	


}
