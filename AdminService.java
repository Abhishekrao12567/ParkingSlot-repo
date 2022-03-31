package com.example.demo.service;

import java.util.List;

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

public interface AdminService {
	public boolean login(Login login) throws InvalidLoginCredintialException;
	public boolean blockUser(User user) throws NoSuchUserException;
	
	// Parking Premise
	public boolean addParkingPremise(ParkingPremise parkingPremise) throws DuplicateParkingPremiseException;
	public ParkingPremise getParkingPremiseById(int parkingPremiseId) throws NoSuchParkingPremiseException;
	public List<ParkingPremise> getParkingPremiseByName(String premiseName) throws NoSuchParkingPremiseException;
	public List<ParkingPremise> getAllParkingPremises();
	public ParkingPremise modifyParkingPremise(ParkingPremise parkingPremise) throws NoSuchParkingPremiseException;
	public boolean removeParkingPremise(int parkingPremiseId) throws NoSuchParkingPremiseException;
	
	// Parking Floor
	public boolean addParkingFloor(ParkingFloor parkingFloor) throws DuplicateParkingFloorException;
	public ParkingFloor getParkingFloorById(int parkingFloorId) throws NoSuchParkingFloorException;
	public List<ParkingFloor> getParkingFloorByNumber(int parkingPremiseId, String floorNumber) throws NoSuchParkingFloorException;
	public List<ParkingFloor> getAllParkingFloors(int parkingPremiseId);
	public ParkingFloor modifyParkingCapacity(ParkingFloor parkingFloor) throws NoSuchParkingFloorException;
	public boolean removeParkingFloor(int parkingFloorId) throws NoSuchParkingFloorException;

}
