package com.masai.controller;

import java.util.List;

import javax.validation.Valid;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.masai.dto.AdminDTO;
import com.masai.dto.CabDTO;
import com.masai.dto.LoginDTO;
import com.masai.exception.CabException;
import com.masai.exception.CustomerException;
import com.masai.exception.DriverException;
import com.masai.exception.LoginException;
import com.masai.exception.TripBookingException;
import com.masai.model.Admin;
import com.masai.model.AdminSession;
import com.masai.model.Cab;
import com.masai.model.CabType;
import com.masai.model.Customer;
import com.masai.model.Driver;
import com.masai.model.TripBooking;
import com.masai.service.AdminService;
import com.masai.service.CabService;
import com.masai.service.DriverServices;
import com.masai.service.TripBookingService;



@RestController
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private AdminService adminService;
	
	@Autowired
	private CabService cabService;
	
	@Autowired
	private DriverServices driverServices;
	
	@Autowired
	private TripBookingService tripBookingService;

	
	@PostMapping("/register")
	public ResponseEntity<Admin> registerAdminHandler(@Valid @RequestBody Admin admin) {
		Admin registered = adminService.adminRegister(admin);
		return new  ResponseEntity<Admin>(registered, HttpStatus.OK);
	}
	
	@PostMapping("/login")
	public ResponseEntity<AdminSession> loginAdminHandler(@RequestBody LoginDTO loginDto) throws LoginException {
		AdminSession currentSession = adminService.adminLogin(loginDto);
		return new ResponseEntity<AdminSession>(currentSession, HttpStatus.CREATED);
	}
	
	@PostMapping("/logout")
	public ResponseEntity<String> logoutAdminHandler(@RequestParam String key) throws LoginException {
		String logOutCurrentSession = adminService.logoutAdmin(key);
		return new ResponseEntity<String>(logOutCurrentSession, HttpStatus.OK);
	}
	
	@PutMapping("/update/{username}")
	public ResponseEntity<Admin> updateAdminHandler(@Valid @RequestBody Admin admin, @PathVariable String username, @RequestParam String key ) throws LoginException {
		Admin updatedAdmin = adminService.updateAdmin(admin, username, key);
		return new ResponseEntity<Admin>(updatedAdmin, HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/delete/{adminId}")
	public ResponseEntity<Admin> deleteAdminByIdHandler(@PathVariable Integer adminId, @RequestParam String key) throws LoginException {
		Admin deletedAdmin = adminService.deleteAdminById(adminId, key);
		return new ResponseEntity<Admin>(deletedAdmin, HttpStatus.OK);
	}
	
	@GetMapping("/trips")
	public ResponseEntity<List<TripBooking>> getAllTripsHandler(@RequestParam String key) throws LoginException, TripBookingException {
		List<TripBooking> allTrips = adminService.getAllTrips(key);
		return new ResponseEntity<List<TripBooking>>(allTrips, HttpStatus.OK);
	}
	
	@GetMapping("/drivers")
	public ResponseEntity<List<Driver>> getAllDriversHandler(@RequestParam String key) throws LoginException, DriverException {
		List<Driver> allDrivers = adminService.getListOfDrivers(key);
		return new ResponseEntity<List<Driver>>(allDrivers, HttpStatus.OK);
	}
	
	@GetMapping("/customers")
	public ResponseEntity<List<Customer>> getAllCustomersHandler(@RequestParam String key) throws LoginException, CustomerException {
		List<Customer> allCustomers = adminService.getListOfCustomers(key);
		return new ResponseEntity<List<Customer>>(allCustomers, HttpStatus.OK);
	}
	
	@GetMapping("/cabs/{type}")
	public ResponseEntity<List<Cab>> getCabsByCabTypeHandler(@PathVariable CabType type, @RequestParam String key) throws CabException, LoginException {
		List<Cab> cabs = cabService.viewCabsOfType(type, key);
		return new ResponseEntity<List<Cab>>(cabs, HttpStatus.OK);
	}
	
	@GetMapping("/coutCabs/{type}")
	public ResponseEntity<CabDTO> countCabsByCabTypeHandler(@PathVariable CabType type, @RequestParam String key) throws CabException, LoginException {
		CabDTO cabdto = cabService.countCabsOfType(type, key);
		return new ResponseEntity<CabDTO>(cabdto, HttpStatus.OK);
	}
	
	@DeleteMapping("/driver/{username}")
	public ResponseEntity<String> removeDriverHandler(@PathVariable String usernmae, @RequestParam String key) throws LoginException, DriverException {
		String removedDriver = driverServices.removeDriver(usernmae, key);
		return new ResponseEntity<String>(removedDriver, HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/trip/{id}")
	public ResponseEntity<TripBooking> deleteTripHandler(@PathVariable Integer id, @RequestParam String key) throws LoginException, TripBookingException {
		TripBooking deletedTrip = tripBookingService.deleteTripBooking(id, key);
		return new ResponseEntity<TripBooking>(deletedTrip, HttpStatus.ACCEPTED);
	}
	
	
}
