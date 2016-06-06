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
import main.java.com.analytic.reports.controller.ReadGoogleCloudStorageController;
import main.java.com.analytic.reports.controller.response.ProductRecommendationAnalyticsAPIResponse;
import main.java.com.analytic.reports.controller.response.ReadGoogleCloudStorageResponse;
import main.java.com.analytic.reports.datatypes.RawDataDT;
import main.java.com.analytic.reports.interfaces.IController;
import main.java.com.analytic.reports.jdo.model.Customer;
import main.java.com.analytic.reports.utils.AnalyticUtils;
import main.java.com.analytic.reports.utils.HttpClientUtils;
import main.java.com.analytic.reports.utils.consts.GoogleCloudStorageConsts;
import main.java.com.analytic.reports.utils.consts.RequestDispatcherConsts;

import com.csvreader.CsvWriter;
import com.google.api.services.analytics.Analytics;
import com.google.api.services.analytics.model.GaData;
import com.google.gson.Gson;

@SuppressWarnings({ "serial", "unused" })
public class ReadGoogleCloudStorageServlet extends HttpServlet 
{
	private static final Logger log = Logger.getLogger(ReadGoogleCloudStorageServlet.class.getName());
	


	/**
	 * 
	 */

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException 
	{
		IController readGoogleCloudStorageController = getController(req,resp); 
		try 
		{
			readGoogleCloudStorageController.execute();
			writeResponseIntoJson(resp, readGoogleCloudStorageController); 


		} catch (Exception e) 
		{
			log.severe("ReadGoogleCloudStorageServlet failed " +  e.getMessage());
		}

	}
	
	/**
	 * 
	 *@Author:      Moshe Herskovits
	 *@Date:        June 6, 2016
	 *@Description: 
	 */

	private void writeResponseIntoJson(HttpServletResponse resp, IController readGoogleCloudStorageController) throws IOException {
		Gson gson = new Gson();
		String json = gson.toJson(((ReadGoogleCloudStorageResponse)readGoogleCloudStorageController.getResponse()).getRawDataList());
		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");
		resp.getWriter().write(json);
	}


	/**
	 *@throws IOException 
	 * @Author:      Moshe Herskovits
	 *@Date:        Mar 13, 2016
	 *@Description: get Daily SMS Controller
	 */
	private IController getController(HttpServletRequest req, HttpServletResponse resp) throws IOException 
	{
		String userId = HttpClientUtils.getUserIdFromHttpRequest(req);	
		String fileName = GoogleCloudStorageConsts.FILE_NAME_PREFIX + userId + GoogleCloudStorageConsts.FILE_NAME_SUFFIX;
		return new ReadGoogleCloudStorageController(userId,fileName);
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
