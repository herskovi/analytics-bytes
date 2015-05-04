package main.java.com.analytic.reports.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.java.com.analytic.reports.controller.MetricsValueBeforeSubmittionController;
import main.java.com.analytic.reports.controller.response.MetricValueResponse;
import main.java.com.analytic.reports.datatypes.MetricDT;
import main.java.com.analytic.reports.datatypes.ProfileDT;
import main.java.com.analytic.reports.interfaces.IController;
import main.java.com.analytic.reports.utils.AnalyticUtils;

import com.google.gson.Gson;

@SuppressWarnings("serial")
public class MetricsValueBeforeSubmitionServlet extends HttpServlet 
{
	private static final Logger log = Logger.getLogger(MetricsValueBeforeSubmitionServlet.class.getName());
	private static final String APPLICATION_NAME = "";

	
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException 
	{
		
		String userId = (String) req.getSession().getAttribute("userId");
		String accountId= req.getParameter("accountId");
		String webPropertyId= req.getParameter("webPropertyId");
		String profileId= req.getParameter("profileId");
		String metricsArr1 = req.getParameter("metrics");
		String [] metricsArr = (String[]) Arrays.asList(metricsArr1.split("\\s*,\\s*")).toArray();

		List<MetricDT> metricsList = new ArrayList<MetricDT>();

		IController metricsValueBeforeSubmittionController = getController(userId, accountId,webPropertyId,profileId,metricsArr);
		try {
			metricsValueBeforeSubmittionController.execute();
			metricsList = ((MetricValueResponse)(metricsValueBeforeSubmittionController.getResponse())).getMetricsList();
		} catch (Exception e) {
			log.severe("ProfileSelectionServlet failed " + e.getMessage());		
		}
		  
		Gson gson = new Gson();
		String json = gson.toJson(metricsList);		
		req.getSession().setAttribute("metricsJson", json);
		req.getSession().setAttribute("metricsList", metricsList);
		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");
		resp.getWriter().write(json);
	
	}

	

	/**
	 *@Author:      Moshe Herskovits
	 *@Date:        Dec 4, 2014
	 *@Description:
	 */
	private IController getController(String userId, String accountId, String webPropertyId,String profileId,String[] metricsArr) 
	{
		ProfileDT profileDT = new ProfileDT(accountId,webPropertyId,profileId,null);
		MetricsValueBeforeSubmittionController  metricsValueBeforeSubmittionController = new MetricsValueBeforeSubmittionController(profileDT,userId, metricsArr);
		return metricsValueBeforeSubmittionController;
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
	
	