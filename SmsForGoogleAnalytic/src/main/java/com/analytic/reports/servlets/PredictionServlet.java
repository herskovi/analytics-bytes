package main.java.com.analytic.reports.servlets;

import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.java.com.analytic.reports.controller.DailySmsController;
import main.java.com.analytic.reports.controller.GCStorageController;
import main.java.com.analytic.reports.controller.PredictionController;
import main.java.com.analytic.reports.controller.ProductRecommendationAnalyticsAPIController;
import main.java.com.analytic.reports.controller.response.ProductRecommendationAnalyticsAPIResponse;
import main.java.com.analytic.reports.datatypes.RawDataDT;
import main.java.com.analytic.reports.interfaces.IController;
import main.java.com.analytic.reports.jdo.model.Customer;
import main.java.com.analytic.reports.utils.AnalyticUtils;
import main.java.com.analytic.reports.utils.HttpClientUtils;

import com.csvreader.CsvWriter;
import com.google.api.services.analytics.Analytics;
import com.google.api.services.analytics.model.GaData;
import com.google.gson.Gson;

@SuppressWarnings({ "serial", "unused" })
public class PredictionServlet extends HttpServlet 
{
	private static final Logger log = Logger.getLogger(PredictionServlet.class.getName());
	private static final String BUCKET_NAME = "analyticsbytes";
	private static final String FILE_NAME = "raw_data";


	/**
	 * 
	 */

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException 
	{
		IController productRecommendationAnalyticsAPIController = getController(req,resp); 
		try 
		{
			String userId = HttpClientUtils.getUserIdFromHttpRequest(req);
			productRecommendationAnalyticsAPIController.execute();
			List<RawDataDT> googleAnalyticsList = ((ProductRecommendationAnalyticsAPIResponse)productRecommendationAnalyticsAPIController.getResponse()).getGoogleAnalyticsList().get(0).getRawDataList();
			trainTheNewModel(req,googleAnalyticsList,userId);

		} catch (Exception e) 
		{
			log.severe("ProductRecommendationServlet failed " +  e.getMessage());
		}

	}

	/**
	 *@throws Exception 
	 * @Author:      Moshe Herskovits
	 *@Date:        Jun 2, 2016
	 *@Description:
	 */
	private void trainTheNewModel(HttpServletRequest req, List<RawDataDT> rawDataList,String userId) throws Exception 
	{
		IController predictionController = new PredictionController(req.getInputStream(),userId, BUCKET_NAME,FILE_NAME,rawDataList);
		predictionController.execute();
		
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
