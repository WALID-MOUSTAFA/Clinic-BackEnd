package com.clinic.errorHandling;

import java.util.Date;
import java.util.NoSuchElementException;

import com.clinic.models.RequestErrorHandlingError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;


@RestControllerAdvice
public class RequestErrorHandling{


	@ExceptionHandler(value={HttpMessageNotReadableException.class})
	public ResponseEntity<?> handleMissingRequestParams(HttpMessageNotReadableException e) {
  		RequestErrorHandlingError requestErrorHandling = new RequestErrorHandlingError();
		requestErrorHandling.setError(HttpStatus.BAD_REQUEST.name().replaceAll("_", " "));
		requestErrorHandling.setStatus("400");
		requestErrorHandling.setMessage(e.getMessage().split(":")[0]);
		requestErrorHandling.setTimestamp(new Date());
		return new ResponseEntity<RequestErrorHandlingError>(requestErrorHandling, HttpStatus.BAD_REQUEST);
	} 


	@ExceptionHandler(value={MethodArgumentTypeMismatchException.class})
	public ResponseEntity<?> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e) {
  		RequestErrorHandlingError requestErrorHandling = new RequestErrorHandlingError();
		requestErrorHandling.setError(HttpStatus.BAD_REQUEST.name().replaceAll("_", " "));
		requestErrorHandling.setStatus("400");
		requestErrorHandling.setMessage(e.getMessage().split("[;:]")[0]);
		requestErrorHandling.setTimestamp(new Date());
		return new ResponseEntity<RequestErrorHandlingError>(requestErrorHandling, HttpStatus.BAD_REQUEST);
	} 


	
	@ExceptionHandler(value={NoSuchElementException.class})
	public ResponseEntity<?> handleNoSuchElementException(NoSuchElementException e) {
  		RequestErrorHandlingError requestErrorHandling = new RequestErrorHandlingError();
		requestErrorHandling.setError(HttpStatus.BAD_REQUEST.name().replaceAll("_", " "));
		requestErrorHandling.setStatus("404");
		requestErrorHandling.setMessage(e.getMessage().split("[;:]")[0]);
		requestErrorHandling.setTimestamp(new Date());
		return new ResponseEntity<RequestErrorHandlingError>(requestErrorHandling, HttpStatus.NOT_FOUND);
	} 


}
