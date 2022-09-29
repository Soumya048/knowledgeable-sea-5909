package com.masai.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Cab {

	
@Id
@SequenceGenerator(name="cab_generator", sequenceName = "cab_seq", allocationSize=50)
@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cab_generator")
private	Integer cabId;
private CabTpye cabTpye;
private double perKmRate;
private String available;

}


