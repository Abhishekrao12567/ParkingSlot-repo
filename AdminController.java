package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exception.DuplicateParkingFloorException;
import com.example.demo.exception.DuplicateParkingPremiseException;
import com.example.demo.exception.InvalidLoginCredintialException;
import com.example.demo.exception.NoSuchParkingFloorException;
import com.example.demo.exception.NoSuchParkingPremiseException;
import com.example.demo.exception.NoSuchUserException;
import com.example.demo.model.Login;
import com.example.demo.model.ParkingFloor;
import com.example.demo.model.ParkingPremise;
import com.example.demo.model.User;
import com.example.demo.service.AdminService;

@RestController
@RequestMapping("/admin/")
public class AdminController {
	
	@Autowired
	AdminService adminService;
	
	@GetMapping("login")//Get something from DB
	public boolean login(@RequestBody Login login) throws InvalidLoginCredintialException{
		return adminService.login(login);
	}
	
	@DeleteMapping("blockuser")//delete from DB
	public boolean blockUser(@RequestBody User user) throws NoSuchUserException{
		return adminService.blockUser(user);
	}

	@PostMapping("addparkingpremise")//add to DB
	public boolean addParkingPremise(@RequestBody ParkingPremise parkingPremise) throws DuplicateParkingPremiseException{
		return adminService.addParkingPremise(parkingPremise);
	}
	
	@GetMapping("getparkingpremisebyid/{parkingPremiseId}")
	public ParkingPremise getParkingPremiseById(@PathVariable int parkingPremiseId) throws NoSuchParkingPremiseException{
		return adminService.getParkingPremiseById(parkingPremiseId);
	}
	
	@GetMapping("getparkingpremisebyname/{premiseName}")
	public List<ParkingPremise> getParkingPremiseByName(@PathVariable String premiseName) throws NoSuchParkingPremiseException{
		return adminService.getParkingPremiseByName(premiseName);
	}
	
	@GetMapping("getallparkingpremises")
	public List<ParkingPremise> getAllParkingPremises(){
		return adminService.getAllParkingPremises();
	}
	
	@PutMapping("modifyparkingpremise")
	public ParkingPremise modifyParkingPremise(@RequestBody ParkingPremise parkingPremise) throws NoSuchParkingPremiseException{
		return adminService.modifyParkingPremise(parkingPremise);
	}
	
	@DeleteMapping("removeparkingpremise")
	public boolean removeParkingPremise(@PathVariable int parkingPremiseId) throws NoSuchParkingPremiseException{
		return adminService.removeParkingPremise(parkingPremiseId);
	}
	
	@PostMapping("addparkingfloor")
	public boolean addParkingFloor(@RequestBody ParkingFloor parkingFloor) throws DuplicateParkingFloorException{
		return adminService.addParkingFloor(parkingFloor);
	}
	
	@GetMapping("getparkingfloorbyid/{parkingFloorId}")
	public ParkingFloor getParkingFloorById(@PathVariable int parkingFloorId) throws NoSuchParkingFloorException{
		return adminService.getParkingFloorById(parkingFloorId);
	}
	
	@GetMapping("getparkingfloorbynumber/{parkingPremiseId}/{floorNumber}")
	public List<ParkingFloor> getParkingFloorByNumber(@PathVariable int parkingPremiseId,@PathVariable  String floorNumber) throws NoSuchParkingFloorException{
		return adminService.getParkingFloorByNumber(parkingPremiseId, floorNumber);
	}
	
	@GetMapping("getallparkingfloors/{parkingPremiseId}")
	public List<ParkingFloor> getAllParkingFloors(@PathVariable int parkingPremiseId){
		return adminService.getAllParkingFloors(parkingPremiseId);
	}
	
	@GetMapping("modifyParkingCapacity")
	public ParkingFloor modifyParkingCapacity(ParkingFloor parkingFloor) throws NoSuchParkingFloorException{
		return adminService.modifyParkingCapacity(parkingFloor);
	}
	
	@GetMapping("removeparkingfloor/{parkingFloorId}")
	public boolean removeParkingFloor(@PathVariable int parkingFloorId) throws NoSuchParkingFloorException{
		return adminService.removeParkingFloor(parkingFloorId);
	}

}
