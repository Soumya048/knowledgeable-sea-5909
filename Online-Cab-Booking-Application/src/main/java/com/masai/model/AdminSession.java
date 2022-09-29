package com.masai.model;

import java.time.LocalDateTime;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

public class AdminSession {
	
	@Id
	@SequenceGenerator(name="adminSession_generator", sequenceName = "adminSession_sequence", allocationSize=100)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "adminSession_generator")
	private Integer sessionId;
	private Integer adminId;
	private String uuid;
	private String userType;
	private LocalDateTime sessionStartTime;
	private LocalDateTime sessionEndTime;

}
