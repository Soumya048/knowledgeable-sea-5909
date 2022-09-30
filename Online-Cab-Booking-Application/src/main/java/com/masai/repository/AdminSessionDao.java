package com.masai.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.masai.model.AdminSession;

@Repository
public interface AdminSessionDao extends JpaRepository<AdminSession, Integer> {
	
	public Optional<AdminSession> findByAdminId(Integer adminId);
	public Optional<AdminSession> findByUuid(String uuid);

}
