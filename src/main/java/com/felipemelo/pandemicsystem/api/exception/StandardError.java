package com.felipemelo.pandemicsystem.api.exception;

import java.io.Serializable;
import java.time.OffsetDateTime;

public class StandardError implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer Status;
	private String message;
	private OffsetDateTime data;
	
	public StandardError(Integer status, String message, OffsetDateTime data) {
		super();
		Status = status;
		this.message = message;
		this.data = data;
	}

	public Integer getStatus() {
		return Status;
	}

	public void setStatus(Integer status) {
		Status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public OffsetDateTime getTimeStamp() {
		return data;
	}

	public void setTimeStamp(OffsetDateTime timeStamp) {
		this.data = timeStamp;
	}

}
