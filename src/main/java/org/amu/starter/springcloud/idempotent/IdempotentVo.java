package org.amu.starter.springcloud.idempotent;

import java.util.Map;

public class IdempotentVo {

	public static final Integer IDEMPOMENT_STATUS_START = 100;
	public static final Integer IDEMPOMENT_STATUS_REDIRECT = 101;
	public static final Integer IDEMPOMENT_STATUS_FINISIED = 200;
	
	private String idempotentKey;

	private Integer idempotentStatus;
	
	private Integer statusCode;
	
	private String statusMessage;
	
	private String result;
	
	private Map<String, String> headers;


	public Map<String, String> getHeaders() {
		return headers;
	}

	public void setHeaders(Map<String, String> headers) {
		this.headers = headers;
	}

	public Integer getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(Integer statusCode) {
		this.statusCode = statusCode;
	}

	public String getStatusMessage() {
		return statusMessage;
	}

	public void setStatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
	}


	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public Integer getIdempotentStatus() {
		return idempotentStatus;
	}

	public void setIdempotentStatus(Integer idempotentStatus) {
		this.idempotentStatus = idempotentStatus;
	}

	public String getIdempotentKey() {
		return idempotentKey;
	}

	public void setIdempotentKey(String idempotentKey) {
		this.idempotentKey = idempotentKey;
	}
	
}
