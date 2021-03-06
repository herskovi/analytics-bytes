package main.java.com.analytic.reports.servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.java.com.analytic.reports.utils.PaypalFunctionsUtils;

@SuppressWarnings("serial")
public class PayPalCheckoutServlet extends HttpServlet 
{

	/*==================================================================
	 PayPal Express Checkout Call
	 ===================================================================
	 */



		public void doGet(HttpServletRequest req, HttpServletResponse resp)
				throws ServletException, IOException {

			// Use "request" to read incoming HTTP headers (e.g. cookies)
			// and HTML form data (e.g. data the user entered and submitted)

			// Use "response" to specify the HTTP response line and headers
			// (e.g. specifying the content type, setting cookies).

			/*
			 *  The paymentAmount is the total value of ' the purchase. ' ' 
			 * TODO:  Enter the total Payment Amount within the quotes. ' example :
			 * paymentAmount = "15.00"; 
			 */
			String paymentAmount =  req.getParameter("amount");


			/*
			 * '------------------------------------ ' The returnURL is the location
			 * where buyers return to when a ' payment has been succesfully
			 * authorized. ' ' This is set to the value entered on the Integration
			 * Assistant '------------------------------------
			 */

			String returnURL = "http://dailyreportbysmsforga.appspot.com/private/orderConfirmation.jsp";

			/*
			 * '------------------------------------ ' The cancelURL is the location
			 * buyers are sent to when they hit the ' cancel button during
			 * authorization of payment during the PayPal flow ' ' This is set to
			 * the value entered on the Integration Assistant
			 * '------------------------------------
			 */
			String cancelURL = "http://dailyreportbysmsforga.appspot.com/private/dashboard.jsp";

			/*
			 * '------------------------------------ ' The items hashmap contains
			 * the details of each item '------------------------------------
			 * TODO: change "item name" to desired item name
			 */

			Map item = new HashMap();
			item.put("name", "SMS For Google Analytics");
			item.put("amt", paymentAmount);
			item.put("qty", "1");

			/*
			 * '------------------------------------ ' Calls the SetExpressCheckout
			 * API call ' ' The SetExpressCheckout function is defined in the file
			 * PayPalFunctions.java,
			 * '-------------------------------------------------
			 */
			PaypalFunctionsUtils ppf = new PaypalFunctionsUtils();
			HashMap nvp = ppf.setExpressCheckout(paymentAmount, returnURL,
					cancelURL, item);
			String strAck = nvp.get("ACK").toString();
			if (strAck != null && strAck.equalsIgnoreCase("Success")) {

				// ' Redirect to paypal.com
				String redirectURL = "https://www.sandbox.paypal.com/incontext?token="
						+ nvp.get("TOKEN").toString();

				resp.sendRedirect(redirectURL);
			} else {
				// Display a user friendly Error on the page using any of the
				// following error information returned by PayPal

				String ErrorCode = nvp.get("L_ERRORCODE0").toString();
				String ErrorShortMsg = nvp.get("L_SHORTMESSAGE0").toString();
				String ErrorLongMsg = nvp.get("L_LONGMESSAGE0").toString();
				String ErrorSeverityCode = nvp.get("L_SEVERITYCODE0").toString();
			}
		}

		public void doPost(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			doGet(request, response);
		}

	}