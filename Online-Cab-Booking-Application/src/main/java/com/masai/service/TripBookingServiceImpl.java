package com.masai.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.exception.TripBookingException;
import com.masai.model.TripBooking;
import com.masai.repository.TripbookingDao;

@Service
public class TripBookingServiceImpl implements TripBookingService {

	@Autowired
	TripbookingDao tripBookingDao;
	
	@Override
	public TripBooking insertTripBooking(TripBooking tripBooking) {
		
		TripBooking insertedTrip = tripBookingDao.save(tripBooking);
		return insertedTrip;
		
	}

	@Override
	public TripBooking updateTripBooking(TripBooking tripBooking) throws TripBookingException {
		
		Optional<TripBooking> opt = tripBookingDao.findById(tripBooking.getTripbookingId());
		
		if(opt.isPresent()) {
			TripBooking updatedTrip = tripBookingDao.save(tripBooking);
			return updatedTrip;
		}
		else
			throw new TripBookingException("User not found!");
	}

	@Override
	public TripBooking deleteTripBooking(Integer tripBookingId) throws TripBookingException {
		
		Optional<TripBooking> opt = tripBookingDao.findById(tripBookingId);
		
		if(opt.isPresent()) {
			
			TripBooking existingTrip = opt.get();
			tripBookingDao.delete(existingTrip);
			
			return existingTrip;
		}
		else
			throw new TripBookingException("User not found!");
	}

	@Override
	public List<TripBooking> viewAllTripsCustomer(Integer customerId) {
		
//		List<TripBooking> trips = ;
		
		return null;
	}

	@Override
	public TripBooking calculateBill(Integer customerId) {
		// TODO Auto-generated method stub
		return null;
	}

	
	
}
