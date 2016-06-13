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
import main.java.com.analytic.reports.controller.GoogleCloudStorageController;
import main.java.com.analytic.reports.controller.GoogleTrainModelController;
import main.java.com.analytic.reports.controller.PredictionController;
import main.java.com.analytic.reports.controller.ProductRecommendationAnalyticsAPIController;
import main.java.com.analytic.reports.controller.ReadGoogleCloudStorageController;
import main.java.com.analytic.reports.controller.response.ProductRecommendationAnalyticsAPIResponse;
import main.java.com.analytic.reports.controller.response.ReadGoogleCloudStorageResponse;
import main.java.com.analytic.reports.datatypes.RawDataDT;
import main.java.com.analytic.reports.interfaces.IController;
import main.java.com.analytic.reports.interfaces.IResponse;
import main.java.com.analytic.reports.jdo.model.Customer;
import main.java.com.analytic.reports.utils.AnalyticUtils;
import main.java.com.analytic.reports.utils.HttpClientUtils;
import main.java.com.analytic.reports.utils.URLUtils;
import main.java.com.analytic.reports.utils.consts.GoogleCloudStorageConsts;

import com.csvreader.CsvWriter;
import com.google.api.services.analytics.Analytics;
import com.google.api.services.analytics.model.GaData;
import com.google.gson.Gson;

@SuppressWarnings({ "serial", "unused" })
public class TrainModelServlet extends HttpServlet 
{
	private static final Logger log = Logger.getLogger(TrainModelServlet.class.getName());
	


	/**
	 * 
	 */

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException 
	{
		//IController productRecommendationAnalyticsAPIController = getController(req,resp); 
		try 
		{
			String emailAddress = HttpClientUtils.getUserIdFromHttpRequest(req);
			String fileName =  getFileName(emailAddress);
			String modelType = HttpClientUtils.getModelTypewFromHttpRequest(req);
			
			ReadGoogleCloudStorageController readGoogleCloudStorageController = new ReadGoogleCloudStorageController(emailAddress, fileName);
			readGoogleCloudStorageController.execute();
			List<RawDataDT> googleAnalyticsList =  ((ReadGoogleCloudStorageResponse) readGoogleCloudStorageController.getResponse()).getRawDataList();		
			
			trainTheNewModel(req,resp,googleAnalyticsList,emailAddress,modelType);
			
		} catch (Exception e) 
		{
			log.severe("TrainModelServlet failed " +  e.getMessage());
		}

	}

	/**
	 *@throws Exception 
	 * @Author:      Moshe Herskovits
	 *@Date:        Jun 2, 2016
	 *@Description:
	 */
	private void trainTheNewModel(HttpServletRequest req, HttpServletResponse resp,List<RawDataDT> rawDataList,String userId, String modelType) throws Exception 
	{
		String fileName = getFileName(userId);
		IController googleTrainModelController = new GoogleTrainModelController(req.getInputStream(),userId, GoogleCloudStorageConsts.BUCKET_NAME,fileName,rawDataList);
		googleTrainModelController.execute();
		writeResponseIntoJson(resp,googleTrainModelController.getResponse());
		
		
	}
	
	/**
	 * 
	 *@Author:      Moshe Herskovits
	 *@Date:        Jun 10, 2016
	 *@Description: Get File Name from Cloud Storage
	 */

	private String getFileName(String userId) {
		String fileName = GoogleCloudStorageConsts.FILE_NAME_PREFIX + userId + GoogleCloudStorageConsts.FILE_NAME_SUFFIX;
		return fileName;
	}

	

	



//	/**
//	 *@Author:      Moshe Herskovits
//	 *@Date:        Mar 13, 2016
//	 *@Description: get Daily SMS Controller
//	 */
//	private IController getController(HttpServletRequest req, HttpServletResponse resp) 
//	{
//		String userId = HttpClientUtils.getUserIdFromHttpRequest(req);	
//		String startDate = HttpClientUtils.getStartDateForGoogleAnalyticsFromHttpRequest(req);		
//		return new ProductRecommendationAnalyticsAPIController(userId, startDate, URLUtils.isServerRunningInLocalMode(req.getRequestURL().toString()));
//	}

	/**
	 * @Author: Moshe Herskovits
	 * @Date: Mar 13, 2016
	 * @Description: call doGet
	 */

	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException
	{
		doGet(req, resp);
	}
	
	/**
	 * 
	 *@Author:      Moshe Herskovits
	 *@Date:        Mar 13, 2016
	 *@Description: Write ResponseIntoJson
	 */

	private void writeResponseIntoJson(HttpServletResponse resp, IResponse response) throws IOException {
		Gson gson = new Gson();
		String json = gson.toJson(response);
		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");
		resp.getWriter().write(json);
	}


}
