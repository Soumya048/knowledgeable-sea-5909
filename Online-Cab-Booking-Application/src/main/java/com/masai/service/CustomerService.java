package com.masai.service;

import java.util.List;

import com.masai.dto.LoginDTO;
import com.masai.exception.CustomerException;
import com.masai.exception.LoginException;
import com.masai.model.Customer;
import com.masai.model.TripBooking;
import com.masai.model.UserSession;

public interface CustomerService  {
	Customer insertCustomer(Customer customer) throws CustomerException;     
	Customer updateCustomer(Customer customer, String Key) throws CustomerException;                                          
	  Customer deleteCustomer(int customerId) throws CustomerException;                                                  

	  
	UserSession  loginCustomer(LoginDTO customer) throws LoginException,CustomerException ;
	String logoutCustomer(String key)throws CustomerException;
	TripBooking bookTrip(TripBooking trip, String key)throws CustomerException ;
	  
}
