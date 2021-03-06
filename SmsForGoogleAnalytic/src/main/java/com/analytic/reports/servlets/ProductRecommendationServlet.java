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
import main.java.com.analytic.reports.controller.PredictionController;
import main.java.com.analytic.reports.controller.ProductRecommendationAnalyticsAPIController;
import main.java.com.analytic.reports.controller.response.ProductRecommendationAnalyticsAPIResponse;
import main.java.com.analytic.reports.datatypes.RawDataDT;
import main.java.com.analytic.reports.interfaces.IController;
import main.java.com.analytic.reports.jdo.model.Customer;
import main.java.com.analytic.reports.utils.AnalyticUtils;
import main.java.com.analytic.reports.utils.HttpClientUtils;
import main.java.com.analytic.reports.utils.URLUtils;
import main.java.com.analytic.reports.utils.consts.GoogleCloudStorageConsts;
import main.java.com.analytic.reports.utils.consts.RequestDispatcherConsts;

import com.csvreader.CsvWriter;
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
			//writeResponseIntoJson(resp, productRecommendationAnalyticsAPIController); TODO - Move it to different servlet.
			List<RawDataDT> googleAnalyticsList = ((ProductRecommendationAnalyticsAPIResponse)productRecommendationAnalyticsAPIController.getResponse()).getGoogleAnalyticsList().get(0).getRawDataList();
			String emailAddress = HttpClientUtils.getUserIdFromHttpRequest(req);
			writeToGoogleCloudStorage(req,googleAnalyticsList,emailAddress);// - FIXME - Remove It After debug next jsp. 
			//trainTheNewModel(req,googleAnalyticsList,userId); TODO - Move it to different sevlet that train model with specific model
			req.getSession().setAttribute("userid", emailAddress);
			getServletContext().getRequestDispatcher(RequestDispatcherConsts.FROM_WIZARD_PRODCUT_RECOMMENDATION_UPLOAD_TO_WIZARD_PRODCUT_RECOMMENDATION_MODEL_TRAINNING).forward(req, resp);


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
		String fileName = GoogleCloudStorageConsts.FILE_NAME_PREFIX + userId + GoogleCloudStorageConsts.FILE_NAME_SUFFIX;
		IController predictionController = new PredictionController(req.getInputStream(),userId, GoogleCloudStorageConsts.BUCKET_NAME,fileName,rawDataList);
		predictionController.execute();
		
	}

	/**
	 *@param req 
	 * @Author:      Moshe Herskovits
	 *@Date:        May 30, 2016
	 *@Description: Write to CSV All RawData
	 */
	private void writeToGoogleCloudStorage(HttpServletRequest req, List<RawDataDT> rawDataList,String userId) 
	{
		String fileName = GoogleCloudStorageConsts.FILE_NAME_PREFIX + userId + GoogleCloudStorageConsts.FILE_NAME_SUFFIX;
		 
		try
		{
			IController gcStorageController = new GoogleCloudStorageController(req.getInputStream(),userId, GoogleCloudStorageConsts.BUCKET_NAME,fileName,rawDataList,isFileNameExistsingoogleCloudStorage());
			gcStorageController.execute();
		}
		
		catch (Exception e)
		{
			log.severe("Raw Data was not uploaded into Google Cloud Storage ");
		}
	}

	/**
	 * 
	 *@Author:      Moshe Herskovits
	 *@Date:        Jun 9, 2016
	 *@Description:
	 */
	
	private boolean isFileNameExistsingoogleCloudStorage() 
	{
		return false;
	}

	/**
	 * 
	 *@Author:      Moshe Herskovits
	 *@Date:        Mar 13, 2016
	 *@Description:
	 */

	private void writeResponseIntoJson(HttpServletResponse resp, IController productRecommendationAnalyticsAPIController) throws IOException {
		Gson gson = new Gson();
		String json = gson.toJson(((ProductRecommendationAnalyticsAPIResponse)productRecommendationAnalyticsAPIController.getResponse()).getGoogleAnalyticsList().get(0).getRawDataList());
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
		String emailAddress = HttpClientUtils.getUserIdFromHttpRequest(req);
		String startDate =  HttpClientUtils.getStartDateForGoogleAnalyticsFromHttpRequest(req);
		return new ProductRecommendationAnalyticsAPIController(emailAddress,startDate,URLUtils.isServerRunningInLocalMode(req.getRequestURL().toString()));
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
