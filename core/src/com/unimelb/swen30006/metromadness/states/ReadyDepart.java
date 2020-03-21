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
 * ReadyDepart.java
 */
public class ReadyDepart extends TrainState {

	public ReadyDepart(Train train) {
		super(train);
	}
	
	@Override
	public void handleState(float delta) {
		boolean hasChanged = false;
		if(train.getPreviousState() == null || !train.getPreviousState().equals(train.getState())){
			train.setPreviousState(train.getState());
			hasChanged = true;
		}
		
		// When ready to depart, check that the track is clear and if
		// so, then occupy it if possible.
		if(train.getTrack().canEnter(train.isForward())){
			try {
				// Find the next
				Station next = train.getTrainLine().nextStation(train.getStation(), train.isForward());
				// Depart our current train.getStation()
				train.getStation().depart(train);
				train.setStation(next);

			} catch (Exception e) {
				e.printStackTrace();
			}
			train.getTrack().enter(train);
			train.setState(new OnRoute(train));
		}
		
		if(hasChanged){
			logger.info(train.getName()+ " is ready to depart for "+train.getStation().name+" Station!");
		}
	}

}
