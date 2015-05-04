package main.java.com.analytic.reports.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.java.com.analytic.reports.controller.GoalSelectionController;
import main.java.com.analytic.reports.controller.ProfileSelectionController;
import main.java.com.analytic.reports.controller.response.GoalResponse;
import main.java.com.analytic.reports.controller.response.ProfileResponse;
import main.java.com.analytic.reports.datatypes.GoalDT;
import main.java.com.analytic.reports.datatypes.ProfileDT;
import main.java.com.analytic.reports.interfaces.IController;

import com.google.gson.Gson;

@SuppressWarnings("serial")
public class GoalNameSelectionServlet extends HttpServlet 
{
	private static final Logger log = Logger.getLogger(GoalNameSelectionServlet.class.getName());
	private static final String APPLICATION_NAME = "";

	
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException 
	{
		
		String userId = (String) req.getSession().getAttribute("userId");
		String accountId= req.getParameter("accountId");
		String webPropertyId= req.getParameter("webPropertyId");
		String profileId= req.getParameter("profileId");

		List<GoalDT> goalList = new ArrayList<GoalDT>();

		IController goalNameController = getController(userId, accountId,webPropertyId,profileId);
		try {
			goalNameController.execute();
			goalList = ((GoalResponse)(goalNameController.getResponse())).getGoalList();
		} catch (Exception e) {
			log.severe("ProfileSelectionServlet failed " + e.getMessage());		
		}
		  
		Gson gson = new Gson();
		String json = gson.toJson(goalList);		
		req.getSession().setAttribute("gaolJson", json);
		req.getSession().setAttribute("goal", goalList);
		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");
		resp.getWriter().write(json);
	
	}

	

	/**
	 *@Author:      Moshe Herskovits
	 *@Date:        Dec 4, 2014
	 *@Description:
	 */
	private IController getController(String userId, String accountId, String webPropertyId,String profileId) 
	{
		GoalDT goalDT = new GoalDT(accountId,webPropertyId,profileId);
		GoalSelectionController  goalSelectionController = new GoalSelectionController(goalDT,userId);
		return goalSelectionController;
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
	
	