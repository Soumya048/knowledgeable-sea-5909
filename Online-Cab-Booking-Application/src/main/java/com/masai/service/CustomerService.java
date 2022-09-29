package com.masai.service;

import java.util.List;

import com.masai.exception.CustomerException;
import com.masai.model.Customer;

public interface CustomerService  {
	Customer insertCustomer(Customer customer) throws CustomerException;     
	Customer updateCustomer(Customer customer) throws CustomerException;                                          
	  Customer deleteCustomer(int customerId) throws CustomerException;                                                  

	  List<Customer> viewCustomers() throws CustomerException ;                                                                 

	  Customer viewCustomer(Integer customerId)  throws CustomerException    ;                                        

	  Customer	validateCustomer(String username, String password) throws CustomerException  ;              
}
