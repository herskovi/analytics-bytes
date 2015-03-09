/**
 * 
 */
package main.java.com.analytic.reports.utils;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import main.java.com.analytic.reports.interfaces.IController;
import main.java.com.analytic.reports.interfaces.IResponse;

import com.google.gson.Gson;

/**
 * @author admin
 * Sep 12, 2014
 */
public class JsonUtils 
{
	
	/**
	 * 
	 *@Author:      Moshe Herskovits
	 *@Date:        Sep 12, 2014
	 *@Description:
	 */

	public void writeMessageResponseIntoJson(HttpServletResponse resp, IResponse iConrollerResponse) throws IOException 
	{
		Gson gson = new Gson();
		String json = gson.toJson(iConrollerResponse);
		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");
		resp.getWriter().write(json);
	}


}
