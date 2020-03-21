package com.unimelb.swen30006.metromadness.passengers;

import java.util.Random;

import com.unimelb.swen30006.metromadness.stations.CargoStation;
import com.unimelb.swen30006.metromadness.stations.Station;

/**
 * SWEN30006 Software Modelling and Design
 * 2017 Semester 1
 * Project Part B - Metro Madness
 * Project Group 97
 * Ruifeng Luo        686141
 * Wuang Shen         716090
 * Ivan Ken Weng Chee 736901
 * Passenger.java
 */
public class Passenger {

	private final int id;
	private Station beginning;
	private Station destination;
	private float travelTime;
	private boolean reachedDestination;
	private Cargo cargo;
	
	public Passenger(int id, Random random, Station start, Station end){
		this.id = id;
		this.beginning = start;
		this.destination = end;
		this.reachedDestination = false;
		this.travelTime = 0;
		this.cargo = generateCargo(random);
	}
	
	public void update(float time){
		if(!this.reachedDestination){
			this.travelTime += time;
		}
	}
	public Cargo getCargo(){
		return cargo;
	}
	public Cargo generateCargo(Random random){
		if (this.beginning instanceof CargoStation) {
			return new Cargo(random.nextInt(51));
		}
		return new Cargo(0);
	}
	
	public class Cargo{
		private int weight;
		
		public Cargo(int weight){
			this.setWeight(weight);
		}

		public int getWeight() {
			return weight;
		}

		public void setWeight(int weight) {
			this.weight = weight;
		}
	}

	public Station getBeginning() {
		return beginning;
	}

	public void setBeginning(Station beginning) {
		this.beginning = beginning;
	}

	public Station getDestination() {
		return destination;
	}

	public void setDestination(Station destination) {
		this.destination = destination;
	}

	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}

	public float getTravelTime() {
		return travelTime;
	}

	public void setTravelTime(float travelTime) {
		this.travelTime = travelTime;
	}

	public int getId() {
		return id;
	}

	
	
}
