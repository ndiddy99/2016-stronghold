package org.usfirst.frc.team2537.robot.input;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
//import edu.wpi.first.wpilibj.SerialPort;
//import edu.wpi.first.wpilibj.SerialPort.Port;
import edu.wpi.first.wpilibj.Ultrasonic;

/**
 * 
 * @author Alex Taber
 *
 */
public class Sensors {
	private final List<SensorListener> listeners;
	private final HashMap<SensorEnum, Double> sensorVals;
	//private SerialPort serial = new SerialPort(57600, Port.kMXP);
	boolean done;
	private final Ultrasonic ultrasonic;
	private final TiltSensor shooterTilt;
	public Sensors(){
		//Create the sensors.
		listeners	= new ArrayList<SensorListener>();
		sensorVals	= new HashMap<SensorEnum, Double>();
		ultrasonic	= new Ultrasonic(Ports.LIDAR_SENSOR_ECHO_PORT, 
				Ports.LIDAR_SENSOR_INPUT_PORT);
		shooterTilt	= new TiltSensor(Ports.TILT_SENSOR_PORT);
	}

	public void registerListener(SensorListener listener) {
		listeners.add(listener);
	}

	public void init() {
		//serial.flush();
		ultrasonic.setAutomaticMode(true );
	}

	/**
	 * Handle sensor values.
	 */
	public void handleEvents() {
		sensorVals.put(SensorEnum.LIDAR_DISTANCE, getUltrasonicVal(ultrasonic));
		sensorVals.put(SensorEnum.ARM_ANGLE, null);
		sensorVals.put(SensorEnum.SHOOTER_ANGLE, shooterTilt.getValue());

		for (SensorListener b : listeners) {
			b.receivedValue(sensorVals);
		}
	}
	
	protected double getUltrasonicVal(Ultrasonic u) {
		return (double) u.getRangeInches();
	}
	
	protected Double getTalonAngle(int count, int resolution, double lowAngle, double highAngle) {
		
		return null;
	}
	
	
	
	protected double computeAngle(int count, int numOfTicks, double lowAngle, double highAngle) {
		return (count/numOfTicks) * 360 - lowAngle;
	}
}
