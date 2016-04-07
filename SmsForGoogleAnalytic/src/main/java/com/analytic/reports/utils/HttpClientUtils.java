package main.java.com.analytic.reports.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import main.java.com.analytic.reports.datatypes.TokenParamsDT;

import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;


public class HttpClientUtils 
{

	public static String invokeHttpRequestPost(TokenParamsDT tokenParamsDT) {

		String response = null;
		try {

			// prepare call
			HttpClient client = new DefaultHttpClient();
			HttpPost post = new HttpPost(tokenParamsDT.getUrl());
			post.setHeader("Content-Type", "application/x-www-form-urlencoded");
			List <NameValuePair> nvps = new ArrayList <NameValuePair>();
			nvps.add(new BasicNameValuePair("code",tokenParamsDT.getCode()));
			nvps.add(new BasicNameValuePair("client_id", tokenParamsDT.getClientId()));
			nvps.add(new BasicNameValuePair("client_secret", tokenParamsDT.getClientSecret()));
			nvps.add(new BasicNameValuePair("redirect_uri", tokenParamsDT.getRedirectUri()));
			nvps.add(new BasicNameValuePair("grant_type", tokenParamsDT.getGrantType()));
			post.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));
			// make the call
			ResponseHandler<String> responseHandler = new BasicResponseHandler();
			response = client.execute(post, responseHandler);


		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}
	
	/**
	 * 
	 *@Author:      Moshe Herskovits
	 *@Date:        Aug 30, 2014
	 *@Description: Populate Support Data Type From Http Request
	 */

	public static String getUserIdFromHttpRequest(HttpServletRequest req) 
	{
		String userId = req.getParameter("email_address");
		return userId;
	}


	
	
}