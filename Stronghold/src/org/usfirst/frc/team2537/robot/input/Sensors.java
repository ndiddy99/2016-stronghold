package org.usfirst.frc.team2537.robot.input;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Class that handles the other sensor classes and passes values to the other subsystem
 * 
 * @author Alex Taber
 *
 */
public class Sensors {
	private List<SensorListener> listeners = new ArrayList<SensorListener>();
	private List<SensorInterface> sensors = new ArrayList<SensorInterface>();
	private HashMap<Sensor, Double> sensorVals = new HashMap<Sensor, Double>();

	public void registerListener(SensorListener listener) {
		listeners.add(listener);
	}

	public void init() {
		sensors.add(new UltrasonicSensor(Ports.DRIVE_ULTRASONIC_ECHO, Ports.DRIVE_ULTRASONIC_INPUT));
		sensors.add(new IMU(Ports.ARM_IMU, 0, 0, Sensor.ARM_ANGLE));
	}

	public void addValue(Sensor sensor, double val) {
		sensorVals.put(sensor, val);
	}

	public void handleEvents() {
		for (SensorInterface s : sensors) {
			s.getValue();
		}

		for (SensorListener l : listeners) {
			l.receivedValue(sensorVals);
		}
	}
}
