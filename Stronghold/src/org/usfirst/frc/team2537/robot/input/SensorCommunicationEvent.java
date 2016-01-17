package org.usfirst.frc.team2537.robot.input;

import java.util.Map;
import java.util.HashMap;

public class SensorCommunicationEvent {
	private Map<String, Double> sensorvals = new HashMap<String, Double>();
	
	
	void addPair(String tag, double val) {
		sensorvals.put(tag, val);
		System.out.println("[Sensors] Added tag " + tag +" and val " + val);
	}
	
	double getPair(String tag) {
		return sensorvals.get(tag) != null ? sensorvals.get(tag) : Double.POSITIVE_INFINITY;
	}
}
