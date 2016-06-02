/**
 * 
 */
package main.java.com.analytic.reports.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

import main.java.com.analytic.reports.datatypes.GoalDT;
import main.java.com.analytic.reports.utils.consts.AnalyticConsts;

import com.google.api.client.http.InputStreamContent;
import com.google.api.client.util.ArrayMap;
import com.google.api.services.analytics.Analytics;
import com.google.api.services.analytics.Analytics.Data;
import com.google.api.services.analytics.Analytics.Data.Ga;
import com.google.api.services.analytics.Analytics.Data.Ga.Get;
import com.google.api.services.analytics.Analytics.Data.Mcf;
import com.google.api.services.analytics.model.Accounts;
import com.google.api.services.analytics.model.GaData;
import com.google.api.services.analytics.model.Profiles;
import com.google.api.services.analytics.model.Webproperties;
import com.google.api.services.storage.Storage;
import com.google.api.services.storage.model.Bucket;
import com.google.api.services.storage.model.ObjectAccessControl;
import com.google.api.services.storage.model.StorageObject;

/**
 * @author admin
 * May 15, 2014
 */
public class StorageUtils 
{
	private static final Logger log = Logger.getLogger(StorageUtils.class.getName());



	
	public static Bucket getBucket(Storage storageClientService, String bucketName) throws IOException, GeneralSecurityException 
	{
	   // Storage client = StorageFactory.getService();
	    Storage.Buckets.List listBuckets = storageClientService.buckets().list("dailyreportbysmsforga.appspot.com");
	    Storage.Buckets.List listBuckets1 = storageClientService.buckets().list("analyticsbytes");
	    Storage.Buckets.List listBuckets2 = storageClientService.buckets().list("XXXSW");


	    Storage.Buckets.Get bucketRequest = storageClientService.buckets().get(bucketName);
	    // Fetch the full set of the bucket's properties (e.g. include the ACLs in the response)
	    bucketRequest.setProjection("full");
	    return bucketRequest.execute();
	 }
	
	/**
	   * Uploads data to an object in a bucket.
	   *
	   * @param name the name of the destination object.
	   * @param contentType the MIME type of the data.
	   * @param file the file to upload.
	   * @param bucketName the name of the bucket to create the object in.
	   */
	  public static void uploadFile(
	      String name, String contentType, File file, String bucketName)
	      throws IOException, GeneralSecurityException
	  {

	    InputStreamContent contentStream = new InputStreamContent(
	        contentType, new FileInputStream(file));
	    // Setting the length improves upload performance
	    contentStream.setLength(file.length());
	    StorageObject objectMetadata = new StorageObject()
	        // Set the destination object name
	        .setName(name)
	        // Set the access control list to publicly read-only
	        .setAcl(Arrays.asList(
	            new ObjectAccessControl().setEntity("allUsers").setRole("READER")));

	    // Do the insert
	    Storage client = StorageFactory.getService();
	    Storage.Objects.Insert insertRequest = client.objects().insert(
	        bucketName, objectMetadata, contentStream);

	    insertRequest.execute();
	  }

	
	
	
	

}
