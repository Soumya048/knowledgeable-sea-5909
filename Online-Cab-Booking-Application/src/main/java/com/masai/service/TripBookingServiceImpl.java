package com.masai.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.exception.LoginException;
import com.masai.exception.TripBookingException;
import com.masai.model.AdminSession;
import com.masai.model.DriverSession;
import com.masai.model.TripBooking;
import com.masai.model.UserSession;
import com.masai.repository.AdminSessionDao;
import com.masai.repository.DriverSessionDao;
import com.masai.repository.TripbookingDao;
import com.masai.repository.UserSessionDao;

import io.swagger.models.auth.In;

@Service
public class TripBookingServiceImpl implements TripBookingService {

	@Autowired
	TripbookingDao tripBookingDao;
	
	@Autowired
	AdminSessionDao adminSessionDao;
	
	@Autowired
	UserSessionDao userSessionDao;
	
	@Autowired
	DriverSessionDao driverSessionDao;
	
	@Override
	public TripBooking insertTripBooking(TripBooking tripBooking) {
		
		TripBooking insertedTrip = tripBookingDao.save(tripBooking);
		return insertedTrip;
		
	}

	@Override
	public TripBooking updateTripBooking(TripBooking tripBooking, String key) throws TripBookingException, LoginException {
		
		Optional<UserSession> userOpt = userSessionDao.findByUuId(key);
		
		if(!userOpt.isPresent()) 
			throw new LoginException("Customer is not loged in, Please Log in first");
		
		
		Optional<TripBooking> opt = tripBookingDao.findById(tripBooking.getTripbookingId());
		
		if(opt.isPresent()) {
			TripBooking updatedTrip = tripBookingDao.save(tripBooking);
			return updatedTrip;
		}
		else
			throw new TripBookingException("User not found!");
	}

	@Override
	public TripBooking deleteTripBooking(Integer tripBookingId, String key) throws TripBookingException, LoginException {
		
		Optional<AdminSession> Opt = adminSessionDao.findByUuid(key);
		
		if(!Opt.isPresent()) 
			throw new LoginException("Admin is not logged in, Please Log in first");
		
		
		Optional<TripBooking> opt = tripBookingDao.findById(tripBookingId);
		
		if(opt.isPresent()) {
			
			TripBooking existingTrip = opt.get();
			tripBookingDao.delete(existingTrip);
			
			return existingTrip;
		}
		else
			throw new TripBookingException("Trip is not found with id: " + tripBookingId);
	}

	@Override
	public TripBooking updateTripStatus(Integer tripBookingId, String status, String key) throws TripBookingException, LoginException {
		
		Optional<DriverSession> driverOpt = driverSessionDao.findByUuid(key);
		
		
		if(driverOpt.isPresent()) {
			
			Optional<TripBooking> tripOpt = tripBookingDao.findById(tripBookingId);
			
			if(!tripOpt.isPresent())
				throw new TripBookingException("Trip not present with trip id:" + tripBookingId);
			
			TripBooking existTrip = tripOpt.get();
			status = status.toLowerCase();
			if(status.equals("completed") || status.equals("ongoing") || status.equals("cancelled")) {
				existTrip.setStatus(status);
				return tripBookingDao.save(existTrip);
			}
			else {
				throw new TripBookingException("Invalid status, valid status are completed, ongoing, cancelled");
			}
			
		}
		else {
			throw new LoginException("Please Login first!");
		}
		
	}




}
