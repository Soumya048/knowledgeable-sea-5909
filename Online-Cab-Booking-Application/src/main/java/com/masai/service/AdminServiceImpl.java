package com.masai.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.dto.LoginDTO;
import com.masai.exception.AdminException;
import com.masai.exception.CustomerException;
import com.masai.exception.DriverException;
import com.masai.exception.LoginException;
import com.masai.exception.TripBookingException;
import com.masai.model.Admin;
import com.masai.model.AdminSession;
import com.masai.model.Cab;
import com.masai.model.CabType;
import com.masai.model.Customer;
import com.masai.model.Driver;
import com.masai.model.TripBooking;
import com.masai.repository.AdminDao;
import com.masai.repository.AdminSessionDao;
import com.masai.repository.CustomerDao;
import com.masai.repository.DriverDao;
import com.masai.repository.TripbookingDao;

@Service
public class AdminServiceImpl implements AdminService {
	
	@Autowired
	AdminDao adminDao;
	
	@Autowired
	AdminSessionDao adminSessionDao;
	
	@Autowired
	TripbookingDao tripBookingDao;
	
	
	@Autowired
	DriverDao driverDao;

	@Autowired
	CustomerDao customerDao;
	
	
	@Override
	public Admin adminRegister(Admin admin) throws AdminException {
		
		String adminMobile = admin.getAbstractUser().getMobile();
		
		Optional<Admin> opt = adminDao.findByAbstractUserMobile(adminMobile);
		
		if(opt.isPresent())
			throw new AdminException("Admin already exist with" + adminMobile);
		
		Admin registeredAdmin = adminDao.save(admin);
		return registeredAdmin;
	}
	
	@Override
	public AdminSession adminLogin(LoginDTO loginDto) throws LoginException {
		
		Optional<Admin> opt = adminDao.findByAbstractUserMobile(loginDto.getMobile());
		
		
		if(!opt.isPresent()) {
			throw new LoginException("User Not found");
		}
		
		Admin existingAdmin = opt.get();
		Optional<AdminSession> sessionOpt = adminSessionDao.findByAdminId(existingAdmin.getAdminId());
		
		if(sessionOpt.isPresent()) {
			throw new LoginException("User already logged in");
		}
		
		if(existingAdmin.getAbstractUser().getPassword().equals(loginDto.getPassword())) {
			
			UUID randomUUID = UUID.randomUUID();
			String key = randomUUID.toString().replaceAll("-", "");
			
			
			AdminSession newAdminSession = new AdminSession();
			
			newAdminSession.setAdminId(existingAdmin.getAdminId());
			newAdminSession.setUuid(key);
			newAdminSession.setUserType("Admin");
			newAdminSession.setSessionStartTime(LocalDateTime.now());
			newAdminSession.setSessionEndTime(LocalDateTime.now().plusHours(2));
			return adminSessionDao.save(newAdminSession);
		}
		else
			throw new LoginException("Password Incorrect, Please Try Again");
	}

	@Override
	public Admin updateAdmin(Admin admin, String Username, String key) throws LoginException {
		Admin updated = null;
		Optional<AdminSession> opt = adminSessionDao.findByUuid(key);
		
		if(opt.isPresent()) {
			Optional<Admin> adminOpt = adminDao.findByAbstractUserUsername(Username);
			
			System.out.println(adminOpt.get());
			
			if(adminOpt.isPresent()) {
				
				Integer id = admin.getAdminId();
				Admin newData = new Admin();
				newData.setAdminId(id);
				newData.setAbstractUser(admin.getAbstractUser());
				
				updated = adminDao.save(newData);
				
			}
			
		}
		else 
			throw new LoginException("Admin is not logged in, Please Login first");
		
		return updated;
		
	}

	@Override
	public Admin deleteAdminById(Integer adminId, String key) throws LoginException {
		
		Admin existingAdmin = null;
		
		Optional<AdminSession> opt = adminSessionDao.findByUuid(key);
		
		if(opt.isPresent()) {
			Optional<Admin> adminOpt = adminDao.findById(adminId);
			
			if(adminOpt.isPresent()) {
				existingAdmin = adminOpt.get();
				adminDao.delete(existingAdmin);
			}
			else
				throw new AdminException("Admin not found with id: " + adminId);
		}
		else 
			throw new LoginException("Admin is not logged in, Please Login first");
		
		return existingAdmin;
	}

	@Override
	public String logoutAdmin(String key) throws LoginException {
		Optional<AdminSession> opt = adminSessionDao.findByUuid(key);

		if(!opt.isPresent()) {
			throw new LoginException("Not Logged in, Log in first");
		}
		adminSessionDao.delete(opt.get());
		return "Log out Successful";
	}


	@Override
	public List<TripBooking> getAllTrips(String key) throws LoginException, TripBookingException {
		
		Optional<AdminSession> opt = adminSessionDao.findByUuid(key);
		
		if(!opt.isPresent()) {
			throw new LoginException("Please Login first");
		}
		
		List<TripBooking> trips = tripBookingDao.findAll();
				
		if(trips.size() > 0) {
			return trips;
		}
		else
			throw new TripBookingException("Trips not found!");
		
	}


	@Override
	public List<Driver> getListOfDrivers(String key) throws LoginException, DriverException {
		
		Optional<AdminSession> opt = adminSessionDao.findByUuid(key);
		
		if(!opt.isPresent()) {
			throw new LoginException("Admin is not logged in, Please login first!");
		}
		
		List<Driver> listOfDrivers = driverDao.findAll();
		
		if(listOfDrivers.isEmpty())
			throw new DriverException("No Drivers found");
		
		return listOfDrivers;
	}

	@Override
	public List<Customer> getListOfCustomers(String key) throws LoginException, CustomerException {
		Optional<AdminSession> opt = adminSessionDao.findByUuid(key);
		
		if(!opt.isPresent()) {
			throw new LoginException("Admin is not logged in, Please login first!");
		}
		
		List<Customer> listOfCustomer = customerDao.findAll();
		
		if(listOfCustomer.isEmpty())
			throw new CustomerException("Customers not found");
		
		return listOfCustomer;
	}
	

}
