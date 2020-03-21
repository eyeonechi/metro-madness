package com.unimelb.swen30006.metromadness.trains;

import java.awt.geom.Point2D;
import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.unimelb.swen30006.metromadness.passengers.Passenger;
import com.unimelb.swen30006.metromadness.states.FromDepot;
import com.unimelb.swen30006.metromadness.states.InStation;
import com.unimelb.swen30006.metromadness.states.ReadyDepart;
import com.unimelb.swen30006.metromadness.states.TrainState;
import com.unimelb.swen30006.metromadness.stations.Station;
import com.unimelb.swen30006.metromadness.tracks.Line;
import com.unimelb.swen30006.metromadness.tracks.Track;

/**
 * SWEN30006 Software Modelling and Design
 * 2017 Semester 1
 * Project Part B - Metro Madness
 * Project Group 97
 * Ruifeng Luo        686141
 * Wuang Shen         716090
 * Ivan Ken Weng Chee 736901
 * Train.java
 */
public abstract class Train {
	
	// Logger
	protected static Logger logger = LogManager.getLogger();

	// Constants
	protected static final int MAX_TRIPS=4;
	public static final Color FORWARD_COLOUR = Color.ORANGE;
	public static final Color BACKWARD_COLOUR = Color.VIOLET;
	public static final float TRAIN_WIDTH=4;
	protected static final float TRAIN_LENGTH = 6;
	protected static final float TRAIN_SPEED=50f;
	
	// The train's name
	protected String name;
	
	// The train's type
	protected String type;

	// The line that this is travelling on
	protected Line trainLine;

	// Passenger Information
	protected ArrayList<Passenger> passengers;
	protected float departureTimer;
	
	// Station and track and position information
	protected Station station; 
	protected Track track;
	protected Point2D.Float pos;

	// Direction and direction
	protected boolean forward;

	protected TrainState state;
	protected TrainState previousState;

	// TrainState variables
	protected int numTrips;
	protected boolean disembarked;

	public Train(Line trainLine, Station start, boolean forward, String name){
		this.trainLine = trainLine;
		this.station = start;
		this.forward = forward;
		this.passengers = new ArrayList<Passenger>();
		this.name = name;
		this.previousState = null;
		this.state = new FromDepot(this);
	}

	public void update(float delta){
		// Update all passengers
		for(Passenger p: this.passengers){
			p.update(delta);
		}
		this.state.handleState(delta);
	}

	public void move(float delta){
		// Work out where we're going
		float angle = angleAlongLine(this.pos.x,this.pos.y,this.station.position.x,this.station.position.y);
		float newX = this.pos.x + (float)( Math.cos(angle) * delta * TRAIN_SPEED);
		float newY = this.pos.y + (float)( Math.sin(angle) * delta * TRAIN_SPEED);
		this.pos.setLocation(newX, newY);
	}

	public void embark(Passenger p) throws Exception {
		throw new Exception();
	}


	public ArrayList<Passenger> disembark(){
		return null;
	}

	@Override
	public String toString() {
		return "Train [line=" + this.trainLine.getName() +", departureTimer=" + departureTimer + ", pos=" + pos + ", forward=" + forward + ", state=" + state
				+ ", numTrips=" + numTrips + ", disembarked=" + disembarked + "]";
	}

	public boolean inStation(){
		return (this.state instanceof InStation || this.state instanceof ReadyDepart);
	}
	
	public float angleAlongLine(float x1, float y1, float x2, float y2){	
		return (float) Math.atan2((y2-y1),(x2-x1));
	}

	public void render(ShapeRenderer renderer){
	}
	
	public TrainState getState() {
		return state;
	}

	public void setState(TrainState state) {
		this.state = state;
	}

	
	public Station getStation() {
		return station;
	}

	public void setStation(Station station) {
		this.station = station;
	}
	
	public boolean isDisembarked() {
		return disembarked;
	}

	public ArrayList<Passenger> getPassengers() {
		return passengers;
	}

	public void setPassengers(ArrayList<Passenger> passengers) {
		this.passengers = passengers;
	}

	public void setDisembarked(boolean disembarked) {
		this.disembarked = disembarked;
	}
	
	public boolean isForward() {
		return forward;
	}

	public void setForward(boolean forward) {
		this.forward = forward;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getDepartureTimer() {
		return departureTimer;
	}

	public void setDepartureTimer(float departureTimer) {
		this.departureTimer = departureTimer;
	}

	public Track getTrack() {
		return track;
	}

	public Line getTrainLine() {
		return trainLine;
	}

	public void setTrainLine(Line trainLine) {
		this.trainLine = trainLine;
	}

	public void setTrack(Track track) {
		this.track = track;
	}

	public Point2D.Float getPos() {
		return pos;
	}

	public void setPos(Point2D.Float pos) {
		this.pos = pos;
	}
	
	public TrainState getPreviousState() {
		return previousState;
	}

	public void setPreviousState(TrainState previousState) {
		this.previousState = previousState;
	}

	public Logger getLogger() {
		return logger;
	}

	public static void setLogger(Logger logger) {
		Train.logger = logger;
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}


}
