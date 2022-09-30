package com.masai.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.masai.model.Admin;

@Service
public interface AdminDao extends JpaRepository<Admin, Integer> {

}
