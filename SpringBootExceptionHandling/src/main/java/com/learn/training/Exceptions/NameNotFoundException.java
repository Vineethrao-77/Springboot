package com.learn.training.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class NameNotFoundException extends Exception {
	
	
		private static final long serialVersionUID = 1L;

		public NameNotFoundException(String message){
	    	super(message);
	    }
	}