package com.unimelb.swen30006.metromadness.tracks;

import java.awt.geom.Point2D;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.unimelb.swen30006.metromadness.trains.Train;

/**
 * SWEN30006 Software Modelling and Design
 * 2017 Semester 1
 * Project Part B - Metro Madness
 * Project Group 97
 * Ruifeng Luo        686141
 * Wuang Shen         716090
 * Ivan Ken Weng Chee 736901
 * Track.java
 */
public class Track {
	protected static final float DRAW_RADIUS=10f;
	protected static final int LINE_WIDTH=6;
	protected Point2D.Float startPos;
	protected Point2D.Float endPos;
	protected Color trackColour;
	protected boolean occupied;
	
	public Track(Point2D.Float start, Point2D.Float end, Color trackCol){
		this.startPos = start;
		this.endPos = end;
		this.trackColour = trackCol;
		this.occupied = false;
	}
	
	public void render(ShapeRenderer renderer){
		
	}
	
	public boolean canEnter(boolean forward){
		return !this.occupied;
	}
	
	public void enter(Train train){
		this.occupied = true;
	}
	
	@Override
	public String toString() {
		return "Track [startPos=" + startPos + ", endPos=" + endPos + ", trackColour=" + trackColour + ", occupied="
				+ occupied + "]";
	}

	public void leave(Train train){
		this.occupied = false;
	}

	public Point2D.Float getStartPos() {
		return startPos;
	}

	public void setStartPos(Point2D.Float startPos) {
		this.startPos = startPos;
	}

	public Point2D.Float getEndPos() {
		return endPos;
	}

	public void setEndPos(Point2D.Float endPos) {
		this.endPos = endPos;
	}

	public Color getTrackColour() {
		return trackColour;
	}

	public void setTrackColour(Color trackColour) {
		this.trackColour = trackColour;
	}

	public boolean isOccupied() {
		return occupied;
	}

	public void setOccupied(boolean occupied) {
		this.occupied = occupied;
	}

	public static int getLineWidth() {
		return LINE_WIDTH;
	}
	
}
