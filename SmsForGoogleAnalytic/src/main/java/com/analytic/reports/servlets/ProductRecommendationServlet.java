package main.java.com.analytic.reports.servlets;

import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.logging.Logger;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.java.com.analytic.reports.controller.DailySmsController;
import main.java.com.analytic.reports.controller.ProductRecommendationAnalyticsAPIController;
import main.java.com.analytic.reports.controller.response.ProductRecommendationAnalyticsAPIResponse;
import main.java.com.analytic.reports.interfaces.IController;
import main.java.com.analytic.reports.jdo.model.Customer;
import main.java.com.analytic.reports.utils.AnalyticUtils;
import main.java.com.analytic.reports.utils.HttpClientUtils;

import com.google.api.services.analytics.Analytics;
import com.google.api.services.analytics.model.GaData;
import com.google.gson.Gson;

@SuppressWarnings({ "serial", "unused" })
public class ProductRecommendationServlet extends HttpServlet 
{
	private static final Logger log = Logger.getLogger(ProductRecommendationServlet.class.getName());

	/**
	 * 
	 */

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException 
	{
		IController productRecommendationAnalyticsAPIController = getController(req,resp); 
		try 
		{
			productRecommendationAnalyticsAPIController.execute();
			writeResponseIntoJson(resp, productRecommendationAnalyticsAPIController);

		} catch (Exception e) 
		{
			log.severe("ProductRecommendationServlet failed " +  e.getMessage());
		}

	}

	/**
	 * 
	 *@Author:      Moshe Herskovits
	 *@Date:        Mar 13, 2016
	 *@Description:
	 */

	private void writeResponseIntoJson(HttpServletResponse resp, IController productRecommendationAnalyticsAPIController) throws IOException {
		Gson gson = new Gson();
		String json = gson.toJson((ProductRecommendationAnalyticsAPIResponse)productRecommendationAnalyticsAPIController.getResponse());
		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");
		resp.getWriter().write(json);
	}



	/**
	 *@Author:      Moshe Herskovits
	 *@Date:        Mar 13, 2016
	 *@Description: get Daily SMS Controller
	 */
	private IController getController(HttpServletRequest req, HttpServletResponse resp) 
	{
		String userId = HttpClientUtils.getUserIdFromHttpRequest(req);		
		return new ProductRecommendationAnalyticsAPIController(userId);
	}

	/**
	 * @Author: Moshe Herskovits
	 * @Date: Mar 13, 2016
	 * @Description: call doGet
	 */

	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException
	{
		doGet(req, resp);
	}
	
	
}
