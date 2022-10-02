package com.masai.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.dto.LoginDTO;
import com.masai.exception.CustomerException;
import com.masai.exception.LoginException;
import com.masai.model.Customer;
import com.masai.model.Driver;
import com.masai.model.TripBooking;
import com.masai.model.UserSession;
import com.masai.repository.CustomerDao;
import com.masai.repository.DriverDao;
import com.masai.repository.TripbookingDao;
import com.masai.repository.UserSessionDao;


@Service
public class CustomerServiceImpl implements CustomerService {
	@Autowired
	private CustomerDao customerDao;
	@Autowired
	private UserSessionDao sessionDao;
	@Autowired
	private DriverDao driverDao;
	@Autowired
	private TripbookingDao tb;
	
	@Override
	public UserSession loginCustomer(LoginDTO customer) throws LoginException, CustomerException {

		Optional<Customer> res =customerDao.findByAbstractUserMobile(customer.getMobile());

		if (!res.isPresent()) {
			
			System.out.println(res + " Data is empty");
			throw new CustomerException("Customer does not exist with the given mobile number");
		}

		Customer existingCustomer = res.get();
		Optional<UserSession> opt = sessionDao.findByUserId(existingCustomer.getCustomerId());

		if (opt.isPresent())
			throw new LoginException("User already logged in");

		if (existingCustomer.getAbstractUser().getPassword().equals(customer.getPassword())) {

			UserSession newSession = new UserSession();

			newSession.setUserId(existingCustomer.getCustomerId());
			newSession.setUserType("Customer");
			newSession.setSessionStartTime(LocalDateTime.now());
			newSession.setSessionEndTime(LocalDateTime.now().plusHours(1));

			UUID uuid = UUID.randomUUID();
			String token = uuid.toString().split("-")[0];

			newSession.setUuId(token);
			

			return sessionDao.save(newSession);
		} else {
			throw new LoginException("Password Incorrect. Try again.");
		}

	}


	@Override
	public Customer insertCustomer(Customer customer)throws CustomerException {
		
		Optional<Customer> existing = customerDao.findByAbstractUserMobile(customer.getAbstractUser().getMobile());

		if (existing.isPresent()) {

			System.out.println("Customer is already present");
			throw new CustomerException("A Customer already exist with this mobile number in the Database");
		}

		return customerDao.save(customer);

	}
	@Override
	public String logoutCustomer(String key)throws CustomerException  {
		Optional<UserSession> otp = sessionDao.findByUuId(key);
		if (otp.isEmpty())
			throw new CustomerException("User is not logged in, Please login first!");
		else {

			sessionDao.delete(otp.get());
		}

		return "Customer has succefully logged out.";

	}
	
	@Override
	public TripBooking bookTrip(TripBooking trip, String key) throws CustomerException {
		TripBooking res = null;
		Random r = new Random();
		Double doubleKM = r.nextDouble(10, 500);
		BigDecimal bigD = new BigDecimal(doubleKM).setScale(2, RoundingMode.HALF_UP);
		Double distanceKm = bigD.doubleValue();

		Optional<UserSession> otp = sessionDao.findByUuId(key);
		if (otp.isEmpty())
			throw new CustomerException("User is not logged in, Please login first!");
		else {
			Optional<Customer> cust = customerDao.findById(trip.getCustomer().getCustomerId());
			Customer checkCustomer = cust.get();

			Optional<Driver> driv = driverDao.findById(trip.getDriver().getDriverId());
			Driver checkDriver = driv.get();
			
			TripBooking newTrip = new TripBooking();
			newTrip.setCustomer(checkCustomer);
			newTrip.setDistanceInKm(distanceKm);
			
			Double billAmount = checkDriver.getCab().getCabType().getPrice() * newTrip.getDistanceInKm();
			BigDecimal bigDec = new BigDecimal(billAmount).setScale(2, RoundingMode.HALF_UP);
			Double roundBill = bigDec.doubleValue();
			newTrip.setBill(roundBill);
			
			newTrip.setDriver(checkDriver);
			newTrip.setFromDate(trip.getFromDate());
			newTrip.setFromLocation(trip.getFromLocation());
			newTrip.setStatus("Confirmed");
			newTrip.setToDate(trip.getToDate());
			newTrip.setToLocation(trip.getToLocation());
			res = tb.save(newTrip);

		}
		return res;
	}

	@Override
	public Customer updateCustomer(Customer customer,String key) throws CustomerException {
		
		Optional<UserSession> otp = sessionDao.findByUuId(key);
		if (otp.isEmpty())
			throw new CustomerException("User is not logged in, Please login first!");
		else {
			
		}
		return null;
	}	
	

	@Override
	public Customer deleteCustomer(int customerId, String key) throws CustomerException {
		Optional<UserSession> otp = sessionDao.findByUuId(key);
		if (otp.isEmpty())
			throw new CustomerException("User is not logged in, Please login first!");
		else {
			
		}
		
		return null;
	}

}
