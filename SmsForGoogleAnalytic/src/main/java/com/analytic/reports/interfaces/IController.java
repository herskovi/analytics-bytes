/**
 * 
 */
package main.java.com.analytic.reports.interfaces;

/**
 * @author admin
 * Jul 19, 2014
 */
public interface IController 
{
	public void execute() throws Exception;
	public IResponse getResponse();
	public void setResponse(String message);
	public IResponse newResponse();

}
