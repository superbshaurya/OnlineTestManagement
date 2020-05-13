package com.capgemini.onlinetestmanagement.controller;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.capgemini.onlinetestmanagement.exception.Test1Exception;
import com.capgemini.onlinetestmanagement.exception.ErrorInfo;

@RestControllerAdvice

public class Test1Advice {
	@ExceptionHandler(value = {Test1Exception.class})
	@ResponseStatus(code=HttpStatus.BAD_REQUEST)
	public ErrorInfo handleException1(Exception ex) {
		return new ErrorInfo(ex.getMessage());
	}


}
