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
	public ProximitySensor prox = new ProximitySensor(Ports.SHOOTER_PROXIMITY_PORT,Sensor.SHOOTER_PROXIMITY);
	public IMU tilt = new IMU(Ports.TILT_SENSOR_PORT, 0, 180, 2023, Sensor.SHOOTER_ANGLE);
	public LidarSensor lidar = new LidarSensor(Ports.SHOOTER_SIDE_LIDAR_SENSOR_TRIGGER_PORT, Ports.SHOOTER_SIDE_LIDAR_SENSOR_INPUT_PORT, Sensor.SHOOTER_LIDAR);
	public UltrasonicSensor ultrasonic = new UltrasonicSensor(Ports.DRIVE_ULTRASONIC_ECHO, Ports.DRIVE_ULTRASONIC_INPUT, Sensor.ULTRASONIC_DISTANCE);
	
	public void registerListener(SensorListener listener) {
		listeners.add(listener);
	}

	public void init() {

		sensors.add(ultrasonic);
		sensors.add(tilt);
		sensors.add(lidar);
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
