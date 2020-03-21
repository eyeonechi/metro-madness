package com.unimelb.swen30006.metromadness.states;

import java.awt.geom.Point2D;

import com.unimelb.swen30006.metromadness.trains.Train;

/**
 * SWEN30006 Software Modelling and Design
 * 2017 Semester 1
 * Project Part B - Metro Madness
 * Project Group 97
 * Ruifeng Luo        686141
 * Wuang Shen         716090
 * Ivan Ken Weng Chee 736901
 * WaitingEntry.java
 */
public class WaitingEntry extends TrainState {

	public WaitingEntry(Train train) {
		super(train);
	}
	
	@Override
	public void handleState(float delta) {
		boolean hasChanged = false;
		if(train.getPreviousState() == null || !train.getPreviousState().equals(train.getState())){
			train.setPreviousState(train.getState());
			hasChanged = true;
		}
		if(hasChanged){
			logger.info(train.getName()+ " is awaiting entry "+train.getStation().name+" Station..!");
		}
		
		// Waiting to enter, we need to check the train.getStation() has room and if so
		// then we need to enter, otherwise we just wait
		try {
			if(train.getStation().canEnter(train.getTrainLine())){
				train.getTrack().leave(train);
				train.setPos((Point2D.Float) train.getStation().position.clone());
				train.getStation().enter(train);
				train.setState(new InStation(train));
				train.setDisembarked(false);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
