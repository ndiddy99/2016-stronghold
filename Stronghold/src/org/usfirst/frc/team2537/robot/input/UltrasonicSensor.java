package org.usfirst.frc.team2537.robot.input;

import org.usfirst.frc.team2537.robot.Robot;

import edu.wpi.first.wpilibj.Ultrasonic;

public class UltrasonicSensor implements SensorInterface {

	private Ultrasonic ultrasonic;

	UltrasonicSensor(int echoPort, int inputPort) {
		ultrasonic = new Ultrasonic(echoPort, inputPort);
		ultrasonic.setAutomaticMode(true);
	}

	public void getValue() {
		Robot.sensorSys.addValue(Sensor.ULTRASONIC_DISTANCE, ultrasonic.getRangeInches());
	}

}
