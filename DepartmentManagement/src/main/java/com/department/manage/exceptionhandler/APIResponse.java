package com.department.manage.exceptionhandler;

public class APIResponse
{
	
	private String responseStatus;
	private String responseCode;
	private String responseMessage;
	public String getResponseStatus() {
		return responseStatus;
	}
	public void setResponseStatus(String responseStatus) {
		this.responseStatus = responseStatus;
	}
	public String getResponseCode() {
		return responseCode;
	}
	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}
	public String getResponseMessage() {
		return responseMessage;
	}
	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}
	public APIResponse(String responseStatus, String responseCode, String responseMessage) {
		super();
		this.responseStatus = responseStatus;
		this.responseCode = responseCode;
		this.responseMessage = responseMessage;
	}
	@Override
	public String toString() {
		return "APIResponse [responseStatus=" + responseStatus + ", responseCode=" + responseCode + ", responseMessage="
				+ responseMessage + "]";
	}
	
	

}
