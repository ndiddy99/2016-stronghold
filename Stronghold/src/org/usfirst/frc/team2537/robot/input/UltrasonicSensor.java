package org.usfirst.frc.team2537.robot.input;

import org.usfirst.frc.team2537.robot.Robot;

import edu.wpi.first.wpilibj.Ultrasonic;

public class UltrasonicSensor implements SensorInterface {

	private Ultrasonic ultrasonic;
	private Sensor sensor;

	UltrasonicSensor(int echoPort, int inputPort, Sensor sensor) {
		ultrasonic = new Ultrasonic(echoPort, inputPort);
		ultrasonic.setAutomaticMode(true);
		this.sensor = sensor;
	}

	public void getValue() {
		Robot.sensorSys.addValue(sensor, ultrasonic.getRangeInches());
	}

}
