package com.unimelb.swen30006.metromadness.passengers;

import java.util.ArrayList;
import java.util.Random;

import com.unimelb.swen30006.metromadness.stations.CargoStation;
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
 * PassengerGenerator.java
 */
public class PassengerGenerator {
	
	// Random number generator
	private final static Random RANDOM = new Random(30006);
	
	// Passenger id generator
	private static int idGen = 1;
	
	// The station that passengers are getting on
	public Station s;
	// The line they are travelling on
	public ArrayList<Line> lines;
	
	// The max volume
	public float maxVolume;
	
	public PassengerGenerator(Station s, ArrayList<Line> lines, float max){
		this.s = s;
		this.lines = lines;
		this.maxVolume = max;
	}
	
	public Passenger[] generatePassengers(){
		int count = RANDOM.nextInt(4);
		Passenger[] passengers = new Passenger[count];
		for(int i=0; i<count; i++){
			passengers[i] = generatePassenger(RANDOM);
		}
		return passengers;
	}
	
	public Passenger generatePassenger(Random RANDOM){
		// Pick a random station from the line
		Line l = this.lines.get(RANDOM.nextInt(this.lines.size()));
		ArrayList<Station> cargoStations = new ArrayList<Station>();
		for (Station s: l.getStations()) {
			if (s instanceof CargoStation) {
				cargoStations.add(s);
			}
		}
		int current_station = l.getStations().indexOf(this.s);
		boolean forward = RANDOM.nextBoolean();
		
		// If we are the end of the line then set our direction forward or backward
		if(current_station == 0){
			forward = true;
		} else if (current_station == l.getStations().size()-1){
			forward = false;
		}
		
		// Find the station
		int index = 0;
		
		Station s;
		// Cargo station
		if (l.getStations().get(current_station) instanceof CargoStation) {
			int current_cargo_station = cargoStations.indexOf(this.s);
			if (current_cargo_station == 0) {
				forward = true;
			} else if (current_cargo_station == cargoStations.size() - 1) {
				forward = false;
			}
			if (cargoStations.size() > 1) {
				if (forward) {
					index = RANDOM.nextInt(cargoStations.size() - 1 - current_cargo_station) + current_cargo_station + 1;
				} else {
					index = current_cargo_station - 1 - RANDOM.nextInt(current_cargo_station);
				}
				s = cargoStations.get(index);
			} else {
				s = l.getStations().get(current_station);
			}
		}
		// Normal station
		else {
			if (forward){
				index = RANDOM.nextInt(l.getStations().size()-1-current_station) + current_station + 1;
			} else {
				index = current_station - 1 - RANDOM.nextInt(current_station);
			}
			s = l.getStations().get(index);
		}
		return new Passenger(idGen++, RANDOM, l.getStations().get(current_station), s);
	}
	
}
