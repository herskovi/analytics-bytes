/**
 * 
 */
package main.java.com.analytic.reports.utils.consts;

/**
 * @author admin
 * Jan 4, 2015
 */
public interface PayPalConsts extends GeneralConsts 
{	
	public static final String PAYMENT_DATA_TRANSFER_TOKEN = "x_w1xr0Do3TjXej7CD6QBYbxNPqxAUTzT7-XAUQh-Th8-bx7cERKs1bG3ay";
	public static final String REDIRECT_URL_SANDBOX = "https://www.sandbox.paypal.com/cgi-bin/webscr?cmd=_express-checkout&token=";
	public static final String REDIRECT_URL = "https://www.paypal.com/cgi-bin/webscr?cmd=_express-checkout&token=";
	public static final String AMOUNT = "amount";
	public static final String RETURN_URL = "http://www.analyticsbytes.com/private/orderConfirmation";
	public static final String CANCEL_URL = "http://www.analyticsbytes.com/private/dashboard.jsp";
	public static final String PRODUCT_NAME = "Analytics Bytes";
	public static final String TOKEN = "TOKEN";
	public static final String ACK = "ACK";
	public static final String SUCCESS = "Success";

	
	 
}
