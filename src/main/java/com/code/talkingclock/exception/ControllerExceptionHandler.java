package com.code.talkingclock.exception;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import com.code.talkingclock.model.ErrorResponse;

/***
 * Handles different exceptions raised by application at controller level
 * @author 91959
 *
 */

@RestControllerAdvice
public class ControllerExceptionHandler {
	
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<ErrorResponse> handleConstraintViolationException(ConstraintViolationException e, WebRequest request) {
		List<String> errors =  e.getConstraintViolations().stream().map( error ->  error.getMessage()).collect(Collectors.toList());
	    
		ErrorResponse response = buildErrorResponse( errors, HttpStatus.BAD_REQUEST.getReasonPhrase());
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	
	}
	
	private ErrorResponse buildErrorResponse(List<String> errors, String message) {
		ErrorResponse response = new ErrorResponse();
		response.setErrors(errors);
		response.setMessage(message);
		return response;
	}	

}
