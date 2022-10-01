package com.masai.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.masai.exception.TripBookingException;
import com.masai.model.TripBooking;

@Repository
public interface TripbookingDao extends JpaRepository<TripBooking, Integer> {
	
//	public List<TripBooking> findByCustomerCustomerId(Integer customerId);
	
}
