package com.unimelb.swen30006.metromadness.stations;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Random;

import com.unimelb.swen30006.metromadness.passengers.Passenger;
import com.unimelb.swen30006.metromadness.passengers.PassengerGenerator;
import com.unimelb.swen30006.metromadness.routers.PassengerRouter;
import com.unimelb.swen30006.metromadness.states.PassiveStation;
import com.unimelb.swen30006.metromadness.states.StationState;
import com.unimelb.swen30006.metromadness.tracks.Line;
import com.unimelb.swen30006.metromadness.trains.Train;

public abstract class Station {
	
	public static final int PLATFORMS=2;
	public static final float RADIUS=6;
	public static final int NUM_CIRCLE_STATMENTS=100;
	public static final int MAX_LINES=3;
	public Point2D.Float position;
	public String name;
	public ArrayList<Line> lines;
	public ArrayList<Train> trains;
	public ArrayList<Passenger> waiting;
	public static final float DEPARTURE_TIME = 2;
	public PassengerRouter router;
	public StationState state;
	
	public PassengerGenerator g;
	public float maxVolume;

	public Station(float x, float y, PassengerRouter router, String name){
		this.name = name;
		this.router = router;
		this.position = new Point2D.Float(x,y);
		this.lines = new ArrayList<Line>();
		this.trains = new ArrayList<Train>();
		this.state = new PassiveStation(this);
	}
	
	public void registerLine(Line l){
		this.lines.add(l);
	}
	
	public void enter(Train train) throws Exception {
		state.handleState(train);
	}
	
	public void depart(Train train) throws Exception {
		if(this.trains.contains(train)){
			this.trains.remove(train);
		} else {
			throw new Exception();
		}
	}
	
	public boolean canEnter(Line l) throws Exception {
		return trains.size() < PLATFORMS;
	}

	// Returns departure time in seconds
	public float getDepartureTime() {
		return DEPARTURE_TIME;
	}

	public boolean shouldLeave(Passenger p) {
		return this.router.shouldLeave(this, p);
	}

	@Override
	public String toString() {
		return "Station [position=" + position + ", name=" + name + ", trains=" + trains.size()
				+ ", router=" + router + "]";
	}

	public Passenger generatePassenger(int id, Random random, Station s) {
		return new Passenger(id, random, this, s);
	}

	public Point2D.Float getPosition() {
		return position;
	}

	public void setPosition(Point2D.Float position) {
		this.position = position;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<Train> getTrains() {
		return trains;
	}

	public void setTrains(ArrayList<Train> trains) {
		this.trains = trains;
	}

	public PassengerRouter getRouter() {
		return router;
	}

	public void setRouter(PassengerRouter router) {
		this.router = router;
	}

	public StationState getState() {
		return state;
	}

	public void setState(StationState state) {
		this.state = state;
	}

	public static int getPlatforms() {
		return PLATFORMS;
	}
	
	public ArrayList<Passenger> getWaiting() {
		return waiting;
	}

	public void setWaiting(ArrayList<Passenger> waiting) {
		this.waiting = waiting;
	}

	public ArrayList<Line> getLines() {
		return lines;
	}

	public void setLines(ArrayList<Line> lines) {
		this.lines = lines;
	}

	public PassengerGenerator getG() {
		return g;
	}

	public void setG(PassengerGenerator g) {
		this.g = g;
	}

	public float getMaxVolume() {
		return maxVolume;
	}

	public void setMaxVolume(float maxVolume) {
		this.maxVolume = maxVolume;
	}
	
}
