package com.masai.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class TripBooking {

	@Id
	@SequenceGenerator(name = "trip_generator", sequenceName = "trip_sequence", allocationSize = 50)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "trip_generator")
	private Integer tripbookingId;
	
	private String fromLocation;
	private String toLocation;
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate fromDate;
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate toDate;
	
	private String status;

	private Double distanceInKm;

	private Double bill;

	@ManyToOne
	private Customer customer;

	@ManyToOne
	private Driver driver;

}
