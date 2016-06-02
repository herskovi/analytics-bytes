package main.java.com.analytic.reports.servlets;

import java.io.IOException;
import java.util.logging.Logger;

import javax.jdo.PersistenceManager;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.PhoneNumberUtil.PhoneNumberFormat;
import com.google.i18n.phonenumbers.Phonenumber.PhoneNumber;

import main.java.com.analytic.reports.exceptions.AppException;
import main.java.com.analytic.reports.interfaces.IValidator;
import main.java.com.analytic.reports.jdo.PMF;
import main.java.com.analytic.reports.jdo.dao.CustomerDAO;
import main.java.com.analytic.reports.jdo.model.Customer;
import main.java.com.analytic.reports.utils.URLUtils;
import main.java.com.analytic.reports.utils.consts.BalanceConsts;
import main.java.com.analytic.reports.utils.consts.RegistrationConsts;
import main.java.com.analytic.reports.utils.consts.RequestDispatcherConsts;
import main.java.com.analytic.reports.validator.RegistrationValidator;


@SuppressWarnings("serial")
public class GoogleCloudStorageServlet extends HttpServlet 
{
	private static final Logger log = Logger
			.getLogger(GoogleCloudStorageServlet.class.getName());

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException 
	{

		String emailAddress = req.getParameter(RegistrationConsts.EMAIL_ADDRESS);//FIXME - Needs to be removed
		boolean isLocalMode = URLUtils.isServerRunningInLocalMode(req.getRequestURL().toString());
		String url = URLUtils.buildCloudStorageUrl(emailAddress, isLocalMode); 
		resp.sendRedirect(url);
		
	}

	
	/**
	 * 
	 * @throws ServletException 
	 * @Author: Moshe Herskovits
	 * @Date: Apr 29, 2014
	 * @Description: Invoke doGet
	 */

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {
		doGet(req, resp);
	}

}
