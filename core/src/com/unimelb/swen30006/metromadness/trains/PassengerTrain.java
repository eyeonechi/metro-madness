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
 * PassengerTrain.java
 */
public abstract class PassengerTrain extends Train {
	
	private static final String TYPE = "Passenger";
	private int passengerCapacity;

	public PassengerTrain(Line trainLine, Station start, boolean forward, String name, int passengerCapacity) {
		super(trainLine, start, forward, name);
		this.type = TYPE;
		this.passengerCapacity = passengerCapacity;
	}
	
	@Override
	public void embark(Passenger p) throws Exception {
		// Passenger is not carrying any cargo
		if (p.getCargo().getWeight() == 0) {
			if(this.passengers.size() > this.passengerCapacity){
				throw new Exception();
			}
			this.passengers.add(p);
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

}
