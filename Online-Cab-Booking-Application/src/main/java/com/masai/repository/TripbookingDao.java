package com.masai.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.masai.model.Customer;
import com.masai.model.TripBooking;

@Repository
public interface TripbookingDao extends JpaRepository<TripBooking, Integer> {
	

	
}
