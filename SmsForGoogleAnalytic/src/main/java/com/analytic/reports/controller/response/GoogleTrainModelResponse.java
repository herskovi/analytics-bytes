/**
 * 
 */
package main.java.com.analytic.reports.controller.response;

import java.util.ArrayList;
import java.util.List;

import com.google.api.services.prediction.model.Analyze.DataDescription.Features;

import main.java.com.analytic.reports.datatypes.GoogleAnalyticsDT;


/**
 * @author admin
 * Jul 23, 2014
 */
public class GoogleTrainModelResponse extends BaseResponse 
{
	private String numberOfInstances="";
	private String numberOfFailures="";
	private String numberOfSuccess="";
	private String trainingStatus="";
	private String modelDescription="";
	private String dataDescription="";
	private String weightedAccuracy="";

	private List<Features> featureList = new ArrayList<Features>();
	private List<GoogleAnalyticsDT> googleAnalyticsList = new ArrayList<GoogleAnalyticsDT>();
		
	public String getNumberOfInstances() {
		return numberOfInstances;
	}
	public void setNumberOfInstances(String numberOfInstances) {
		this.numberOfInstances = numberOfInstances;
	}
	
	public String getNumberOfFailures() {
		return numberOfFailures;
	}
	public void setNumberOfFailures(String numberOfFailures) {
		this.numberOfFailures = numberOfFailures;
	}
	public String getNumberOfSuccess() {
		return numberOfSuccess;
	}
	public void setNumberOfSuccess(String numberOfSuccess) {
		this.numberOfSuccess = numberOfSuccess;
	}
	public String getTrainingStatus() {
		return trainingStatus;
	}
	public void setTrainingStatus(String trainingStatus) {
		this.trainingStatus = trainingStatus;
	}
	public String getModelDescription() {
		return modelDescription;
	}
	public void setModelDescription(String modelDescription) {
		this.modelDescription = modelDescription;
	}
	public List<Features> getFeatureList() {
		return featureList;
	}
	public void setFeatureList(List<Features> featureList) {
		this.featureList = featureList;
	}
	public List<GoogleAnalyticsDT> getGoogleAnalyticsList() {
		return googleAnalyticsList;
	}
	public void setGoogleAnalyticsList(List<GoogleAnalyticsDT> googleAnalyticsList) {
		this.googleAnalyticsList = googleAnalyticsList;
	}
	@Override
	public String getMessage() 
	{
		return this.responseMessage;
	}
	@Override
	public void setMessage(String message) 
	{
		this.responseMessage = message;
	}
	public String getWeightedAccuracy() {
		return weightedAccuracy;
	}
	public void setWeightedAccuracy(String weightedAccuracy) {
		this.weightedAccuracy = weightedAccuracy;
	}
	public String getDataDescription() {
		return dataDescription;
	}
	public void setDataDescription(String dataDescription) {
		this.dataDescription = dataDescription;
	}
	
	
	
}
