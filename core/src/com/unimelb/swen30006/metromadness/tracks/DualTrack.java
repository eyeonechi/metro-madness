package com.unimelb.swen30006.metromadness.tracks;

import java.awt.geom.Point2D.Float;

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
 * DualTrack.java
 */
public class DualTrack extends Track {

	private boolean forwardOccupied;
	private boolean backwardOccupied;
	
	public DualTrack(Float start, Float end, Color col) {
		super(start, end, col);
		this.forwardOccupied = false;
		this.backwardOccupied = false;
	}
	
	public void render(ShapeRenderer renderer){
		renderer.rectLine(startPos.x, startPos.y, endPos.x, endPos.y, LINE_WIDTH);
		renderer.setColor(new Color(245f/255f,245f/255f,245f/255f,0.5f).lerp(this.trackColour, 0.5f));
		renderer.rectLine(startPos.x, startPos.y, endPos.x, endPos.y, LINE_WIDTH/3);
		renderer.setColor(this.trackColour);
	}
	
	@Override
	public void enter(Train t){
		if(t.isForward()){
			this.forwardOccupied = true;
		} else {
			this.backwardOccupied = true;
		}
	}

	@Override
	public boolean canEnter(boolean forward) {
		if(forward){
			return !this.forwardOccupied;
		} else {
			return !this.backwardOccupied;
		}
	}

	@Override
	public void leave(Train t) {
		if(t.isForward()){
			this.forwardOccupied = false;
		} else {
			this.backwardOccupied = false;
		}
	}

	public boolean isForwardOccupied() {
		return forwardOccupied;
	}

	public void setForwardOccupied(boolean forwardOccupied) {
		this.forwardOccupied = forwardOccupied;
	}

	public boolean isBackwardOccupied() {
		return backwardOccupied;
	}

	public void setBackwardOccupied(boolean backwardOccupied) {
		this.backwardOccupied = backwardOccupied;
	}
	
	
	
	
	


}
