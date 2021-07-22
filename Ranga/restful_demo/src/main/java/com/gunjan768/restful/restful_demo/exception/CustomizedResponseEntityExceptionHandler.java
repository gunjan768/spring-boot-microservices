package com.gunjan768.restful.restful_demo.exception;

import java.util.Date;
import java.util.Objects;

import com.gunjan768.restful.restful_demo.user.UserNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


// When we have multiple controller classes and we would want to share things across them, then we can use the annotation @ControllerAdvice. We
// want to share the Exception Handling across each controllers. And one of the typical use cases of @ControllerAdvice is Exception Handling.

// Basically we created this class to customize our Exception Handling. 
@ControllerAdvice
@RestController
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	// All exceptions will come to this method and here we can customize our exception.
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));

		return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	// If we throw an error using user made class "UserNotFoundException" then it will come here instead of going up.
	@ExceptionHandler(UserNotFoundException.class)
	public final ResponseEntity<Object> handleUserNotFoundException(UserNotFoundException ex, WebRequest request) {

		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(true));

		return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
	}
		
	// This is the method which is fired when binding to specific method argument fails. See "UserResource" class, there createUser() method
	// has an annotation @Valid which validates the method arguments and when when validation fails, this exception will be called.
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex,
			HttpHeaders headers,
			HttpStatus status,
			WebRequest request
	) {
		// System.out.println("Method Argument validation failed : " + ex.getMessage());
		// System.out.println("MethodArgumentNotValidException: " + Objects.requireNonNull(ex.getFieldError()).getField());

		// Give consumers (user) the details what has went wrong and that stored in something called "Binding Result" using method
		// getBindingResult().
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), "Validation Failed", ex.getBindingResult().toString());

		//For input validation failure: 400 Bad Request + your optional description.
		return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
	}
}