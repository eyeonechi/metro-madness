package com.unimelb.swen30006.metromadness.states;

import com.unimelb.swen30006.metromadness.stations.NormalStation;
import com.unimelb.swen30006.metromadness.trains.Train;

/**
 * SWEN30006 Software Modelling and Design
 * 2017 Semester 1
 * Project Part B - Metro Madness
 * Project Group 97
 * Ruifeng Luo        686141
 * Wuang Shen         716090
 * Ivan Ken Weng Chee 736901
 * InStation.java
 */
public class InStation extends TrainState {
	
	private final static String CARGO_TRAIN = "Cargo";

	public InStation(Train train) {
		super(train);
	}
	
	@Override
	public void handleState(float delta) {
		boolean hasChanged = false;
		if(train.getPreviousState() == null || !train.getPreviousState().equals(train.getState())){
			train.setPreviousState(train.getState());
			hasChanged = true;
		}
		
		// Train is a cargo train and Station is not a cargo station.
		if (train.getType().equals(CARGO_TRAIN) && train.getStation() instanceof NormalStation) {
			// Not a cargo station, depart and find the next track and wait until we can enter 
			try {
				boolean endOfLine = train.getTrainLine().endOfLine(train.getStation());
				if(endOfLine){
					train.setForward(!train.isForward());
				}
				train.setTrack(train.getTrainLine().nextTrack(train.getStation(), train.isForward()));
				train.setState(new ReadyDepart(train));
			} catch (Exception e){
				// Massive error.
				e.printStackTrace();
				return;
			}
		} else {
			// When in train.getStation() we want to disembark passengers
			// and wait 10 seconds for incoming passengers
			if(!train.isDisembarked()){
				train.disembark();
				train.setDepartureTimer(train.getStation().getDepartureTime());
				train.setDisembarked(true);
			} else {
				// Count down if departure timer. 
				if(train.getDepartureTimer()>0){
					train.setDepartureTimer(train.getDepartureTimer() - delta);
				} else {
					// We are ready to depart, find the next track and wait until we can enter 
					try {
						boolean endOfLine = train.getTrainLine().endOfLine(train.getStation());
						if(endOfLine){
							train.setForward(!train.isForward());
						}
						train.setTrack(train.getTrainLine().nextTrack(train.getStation(), train.isForward()));
						train.setState(new ReadyDepart(train));
					} catch (Exception e){
						// Massive error.
						e.printStackTrace();
						return;
					}
				}
			}
		}
		
		if(hasChanged){
			logger.info(train.getName()+" is in "+train.getStation().name+" Station.");
		}
	}

}
