package com.masai.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.masai.model.DriverSession;

@Repository
public interface DriverSessionDao extends JpaRepository<DriverSession, Integer> {

	public Optional<DriverSession> findByDriverId(Integer userId);

	public Optional<DriverSession> findByUuid(String uuid);

}