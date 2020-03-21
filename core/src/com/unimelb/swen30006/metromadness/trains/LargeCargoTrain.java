package com.unimelb.swen30006.metromadness.trains;

import com.unimelb.swen30006.metromadness.stations.Station;
import com.unimelb.swen30006.metromadness.tracks.Line;

public class LargeCargoTrain extends CargoTrain {
	
	private static final int PASSENGER_CAPACITY = 80;
	private static final int CARGO_CAPACITY = 1000;

	public LargeCargoTrain(Line trainLine, Station start, boolean forward, String name) {
		super(trainLine, start, forward, name, PASSENGER_CAPACITY, CARGO_CAPACITY);
	}
	
}
