package com.masai.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.masai.dto.LoginDTO;
import com.masai.exception.DriverException;
import com.masai.exception.LoginException;
import com.masai.model.Admin;
import com.masai.model.Driver;
import com.masai.model.DriverSession;
import com.masai.repository.DriverDao;
import com.masai.service.DriverServices;

@RestController
@RequestMapping("/driver")
public class DriverController {
	
	@Autowired
	private DriverServices driverService;
	
	@PostMapping("/register")
	public ResponseEntity<Driver> newDriverHandler(@Valid @RequestBody Driver driver) throws DriverException {
		Driver registered = driverService.newDriver(driver);
		return new  ResponseEntity<Driver>(registered, HttpStatus.OK);
	}
	
	@PostMapping("/login")
	public ResponseEntity<DriverSession> loginDriverHandler(@RequestBody LoginDTO logindto) throws LoginException{
		
		DriverSession currentSession= driverService.loginDriver(logindto);
		return new ResponseEntity<DriverSession>(currentSession, HttpStatus.ACCEPTED);
		
	}
	
	@DeleteMapping("/logout")
	public ResponseEntity<String> logoutDriverHandler(@RequestParam String key) throws LoginException{
		String msg= driverService.logoutDriver(key);
		return new ResponseEntity<String>(msg,HttpStatus.OK);
	}
	
	
	@DeleteMapping("/delete")
	public ResponseEntity<String> removeDriverHandler(@RequestParam String name,@RequestParam String key) throws LoginException, DriverException{
		
		String msg= driverService.removeDriver(name, key);
		
		return new ResponseEntity<String>(msg, HttpStatus.OK);
	}
	
	
	

}
