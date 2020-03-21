package com.unimelb.swen30006.metromadness.tracks;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Color;
import com.unimelb.swen30006.metromadness.stations.Station;

/**
 * SWEN30006 Software Modelling and Design
 * 2017 Semester 1
 * Project Part B - Metro Madness
 * Project Group 97
 * Ruifeng Luo        686141
 * Wuang Shen         716090
 * Ivan Ken Weng Chee 736901
 * Line.java
 */
public class Line {
	
	// The colour of this line
	private Color lineColour;
	private Color trackColour;
	
	// The name of this line
	private String name;
	// The stations on this line
	private ArrayList<Station> stations;
	// The tracks on this line between stations
	private ArrayList<Track> tracks;
		
	// Create a line
	public Line(Color stationColour, Color lineColour, String name){
		// Set the line colour
		this.lineColour = stationColour;
		this.trackColour = lineColour;
		this.name = name;
		
		// Create the data structures
		this.stations = new ArrayList<Station>();
		this.tracks = new ArrayList<Track>();
	}
	
	
	public void addStation(Station s, Boolean two_way){
		// We need to build the track if this is adding to existing stations
		if(this.stations.size() > 0){
			// Get the last station
			Station last = this.stations.get(this.stations.size()-1);
			
			// Generate a new track
			Track t;
			if(two_way){
				t = new DualTrack(last.position, s.position, this.trackColour);
			} else {
				t = new Track(last.position, s.position, this.trackColour);
			}
			this.tracks.add(t);
		}
		
		// Add the station
		s.registerLine(this);
		this.stations.add(s);
	}
	
	@Override
	public String toString() {
		return "Line [lineColour=" + lineColour + ", trackColour=" + trackColour + ", name=" + name + "]";
	}

	public boolean endOfLine(Station s) throws Exception{
		if(this.stations.contains(s)){
			int index = this.stations.indexOf(s);
			return (index==0 || index==this.stations.size()-1);
		} else {
			throw new Exception();
		}
	}

	public Track nextTrack(Station currentStation, boolean forward) throws Exception {
		if(this.stations.contains(currentStation)){
			// Determine the track index
			int curIndex = this.stations.lastIndexOf(currentStation);
			// Increment to retrieve
			if(!forward){ curIndex -=1;}
			
			// Check index is within range
			if((curIndex < 0) || (curIndex > this.tracks.size()-1)){
				throw new Exception();
			} else {
				return this.tracks.get(curIndex);
			}
			
		} else {
			throw new Exception();
		}
	}
	
	public Station nextStation(Station s, boolean forward) throws Exception{
		if(this.stations.contains(s)){
			int curIndex = this.stations.lastIndexOf(s);
			if(forward){
				curIndex+=1;
			}else{
				curIndex -=1;
			}
			
			// Check index is within range
			if((curIndex < 0) || (curIndex > this.stations.size()-1)){
				throw new Exception();
			} else {
				return this.stations.get(curIndex);
			}
		} else {
			throw new Exception();
		}
	}

	public Color getLineColour() {
		return lineColour;
	}


	public void setLineColour(Color lineColour) {
		this.lineColour = lineColour;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public ArrayList<Station> getStations() {
		return stations;
	}


	public void setStations(ArrayList<Station> stations) {
		this.stations = stations;
	}


	public Color getTrackColour() {
		return trackColour;
	}


	public void setTrackColour(Color trackColour) {
		this.trackColour = trackColour;
	}


	public ArrayList<Track> getTracks() {
		return tracks;
	}


	public void setTracks(ArrayList<Track> tracks) {
		this.tracks = tracks;
	}
	
}
