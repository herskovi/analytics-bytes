/**
 * 
 */
package main.java.com.analytic.reports.utils;

import main.java.com.analytic.reports.utils.consts.CredentialConsts;
import main.java.com.analytic.reports.utils.consts.URLConsts;

/**
 * @author admin
 * May 17, 2014
 */
public class URLUtils 
{
	
	/**
	 *@Author:      Moshe Herskovits
	 *@Date:        May 15, 2014
	 *@Description: Build The Url for web registration whcih recieve email and pass it forward
	 */
	public static String buildUrlForLocalMode(String googleAnalyticEmailAddress) 
	{
		String str =  CredentialConsts.GOOGLE_OAUTH_URL_IS_AUTH +  CredentialConsts.HTML_OPERAND_AND + 
				CredentialConsts.GOOGLE_OAUTH_SCOPE_IS_ANALYTICS_READ_ONLY + CredentialConsts.HTML_OPERAND_AND +
				CredentialConsts.GOOGLE_OAUTH_CLIENT_ID + CredentialConsts.HTML_OPERAND_AND +
				CredentialConsts.GOOGLE_OAUTH_STATE_IS_EMPTY + googleAnalyticEmailAddress + CredentialConsts.HTML_OPERAND_AND +
				CredentialConsts.GOOGLE_OAUTH_REDIERCET_URL_IS_LOCAL_HOST + CredentialConsts.HTML_OPERAND_AND +
				CredentialConsts.GOOGLE_OAUTH_RESPONSE_TYPE_IS_CODE + CredentialConsts.HTML_OPERAND_AND +
				CredentialConsts.GOOGLE_OAUTH_ACCESS_TYPE_IS_OFFLINE + CredentialConsts.HTML_OPERAND_AND +
				CredentialConsts.GOOGLE_OAUTH_APPROVAL_PROMPT_IS_FORCE + CredentialConsts.HTML_OPERAND_AND +
				CredentialConsts.GOOGLE_OAUTH_INCLUDE_GRANTED_SCOPE_IS_TRUE;
		return str;

	}
	
	/**
	 *@Author:      Moshe Herskovits
	 *@Date:        May 15, 2016
	 *@Description: Build The Url for web registration whcih recieve email and pass it forward
	 */
	public static String buildUrlForCloudStorageAuthWithLocalMode(String googleAnalyticEmailAddress) 
	{
		String str =  CredentialConsts.GOOGLE_OAUTH_URL_IS_AUTH +  CredentialConsts.HTML_OPERAND_AND + 
				CredentialConsts.GOOGLE_OAUTH_SCOPE_IS_GOOGLE_CLOUD_MANAGER + CredentialConsts.HTML_OPERAND_AND +
				CredentialConsts.GOOGLE_OAUTH_CLIENT_ID + CredentialConsts.HTML_OPERAND_AND +
				CredentialConsts.GOOGLE_OAUTH_STATE_IS_EMPTY + googleAnalyticEmailAddress + CredentialConsts.HTML_OPERAND_AND +
				CredentialConsts.GOOGLE_OAUTH_REDIERCET_URL_IS_LOCAL_HOST + CredentialConsts.HTML_OPERAND_AND +
				CredentialConsts.GOOGLE_OAUTH_RESPONSE_TYPE_IS_CODE + CredentialConsts.HTML_OPERAND_AND +
				CredentialConsts.GOOGLE_OAUTH_ACCESS_TYPE_IS_OFFLINE + CredentialConsts.HTML_OPERAND_AND +
				CredentialConsts.GOOGLE_OAUTH_APPROVAL_PROMPT_IS_FORCE + CredentialConsts.HTML_OPERAND_AND +
				CredentialConsts.GOOGLE_OAUTH_INCLUDE_GRANTED_SCOPE_IS_TRUE;
		return str;

	}
	
	/**
	 *@Author:      Moshe Herskovits
	 *@Date:        May 15, 2014
	 *@Description: Build The Url for web registration whcih recieve email and pass it forward
	 */
	
