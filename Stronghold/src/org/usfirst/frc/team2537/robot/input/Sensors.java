package org.usfirst.frc.team2537.robot.input;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Lol you don't need to know about this friendo
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
		//sensors.add(new UltrasonicSensor(Ports.DRIVE_ULTRASONIC_ECHO, Ports.DRIVE_ULTRASONIC_INPUT));
		sensors.add(new TiltSensor(Ports.TILT_SENSOR_PORT));
		sensors.add(new LidarSensor(Ports.LIDAR_SENSOR_TRIGGER_PORT, Ports.LIDAR_SENSOR_INPUT_PORT));
		sensors.add(new ProximitySensor(Ports.SHOOTER_PROXIMITY_PORT));
	}
	
	public void addValue(Sensor sensor, double val) {
		sensorVals.put(sensor, val);
	}
	
	public void handleEvents() {
		sensorVals.clear();//Make sure we don't copy old values.
		
		for (SensorInterface s : sensors) {
			s.getValue();
		}
		
		for (SensorListener l : listeners) {
			l.receivedValue(sensorVals);
		}
	}
}
