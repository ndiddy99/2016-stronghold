package org.usfirst.frc.team2537.robot.input;

import java.util.HashMap;

public interface SensorListener {
	void receivedValue(HashMap<String, Double> e);
}