	public static String buildUrlForServerMode(String googleAnalyticEmailAddress) 
	{
		String str =  CredentialConsts.GOOGLE_OAUTH_URL_IS_AUTH +  CredentialConsts.HTML_OPERAND_AND + 
				CredentialConsts.GOOGLE_OAUTH_SCOPE_IS_ANALYTICS_READ_ONLY + CredentialConsts.HTML_OPERAND_AND +
				CredentialConsts.GOOGLE_OAUTH_CLIENT_ID + CredentialConsts.HTML_OPERAND_AND +
				CredentialConsts.GOOGLE_OAUTH_STATE_IS_EMPTY + googleAnalyticEmailAddress + CredentialConsts.HTML_OPERAND_AND +
				CredentialConsts.GOOGLE_OAUTH_REDIERCET_URL_IS_GAE_HOST + CredentialConsts.HTML_OPERAND_AND +
				CredentialConsts.GOOGLE_OAUTH_RESPONSE_TYPE_IS_CODE + CredentialConsts.HTML_OPERAND_AND +
				CredentialConsts.GOOGLE_OAUTH_ACCESS_TYPE_IS_OFFLINE + CredentialConsts.HTML_OPERAND_AND +
				CredentialConsts.GOOGLE_OAUTH_APPROVAL_PROMPT_IS_FORCE + CredentialConsts.HTML_OPERAND_AND +
				CredentialConsts.GOOGLE_OAUTH_INCLUDE_GRANTED_SCOPE_IS_TRUE;
		return str;

	}
	
	/**
	 *@Author:      Moshe Herskovits
	 *@Date:        May 31, 2016
	 *@Description: Build The Url for web registration whcih recieve email and pass it forward
	 */
	
	public static String buildUrlForCloudStorageServerMode(String googleAnalyticEmailAddress) 
	{
		String str =  CredentialConsts.GOOGLE_OAUTH_URL_IS_AUTH +  CredentialConsts.HTML_OPERAND_AND + 
				CredentialConsts.GOOGLE_OAUTH_SCOPE_IS_GOOGLE_CLOUD_MANAGER + CredentialConsts.HTML_OPERAND_AND +
				CredentialConsts.GOOGLE_OAUTH_CLIENT_ID + CredentialConsts.HTML_OPERAND_AND +
				CredentialConsts.GOOGLE_OAUTH_STATE_IS_EMPTY + googleAnalyticEmailAddress + CredentialConsts.HTML_OPERAND_AND +
				CredentialConsts.GOOGLE_OAUTH_REDIERCET_URL_IS_GAE_HOST + CredentialConsts.HTML_OPERAND_AND +
				CredentialConsts.GOOGLE_OAUTH_RESPONSE_TYPE_IS_CODE + CredentialConsts.HTML_OPERAND_AND +
				CredentialConsts.GOOGLE_OAUTH_ACCESS_TYPE_IS_OFFLINE + CredentialConsts.HTML_OPERAND_AND +
				CredentialConsts.GOOGLE_OAUTH_APPROVAL_PROMPT_IS_FORCE + CredentialConsts.HTML_OPERAND_AND +
				CredentialConsts.GOOGLE_OAUTH_INCLUDE_GRANTED_SCOPE_IS_TRUE;
		return str;

	}
	

	/**
	 *@Author:      Moshe Herskovits
	 *@Date:        May 17, 2014
	 *@Description: Return true if server is running In Local Mode
	 */
	
	public static boolean isServerRunningInLocalMode(String url) 
	{
		return (url.contains((URLConsts.URL_LOCAL_HOST)) ? true : false);		
	}
	
	/**
	 *@Author:      Moshe Herskovits
	 *@Date:        May 17, 2014
	 *@Description: Build URL for Local or Server Mode according to isLocalMode Input
	 */
	
	public static String buildUrl(String googleAnalyticEmailAddress, boolean isLocalMode) 
	{
		return (isLocalMode ? buildUrlForLocalMode(googleAnalyticEmailAddress) : buildUrlForServerMode(googleAnalyticEmailAddress));
	}

	/**
	 *@Author:      Moshe Herskovits
	 *@Date:        May 17, 2014
	 *@Description: Build URL for Local or Server Mode according to isLocalMode Input
	 */
	
	public static String buildCloudStorageUrl(String googleAnalyticEmailAddress, boolean isLocalMode) 
	{
		return (isLocalMode ? buildUrlForCloudStorageAuthWithLocalMode(googleAnalyticEmailAddress) : buildUrlForCloudStorageServerMode(googleAnalyticEmailAddress));
	}
	

}
