package com.masai.service;

import java.util.List;

import com.masai.exception.TripBookingException;
import com.masai.model.TripBooking;

public interface TripBookingService {
	
	public TripBooking insertTripBooking(TripBooking tripBooking) throws TripBookingException;
	public TripBooking updateTripBooking(TripBooking tripBooking) throws TripBookingException;
	public TripBooking deleteTripBooking(Integer tripBookingId) throws TripBookingException;
	public List<TripBooking> viewAllTripsCustomer(Integer customerId) throws TripBookingException;
	public TripBooking calculateBill(Integer customerId) throws TripBookingException;

}
