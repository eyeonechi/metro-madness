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
 * StationState.java
 */
public abstract class StationState {
	
	protected Station station;

	public StationState(Station station) {
		this.station = station;
	}
	
	public abstract void handleState(Train t) throws Exception;

}
