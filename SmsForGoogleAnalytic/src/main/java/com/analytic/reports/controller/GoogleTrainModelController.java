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

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.ServletInputStream;

import main.java.com.analytic.reports.controller.response.GoogleCloudStorageResponse;
import main.java.com.analytic.reports.controller.response.GoogleTrainModelResponse;
import main.java.com.analytic.reports.datatypes.RawDataDT;
import main.java.com.analytic.reports.interfaces.IModelTrainning;
import main.java.com.analytic.reports.interfaces.IResponse;
import main.java.com.analytic.reports.utils.CredentialUtils;
import main.java.com.analytic.reports.utils.consts.GoogleCloudStorageConsts;

import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.HttpResponseException;
import com.google.api.services.prediction.Prediction;
import com.google.api.services.prediction.Prediction.Trainedmodels.Analyze;
import com.google.api.services.prediction.model.Analyze.DataDescription.Features;
import com.google.api.services.prediction.model.Input;
import com.google.api.services.prediction.model.Input.InputInput;
import com.google.api.services.prediction.model.Insert2;
import com.google.api.services.prediction.model.Output;


/**
 * A simple servlet that proxies reads and writes to its Google Cloud Storage bucket.
 */
@SuppressWarnings("serial")
public class GoogleTrainModelController extends BaseController implements IModelTrainning {

	public static final boolean SERVE_USING_BLOBSTORE_API = false;
	private String userId ="";
	private String bucketName ="";
	private String fileName ="";
	GoogleCloudStorageResponse gcStorageResponse= null;
	ServletInputStream inputStream = null;
	List<RawDataDT> rawDataList = null;
	private static final Logger log = Logger.getLogger(GoogleTrainModelController.class.getName());
	 /**
	   * Be sure to specify the name of your application. If the application name is {@code null} or
	   * blank, the application will log a warning. Suggested format is "MyCompany-ProductName/1.0".
	   */
	  private static final String APPLICATION_NAME = "SWC-AnalyticsBytes/1.0";

	  /** Specify the Cloud Storage location of the training data. */
	  //private static final String STORAGE_DATA_LOCATION = "analyticsbytes/rawData_";
	
	  private static GoogleTrainModelResponse googleTrainModelResponse= new GoogleTrainModelResponse();
	


	/**
	 * @param bucketName
	 * @param objectName
	 * @throws IOException 
	 */
	public GoogleTrainModelController(ServletInputStream inputStream,String userId, String bucketName, String fileName, List<RawDataDT> rawDataList) throws IOException 
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
			com.google.api.services.prediction.Prediction.Trainedmodels.List strList = prediction.trainedmodels().list(GoogleCloudStorageConsts.PROJECT_ID);
			com.google.api.services.prediction.Prediction.Trainedmodels.Analyze an3 = prediction.trainedmodels().analyze(GoogleCloudStorageConsts.PROJECT_ID, GoogleCloudStorageConsts.MODEL_ID + "_" + this.userId);
			try
			{
				an3.execute();
			}catch(Exception ex)
			{
				log.severe("an3.execute(); failed");
			}
			log.info("Delete started ");

			
			prediction.trainedmodels().delete(GoogleCloudStorageConsts.PROJECT_ID, GoogleCloudStorageConsts.MODEL_ID + "_" + this.userId).execute();
			log.info("Delete was completed ");
			com.google.api.services.prediction.Prediction.Trainedmodels.List strList2 = prediction.trainedmodels().list(GoogleCloudStorageConsts.PROJECT_ID);
			
			com.google.api.services.prediction.model.Insert trainingData = new com.google.api.services.prediction.model.Insert();
		    trainingData.setId(GoogleCloudStorageConsts.MODEL_ID + "_" + this.userId);
		    trainingData.setStorageDataLocation(GoogleCloudStorageConsts.BUCKET_NAME + "/" + GoogleCloudStorageConsts.FILE_NAME_PREFIX + this.userId + GoogleCloudStorageConsts.FILE_NAME_SUFFIX);
		    trainingData.setModelType(GoogleCloudStorageConsts.MODEL_TYPE);
		    prediction.trainedmodels().insert(GoogleCloudStorageConsts.PROJECT_ID, trainingData).execute();
		    prediction.trainedmodels().list(GoogleCloudStorageConsts.PROJECT_ID);
		    log.info("Training started.");
		    log.info("Waiting for training to complete");

