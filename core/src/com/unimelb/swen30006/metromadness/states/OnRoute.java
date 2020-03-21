package com.unimelb.swen30006.metromadness.states;

import com.unimelb.swen30006.metromadness.trains.Train;

/**
 * SWEN30006 Software Modelling and Design
 * 2017 Semester 1
 * Project Part B - Metro Madness
 * Project Group 97
 * Ruifeng Luo        686141
 * Wuang Shen         716090
 * Ivan Ken Weng Chee 736901
 * OnRoute.java
 */
public class OnRoute extends TrainState {

	public OnRoute(Train train) {
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
			logger.info(train.getName()+ " enroute to "+train.getStation().name+" Station!");
		}
		
		// Checkout if we have reached the new train.getStation()
		if(train.getPos().distance(train.getStation().position) < 10 ){
			train.setState(new WaitingEntry(train));
		} else {
			train.move(delta);
		}
	}

}
