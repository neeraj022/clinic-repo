package com.neeraj.core.generics;

import java.io.Serializable;
import java.util.List;

import org.apache.log4j.Logger;

public class MyResponse implements Serializable{
	
	/**
	 * 
	 */
	private Logger logger=Logger.getLogger(MyResponse.class);
	private static final long serialVersionUID = 1L;
	private List<Object> data;
	private boolean success;
	private String errorMessage;
	public List<Object> getData() {
		return data;
	}
	public void setData(List<Object> data) {
		this.data = data;
	}
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
		logger.error(errorMessage);
	}
	@Override
	public String toString() {
		return "MyResponse [data=" + data + ", success=" + success
				+ ", errorMessage=" + errorMessage + "]";
	}

}
