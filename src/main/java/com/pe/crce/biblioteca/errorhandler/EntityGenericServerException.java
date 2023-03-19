package com.pe.crce.biblioteca.errorhandler;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class EntityGenericServerException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	private String code;

	public EntityGenericServerException() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public EntityGenericServerException(String message, String code) {
		super(message);
		this.code = code;
		// TODO Auto-generated constructor stub
	}

	public EntityGenericServerException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public EntityGenericServerException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public EntityGenericServerException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public EntityGenericServerException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}
	
	

}
