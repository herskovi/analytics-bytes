/*
 * Copyright 2013 Google Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package main.java.com.analytic.reports.controller;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.ServletInputStream;

import main.java.com.analytic.reports.controller.response.GCStorageResponse;
import main.java.com.analytic.reports.datatypes.RawDataDT;
import main.java.com.analytic.reports.interfaces.IResponse;
import main.java.com.analytic.reports.utils.CredentialUtils;
import main.java.com.analytic.reports.utils.StorageUtils;

import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.HttpResponseException;
import com.google.api.client.http.InputStreamContent;
import com.google.api.services.prediction.Prediction;
import com.google.api.services.prediction.Prediction.Trainedmodels.Insert;
import com.google.api.services.prediction.model.Input;
import com.google.api.services.prediction.model.Input.InputInput;
import com.google.api.services.prediction.model.Insert2;
import com.google.api.services.prediction.model.Output;
import com.google.api.services.storage.Storage;
import com.google.api.services.storage.model.Bucket;
import com.google.api.services.storage.model.ObjectAccessControl;
import com.google.api.services.storage.model.StorageObject;
import com.google.appengine.tools.cloudstorage.GcsService;
import com.google.appengine.tools.cloudstorage.GcsServiceFactory;
import com.google.appengine.tools.cloudstorage.RetryParams;
import com.google.gson.Gson;


/**
 * A simple servlet that proxies reads and writes to its Google Cloud Storage bucket.
 */
@SuppressWarnings("serial")
public class PredictionController extends BaseController {

	public static final boolean SERVE_USING_BLOBSTORE_API = false;
	private String userId ="";
	private String bucketName ="";
	private String fileName ="";
	GCStorageResponse gcStorageResponse= null;
	ServletInputStream inputStream = null;
	List<RawDataDT> rawDataList = null;
	private static final Logger log = Logger.getLogger(PredictionController.class.getName());
	 /**
	   * Be sure to specify the name of your application. If the application name is {@code null} or
	   * blank, the application will log a warning. Suggested format is "MyCompany-ProductName/1.0".
	   */
	  private static final String APPLICATION_NAME = "SWC-AnalyticsBytes/1.0";

	  /** Specify the Cloud Storage location of the training data. */
	  private static final String STORAGE_DATA_LOCATION = "analyticsbytes/rawData_test2.txt";
	  private static final String MODEL_ID = "analyticsbytesprediction";
	  private static final String PROJECT_ID = "dailyreportbysmsforga";
	


	/**
	 * @param bucketName
	 * @param objectName
	 * @throws IOException 
	 */
	public PredictionController(ServletInputStream inputStream,String userId, String bucketName, String fileName, List<RawDataDT> rawDataList) throws IOException 
	{
		super();
		this.userId = userId;
		this.bucketName = bucketName;
		this.fileName = fileName;
		this.inputStream= inputStream;
		this.rawDataList = rawDataList;
	}

	@Override
	public void execute() throws Exception
	{	

		try {

			
			Prediction prediction = getPredictionService(userId); 
		    train(prediction);
		    predict(prediction,"Android Browser,02,38,google / cpc,AnalyticsBytes Search only,Philippines,/login,null,Android Browser,mobile,/,/login");
		    predict(prediction,"Chrome,15,54,Facebook / Paid,AnalyticsBytes Starter,Poland,/register,null,Chrome,mobile,/,/whatwedo");
		    predict(prediction,"Android Browser,08,31,google / cpc,AnalyticsBytes Search only,India,/terms/,null,Android Browser,mobile,/,/register");
		    predict(prediction,"Chrome,17,32,google / organic,(not set),Israel,/whatwedo,null,Chrome,desktop,/,/mobileselectionwizard");
		    

		    

//			String body = "";
//			Insert insert = null;
//			insert.setFields(body);
//			
			//prediction.trainedmodels().insert("dailyreportbysmsforga", Insert.);

//			
//			train a new classification model
//api.trainedmodels().insert(project=project_id, body={
//    'id': analyticsbytesprediction,
//    'storageDataLocation': 'machine-learning-dataset/dataset.csv',
//    'modelType': 'CLASSIFICATION'
//}).execute()

			System.out.println("DONE");



		}catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}


