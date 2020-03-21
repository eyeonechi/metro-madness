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
 * FromDepot.java
 */
public class FromDepot extends TrainState {

	public FromDepot(Train train) {
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
			logger.info(train.getName()+ " is travelling from the depot: "+train.getStation().name+" Station...");
		}
		
		// We have our train.getStation() initialized we just need to retrieve the next track, enter the
		// current train.getStation() officially and mark as in train.getStation()
		try {
			if(train.getStation().canEnter(train.getTrainLine())) {
				train.getStation().enter(train);
				train.setPos((Point2D.Float) train.getStation().position.clone());
				train.setDisembarked(false);
				train.setState(new InStation(train));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
