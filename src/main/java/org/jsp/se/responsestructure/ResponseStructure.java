package org.jsp.se.responsestructure;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseStructure<T> {
	private int httpCode;
	private String message;
	private T body;
}
