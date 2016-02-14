package org.usfirst.frc.team2537.robot.input;

import java.util.HashMap;

/**
 * Implement this in order to receive sensor values
 * 
 * @author Alex Taber
 *
 */
public interface SensorListener {

	void receivedValue(HashMap<Sensor, Double> sensorVals);
}