	/**
	 *@Author:      Moshe Herskovits
	 *@Date:        Jun 2, 2016
	 *@Description:
	 */
	private void train(Prediction prediction) 
	{
		
		
				
		//com.google.api.services.prediction.model.Insert trainingData = null;
		try{
		 

			com.google.api.services.prediction.model.Insert trainingData = new com.google.api.services.prediction.model.Insert();
		    trainingData.setId(MODEL_ID);
		    trainingData.setStorageDataLocation(STORAGE_DATA_LOCATION);
		    trainingData.setModelType("classification");
		    prediction.trainedmodels().insert(PROJECT_ID, trainingData).execute();
		    System.out.println("Training started.");
		    System.out.print("Waiting for training to complete");
		    System.out.flush();

		    int triesCounter = 0;
		    Insert2 trainingModel;
		    while (triesCounter < 100) {
		      // NOTE: if model not found, it will throw an HttpResponseException with a 404 error
		      try {
		        HttpResponse response = prediction.trainedmodels().get(PROJECT_ID, MODEL_ID).executeUnparsed();
		        if (response.getStatusCode() == 200) {
		          trainingModel = response.parseAs(Insert2.class);
		          String trainingStatus = trainingModel.getTrainingStatus();
		          if (trainingStatus.equals("DONE")) {
		            System.out.println();
		            System.out.println("Training completed.");
		            System.out.println(trainingModel.getModelInfo());
		            return;
		          }
		        }
		        response.ignore();
		      } catch (HttpResponseException e) {
		      }

		      try {
		        // 5 seconds times the tries counter
		        Thread.sleep(5000 * (triesCounter + 1));
		      } catch (InterruptedException e) {
		        break;
		      }
		      System.out.print(".");
		      System.out.flush();
		      triesCounter++;
		    }
		    log.severe("ERROR: training not completed.");
		}catch (Exception e) 
		{
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 
	 *@Author:      Moshe Herskovits
	 *@Date:        Jun 2, 2016
	 *@Description: Predict based on trainning model_id
	 */
	
	 private static void predict(Prediction prediction, String text) throws IOException {
		    Input input = new Input();
		    InputInput inputInput = new InputInput();
		    inputInput.setCsvInstance(Collections.<Object>singletonList(text));
		    input.setInput(inputInput);
		    Output output = prediction.trainedmodels().predict(PROJECT_ID, MODEL_ID, input).execute();
		    System.out.println("Text: " + text);
		    System.out.println("Predicted language: " + output.getOutputLabel());
		  }


	/**
	 * 
	 *@Author:      Moshe Herskovits
	 *@Date:        Mar 10, 2016
	 *@Description: Get Analytics Credential
	 */

	private Storage getStorageService(String userId) throws IOException 
	{
		Storage storageService = null;
		try
		{		
			storageService = CredentialUtils.loadStorage(userId);
		} catch (Exception ex) 
		{
			log.severe(" EXCEPTION CredentialUtils.loadStorage(userId); - Exception!!!  " + userId);
		}
		return storageService;
	}
	
	/**
	 * 
	 *@Author:      Moshe Herskovits
	 *@Date:        Mar 10, 2016
	 *@Description: Get Analytics Credential
	 */

	private Prediction getPredictionService(String userId) throws IOException 
	{
		Prediction predictionService = null;
		try
		{		
			predictionService = CredentialUtils.loadPrediction(userId);
		} catch (Exception ex) 
		{
			log.severe(" EXCEPTION CredentialUtils.loadStorage(userId); - Exception!!!  " + userId);
		}
		return predictionService;
	}
	


	/* (non-Javadoc)
	 * @see main.java.com.analytic.reports.interfaces.IController#getResponse()
	 */
	@Override
	public IResponse getResponse() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see main.java.com.analytic.reports.interfaces.IController#setResponse(java.lang.String)
	 */
	@Override
	public void setResponse(String message) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see main.java.com.analytic.reports.interfaces.IController#newResponse()
	 */
	@Override
	public IResponse newResponse() {
		// TODO Auto-generated method stub
		return null;
	}
}