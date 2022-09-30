package com.masai.controller;

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
import com.masai.dto.LoginDTO;
import com.masai.exception.LoginException;
import com.masai.model.Admin;
import com.masai.model.AdminSession;
import com.masai.model.TripBooking;
import com.masai.service.AdminService;



@RestController
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private AdminService adminService;

	
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
	
//	@GetMapping("/trips")
//	public ResponseEntity<TripBooking> getAllTripsHandler() {
//		
//	}
	
}
