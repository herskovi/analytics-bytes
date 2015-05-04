package main.java.com.analytic.reports.restful.services;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import com.sun.jersey.spi.dispatch.RequestDispatcher;

import main.java.com.analytic.reports.controller.response.EmailNotificationResponse;
import main.java.com.analytic.reports.jdo.dao.CustomerDAO;
import main.java.com.analytic.reports.jdo.dao.UserStatusDAO;
import main.java.com.analytic.reports.jdo.model.Customer;
import main.java.com.analytic.reports.jdo.model.UserStatus;
import main.java.com.analytic.reports.utils.consts.RequestDispatcherConsts;

import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;




@Path("/recieveConfirmationEmail")

public class EmailNotificationService extends Application{
 @GET
 @Produces("text/plain")
 @Path("/employee")
 public String getEmployee() {
        return "Hello World!";
    }
}


/**
public class EmailNotificationService extends Application 
{


	//	@Path("/recieveConfirmationEmail")
	////	@Produces("application/json")
	//	@PUT
	//	  @Consumes(MediaType.APPLICATION_XML)
	//	  public Response putTodo(JAXBElement<Todo> todo) {
	//	    Todo c = todo.getValue();
	//	    return putAndGetResponse(c);
	//	  }

	@PUT
	@Produces(MediaType.TEXT_HTML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Path("/")
	public void newTodo(@FormParam("email") String email,
			@FormParam("uniqueKey") String uniqueKey,
			@Context HttpServletResponse servletResponse) throws IOException 
	{
		//UserStatus userStatus = new UserStatus(email,uniqueKey);
		String uniqueKeyFromDB = UserStatusDAO.getUniquKeyInformationByEmail(email);
		if (uniqueKey != null && uniqueKey.equals(uniqueKeyFromDB))
		{
			Customer customer = CustomerDAO.getCustomerInformationByUserID(email);
			customer.setUserSts("2"); // User confirmed his mail and verified his mail.   
		}

		servletResponse.sendRedirect(RequestDispatcherConsts.FROM_LOGIN_TO_DASHBOARD);
	}
	
	@GET
	@Path("/getCustomerInfo")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Customer getEmailNotification(@FormParam("email") String email,
			@Context HttpServletResponse servletResponse) throws IOException 
	{
		//UserStatus userStatus = new UserStatus(email,uniqueKey);
			Customer customer = CustomerDAO.getCustomerInformationByUserID(email);
			return customer;
	
	}
}
**/
