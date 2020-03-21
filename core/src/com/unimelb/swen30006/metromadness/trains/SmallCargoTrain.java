package com.unimelb.swen30006.metromadness.trains;

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
 * SmallCargoTrain.java
 */
public class SmallCargoTrain extends CargoTrain {
	
	private static final int PASSENGER_CAPACITY = 10;
	private static final int CARGO_CAPACITY = 200;

	public SmallCargoTrain(Line trainLine, Station start, boolean forward, String name) {
		super(trainLine, start, forward, name, PASSENGER_CAPACITY, CARGO_CAPACITY);
	}

}
