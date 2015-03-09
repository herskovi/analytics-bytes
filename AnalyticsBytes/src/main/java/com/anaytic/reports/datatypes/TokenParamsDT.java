/**
 * 
 */
package main.java.com.anaytic.reports.datatypes;

import java.io.Serializable;

/**
 * @author admin
 * May 7, 2014
 */
public class TokenParamsDT implements Serializable
{
	
	private static final long serialVersionUID = -1501028396080821138L;
	private String url = "";
    private String code ="";
    private String clientId ="";
    private String clientSecret="";
    private String redirectUri="";
    private String grantType ="";
    
    
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getClientId() {
		return clientId;
	}
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	public String getClientSecret() {
		return clientSecret;
	}
	public void setClientSecret(String clientSecret) {
		this.clientSecret = clientSecret;
	}
	public String getRedirectUri() {
		return redirectUri;
	}
	public void setRedirectUri(String redirectUri) {
		this.redirectUri = redirectUri;
	}
	public String getGrantType() {
		return grantType;
	}
	public void setGrantType(String grantType) {
		this.grantType = grantType;
	}

}
