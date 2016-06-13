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
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.ServletInputStream;

import main.java.com.analytic.reports.controller.response.GoogleCloudStorageResponse;
import main.java.com.analytic.reports.controller.response.ReadGoogleCloudStorageResponse;
import main.java.com.analytic.reports.datatypes.RawDataDT;
import main.java.com.analytic.reports.interfaces.IResponse;
import main.java.com.analytic.reports.utils.CredentialUtils;
import main.java.com.analytic.reports.utils.HttpClientUtils;
import main.java.com.analytic.reports.utils.StorageUtils;
import main.java.com.analytic.reports.utils.consts.GoogleCloudStorageConsts;

import com.google.api.client.http.InputStreamContent;
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
public class GoogleCloudStorageController extends BaseController {

	public static final boolean SERVE_USING_BLOBSTORE_API = false;
	private String userId ="";
	private String bucketName ="";
	private String fileName ="";
	GoogleCloudStorageResponse gcStorageResponse= null;
	ServletInputStream inputStream = null;
	List<RawDataDT> rawDataList = null;
	private static final Logger log = Logger.getLogger(GoogleCloudStorageController.class.getName());
	private boolean isUpdateMode = false; 
	/**Used below to determine the size of chucks to read in. Should be > 1kb and < 10MB */
	private static final int BUFFER_SIZE = 2 * 1024 * 1024;



	/**
	 * @param bucketName
	 * @param objectName
	 * @throws IOException 
	 */
	public GoogleCloudStorageController(ServletInputStream inputStream,String userId, String bucketName, String fileName, List<RawDataDT> rawDataList, boolean isUpdateMode) throws IOException 
	{
		super();
		this.userId = userId;
		this.bucketName = bucketName;
		this.fileName = fileName;
		this.inputStream= inputStream;
		this.rawDataList = rawDataList;
		this.isUpdateMode = isUpdateMode;
	}

	@Override
	public void execute() throws Exception
	{	
		Storage.Objects.Insert insertObject = null;

		try {

			Storage storage = getStorageService(GoogleCloudStorageConsts.DEFAULT_USER_STORAGE);
			log.info("storage.getBaseUrl() " + storage.getBaseUrl() ); 
//			Bucket bucket = StorageUtils.getBucket(storage, bucketName);
//			System.out.println(bucket.getId());
//			long byteCount = 0;  // size of input stream

			// Knowing the stream length allows server-side optimization, and client-side progress
			// reporting with a MediaHttpUploaderProgressListener.
			// TODO - Adding byteCount 
			//mediaContent.setLength(byteCount);

			StorageObject objectMetadata = null;
			boolean useCustomMetadata = true;
			objectMetadata = new StorageObject()
			// Set the destination object name
			.setName(this.fileName)
			// Set the access control list to publicly read-only
			.setAcl(Arrays.asList(
					new ObjectAccessControl().setEntity("allUsers").setRole("READER")));

			log.info("objectMetadata "); 

			
			StringBuffer csv = new StringBuffer();
			preapreNewDataToCSVFormat(csv);
			
			log.info("preapreNewDataToCSVFormat Finished");		

//			String jsonStr = new Gson().toJson(rawDataList);
//			InputStream is2 = new ByteArrayInputStream(jsonStr.getBytes());
		

			if(isUpdateMode)
			{
				//objectMetadata.setContentDisposition(csv.toString());
				List<RawDataDT> existingRawDataDT = readExistingObjectFromGoogleCloudStorage();
				log.info("convertRawDataArrayListToStringBuffer Start");
				 convertRawDataArrayListToStringBuffer(existingRawDataDT, csv);
				 log.info("convertRawDataArrayListToStringBuffer Finished");
			}
			
			InputStream is3 = new ByteArrayInputStream(csv.toString().getBytes());
			InputStreamContent mediaContent = new InputStreamContent("application/text", is3);
			mediaContent.getType();
			
			
				
			
//			if (!useCustomMetadata) {
//				// If you don't provide metadata, you will have specify the object
//				// name by parameter. You will probably also want to ensure that your
//				// default object ACLs (a bucket property) are set appropriately:
//				// https://developers.google.com/storage/docs/json_api/v1/buckets#defaultObjectAcl
//				insertObject.setName("analyticsbytes_rawdata");
//			}
//
//			// For small files, you may wish to call setDirectUploadEnabled(true), to
//			// reduce the number of HTTP requests made to the server.
//			if (mediaContent.getLength() > 0 && mediaContent.getLength() <= 2 * 1000 * 1000 /* 2MB */) {
//				insertObject.getMediaHttpUploader().setDirectUploadEnabled(true);
//			}
			
			insertObject = storage.objects().insert(GoogleCloudStorageConsts.BUCKET_NAME, objectMetadata,mediaContent);
			

			insertObject.execute();
			
			log.info("GCStorageController is done to write into Storage");		



		}catch(Exception ex)
		{
			log.severe("GCStorageController failed to write into Storage" + ex.getMessage());		
		}
	}
	/**
	 * 
	 *@Author:      Moshe Herskovits
	 *@Date:        Jun 7, 2016
	 *@Description: Prepare new Data to CSV file
	 */

