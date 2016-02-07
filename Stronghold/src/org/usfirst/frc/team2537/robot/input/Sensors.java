package org.usfirst.frc.team2537.robot.input;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.SerialPort.Port;
import edu.wpi.first.wpilibj.Ultrasonic;

/**
 * Lol you don't need to know about this friendo
 * 
 * @author Alex Taber
 *
 */
public class Sensors {
	private List<SensorListener> listeners = new ArrayList<SensorListener>();
	private HashMap<String, Double> sensorVals = new HashMap<String, Double>();
	private SerialPort serial = new SerialPort(57600, Port.kMXP);
	boolean done;
	Ultrasonic ultrasonic = new Ultrasonic(Ports.DRIVE_ULTRASONIC_ECHO, Ports.DRIVE_ULTRASONIC_INPUT);

	public void registerListener(SensorListener listener) {
		listeners.add(listener);
	}

	public void init() {
		serial.flush();
		ultrasonic.setAutomaticMode(true);
	}

	/**
	 * Handle sensor values from Arduino
	 */
	public void handleEvents() {
		sensorVals.put(Sensor.ULTRASONIC_DISTANCE, (double) ultrasonic.getRangeInches());
		sensorVals.put(Sensor.ARM_ANGLE, null);

		for (SensorListener b : listeners) {
			b.receivedValue(sensorVals);
		}
	}
	
}
