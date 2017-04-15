package com.pr.expense.exception;

import com.pr.commons.dto.Message;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @Author Rahul Gaur.
 * Created on 2/2/2017
 */
@ControllerAdvice
public class ApplicationExceptionHandler {

	@ExceptionHandler(IllegalArgumentException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public String handleIllegalArgumentException(IllegalArgumentException ex) {
		return ex.getMessage();
	}

	@ExceptionHandler(AuthenticationException.class)
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	@ResponseBody
	public Message handleAuthenticationException(AuthenticationException ex) {
		return ex.getProperties();
	}


	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ResponseBody
	public String handleGeneralException(Exception ex) {
		ex.printStackTrace();
		return ex.getMessage();
	}

}
