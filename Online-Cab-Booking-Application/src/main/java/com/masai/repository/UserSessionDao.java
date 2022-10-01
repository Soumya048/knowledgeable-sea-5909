package com.masai.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.masai.model.UserSession;

@Repository
public interface UserSessionDao extends JpaRepository<UserSession, Integer> {

//	public Optional<UserSession> findByAId(Integer userId);
	public Optional<UserSession> findByUuId(String  uuId);
	
	public Optional<UserSession> findByUserId(Integer userId);
	
}
