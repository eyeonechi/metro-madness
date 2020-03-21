package com.unimelb.swen30006.metromadness.trains;

import java.util.ArrayList;
import java.util.Iterator;

import com.unimelb.swen30006.metromadness.passengers.Passenger;
import com.unimelb.swen30006.metromadness.stations.Station;
import com.unimelb.swen30006.metromadness.tracks.Line;

/**
 * SWEN30006 Software Modelling and Design
 * 2017 Semester 1
 * Project Part B - Metro Madness
 * Project Group 97
 * Ruifeng Luo        686141
 * Wuang Shen         716090
 * Ivan Ken Weng Chee 736901
 * CargoTrain.java
 */
public abstract class CargoTrain extends Train {
	
	private static final String TYPE = "Cargo";
	
	private int passengerCapacity;
	private int cargoCapacity;
	
	protected ArrayList<Passenger.Cargo> cargo;

	public CargoTrain(Line trainLine, Station start, boolean forward, String name, int passengerCapacity, int cargoCapacity) {
		super(trainLine, start, forward, name);
		this.type = TYPE;
		this.cargo = new ArrayList<Passenger.Cargo>();
		this.passengerCapacity = passengerCapacity;
		this.cargoCapacity = cargoCapacity;
	}
	
	@Override
	public void embark(Passenger p) throws Exception {
		if (p.getCargo().getWeight() == 0) {
			if(this.passengers.size() + 1 < passengerCapacity){
				this.passengers.add(p);
			} else {
				throw new Exception();
			}
		} else {
			if(this.passengers.size() + 1 < passengerCapacity
					&& getCargoWeight() + p.getCargo().getWeight() < cargoCapacity){
				this.passengers.add(p);
				this.cargo.add(p.getCargo());
			} else {
				throw new Exception();
			}
		}
	}
	
	@Override
	public ArrayList<Passenger> disembark(){
		ArrayList<Passenger> disembarking = new ArrayList<Passenger>();
		Iterator<Passenger> iterator = this.passengers.iterator();
		while(iterator.hasNext()){
			Passenger p = iterator.next();
			if(this.station.shouldLeave(p)){
				logger.info("Passenger "+p.getId()+" is disembarking at "+this.station.name);
				disembarking.add(p);
				iterator.remove();
			}
		}
		return disembarking;
	}
	
	public int getCargoWeight() {
		int weight = 0;
		for (Passenger.Cargo c: this.cargo) {
			weight += c.getWeight();
		}
		return weight;
	}

}
