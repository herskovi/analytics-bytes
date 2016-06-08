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

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

import javax.servlet.ServletInputStream;

import main.java.com.analytic.reports.controller.response.GCStorageResponse;
import main.java.com.analytic.reports.controller.response.ReadGoogleCloudStorageResponse;
import main.java.com.analytic.reports.datatypes.RawDataDT;
import main.java.com.analytic.reports.interfaces.IResponse;
import main.java.com.analytic.reports.utils.CredentialUtils;
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
public class ReadGoogleCloudStorageController extends BaseController {

	public static final boolean SERVE_USING_BLOBSTORE_API = false;
	public static final boolean IS_APP_ENGINE = true;
	private String userId ="";
	private String fileName ="";
	private IResponse readGoogleCloudStorageResponse= newResponse();
	private static final Logger log = Logger.getLogger(ReadGoogleCloudStorageController.class.getName());

	/**Used below to determine the size of chucks to read in. Should be > 1kb and < 10MB */
	private static final int BUFFER_SIZE = 2 * 1024 * 1024;



	/**
	 * @param bucketName
	 * @param objectName
	 * @throws IOException 
	 */
	public ReadGoogleCloudStorageController(String userId, String fileName) throws IOException 
	{
		super();
		this.userId = userId;
		this.fileName = fileName;
		
	}

	@Override
	public void execute() throws Exception
	{	

		try {

			Storage storage = getStorageService(userId);
			Storage.Objects.Get getObject = storage.objects().get(GoogleCloudStorageConsts.BUCKET_NAME, fileName);
	        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
	        getObject.getMediaHttpDownloader().setDirectDownloadEnabled(!IS_APP_ENGINE);
	        getObject.executeMediaAndDownloadTo(outputStream); 	        
	        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(outputStream.toByteArray())));
	        List<RawDataDT> rawDataList = new ArrayList<RawDataDT>();
	        String line = null;

	        try {
	         while ((line = bufferedReader.readLine()) != null) {

	          RawDataDT rawDataDT =  convertToRawData(line);
	          rawDataList.add(rawDataDT);
	         }

	         ((ReadGoogleCloudStorageResponse) readGoogleCloudStorageResponse).setRawDataList(rawDataList);
	        } catch (IOException e) {
	        	log.severe("ReadGoogleCloudStorageController execute failed while reading from Storage" +e.getMessage());
	        }
	        log.info("ReadGoogleCloudStorageController completed " );



		}catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}


	/**
	 *@Author:      Moshe Herskovits
	 *@Date:        Jun 6, 2016
	 *@Description: convert To RawData From google Cloud Storage
	 */
	private RawDataDT convertToRawData(String line) 
	{
		String[] parts = line.split(",");
		RawDataDT rawData = new RawDataDT(UUID.randomUUID().toString()); //FIXME - Generate Client ID
		rawData.setLabel(parts[0]);
		rawData.setBrowser(parts[1]);
		rawData.setHour(parts[2]);
		rawData.setMinute(parts[3]);
		rawData.setSourceMedium(parts[4]);
		rawData.setCampaign(parts[5]);
		rawData.setCountry(parts[6]);
		rawData.setPagePath(parts[7]);
		rawData.setMobileDeviceInfo(parts[8]);
		rawData.setBrowser(parts[9]);
		rawData.setDeviceCategory(parts[10]);
		rawData.setLandingPagePath(parts[11]);
		return rawData;
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
		return readGoogleCloudStorageResponse;
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
		
		return new ReadGoogleCloudStorageResponse();
	}
}