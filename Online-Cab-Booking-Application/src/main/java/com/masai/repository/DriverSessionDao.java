package com.masai.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.masai.model.DriverSession;

@Repository
public interface DriverSessionDao extends JpaRepository<DriverSession, Integer> {

}