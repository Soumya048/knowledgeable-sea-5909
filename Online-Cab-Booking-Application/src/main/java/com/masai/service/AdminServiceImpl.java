package com.masai.service;

import java.sql.Driver;
import java.util.List;
import java.util.Optional;

import javax.security.auth.login.LoginException;

import org.apache.catalina.users.AbstractUser;
import org.springframework.beans.factory.annotation.Autowired;

import com.masai.dto.AdminDTO;
import com.masai.model.Admin;
import com.masai.model.AdminSession;
import com.masai.repository.AdminDao;
import com.masai.repository.AdminSessionDao;

public class AdminServiceImpl implements AdminService{

	@Autowired
	AdminDao adminDao;

	@Autowired
	AdminSessionDao adminSessionDao;

	@Autowired
	CompletedTripsDao completedTripsDao;

	@Autowired
	DriverDAO driverDao;

	@Autowired
	CustomerDAO customerDao;

	@Override
	public Admin adminRegister(Admin admin) {
		return adminDao.save(admin);
	}

	@Override
	public Admin updatePassword(AdminDTO dto, String username, String key) {
		Optional<AdminSession> otp = adminSessionDao.findByUuid(key);
		Admin updated = null;
		if (otp.isEmpty())
			throw new LoginException("Admin is not logged in, Please login first!");
		Optional<Admin> opt = adminDao.findByUserUsername(username);
		if (opt.isEmpty())
			throw new UsernameNotFoundException("Username not found");
		else {
			Admin toUpdate = opt.get();
			Integer id = toUpdate.getAdminId();
			AbstractUser user = toUpdate.getUser();
			user.setPassword(dto.getPassword());
			Admin newOne = new Admin(id, user);
			updated = adminDao.save(newOne);
		}
		return updated;

	}

	@Override
	public Admin update(Admin admin, String Username, String key) {
		Admin updated = null;
		Optional<AdminSession> otp = adminSessionDao.findByUuid(key);

		if (otp.isEmpty())
			throw new LoginException("Admin is not logged in, Please login first!");
		else {
			Optional<Admin> opt = adminDao.findByUserUsername(Username);
			if (opt.isEmpty())
				throw new UsernameNotFoundException("Username not found. Please provide proper username");
			else {
				Admin toUpdate = opt.get();
				Integer id = toUpdate.getAdminId();
				Admin newOne = new Admin(id, admin.getUser());
				updated = adminDao.save(newOne);
			}
		}
		return updated;

	}

	@Override

	public String deleteByUsername(AdminDTO dto, String key) {
		Optional<AdminSession> otp = adminSessionDao.findByUuid(key);
		if (otp.isEmpty())
			throw new LoginException("Admin is not logged in, Please login first!");
		else {
			Optional<Admin> opt = adminDao.findByUserUsername(dto.getUsername());
			if (opt.isEmpty())
				throw new UsernameNotFoundException("Username not found");
			else {
				Admin toBeDelete = opt.get();
				adminDao.delete(toBeDelete);
			}
		}
		return "Your Id with Username " + dto.getUsername() + " is Deleted.";
	}

	@Override
	public String logoutAdmin(String key) {
		Optional<AdminSession> otp = adminSessionDao.findByUuid(key);
		if (otp.isEmpty())
			throw new LoginException("Admin is not logged in, Please login first!");

		adminSessionDao.delete(otp.get());
		return "Admin has succefully logged out.";
	}

	@Override
	public List<CompletedTrips> getTripsByCustomerId(Integer customerId, String key) {
		Optional<AdminSession> otp = adminSessionDao.findByUuid(key);
		if (otp.isEmpty())
			throw new LoginException("Admin is not logged in, Please login first!");

		List<CompletedTrips> listOfTrips = completedTripsDao.findByCustomerId(customerId);
		if (listOfTrips.isEmpty())
			throw new CustomerException("No trips Found by this Customer id " + customerId);
		return listOfTrips;
	}

	@Override
	public List<CompletedTrips> getAllTrips(String key) {
		Optional<AdminSession> otp = adminSessionDao.findByUuid(key);
		if (otp.isEmpty())
			throw new LoginException("Admin is not logged in, Please login first!");

		List<CompletedTrips> listOfTrips = completedTripsDao.findAll();
		if (listOfTrips.isEmpty())
			throw new CustomerException("No trips Found Currently.");
		return listOfTrips;
	}

	@Override
	public List<Driver> getListOfDrivers(String key) {
		Optional<AdminSession> otp = adminSessionDao.findByUuid(key);
		if (otp.isEmpty())
			throw new LoginException("Admin is not logged in, Please login first!");

		List<Driver> listOfDrivers = driverDao.findAll();
		if (listOfDrivers.isEmpty())
			throw new DriverException("There are no Drivers present in the Database");
		return listOfDrivers;
	}

	@Override
	public List<Customer> getListOfCustomers(String key) {
		Optional<AdminSession> otp = adminSessionDao.findByUuid(key);
		if (otp.isEmpty())
			throw new LoginException("Admin is not logged in, Please login first!");

		List<Customer> listOfCustomers = customerDao.findAll();
		if (listOfCustomers.isEmpty())
			throw new CustomerException("There are no Customers present in the Database");

		return listOfCustomers;
	}
}
