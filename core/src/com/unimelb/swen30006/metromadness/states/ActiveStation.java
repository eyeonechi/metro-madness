package com.unimelb.swen30006.metromadness.states;

import java.util.Iterator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.unimelb.swen30006.metromadness.passengers.Passenger;
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
 * ActiveStation.java
 */
public class ActiveStation extends StationState {
	
	// Logger
	private static Logger logger = LogManager.getLogger();

	public ActiveStation(Station station) {
		super(station);
	}

	@Override
	public void handleState(Train t) throws Exception {
		if(station.getTrains().size() >= Station.PLATFORMS){
			throw new Exception();
		} else {
			// Add the train
			station.getTrains().add(t);
			// Add the waiting passengers
			Iterator<Passenger> pIter = station.getWaiting().iterator();
			while(pIter.hasNext()){
				Passenger p = pIter.next();
				try {
					logger.info("Passenger "+p.getId()+" carrying "+p.getCargo().getWeight() +" kg cargo embarking at "+station.getName()+" heading for "+p.getDestination().name);
					t.embark(p);
					pIter.remove();
				} catch (Exception e){
					// Do nothing, already waiting
					break;
				}
			}
			
			//Do not add new passengers if there are too many already
			if (station.getWaiting().size() > station.getMaxVolume()){
				return;
			}
			// Add the new passenger
			Passenger[] ps = station.getG().generatePassengers();
			for(Passenger p: ps){
				try {
					logger.info("Passenger "+p.getId()+" carrying "+p.getCargo().getWeight() +" kg embarking at "+station.getName()+" heading to "+p.getDestination().name);
					t.embark(p);
				} catch(Exception e){
					station.getWaiting().add(p);
				}
			}
		}
		station.setState(new PassiveStation(station));
	}

}
