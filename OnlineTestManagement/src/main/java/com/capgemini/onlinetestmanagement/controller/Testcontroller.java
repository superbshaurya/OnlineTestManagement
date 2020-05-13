package com.capgemini.onlinetestmanagement.controller;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.capgemini.onlinetestmanagement.entity.Test1;
import com.capgemini.onlinetestmanagement.exception.Test1Exception;
import com.capgemini.onlinetestmanagement.services.TestService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class Testcontroller {
	@Autowired
	private TestService testservice;
	@PostMapping("/addtest")
	public ResponseEntity<String> addTest(@Valid @RequestBody Test1 test, BindingResult br)
			throws Test1Exception {
		String err = "";
		if (br.hasErrors()) {
			List<FieldError> errors = br.getFieldErrors();
			for (FieldError error : errors)
				err += error.getDefaultMessage() + "<br/>";
			throw new Test1Exception(err);
		}
		try {
			testservice.addtest(test);
			return new ResponseEntity<String>("test added successfully", HttpStatus.OK);

		} catch (DataIntegrityViolationException ex) {
			throw new Test1Exception("ID already exists");
		}
	}
	@CrossOrigin
	@GetMapping("/viewalltest")
	public ResponseEntity<List<Test1>> getTestlist() {
		List<Test1> testList = testservice.show();
		return new ResponseEntity<List<Test1>>(testList, HttpStatus.OK);
	}
	
	@CrossOrigin
	@DeleteMapping("/deletetest/{id}")
	public ResponseEntity<String> deletetest(@Valid @RequestParam int testId) throws Test1Exception
	{
		try
		{
			testservice.deletetest( testId);
			return new ResponseEntity<String>("test is deleted", HttpStatus.OK);
		}
		catch (DataIntegrityViolationException ex) {
			throw new Test1Exception("Test  doesnot exists");
		}
	}

	@CrossOrigin
	@PutMapping("/updatetest/{id}")
	public ResponseEntity updatetest(@Valid @RequestBody Test1 test,@RequestParam int testId,BindingResult br ) throws Test1Exception
	{
		String err = "";
		if (br.hasErrors()) {
			List<FieldError> errors = br.getFieldErrors();
			for (FieldError error : errors)
				err += error.getDefaultMessage() + "<br/>";
			throw new Test1Exception(err);
		}
		try {
			testservice.updatetest(test,testId);
			return new ResponseEntity<String>("Test updated successfully", HttpStatus.OK);

		} catch (DataIntegrityViolationException ex) {
			throw new Test1Exception("Test id already exists");
		}
	}
}