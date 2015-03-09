/**
 * 
 */
package main.java.com.analytic.reports.controller.response;

import java.util.ArrayList;
import java.util.List;

import main.java.com.anaytic.reports.datatypes.NumberDT;
import main.java.com.anaytic.reports.datatypes.UserDashboardDT;

/**
 * @author admin
 * Jul 23, 2014
 */
public class NumberResponse extends BaseResponse 
{

	private String message = null;
	private ArrayList <NumberDT> numberList = new ArrayList<NumberDT>();
	
	
	@Override
	public String getMessage() 
	{
		return this.message;
	}
	@Override
	public void setMessage(String message) 
	{
		this.message = message;
	}

	public ArrayList<NumberDT> getNumberList() {
		return numberList;
	}
	public void setNumberList(ArrayList<NumberDT> numberList) {
		this.numberList = numberList;
	}
	
	

}
