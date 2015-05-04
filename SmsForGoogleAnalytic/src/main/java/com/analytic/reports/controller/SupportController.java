/**
 * 
 */
package main.java.com.analytic.reports.controller;

import java.util.logging.Logger;

import main.java.com.analytic.reports.controller.response.UserDashboardResponse;
import main.java.com.analytic.reports.datatypes.SupportDT;
import main.java.com.analytic.reports.interfaces.IResponse;
import main.java.com.analytic.reports.jdo.dao.SupportDAO;
import main.java.com.analytic.reports.jdo.model.Support;

/**
 * @author admin
 * Aug 31, 2014
 */
public class SupportController extends BaseController
{
	private SupportDT supportDT = new SupportDT();
	private Support support =null;
	private IResponse supportResponse = newResponse();
	private static final Logger log = Logger.getLogger(SupportController.class.getName());





	public SupportController(SupportDT supportDT)
	{
		this.supportDT = supportDT;
	}


	@Override
	public void execute() throws Exception
	{
		Support support = convertToSupportModel(supportDT);
		SupportDAO.insertNewTicket(support);
	}



	/**
	 *@Author:      Moshe Herskovits
	 *@Date:        Aug 30, 2014
	 *@Description: Convert From Support DT to support
	 */
	private Support convertToSupportModel(SupportDT supportDT) 
	{
		support = new Support();
		support.setEmailAddress(supportDT.getEmail());
		support.setFirstName(supportDT.getFirstName());
		support.setLastName(supportDT.getLastName());
		support.setCompanyName(supportDT.getCompany());
		support.setDescription(supportDT.getDescription());
		return support;
	}


	/**
	 * @Author: 	 Moshe Herskovits
	 * @Date: 		 Jun 1, 2014
	 * @Description: Initiate new Response Object for Controller
	 */
	@Override
	public IResponse newResponse()  
	{
		return new UserDashboardResponse();
		
	}
	
	/**
	 * @Author: 	 Moshe Herskovits
	 * @Date: 		 Jun 1, 2014
	 * @Description: Get Response From Controller
	 */
	@Override
	public IResponse getResponse()  
	{
		return this.supportResponse;		
	}


	
	@Override
	public void setResponse(String textMessage)  
	{
		supportResponse.setMessage(textMessage);	
	}



}
