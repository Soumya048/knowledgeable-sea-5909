package com.masai.service;

import java.util.List;

import com.masai.exception.LoginException;
import com.masai.exception.TripBookingException;
import com.masai.model.TripBooking;

public interface TripBookingService {

	public TripBooking insertTripBooking(TripBooking tripBooking) throws TripBookingException;
	public TripBooking updateTripBooking(TripBooking tripBooking, String key) throws TripBookingException, LoginException;
	public TripBooking deleteTripBooking(Integer tripBookingId, String key) throws TripBookingException, LoginException;
	public TripBooking updateTripStatus(Integer tripBookingId, String status, String key) throws TripBookingException, LoginException;

	
}
