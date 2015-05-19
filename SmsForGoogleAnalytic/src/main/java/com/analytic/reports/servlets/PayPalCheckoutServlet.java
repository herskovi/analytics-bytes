package main.java.com.analytic.reports.servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.java.com.analytic.reports.utils.PaypalFunctionsUtils;
import main.java.com.analytic.reports.utils.URLUtils;
import main.java.com.analytic.reports.utils.consts.PayPalConsts;

@SuppressWarnings("serial")
public class PayPalCheckoutServlet extends HttpServlet 
{

	/*==================================================================
	 PayPal Express Checkout Call
	 ===================================================================
	 */
	



		public void doGet(HttpServletRequest req, HttpServletResponse resp)
				throws ServletException, IOException {

			String paymentAmount =  req.getParameter(PayPalConsts.AMOUNT);
			String returnURL = PayPalConsts.RETURN_URL;
			String cancelURL = PayPalConsts.CANCEL_URL;
			Map item = prepareMap(paymentAmount);

			
			boolean isLocalMode = URLUtils.isServerRunningInLocalMode(req.getRequestURL().toString());
			//boolean isLocalMode = true;
			PaypalFunctionsUtils ppf = new PaypalFunctionsUtils(isLocalMode);
			//invoke PayPal
			HashMap nvp = invokePayPal(paymentAmount, returnURL, cancelURL,
					item, ppf);
			
			String strAck = nvp.get(PayPalConsts.ACK).toString();
			if (strAck != null && strAck.equalsIgnoreCase(PayPalConsts.SUCCESS)) {
				// Redirect to paypal.com or to sandbox.paypal.com
				String redirectURL = getRedirectURL(isLocalMode) + nvp.get(PayPalConsts.TOKEN).toString();
				resp.sendRedirect(redirectURL);
			} else {
				// Display a user friendly Error on the page using any of the
				// following error information returned by PayPal
				//FIXME - Add more informative error

				String ErrorCode = nvp.get("L_ERRORCODE0").toString();
				String ErrorShortMsg = nvp.get("L_SHORTMESSAGE0").toString();
				String ErrorLongMsg = nvp.get("L_LONGMESSAGE0").toString();
				String ErrorSeverityCode = nvp.get("L_SEVERITYCODE0").toString();
			}
		}
	/**
	 * 
	 *@Author:      Moshe Herskovits
	 *@Date:        May 19, 2015
	 *@Description: Invoke PayPal
	 */
	private HashMap invokePayPal(String paymentAmount, String returnURL,
			String cancelURL, Map item, PaypalFunctionsUtils ppf) {
		HashMap nvp = ppf.setExpressCheckout(paymentAmount, returnURL, 	cancelURL, item);
		return nvp;
	}
		
	/**
	 * 
	 *@Author:      Moshe Herskovits
	 *@Date:        May 19, 2015
	 *@Description: Prepare Map for Product
	 */

	private Map prepareMap(String paymentAmount) {
		Map item = new HashMap();
		item.put("name", PayPalConsts.PRODUCT_NAME);
		item.put("amt", paymentAmount);
		item.put("qty", "1");
		return item;
	}

		/**
		 * 
		 *@Author:      Moshe Herskovits
		 *@Date:        May 19, 2015
		 *@Description: Get Redirect URL Based on localMode. If Local Mode is on should go for sandbox while 
		 */
		private String getRedirectURL(boolean isLocalMode) 
		{		
			return (isLocalMode ? PayPalConsts.REDIRECT_URL_SANDBOX : PayPalConsts.REDIRECT_URL);	
		}

		public void doPost(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			doGet(request, response);
		}

	}