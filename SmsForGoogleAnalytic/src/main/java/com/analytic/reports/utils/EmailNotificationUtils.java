package main.java.com.analytic.reports.utils;

import java.util.Properties;
import java.util.logging.Logger;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import main.java.com.analytic.reports.utils.consts.URLConsts;



/**
 * 
 * @author  admin
 * Date:    Sep 4, 2014
 * 			The purpose of this class is to provide in Email Notification platform. 
 *  
 */


public class EmailNotificationUtils 
{
	private static final Logger log = Logger.getLogger(EmailNotificationUtils.class.getName());

	/**
	 * @throws Exception 
	 * 
	 */

	public static void sendCancellationEmailNotification(String userId, String customerName, String uniqueKey) throws Exception
	{
		Properties props = new Properties();
		Session session = Session.getDefaultInstance(props, null); 
		//	        InternetHeaders headers = new InternetHeaders();
		//	        headers.addHeader("Content-type", "text/html; charset=UTF-8");
		//	        String html = "Test\n" + text + "\n<a href='http://test.com'>Test.com</a>";
		//	        MimeBodyPart body = new MimeBodyPart(headers, html.getBytes("UTF-8"));
		StringBuffer buf = new StringBuffer();
		buf.append("<table bgcolor=\"#e9eff0\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">");
		buf.append("<tbody><tr><td align=\"center\">");
		buf.append("<table style=\"background:#e9eff0\" bgcolor=\"#e9eff0\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"671\">");
		buf.append(" <tr>");
		buf.append("<td style=\"font-size:26px;line-height:26px;height:26px;text-align:left\" height=\"26\" width=\"20\"></td>");
		buf.append("<td style=\"text-align:left\" width=\"556\"><table style=\"font-family:helvetica,arial,sans-seif;color:#666666;font-size:16px;line-height:22px\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"556\">");
		buf.append("<tbody><tr>");
		buf.append("<td style=\"text-align:left\"></td>");
		buf.append("</tr>");
		buf.append("<tr>");
		buf.append("                <td style=\"text-align:left\"><table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"556\">");
		buf.append("	                          <tbody><tr><td style=\"font-family:helvetica,arial,sans-serif;font-size:30px;line-height:40px;font-weight:normal;color:#253c44;text-align:left\"></td></tr>");
		buf.append("                  <tr><td style=\"font-size:20px;line-height:20px;height:20px;text-align:left\" height=\"20\" width=\"556\"></td></tr>");
		buf.append("                  <tr>");
		buf.append("                    <td style=\"text-align:left\">");
		buf.append("     				   Hi ");
		buf.append(customerName + ",<br>");
		buf.append("      			   <br>");
		buf.append("     					Thanks for contacting <span class=\"il\">Analytics Bytes</span>! Before we end your service, you will need to validate your email address by clicking the link below:");
		buf.append("					<br>");
		buf.append("					<a href=\"");
		buf.append(URLConsts.HTTP_PREFIX+URLConsts.URL_DOMAIN_HOST+ URLConsts.SERVLET_EMAIL_CONFIMATION_NAME + userId + URLConsts.HTML_OPERAND_AND + "uniqueKey=" +  uniqueKey + "\"");
		buf.append("					style=\"color:#ff6600;font-weight:bold;font-family:helvetica,arial,sans-seif;text-decoration:none\" target=\"_blank\">Activate my account</a>.");
		buf.append("					<br>");
		buf.append("					<br>");
		buf.append("					Regards,<br>");
		buf.append("					The Team at <span class=\"il\">Analytics Bytes</span><br>");
		buf.append("					<br>");
		buf.append("				</td>");
		buf.append("                  </tr>");
		buf.append("                  <tr>");
		buf.append("                    <td style=\"font-size:30px;line-height:30px;height:30px;text-align:left\" height=\"30\" width=\"556\">&nbsp;</td>");
		buf.append("                  </tr>");
		buf.append("                </tbody></table></td>");
		buf.append("              </tr>");
		buf.append("            </tbody></table></td>");
		buf.append("            <td style=\"font-size:26px;line-height:26px;height:26px;text-align:left\" height=\"26\" width=\"20\"></td>"); 
		buf.append("          </tr>");
		buf.append("          <tr>");
		buf.append("	                    <td style=\"background-color:#d9dfe1;font-size:2px;line-height:2px;height:2px;text-align:left\" bgcolor=\"#d9dfe1\" height=\"2\" width=\"20\"></td>");
		buf.append("            <td style=\"background-color:#d9dfe1;font-size:2px;line-height:2px;height:2px;text-align:left\" bgcolor=\"#d9dfe1\" height=\"2\" width=\"556\"></td>");
		buf.append("            <td style=\"background-color:#d9dfe1;font-size:2px;line-height:2px;height:2px;text-align:left\" bgcolor=\"#d9dfe1\" height=\"2\" width=\"20\"></td>");
		buf.append("          </tr>");
		buf.append("        </tbody></table></td>");
		buf.append("        <td style=\"font-size:40px;line-height:40px;height:40px;text-align:left\" height=\"40\" width=\"37\"></td>");
		buf.append("      </tr>");
		/**
	        <table bgcolor=/"#e9eff0/" border=/"0/" cellpadding=/"0/" cellspacing=/"0/" width=/"100%/">
	        <tbody><tr><td align="center">
	            <table style="background:#e9eff0" bgcolor="#e9eff0" border="0" cellpadding="0" cellspacing="0" width="671">
	              <tbody><tr>
	                <td style="font-size:30px;line-height:30px;height:30px;text-align:left" height="30" width="38"></td>
	                <td style="font-size:30px;line-height:30px;height:30px;text-align:left" height="30" width="596"></td>
	                <td style="font-size:30px;line-height:30px;height:30px;text-align:left" height="30" width="37"></td>
	              </tr>
	              <tr>
	                <td style="font-size:40px;line-height:40px;height:40px;text-align:left" height="40" width="38"></td>
	                <td style="text-align:left"><table bgcolor="#ffffff" border="0" cellpadding="0" cellspacing="0" width="596">
	                  <tbody><tr>
	                    <td style="font-size:26px;line-height:26px;height:26px;text-align:left" height="26" width="20"></td>
	                    <td style="font-size:26px;line-height:26px;height:26px;text-align:left" height="26" width="556"></td>
	                    <td style="font-size:26px;line-height:26px;height:26px;text-align:left" height="26" width="20"></td>
	                  </tr>
	                  <tr>
	                    <td style="font-size:26px;line-height:26px;height:26px;text-align:left" height="26" width="20"></td>
	                    <td style="text-align:left" width="556"><table style="font-family:helvetica,arial,sans-seif;color:#666666;font-size:16px;line-height:22px" border="0" cellpadding="0" cellspacing="0" width="556">
	                      <tbody><tr>
	                        <td style="text-align:left"></td>
	                      </tr>
	                      <tr>
	                        <td style="text-align:left"><table border="0" cellpadding="0" cellspacing="0" width="556">
	                          <tbody><tr><td style="font-family:helvetica,arial,sans-serif;font-size:30px;line-height:40px;font-weight:normal;color:#253c44;text-align:left"></td></tr>
	                          <tr><td style="font-size:20px;line-height:20px;height:20px;text-align:left" height="20" width="556"></td></tr>
	                          <tr>
	                            <td style="text-align:left">
	             				   Hi Moshe,<br>
	              			   <br>
	             					Thanks for signing up to <span class="il">Nexmo</span>! Before we start, you will need to validate your email address by clicking the link below:
	      						<br>
	              				<a href="https://dashboard.nexmo.com/private/activation/2d95f0fa9132d7c01e2844a3bc45c869" style="color:#ff6600;font-weight:bold;font-family:helvetica,arial,sans-seif;text-decoration:none" target="_blank">Activate my account</a>.
	      						<br>
	      						Regards,<br>
	      						The Team at <span class="il">Nexmo</span><br>
	      						<br>
	      					</td>
	                          </tr>
	                          <tr>
	                            <td style="font-size:30px;line-height:30px;height:30px;text-align:left" height="30" width="556">&nbsp;</td>
	                          </tr>
	                        </tbody></table></td>
	                      </tr>
	                    </tbody></table></td>
	                    <td style="font-size:26px;line-height:26px;height:26px;text-align:left" height="26" width="20"></td>
	                  </tr>
	                  <tr>
	                    <td style="background-color:#d9dfe1;font-size:2px;line-height:2px;height:2px;text-align:left" bgcolor="#d9dfe1" height="2" width="20"></td>
	                    <td style="background-color:#d9dfe1;font-size:2px;line-height:2px;height:2px;text-align:left" bgcolor="#d9dfe1" height="2" width="556"></td>
	                    <td style="background-color:#d9dfe1;font-size:2px;line-height:2px;height:2px;text-align:left" bgcolor="#d9dfe1" height="2" width="20"></td>
	                  </tr>
	                </tbody></table></td>
	                <td style="font-size:40px;line-height:40px;height:40px;text-align:left" height="40" width="37"></td>
	              </tr>
	              <tr>
	                <td style="font-size:30px;line-height:30px;height:30px;text-align:left" height="30" width="38"></td>
	                <td style="font-size:30px;line-height:30px;height:30px;text-align:left" height="30" width="596"></td>
	                <td style="font-size:30px;line-height:30px;height:30px;text-align:left" height="30" width="37"></td>
	              </tr>
	            </tbody></table>
	        </td></tr>
	      </tbody>
	      </table>
		 **/
		//	        String msgBody = "Hi " + customerName  +" ,<br> Thanks You for signing up to SMSGA!" +
		//	        		" Before we start, you will need to validate <br> your email address by clicking the link below: <br>" +
		//	        		"<a style=\"font-size: 20px;\" href='" + URLConsts.HTTP_PREFIX+URLConsts.URL_DOMAIN_HOST  + 
		//	        		URLConsts.SERVLET_EMAIL_NOTIFICATION_NAME + userId + URLConsts.HTML_OPERAND_AND +
		//	        		"uniqueKey=" +  uniqueKey + 
		//	        		"'>Activate my account</a> <br> "+
		//	        		" Regards,<br>" +	
		//	        		" The Team at SMSGA";
		String msgBody = buf.toString();
		log.warning("Message is " + msgBody);

		MimeBodyPart mbp = new MimeBodyPart(); 
		mbp.setContent(msgBody, "text/html");
		MimeMultipart multipart = new MimeMultipart(); 
		multipart.addBodyPart(mbp);

		//	        Message msg = new Message();
		//	        msg.setSender(_sender);
		//	        msg.setTo(_recipient);
		//	        msg.setSubject(_msgSubject);
		//	        msg.setHtmlBody("<h1 style="height:1200px;">THIS IS RUSSIA!!!</h1>");
		//	        MailService service = MailServiceFactory.getMailService();
		//	        try {
		//	            service.send(msg);
		//	        } catch (IOException e) {
		//	            e.printStackTrace();
		//	        }
		//	    

		try {
			Message msg = new MimeMessage(session);
			//msg.setFrom(new InternetAddress("herskovi77@gmail.com"));
			msg.setFrom(new InternetAddress("support@analyticsbytes.com", "Analytics Bytes Support")); 
			msg.addRecipient(Message.RecipientType.TO, new InternetAddress(userId, customerName));
			msg.setSubject("Your Analytics Bytes account has been activated");
			msg.setContent(multipart);

			//	            MimeBodyPart body = new MimeBodyPart(headers, html.getBytes("UTF-8"));
			//	            msg.set
			Transport.send(msg);

		} catch (AddressException e) {
			log.severe("EmailNotificationUtils sendSigningUpEmailNotification AddressException " + e.getMessage());
		} catch (MessagingException e) {
			log.severe("EmailNotificationUtils sendSigningUpEmailNotification MessagingException" + e.getMessage());
		}catch(Exception ex)
		{
			log.severe("EmailNotificationUtils sendSigningUpEmailNotification Exception" + ex.getMessage());
		}

	}
	

	public static void sendSigningUpEmailNotification(String userId, String customerName, String uniqueKey) throws Exception
	{
		Properties props = new Properties();
		Session session = Session.getDefaultInstance(props, null); 
		//	        InternetHeaders headers = new InternetHeaders();
		//	        headers.addHeader("Content-type", "text/html; charset=UTF-8");
		//	        String html = "Test\n" + text + "\n<a href='http://test.com'>Test.com</a>";
		//	        MimeBodyPart body = new MimeBodyPart(headers, html.getBytes("UTF-8"));
		StringBuffer buf = new StringBuffer();
		buf.append("<table bgcolor=\"#e9eff0\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">");
		buf.append("<tbody><tr><td align=\"center\">");
		buf.append("<table style=\"background:#e9eff0\" bgcolor=\"#e9eff0\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"671\">");
		buf.append(" <tr>");
		buf.append("<td style=\"font-size:26px;line-height:26px;height:26px;text-align:left\" height=\"26\" width=\"20\"></td>");
		buf.append("<td style=\"text-align:left\" width=\"556\"><table style=\"font-family:helvetica,arial,sans-seif;color:#666666;font-size:16px;line-height:22px\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"556\">");
		buf.append("<tbody><tr>");
		buf.append("<td style=\"text-align:left\"></td>");
		buf.append("</tr>");
		buf.append("<tr>");
		buf.append("                <td style=\"text-align:left\"><table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"556\">");
		buf.append("	                          <tbody><tr><td style=\"font-family:helvetica,arial,sans-serif;font-size:30px;line-height:40px;font-weight:normal;color:#253c44;text-align:left\"></td></tr>");
		buf.append("                  <tr><td style=\"font-size:20px;line-height:20px;height:20px;text-align:left\" height=\"20\" width=\"556\"></td></tr>");
		buf.append("                  <tr>");
		buf.append("                    <td style=\"text-align:left\">");
		buf.append("     				   Hi ");
		buf.append(customerName + ",<br>");
		buf.append("      			   <br>");
		buf.append("     					Thanks for signing up to <span class=\"il\">Analytics Bytes</span>! Before we start, you will need to validate your email address by clicking the link below:");
		buf.append("					<br>");
		buf.append("					<a href=\"");
		buf.append(URLConsts.HTTP_PREFIX+URLConsts.URL_DOMAIN_HOST+ URLConsts.SERVLET_EMAIL_CONFIMATION_NAME + userId + URLConsts.HTML_OPERAND_AND + "uniqueKey=" +  uniqueKey + "\"");
		buf.append("					style=\"color:#ff6600;font-weight:bold;font-family:helvetica,arial,sans-seif;text-decoration:none\" target=\"_blank\">Activate my account</a>.");
		buf.append("					<br>");
		buf.append("					<br>");
		buf.append("					Regards,<br>");
		buf.append("					The Team at <span class=\"il\">Analytics Bytes</span><br>");
		buf.append("					<br>");
		buf.append("				</td>");
		buf.append("                  </tr>");
		buf.append("                  <tr>");
		buf.append("                    <td style=\"font-size:30px;line-height:30px;height:30px;text-align:left\" height=\"30\" width=\"556\">&nbsp;</td>");
		buf.append("                  </tr>");
		buf.append("                </tbody></table></td>");
		buf.append("              </tr>");
		buf.append("            </tbody></table></td>");
		buf.append("            <td style=\"font-size:26px;line-height:26px;height:26px;text-align:left\" height=\"26\" width=\"20\"></td>"); 
		buf.append("          </tr>");
		buf.append("          <tr>");
		buf.append("	                    <td style=\"background-color:#d9dfe1;font-size:2px;line-height:2px;height:2px;text-align:left\" bgcolor=\"#d9dfe1\" height=\"2\" width=\"20\"></td>");
		buf.append("            <td style=\"background-color:#d9dfe1;font-size:2px;line-height:2px;height:2px;text-align:left\" bgcolor=\"#d9dfe1\" height=\"2\" width=\"556\"></td>");
		buf.append("            <td style=\"background-color:#d9dfe1;font-size:2px;line-height:2px;height:2px;text-align:left\" bgcolor=\"#d9dfe1\" height=\"2\" width=\"20\"></td>");
		buf.append("          </tr>");
		buf.append("        </tbody></table></td>");
		buf.append("        <td style=\"font-size:40px;line-height:40px;height:40px;text-align:left\" height=\"40\" width=\"37\"></td>");
		buf.append("      </tr>");
		/**
	        <table bgcolor=/"#e9eff0/" border=/"0/" cellpadding=/"0/" cellspacing=/"0/" width=/"100%/">
	        <tbody><tr><td align="center">
	            <table style="background:#e9eff0" bgcolor="#e9eff0" border="0" cellpadding="0" cellspacing="0" width="671">
	              <tbody><tr>
	                <td style="font-size:30px;line-height:30px;height:30px;text-align:left" height="30" width="38"></td>
	                <td style="font-size:30px;line-height:30px;height:30px;text-align:left" height="30" width="596"></td>
	                <td style="font-size:30px;line-height:30px;height:30px;text-align:left" height="30" width="37"></td>
	              </tr>
	              <tr>
	                <td style="font-size:40px;line-height:40px;height:40px;text-align:left" height="40" width="38"></td>
	                <td style="text-align:left"><table bgcolor="#ffffff" border="0" cellpadding="0" cellspacing="0" width="596">
	                  <tbody><tr>
	                    <td style="font-size:26px;line-height:26px;height:26px;text-align:left" height="26" width="20"></td>
	                    <td style="font-size:26px;line-height:26px;height:26px;text-align:left" height="26" width="556"></td>
	                    <td style="font-size:26px;line-height:26px;height:26px;text-align:left" height="26" width="20"></td>
	                  </tr>
	                  <tr>
	                    <td style="font-size:26px;line-height:26px;height:26px;text-align:left" height="26" width="20"></td>
	                    <td style="text-align:left" width="556"><table style="font-family:helvetica,arial,sans-seif;color:#666666;font-size:16px;line-height:22px" border="0" cellpadding="0" cellspacing="0" width="556">
	                      <tbody><tr>
	                        <td style="text-align:left"></td>
	                      </tr>
	                      <tr>
	                        <td style="text-align:left"><table border="0" cellpadding="0" cellspacing="0" width="556">
	                          <tbody><tr><td style="font-family:helvetica,arial,sans-serif;font-size:30px;line-height:40px;font-weight:normal;color:#253c44;text-align:left"></td></tr>
	                          <tr><td style="font-size:20px;line-height:20px;height:20px;text-align:left" height="20" width="556"></td></tr>
	                          <tr>
	                            <td style="text-align:left">
	             				   Hi Moshe,<br>
	              			   <br>
	             					Thanks for signing up to <span class="il">Nexmo</span>! Before we start, you will need to validate your email address by clicking the link below:
	      						<br>
	              				<a href="https://dashboard.nexmo.com/private/activation/2d95f0fa9132d7c01e2844a3bc45c869" style="color:#ff6600;font-weight:bold;font-family:helvetica,arial,sans-seif;text-decoration:none" target="_blank">Activate my account</a>.
	      						<br>
	      						Regards,<br>
	      						The Team at <span class="il">Nexmo</span><br>
	      						<br>
	      					</td>
	                          </tr>
	                          <tr>
	                            <td style="font-size:30px;line-height:30px;height:30px;text-align:left" height="30" width="556">&nbsp;</td>
	                          </tr>
	                        </tbody></table></td>
	                      </tr>
	                    </tbody></table></td>
	                    <td style="font-size:26px;line-height:26px;height:26px;text-align:left" height="26" width="20"></td>
	                  </tr>
	                  <tr>
	                    <td style="background-color:#d9dfe1;font-size:2px;line-height:2px;height:2px;text-align:left" bgcolor="#d9dfe1" height="2" width="20"></td>
	                    <td style="background-color:#d9dfe1;font-size:2px;line-height:2px;height:2px;text-align:left" bgcolor="#d9dfe1" height="2" width="556"></td>
	                    <td style="background-color:#d9dfe1;font-size:2px;line-height:2px;height:2px;text-align:left" bgcolor="#d9dfe1" height="2" width="20"></td>
	                  </tr>
	                </tbody></table></td>
	                <td style="font-size:40px;line-height:40px;height:40px;text-align:left" height="40" width="37"></td>
	              </tr>
	              <tr>
	                <td style="font-size:30px;line-height:30px;height:30px;text-align:left" height="30" width="38"></td>
	                <td style="font-size:30px;line-height:30px;height:30px;text-align:left" height="30" width="596"></td>
	                <td style="font-size:30px;line-height:30px;height:30px;text-align:left" height="30" width="37"></td>
	              </tr>
	            </tbody></table>
	        </td></tr>
	      </tbody>
	      </table>
		 **/
		//	        String msgBody = "Hi " + customerName  +" ,<br> Thanks You for signing up to SMSGA!" +
		//	        		" Before we start, you will need to validate <br> your email address by clicking the link below: <br>" +
		//	        		"<a style=\"font-size: 20px;\" href='" + URLConsts.HTTP_PREFIX+URLConsts.URL_DOMAIN_HOST  + 
		//	        		URLConsts.SERVLET_EMAIL_NOTIFICATION_NAME + userId + URLConsts.HTML_OPERAND_AND +
		//	        		"uniqueKey=" +  uniqueKey + 
		//	        		"'>Activate my account</a> <br> "+
		//	        		" Regards,<br>" +	
		//	        		" The Team at SMSGA";
		String msgBody = buf.toString();
		log.warning("Message is " + msgBody);

		MimeBodyPart mbp = new MimeBodyPart(); 
		mbp.setContent(msgBody, "text/html");
		MimeMultipart multipart = new MimeMultipart(); 
		multipart.addBodyPart(mbp);

		//	        Message msg = new Message();
		//	        msg.setSender(_sender);
		//	        msg.setTo(_recipient);
		//	        msg.setSubject(_msgSubject);
		//	        msg.setHtmlBody("<h1 style="height:1200px;">THIS IS RUSSIA!!!</h1>");
		//	        MailService service = MailServiceFactory.getMailService();
		//	        try {
		//	            service.send(msg);
		//	        } catch (IOException e) {
		//	            e.printStackTrace();
		//	        }
		//	    

		try {
			Message msg = new MimeMessage(session);
			//msg.setFrom(new InternetAddress("herskovi77@gmail.com"));  
			msg.setFrom(new InternetAddress("support@analyticsbytes.com", "Analytics Bytes Support")); 			
			msg.addRecipient(Message.RecipientType.TO, new InternetAddress(userId, customerName));
			msg.setSubject("Your Analytics Bytes account has been activated");
			msg.setContent(multipart);

			//	            MimeBodyPart body = new MimeBodyPart(headers, html.getBytes("UTF-8"));
			//	            msg.set
			Transport.send(msg);

		} catch (AddressException e) {
			log.severe("EmailNotificationUtils sendSigningUpEmailNotification AddressException " + e.getMessage());
		} catch (MessagingException e) {
			log.severe("EmailNotificationUtils sendSigningUpEmailNotification MessagingException" + e.getMessage());
		}catch(Exception ex)
		{
			log.severe("EmailNotificationUtils sendSigningUpEmailNotification Exception" + ex.getMessage());
		}

	}
}