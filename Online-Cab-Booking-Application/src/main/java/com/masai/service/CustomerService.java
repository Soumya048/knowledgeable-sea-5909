package com.masai.service;

import java.util.List;

import com.masai.dto.LoginDTO;
import com.masai.exception.CustomerException;
import com.masai.exception.LoginException;
import com.masai.exception.TripBookingException;
import com.masai.model.Customer;
import com.masai.model.TripBooking;
import com.masai.model.UserSession;

public interface CustomerService  {
	
	public Customer insertCustomer(Customer customer) throws CustomerException;     
	public Customer updateCustomer(Customer customer, String Key) throws CustomerException, LoginException;                                          
	public Customer deleteCustomer(int customerId, String key) throws CustomerException, LoginException;                                                  
	public UserSession loginCustomer(LoginDTO customer) throws LoginException,CustomerException ;
	public String logoutCustomer(String key)throws CustomerException;
	public TripBooking bookTrip(TripBooking trip, String key)throws CustomerException ;
	public String cancelTrip(Integer tripBooingId, String key) throws LoginException, TripBookingException; 
}
