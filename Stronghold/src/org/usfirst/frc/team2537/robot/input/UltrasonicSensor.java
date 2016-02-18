package org.usfirst.frc.team2537.robot.input;

import org.usfirst.frc.team2537.robot.Robot;

import edu.wpi.first.wpilibj.Ultrasonic;

public class UltrasonicSensor implements SensorInterface {
	private static final boolean DEBUG = false;

	private Ultrasonic ultrasonic;

	UltrasonicSensor(int echoPort, int inputPort) {
		ultrasonic = new Ultrasonic(echoPort, inputPort);
		ultrasonic.setAutomaticMode(true);
	}

	public void getValue() {
		double value = ultrasonic.getRangeInches();
		if (DEBUG) System.out.println("Ultrasonic sees object " + value + "inches in front.");
		Robot.sensorSys.addValue(Sensor.ULTRASONIC_DISTANCE, value);
	}

}
