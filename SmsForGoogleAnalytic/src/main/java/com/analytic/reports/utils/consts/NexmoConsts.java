/**
 * 
 */
package main.java.com.analytic.reports.utils.consts;

/**
 * @author admin
 * May 16, 2014
 */
public interface NexmoConsts extends SMSConsts 
{
	public static  final String SMS_VENDOR_PARAM_NAME_API_KEY = "api_key";
	public static  final String SMS_VENDOR_PARAM_VALUE_API_KEY = "ae4bdcb0";

	public static  final String SMS_VENDOR_PARAM_NAME_API_SECRET = "api_secret";
	public static  final String SMS_VENDOR_PARAM_VALUE_API_SECRET = "adeb90bd";
	
	public static  final String SMS_VENDOR_PARAM_NAME_SENDER_ID = "from";
	

	public static  final String SMS_VENDOR_PARAM_VALUE_SENDER_ID = "GA Bytes";
	public static  final String SMS_VENDOR_PARAM_VALUE_SENDER_ID_FOR_USA = "19852083212";
	
	public static final String NEXMO_GATEWAY_URL = "https://rest.nexmo.com/sms/json?";
	//public static final String NEXMO_API_SHORT_CODES_GATEWAY_URL = "https://rest.nexmo.com/sc/us/alert/json?";
	
	public static  final String SMS_VENDOR_PARAM_NAME_SENDER_TYPE = "type";
	public static  final String SMS_VENDOR_PARAM_VALUE_SENDER_TYPE = "unicode";

	//https://rest.nexmo.com/sc/us/alert/json?api_key={$your_key}&api_secret={$your_secret}&to={$to}&key1={$value1}&key2={$value2}

	
	//api_key=ae4bdcb0&api_secret=adeb90bd&from=NEXMO&to=972524265342&text=Welcome+to+Nexmo
	
	

}
