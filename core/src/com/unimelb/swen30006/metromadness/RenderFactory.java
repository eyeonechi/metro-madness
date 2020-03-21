package com.unimelb.swen30006.metromadness;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.unimelb.swen30006.metromadness.stations.CargoStation;
import com.unimelb.swen30006.metromadness.stations.Station;
import com.unimelb.swen30006.metromadness.tracks.Line;
import com.unimelb.swen30006.metromadness.tracks.Track;
import com.unimelb.swen30006.metromadness.trains.LargeCargoTrain;
import com.unimelb.swen30006.metromadness.trains.LargePassengerTrain;
import com.unimelb.swen30006.metromadness.trains.SmallCargoTrain;
import com.unimelb.swen30006.metromadness.trains.SmallPassengerTrain;
import com.unimelb.swen30006.metromadness.trains.Train;

/**
 * SWEN30006 Software Modelling and Design
 * 2017 Semester 1
 * Project Part B - Metro Madness
 * Project Group 97
 * Ruifeng Luo        686141
 * Wuang Shen         716090
 * Ivan Ken Weng Chee 736901
 * RenderFactory.java
 */
public class RenderFactory {
	
	public RenderFactory() {
	}

	public void renderTrains(Train t, ShapeRenderer renderer) {
		if(!t.inStation()){
			if (t instanceof LargePassengerTrain || t instanceof LargeCargoTrain) {
				Color col = t.isForward() ? Train.FORWARD_COLOUR : Train.BACKWARD_COLOUR;
				float percentage = t.getPassengers().size() / 20f;
				renderer.setColor(col.cpy().lerp(Color.LIGHT_GRAY, percentage));
				renderer.circle(t.getPos().x, t.getPos().y, Train.TRAIN_WIDTH * (1 + percentage));
			} else if (t instanceof SmallPassengerTrain || t instanceof SmallCargoTrain) {
				Color col = t.isForward() ? Train.FORWARD_COLOUR : Train.BACKWARD_COLOUR;
				float percentage = t.getPassengers().size() / 10f;
				renderer.setColor(col.cpy().lerp(Color.MAROON, percentage));
				renderer.circle(t.getPos().x, t.getPos().y, Train.TRAIN_WIDTH * (1 + percentage));
			} else {
				Color col = t.isForward() ? Train.FORWARD_COLOUR : Train.BACKWARD_COLOUR;
				renderer.setColor(col);
				renderer.circle(t.getPos().x, t.getPos().y, Train.TRAIN_WIDTH);
			}
		}
	}
	
	public void renderLines(Line l, ShapeRenderer renderer) {
		// Set the color to our line
		renderer.setColor(l.getTrackColour());
	
		// Draw all the track sections
		for(Track t: l.getTracks()){
			renderTracks(t, renderer);
		}
	}

	private void renderTracks(final Track t, final ShapeRenderer renderer) {
		renderer.rectLine(t.getStartPos().x, t.getStartPos().y, t.getEndPos().x, t.getEndPos().y, Track.getLineWidth());
	}
	
	public void renderStations(Station s, ShapeRenderer renderer) {
		float radius = Station.RADIUS;
		for (int i = 0; (i < s.getLines().size() && i < Station.MAX_LINES); i ++) {
			Line l = s.getLines().get(i);
			renderer.setColor(l.getLineColour());
			renderer.circle(s.getPosition().x, s.getPosition().y, radius, Station.NUM_CIRCLE_STATMENTS);
			radius = radius - 1;
		}
		// Calculate the percentage
		float t = s.getTrains().size() / (float) Station.PLATFORMS;
		Color c = Color.WHITE.cpy().lerp(Color.DARK_GRAY, t);
		if (s instanceof CargoStation) {
			if (s.getWaiting().size() > 0) {
				c = Color.RED;
			}
		}
		renderer.setColor(c);
		renderer.circle(s.getPosition().x, s.getPosition().y, radius, Station.NUM_CIRCLE_STATMENTS);
	}
}
