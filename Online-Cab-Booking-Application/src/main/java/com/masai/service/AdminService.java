package com.masai.service;

import java.time.LocalDateTime;
import java.util.List;

import com.masai.dto.LoginDTO;
import com.masai.exception.AdminException;
import com.masai.exception.LoginException;
import com.masai.exception.TripBookingException;
import com.masai.model.Admin;
import com.masai.model.AdminSession;
import com.masai.model.Customer;
import com.masai.model.Driver;
import com.masai.model.TripBooking;

public interface AdminService {

	public Admin adminRegister(Admin admin);
	public AdminSession adminLogin(LoginDTO loginDto) throws LoginException;
	public Admin updateAdmin(Admin admin, String Username, String key) throws LoginException ;
	public Admin deleteAdminById(Integer adminId, String key) throws LoginException;
	public String logoutAdmin(String key) throws LoginException;
	public List<TripBooking> getAllTrips(Integer customerId, String key) throws TripBookingException, LoginException;
	public List<TripBooking> getTripsByCustomerId(String key);
	public List<TripBooking> getTripsByDateWise(String key);
	public List<TripBooking> getAllTripsByDays(Integer customerId, LocalDateTime fromDate, LocalDateTime toDate, String key);
	public List<Driver> getListOfDrivers(String key);
	public List<Customer> getListOfCustomers(String key);
	
}
