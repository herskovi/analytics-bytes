package main.java.com.analytic.reports.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.java.com.analytic.reports.controller.ProfileSelectionController;
import main.java.com.analytic.reports.controller.response.ProfileResponse;
import main.java.com.analytic.reports.interfaces.IController;
import main.java.com.anaytic.reports.datatypes.ProfileDT;

import com.google.gson.Gson;

@SuppressWarnings("serial")
public class ProfileSelectionServlet extends HttpServlet 
{
	private static final Logger log = Logger.getLogger(ProfileSelectionServlet.class.getName());
	private static final String APPLICATION_NAME = "";

	
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException 
	{
		
		String userId = (String) req.getSession().getAttribute("userId");
		String accountId= req.getParameter("accountId");
		String webPropertyId= req.getParameter("webPropertyId");
		List<ProfileDT> profilesList = new ArrayList<ProfileDT>();

		IController profileSelectionController = getController(userId, accountId,webPropertyId);
		try {
			profileSelectionController.execute();
			profilesList = ((ProfileResponse)(profileSelectionController.getResponse())).getProfileList();
		} catch (Exception e) {
			log.severe("ProfileSelectionServlet failed " + e.getMessage());		
		}
		  
		Gson gson = new Gson();
		String json = gson.toJson(profilesList);		
		req.getSession().setAttribute("webPropertyJson", json);
		req.getSession().setAttribute("webProperty", profilesList);
		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");
		resp.getWriter().write(json);
	
	}

	

	/**
	 *@Author:      Moshe Herskovits
	 *@Date:        Dec 4, 2014
	 *@Description:
	 */
	private IController getController(String userId, String accountId, String webPropertyId) 
	{
		ProfileDT profileDT = new ProfileDT(accountId,webPropertyId);
		ProfileSelectionController  profileSelectionController = new ProfileSelectionController(profileDT,userId);
		return profileSelectionController;
	}

	
	/**
	 * @throws ServletException 
	 * 
	 * 
	 */

	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException 
	{
		doGet(req, resp);
	}

}
	
	