/**
 * 
 */
package main.java.com.analytic.reports.controller.response;

import java.util.ArrayList;
import java.util.List;

import main.java.com.analytic.reports.datatypes.GoogleAnalyticsDT;
import main.java.com.analytic.reports.datatypes.RawDataDT;


/**
 * @author admin
 * Jul 23, 2014
 */
public class ReadGoogleCloudStorageResponse extends BaseResponse 
{

	
	private List<RawDataDT> rawDataList = new ArrayList<RawDataDT>();
	
	

	public List<RawDataDT> getRawDataList() {
		return rawDataList;
	}

	public void setRawDataList(List<RawDataDT> rawDataList) {
		this.rawDataList = rawDataList;
	}

	/* (non-Javadoc)
	 * @see main.java.com.analytic.reports.interfaces.IResponse#getMessage()
	 */
	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see main.java.com.analytic.reports.interfaces.IResponse#setMessage(java.lang.String)
	 */
	@Override
	public void setMessage(String message) {
		// TODO Auto-generated method stub
		
	}
	
	
		
}
