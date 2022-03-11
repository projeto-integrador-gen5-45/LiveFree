package com.livefree.LiveFree.exceptions;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ModelError {

	private Long status;
	private String exception;
	private String suggestion;
	private @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime timestamp = LocalDateTime.now();

	public ModelError(Long status, String exception, String suggestion) {
		this.status = status;
		this.exception = exception;
		this.suggestion = suggestion;
	}

	public Long getStatus() {
		return status;
	}

	public void setStatus(Long status) {
		this.status = status;
	}

	public String getException() {
		return exception;
	}

	public void setException(String exception) {
		this.exception = exception;
	}

	public String getSuggestion() {
		return suggestion;
	}

	public void setSuggestion(String suggestion) {
		this.suggestion = suggestion;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

}
