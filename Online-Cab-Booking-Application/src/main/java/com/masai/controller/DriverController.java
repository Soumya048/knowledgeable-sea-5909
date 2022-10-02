package com.masai.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.masai.dto.LoginDTO;
import com.masai.exception.DriverException;
import com.masai.exception.LoginException;
import com.masai.exception.TripBookingException;
import com.masai.model.Admin;
import com.masai.model.Driver;
import com.masai.model.DriverSession;
import com.masai.model.TripBooking;
import com.masai.repository.DriverDao;
import com.masai.service.DriverServices;
import com.masai.service.TripBookingService;

@RestController
@RequestMapping("/driver")
public class DriverController {
	
	@Autowired
	private DriverServices driverService;
	
	@Autowired
	private TripBookingService tripBookingService;
	
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
	
	@PutMapping("/update")
	public ResponseEntity<Driver> updateDriverHandler(@RequestBody Driver driver, @RequestParam String key) throws LoginException {
		Driver updateMessage = driverService.updateDriver(driver, key);
		return new ResponseEntity<Driver>(updateMessage, HttpStatus.ACCEPTED);
	}

	@PutMapping("/update/{status}")
	public ResponseEntity<String> updateAvailableStatusHandler(@PathVariable String status, @RequestParam String key) throws LoginException, DriverException {
		String updateMessage = driverService.updateStatus(status, key);
		return new ResponseEntity<String>(updateMessage, HttpStatus.ACCEPTED);
	}
	
	@PutMapping("/tripupdate/{tripId}/{status}")
	public ResponseEntity<TripBooking> updateTripStatusHandler(@PathVariable Integer tripId, @PathVariable String status, @RequestParam String key) throws LoginException, TripBookingException {
		TripBooking updateMessage = tripBookingService.updateTripStatus(tripId, status, key);
		return new ResponseEntity<TripBooking>(updateMessage, HttpStatus.ACCEPTED);
	}

}
