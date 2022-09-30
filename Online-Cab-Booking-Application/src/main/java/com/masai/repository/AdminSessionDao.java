package com.masai.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.masai.model.AdminSession;

@Repository
public interface AdminSessionDao extends JpaRepository<AdminSession, Integer> {

}
