package com.unimelb.swen30006.metromadness.stations;

import java.util.ArrayList;

import com.unimelb.swen30006.metromadness.passengers.Passenger;
import com.unimelb.swen30006.metromadness.passengers.PassengerGenerator;
import com.unimelb.swen30006.metromadness.routers.PassengerRouter;
import com.unimelb.swen30006.metromadness.states.ActiveStation;
import com.unimelb.swen30006.metromadness.trains.Train;

/**
 * SWEN30006 Software Modelling and Design
 * 2017 Semester 1
 * Project Part B - Metro Madness
 * Project Group 97
 * Ruifeng Luo        686141
 * Wuang Shen         716090
 * Ivan Ken Weng Chee 736901
 * NormalStation.java
 */
public class NormalStation extends Station {
		
	public PassengerGenerator g;
	public float maxVolume;
	
	public NormalStation(float x, float y, PassengerRouter router, String name, float maxPax) {
		super(x, y, router, name);
		this.waiting = new ArrayList<Passenger>();
		this.g = new PassengerGenerator(this, this.lines, maxPax);
		this.maxVolume = maxPax;
	}
	
	@Override
	public void enter(Train t) throws Exception {
		this.state = new ActiveStation(this);
		state.handleState(t);
	}
	
	@Override
	public ArrayList<Passenger> getWaiting() {
		return waiting;
	}

	@Override
	public void setWaiting(ArrayList<Passenger> waiting) {
		this.waiting = waiting;
	}

	@Override
	public PassengerGenerator getG() {
		return g;
	}

	@Override
	public void setG(PassengerGenerator g) {
		this.g = g;
	}

	@Override
	public float getMaxVolume() {
		return maxVolume;
	}

	@Override
	public void setMaxVolume(float maxVolume) {
		this.maxVolume = maxVolume;
	}

}
