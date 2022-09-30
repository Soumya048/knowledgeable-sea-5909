package com.masai.service;

import java.time.LocalDateTime;
import java.util.List;

import com.masai.exception.AdminException;
import com.masai.exception.LoginException;
import com.masai.model.Admin;
import com.masai.model.Customer;
import com.masai.model.Driver;
import com.masai.model.TripBooking;

public interface AdminService {

	public Admin adminLogin(Admin admin) throws AdminException;
	public Admin updateAdmin(Admin admin, String Username, String key);
	public String deleteAdminById(Integer adminId, String key);
	public String logoutAdmin(String key) throws LoginException;
	public List<TripBooking> getAllTrips(Integer customerId, String key);
	public List<TripBooking> getTripsByCustomerId(String key);
	public List<TripBooking> getTripsByDateWise(String key);
	public List<TripBooking> getAllTripsByDays(Integer customerId, LocalDateTime fromDate, LocalDateTime toDate, String key);
	public List<Driver> getListOfDrivers(String key);
	public List<Customer> getListOfCustomers(String key);
	
}
