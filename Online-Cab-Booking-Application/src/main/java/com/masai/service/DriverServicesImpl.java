package com.masai.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.dto.LoginDTO;
import com.masai.exception.DriverException;
import com.masai.exception.LoginException;
import com.masai.model.Admin;
import com.masai.model.AdminSession;
import com.masai.model.Driver;
import com.masai.model.DriverSession;
import com.masai.repository.DriverDao;
import com.masai.repository.DriverSessionDao;

@Service
public class DriverServicesImpl implements DriverServices {

	@Autowired
	DriverDao driverDao;

	@Autowired
	DriverSessionDao driverSessionDao;

	
	@Override
	public Driver newDriver(Driver driver) throws DriverException {

		Optional<Driver> optDriver = driverDao.findByUserMobile(driver.getUser().getMobile());

		if (optDriver.isPresent()) {
			System.out.println("Driver already exist with this mobile Number");

			throw new DriverException("Driver already exist with this mobile Number");
		}
		driver.getCab().setPerKmRate(driver.getCab().getCabType().getPrice());
		Driver newDriver = driverDao.save(driver);
		return newDriver;
	}

	

	@Override
	public String removeDriver(String name, String key) throws LoginException, DriverException {
		Optional<DriverSession> opt = driverSessionDao.findByUuid(key);

		if (opt.isEmpty()) {
			throw new LoginException("Driver is not loged in ,Please log in first");
		}

		Optional<Driver> driveropt = driverDao.findByUserUsername(name);
		if (driveropt == null) {
			System.out.println("No driver exist");
			throw new DriverException("Driver not found with this name");
		}
		Driver deletDriver = driveropt.get();

		driverDao.delete(deletDriver);
		return "Driver :" + name + "deleted from the table";
	}
	
	@Override
	public Driver updateDriver(Driver driver, String key) throws LoginException {
		Optional<DriverSession> opt = driverSessionDao.findByUuid(key);

		if (opt.isEmpty()) {
			throw new LoginException("Driver is not loged in ,Please log in first");
		}

		Driver updateddriver = driverDao.save(driver);
		return updateddriver;
	}
	
	@Override
	public String updateStatus(String newStatus, String key) throws LoginException, DriverException {
		String status = null;
		String msg = null;
		Optional<DriverSession> ott = driverSessionDao.findByUuid(key);

		if (ott.isEmpty()) {
			throw new LoginException("Driver is not loged in ,Please log in first");
		}

		if (newStatus.equalsIgnoreCase("Y")) {
			status = "YES";
			msg = "Your Status is Changed to Online.";
		} else if (newStatus.equalsIgnoreCase("N")) {
			status = "NO";
			msg = "Your Status is Changed to Offline.";

		} else {
			throw new DriverException("Please select 'Y' or 'N' only.");
		}

		DriverSession driverSession = ott.get();
		Integer driverId = driverSession.getDriverId();
		Optional<Driver> existingDriverOpt = driverDao.findById(driverId);
		Driver existingDriver = existingDriverOpt.get();
		existingDriver.getCab().setAvailable(status);
		driverDao.save(existingDriver);

		return msg;
	}

	@Override
	public String logoutDriver(String key) throws LoginException {
		Optional<DriverSession> opt = driverSessionDao.findByUuid(key);
		if (!opt.isPresent())
			throw new LoginException("Driver is not loged in ,Please log in first");

		else {

			driverSessionDao.delete(opt.get());
		}

		return "Driver has succefully logged out.";

	}

	@Override
	public DriverSession loginDriver(LoginDTO loginDto) throws LoginException {
		Optional<Driver> opt = driverDao.findByUserMobile(loginDto.getMobile());
		
		
		if(!opt.isPresent()) {
			throw new LoginException("Driver Not found");
		}
		
		Driver existingDriver = opt.get();
		Optional<DriverSession> sessionOpt = driverSessionDao.findByDriverId(existingDriver.getDriverId());
		
		if(sessionOpt.isPresent()) {
			throw new LoginException("User already logged in");
		}
		
		if(existingDriver.getUser().getPassword().equals(loginDto.getPassword())) {
			
			UUID randomUUID = UUID.randomUUID();
			String key = randomUUID.toString().replaceAll("-", "");
			
			
			DriverSession newDriverSession = new DriverSession();
			
			newDriverSession.setDriverId(existingDriver.getDriverId());
			newDriverSession.setUuid(key);
			newDriverSession.setUserType("Driver");
			newDriverSession.setSessionStartTime(LocalDateTime.now());
			newDriverSession.setSessionEndTime(LocalDateTime.now().plusHours(2));
			return driverSessionDao.save(newDriverSession);
		}
		else
			throw new LoginException("Password Incorrect, Please Try Again");
	}
	
	
	

}
