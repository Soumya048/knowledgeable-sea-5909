package com.masai.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.masai.exception.AdminException;
import com.masai.exception.LoginException;
import com.masai.model.Admin;
import com.masai.model.Customer;
import com.masai.model.Driver;
import com.masai.model.TripBooking;

@Service
public class AdminServiceImpl implements AdminService {

	@Override
	public Admin adminLogin(Admin admin) throws AdminException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Admin updateAdmin(Admin admin, String Username, String key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String deleteAdminById(Integer adminId, String key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String logoutAdmin(String key) throws LoginException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TripBooking> getAllTrips(Integer customerId, String key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TripBooking> getTripsByCustomerId(String key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TripBooking> getTripsByDateWise(String key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TripBooking> getAllTripsByDays(Integer customerId, LocalDateTime fromDate, LocalDateTime toDate,
			String key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Driver> getListOfDrivers(String key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Customer> getListOfCustomers(String key) {
		// TODO Auto-generated method stub
		return null;
	}

}
