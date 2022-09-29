package com.masai.model;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Admin {

	@Id
	@SequenceGenerator(name="admin_generator", sequenceName = "admin_generator", allocationSize=100)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "admin_generator")
	private Integer adminId;
	
//	@Embedded
//	private AbstractUser abstractUser;
	
}
