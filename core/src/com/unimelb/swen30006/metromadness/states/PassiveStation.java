package com.unimelb.swen30006.metromadness.states;

import com.unimelb.swen30006.metromadness.stations.Station;
import com.unimelb.swen30006.metromadness.trains.Train;

/**
 * SWEN30006 Software Modelling and Design
 * 2017 Semester 1
 * Project Part B - Metro Madness
 * Project Group 97
 * Ruifeng Luo        686141
 * Wuang Shen         716090
 * Ivan Ken Weng Chee 736901
 * PassiveStation.java
 */
public class PassiveStation extends StationState {

	public PassiveStation(Station station) {
		super(station);
	}

	@Override
	public void handleState(Train t) throws Exception {
		if(station.getTrains().size() >= Station.PLATFORMS){
			throw new Exception();
		} else {
			station.getTrains().add(t);
		}
	}

}
