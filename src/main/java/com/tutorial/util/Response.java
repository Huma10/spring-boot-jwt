package com.tutorial.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Response {

	private String message;
	
	private Boolean statusCode;
	
	private Object data;
}