	private void preapreNewDataToCSVFormat(StringBuffer csv) 
	{
		//setHeadersForCSV(csv);
		setContentForCSV(csv);
	}
	
	/**
	 * 
	 *@Author:      Moshe Herskovits
	 *@Date:        Jun 10, 2016
	 *@Description:
	 */

	private void setContentForCSV(StringBuffer csv) 
	{
		for (RawDataDT rawData : rawDataList)
		{
			rawData.setLabel("FAILURE");
			if ("1".equals(rawData.getGoal5Completions()) || "1".equals(rawData.getGoal4Completions()) 
					|| "1".equals(rawData.getGoal3Completions()) || "1".equals(rawData.getGoal2Completions()))
			{
				rawData.setLabel("SUCCESS");	
			}
		
			String[] list = StorageUtils.convertRawDataToString(rawData); //TODO - Support remove attributes 
			//removeAttributes();
			
			csv.append(StorageUtils.convertToCommaDelimited(list));
			csv.append("\n");
		}
	}

	private void setHeadersForCSV(StringBuffer csv) 
	{
		csv.append(StorageUtils.convertToCommaDelimited(GoogleCloudStorageConsts.headers_v1));
		csv.append("\n");
	}
	
	/**
	 *@param csv 
	 * @Author:      Moshe Herskovits
	 *@Date:        Jun 6, 2016
	 *@Description: Convert ArrayList to CSV File
	 */
	private void convertRawDataArrayListToStringBuffer(List<RawDataDT> existingRawDataList, StringBuffer csv) 
	{
		for (RawDataDT newRawData : existingRawDataList)
		{
			String[] list = StorageUtils.convertRawDataToString(newRawData);
			csv.append(StorageUtils.convertToCommaDelimited(list));
			csv.append("\n");
		}
	}

	/**
	 * 
	 *@Author:      Moshe Herskovits
	 *@Date:        Jun 6, 2016
	 *@Description: Read Existing Object From Google Cloud Storage
	 */

	private List<RawDataDT> readExistingObjectFromGoogleCloudStorage() throws IOException, Exception 
	{
		ReadGoogleCloudStorageController readGoogleCloudStorageController = new ReadGoogleCloudStorageController(userId,fileName);
		readGoogleCloudStorageController.execute();
		readGoogleCloudStorageController.getResponse();
		List<RawDataDT> existingRawDataDT =  ((ReadGoogleCloudStorageResponse)readGoogleCloudStorageController.getResponse()).getRawDataList();
		return existingRawDataDT;
	}
	


	/**
	 * 
	 *@Author:      Moshe Herskovits
	 *@Date:        Mar 10, 2016
	 *@Description: Get Analytics Credential
	 */

	private Storage getStorageService(String userId)
			throws IOException {
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
	
	

	/* (non-Javadoc)
	 * @see main.java.com.analytic.reports.interfaces.IController#getResponse()
	 */
	@Override
	public IResponse getResponse() 
	{
		return null;
	}

	/* (non-Javadoc)
	 * @see main.java.com.analytic.reports.interfaces.IController#setResponse(java.lang.String)
	 */
	@Override
	public void setResponse(String message) 
	{

	}

	/* (non-Javadoc)
	 * @see main.java.com.analytic.reports.interfaces.IController#newResponse()
	 */
	@Override
	public IResponse newResponse() 
	{
		return null;
	}
}