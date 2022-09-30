package com.masai.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.masai.model.Cab;

@Repository
public interface CabDao extends JpaRepository<Cab, Integer>{

	public List<Cab> findByCabType(String cabType);	
	
	@Query("select count(cabType) from Cab c where c.cabType = ?1")
	public Integer countCabByType(String cabType);
	
}
