package com.unimelb.swen30006.metromadness.states;

import org.apache.logging.log4j.Logger;

import com.unimelb.swen30006.metromadness.trains.Train;

/**
 * SWEN30006 Software Modelling and Design
 * 2017 Semester 1
 * Project Part B - Metro Madness
 * Project Group 97
 * Ruifeng Luo        686141
 * Wuang Shen         716090
 * Ivan Ken Weng Chee 736901
 * TrainState.java
 */
public abstract class TrainState {

	protected Train train;
	protected static Logger logger;
	
	public TrainState(Train train) {
		this.train = train;
		logger = train.getLogger();
	}
	
	public abstract void handleState(float delta);

}
