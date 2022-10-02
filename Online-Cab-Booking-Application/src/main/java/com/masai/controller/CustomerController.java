package com.masai.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.masai.dto.LoginDTO;
import com.masai.exception.CustomerException;
import com.masai.exception.LoginException;
import com.masai.exception.TripBookingException;
import com.masai.model.Customer;
import com.masai.model.TripBooking;
import com.masai.model.UserSession;
import com.masai.service.CustomerService;
import com.masai.service.TripBookingService;

@RequestMapping("/customer")
@RestController
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private TripBookingService tripBookingService;
	
	@PostMapping("/register")
	public ResponseEntity<Customer>registerCustomer(@RequestBody Customer customer) throws CustomerException {
		Customer newUser = customerService.insertCustomer(customer);
		return  new ResponseEntity<Customer>(newUser, HttpStatus.CREATED);

	}
	
	@PostMapping("/login")
	public ResponseEntity<UserSession> loginCustomerHandler(@RequestBody LoginDTO loginDto) throws LoginException, CustomerException {
		UserSession currentSession = customerService.loginCustomer(loginDto);
		return new ResponseEntity<UserSession>(currentSession, HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/logout")
	public ResponseEntity<String> logoutCustomer(@RequestParam String key) throws CustomerException {
		String s=customerService.logoutCustomer(key);
		return new ResponseEntity<String>(s, HttpStatus.ACCEPTED);
		
	}
	@PostMapping("/booktrip")
	public ResponseEntity<TripBooking> bookTrip(@RequestBody TripBooking trip, @RequestParam String key) throws CustomerException {
		TripBooking bookedTrip = customerService.bookTrip(trip, key);
		return new ResponseEntity<TripBooking>(bookedTrip, HttpStatus.CREATED);
	}
	
	@PutMapping("/update")
	public ResponseEntity<Customer> updateCustmorHandler(@Valid @RequestBody Customer customer, @RequestParam String key) throws LoginException, CustomerException{
		Customer updateCustmor = customerService.updateCustomer(customer, key);
		return new ResponseEntity<Customer>(updateCustmor, HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/delete/{customerId}")
	public ResponseEntity<Customer> deleteCustmorByIdHandler(@PathVariable Integer customerId,@RequestParam String key)throws LoginException, CustomerException{
		Customer deleteCustomer = customerService.deleteCustomer(customerId, key);
		return new ResponseEntity<Customer>(deleteCustomer, HttpStatus.OK);
	}
	
	@PutMapping("cancel/{tripId}")
	public ResponseEntity<String> cancelTripHandler(@PathVariable Integer tripId, @RequestParam String key) throws LoginException, TripBookingException {
		String canceledTrip = customerService.cancelTrip(tripId, key);
		return new ResponseEntity<String>(canceledTrip, HttpStatus.OK);
	}
	
}
