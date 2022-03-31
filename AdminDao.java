package com.example.demo.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
import com.example.demo.repository.AdminRepository;
import com.example.demo.repository.ParkingFloorRepository;
import com.example.demo.repository.ParkingPremiseRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.AdminService;

@Service("AdminService")
public class AdminDao implements AdminService{
	@Autowired
	AdminRepository adminRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	ParkingPremiseRepository parkingPremiseRepository;
	
	@Autowired
	ParkingFloorRepository parkingFloorRepository;
	
	//1st method
	@Override
	public boolean login(Login login) throws InvalidLoginCredintialException{
		try {
			for(Login i : adminRepository.findAll()) {
				if(i.getUsername().equals(login.getUsername())) {
					if(i.getPassword().equals(login.getPassword())) {
						return true;
					}
					return false;
				}
				return false;
			}
		}
		catch(Exception e) {
			throw new InvalidLoginCredintialException("Login details not found! Invalid details");
		}
		return true;
	}
	
	//2nd method
	@Override
	public boolean blockUser(User user) throws NoSuchUserException{//remove user
		User bean=null;
		try {
			bean = userRepository.findById(user.getUserId()).get();
		}
		catch(Exception e) {
			throw new NoSuchUserException("User details not found");
		}
		userRepository.deleteById(user.getUserId());//deleting an object
		return true;
	}
	
	// Parking Premise
	//3rd method
	@Override
	public boolean addParkingPremise(ParkingPremise parkingPremise) throws DuplicateParkingPremiseException{
		parkingPremiseRepository.save(parkingPremise);//adding object
		return true;
	}
	
	//4th method
	@Override
	public ParkingPremise getParkingPremiseById(int parkingPremiseId) throws NoSuchParkingPremiseException{
		ParkingPremise bean=null;
		try {
			bean = parkingPremiseRepository.findById(parkingPremiseId).get();
		}
		catch(Exception e) {
			throw new NoSuchParkingPremiseException("Parking Premise details not found");
		}
		return bean;
	}
	
	//5th method
	@Override
	public List<ParkingPremise> getParkingPremiseByName(String premiseName) throws NoSuchParkingPremiseException{
		List<ParkingPremise> listOfPremise = new ArrayList<ParkingPremise>();
		try {
			for(ParkingPremise i:parkingPremiseRepository.findAll()) {
				if(i.getParkingPremiseName()==premiseName) {
					listOfPremise.add(i);
				}
			}
		}
		catch(Exception e) {
			throw new NoSuchParkingPremiseException("Parking premise details not found");
		}
		return listOfPremise;
	}
	
	//6th method
	@Override
	public List<ParkingPremise> getAllParkingPremises(){
		return parkingPremiseRepository.findAll();
	}
	
	//7th method
	@Override
	public ParkingPremise modifyParkingPremise(ParkingPremise parkingPremise) throws NoSuchParkingPremiseException{
		ParkingPremise bean=null;
		try {
			bean=parkingPremiseRepository.findById(parkingPremise.getParkingPremiseId()).get();
		}
		catch(Exception e) {
			throw new NoSuchParkingPremiseException("Parking Premise Details Not Found");
		}
		parkingPremiseRepository.saveAndFlush(parkingPremise);
		return bean;
	}
	
	//8th method
	@Override
	public boolean removeParkingPremise(int parkingPremiseId) throws NoSuchParkingPremiseException{
		ParkingPremise bean1 = null;
		try {
			bean1=parkingPremiseRepository.findById(parkingPremiseId).get();
		}
		catch(Exception e) {
			throw new NoSuchParkingPremiseException("Parking Premise Details Not Found");
		}
		parkingPremiseRepository.deleteById(parkingPremiseId);
		return true;
	}
	
	// Parking Floor
	//9th method
	@Override
	public boolean addParkingFloor(ParkingFloor parkingFloor) throws DuplicateParkingFloorException{
		parkingFloorRepository.saveAndFlush(parkingFloor);
		return true;
	}
	
	//10th method
	@Override
	public ParkingFloor getParkingFloorById(int parkingFloorId) throws NoSuchParkingFloorException{
		ParkingFloor bean=null;
		try {
			bean=parkingFloorRepository.findById(parkingFloorId).get();
		}
		catch(Exception e) {
			throw new NoSuchParkingFloorException("Parking Floor Details Not Found");
		}
		return bean;
	}
	
	//11th method
	@Override
	public List<ParkingFloor> getParkingFloorByNumber(int parkingPremiseId, String floorNumber) throws NoSuchParkingFloorException{
		List<ParkingFloor> listOfFloor = new ArrayList<ParkingFloor>();
		try {
			for(ParkingFloor i:parkingFloorRepository.findAll()) {
				if(i.getFloorNumber().equals(floorNumber) && i.getParkingPremise().getParkingPremiseId()==parkingPremiseId) {
					listOfFloor.add(i);
				}
			}
		}
		catch(Exception e) {
			throw new NoSuchParkingFloorException("Parking Floor Details Not Found");
		}
		return listOfFloor;
		
	}
	
	//12th method
	@Override
	public List<ParkingFloor> getAllParkingFloors(int parkingPremiseId){
		return parkingPremiseRepository.findById(parkingPremiseId).get().getParkingFloors();
		
	}
	
	//13th method
	@Override
	public ParkingFloor modifyParkingCapacity(ParkingFloor parkingFloor) throws NoSuchParkingFloorException{
		ParkingFloor bean=null;
		try {
			bean=parkingFloorRepository.findById(parkingFloor.getParkingFloorId()).get();
		}
		catch(Exception e) {
			throw new NoSuchParkingFloorException("Parking Floor Details Not Found");
		}
		parkingFloorRepository.saveAndFlush(parkingFloor);
		return bean;
	}
	
	//14th method
	@Override
	public boolean removeParkingFloor(int parkingFloorId) throws NoSuchParkingFloorException{
		ParkingFloor bean=null;
		try {
			bean=parkingFloorRepository.findById(parkingFloorId).get();
		}
		catch (Exception e) {
			throw new NoSuchParkingFloorException("Parking Floor Details Not Found");
		}
		parkingFloorRepository.deleteById(parkingFloorId);
		return true;
	}

}
