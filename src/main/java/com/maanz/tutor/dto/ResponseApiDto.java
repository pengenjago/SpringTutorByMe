package com.maanz.tutor.dto;

import lombok.Data;

@Data
public class ResponseApiDto {
	private Integer statusCode = 200;
	private String statusDescription = "Success";
	private Object result;
	
	public ResponseApiDto(Object param) { 
		this.result = param;
	}
}
