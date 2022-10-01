package com.masai.service;


import com.masai.dto.LoginDTO;
import com.masai.exception.DriverException;
import com.masai.exception.LoginException;
import com.masai.model.Driver;
import com.masai.model.DriverSession;

public interface DriverServices {

	public Driver newDriver(Driver driver) throws DriverException;
	public String removeDriver(String name, String key) throws LoginException, DriverException;
	public Driver updateDriver(Driver driver, String key) throws LoginException;
	public String updateStatus(String newStatus, String key) throws LoginException, DriverException;
	public String logoutDriver(String key) throws LoginException;
	public DriverSession loginDriver(LoginDTO loginDto) throws LoginException;
	
}