		    int triesCounter = 0;
		    Insert2 trainingModel;
		    while (triesCounter < 100) {
		      // NOTE: if model not found, it will throw an HttpResponseException with a 404 error
		      try {
		        HttpResponse response = prediction.trainedmodels().get(GoogleCloudStorageConsts.PROJECT_ID, GoogleCloudStorageConsts.MODEL_ID+ "_" + this.userId).executeUnparsed();
		        if (response.getStatusCode() == 200) {
		          trainingModel = response.parseAs(Insert2.class);
		          String trainingStatus = trainingModel.getTrainingStatus();
		          if (trainingStatus.equals("DONE")) {
		           log.info("Training completed.");
		            Collection<Object> values2  = trainingModel.values();
		            System.out.println(trainingModel.getModelInfo());
		            String weightedAccuracy = trainingModel.getModelInfo().getClassWeightedAccuracy();
		            log.info("weightedAccuracy "  + weightedAccuracy);

		            googleTrainModelResponse.setWeightedAccuracy(weightedAccuracy);
		            Analyze an = prediction.trainedmodels().analyze(GoogleCloudStorageConsts.PROJECT_ID, GoogleCloudStorageConsts.MODEL_ID+ "_" + this.userId);
		            
		            
		            List<Features> featureList= an.execute().getDataDescription().getFeatures();
		            googleTrainModelResponse.setFeatureList(featureList);
		            googleTrainModelResponse.setModelDescription(an.execute().getModelDescription().toPrettyString());
		            googleTrainModelResponse.setDataDescription(an.execute().getDataDescription().get("outputFeature").toString());

		            System.out.println(an.execute().getModelDescription());
		            log.info(" an.execute().getModelDescription())" + an.execute().getModelDescription());
		            System.out.println(an.execute().getModelDescription().toPrettyString());
		            log.info(" an.execute().getModelDescription().toPrettyString()); " +an.execute().getModelDescription().toPrettyString());
		            System.out.println(an.execute().getModelDescription().getModelinfo());
		            log.info("an.execute().getDataDescription().toPrettyString())" + an.execute().getDataDescription().toPrettyString());
		            System.out.println(an.execute().getDataDescription().toPrettyString());
		            log.info("an.execute().getDataDescription().toPrettyString())" + an.execute().getDataDescription().toPrettyString());
		            System.out.println(an.execute().getDataDescription().get("outputFeature"));
		            log.info("outputFeatures " + an.execute().getDataDescription().get("outputFeature"));
		            System.out.println(an.execute().getId());
		            log.info("an.execute().getId()); "  +an.execute().getId());
		            //System.out.println(an.execute().getModelDescription().getModelinfo().getModelInfo().getClassInfo().getNames());

		            
		            return;
		          }
		        }
		        response.ignore();
		      } catch (HttpResponseException e) 
		      {
		    	  log.severe(" HttpResponseException " + e.getMessage());
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
		    log.severe("ERROR: Exception " + e.getMessage());
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
		    Output output = prediction.trainedmodels().predict(GoogleCloudStorageConsts.PROJECT_ID, GoogleCloudStorageConsts.MODEL_ID, input).execute();
		    System.out.println("Text: " + text);
		    System.out.println("Predicted language: " + output.getOutputLabel());
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
			log.severe(" EXCEPTION CredentialUtils.loadPrediction(userId); - Exception!!!  " + userId);
		}
		return predictionService;
	}
	


	/* (non-Javadoc)
	 * @see main.java.com.analytic.reports.interfaces.IController#getResponse()
	 */
	@Override
	public IResponse getResponse() {
		// TODO Auto-generated method stub
		return googleTrainModelResponse;
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
		return new GoogleTrainModelResponse();
	}
}