package com.masai.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(TripBookingException.class)
	public ResponseEntity<ErrorDetails> accountExceptionHandler(TripBookingException le, WebRequest req) {
		ErrorDetails err = new ErrorDetails();
		
		err.setTimestamp(LocalDateTime.now());
		err.setMessage(le.getMessage());
		err.setDetails(req.getDescription(false));
		
		return new ResponseEntity<ErrorDetails>(err, HttpStatus.BAD_REQUEST);
	}
	
	
	
	
	
	
	
	
	
	
	
	// No handler found exception
	
	@ExceptionHandler(NoHandlerFoundException.class)
	public ResponseEntity<ErrorDetails> notFoundExceptionHandler(NoHandlerFoundException nfe, WebRequest req ){	
		ErrorDetails err = new ErrorDetails();
			
		err.setTimestamp(LocalDateTime.now());
		err.setMessage(nfe.getMessage());
		err.setDetails(req.getDescription(false));
			
		return new ResponseEntity<ErrorDetails>(err, HttpStatus.BAD_REQUEST);
	}
		
	//wrong uri exception
		
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorDetails> MANVExceptionHandler(MethodArgumentNotValidException me) {
		ErrorDetails err = new ErrorDetails();
			
		err.setTimestamp(LocalDateTime.now());
		err.setMessage("Validation Error");
		err.setDetails(me.getBindingResult().getFieldError().getDefaultMessage());
			
		return new ResponseEntity<ErrorDetails>(err, HttpStatus.BAD_REQUEST);
	}
		
	//for other exception
		
	@ExceptionHandler(Exception.class) 
	public ResponseEntity<ErrorDetails> otherExceptionHandler(Exception e, WebRequest req) {	
		ErrorDetails err = new ErrorDetails();
			
		err.setTimestamp(LocalDateTime.now());
		err.setMessage(e.getMessage());
		err.setDetails(req.getDescription(false));
			
		return new ResponseEntity<ErrorDetails>(err, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
