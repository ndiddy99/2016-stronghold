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
	public ProximitySensor prox = new ProximitySensor(Ports.SHOOTER_PROXIMITY_PORT);
	public IMU tilt = new IMU(Ports.TILT_SENSOR_PORT, 0, 180, 2023, Sensor.SHOOTER_ANGLE);
	public LidarSensor lidar = new LidarSensor(Ports.LIDAR_SENSOR_TRIGGER_PORT, Ports.LIDAR_SENSOR_INPUT_PORT);
	public UltrasonicSensor ultrasonic = new UltrasonicSensor(Ports.DRIVE_ULTRASONIC_ECHO, Ports.DRIVE_ULTRASONIC_INPUT);
	public void registerListener(SensorListener listener) {
		listeners.add(listener);
	}

	public void init() {
		sensors.add(ultrasonic);
		sensors.add(tilt);
		sensors.add(lidar);
//		sensors.add(new ProximitySensor(Ports.SHOOTER_PROXIMITY_PORT));
		sensors.add(prox);
	}
	
	public void addValue(Sensor sensor, Double val) {
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
