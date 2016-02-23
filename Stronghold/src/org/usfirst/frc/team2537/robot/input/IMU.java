package org.usfirst.frc.team2537.robot.input;

import org.usfirst.frc.team2537.robot.Robot;

import edu.wpi.first.wpilibj.Counter;

public class IMU implements SensorInterface {
	// Const
	private int maxPeriod;
	private int maxAngle;
	private int minAngle;
	private Sensor sensor;

	// Vars
	private final Counter input;

	public IMU(int inputPort, int minAngle, int maxAngle, int maxPeriod, Sensor sensor) {
		this.maxAngle = maxAngle;
		this.minAngle = minAngle;
		this.maxPeriod = maxPeriod;
		this.sensor = sensor;
		input = new Counter(inputPort);
		input.setSemiPeriodMode(true);
	}

	public void getValue() {
		if (getAngle(input.getPeriod(), 16, maxPeriod, minAngle, maxAngle) == Double.POSITIVE_INFINITY || getAngle(input.getPeriod(), 16, maxPeriod, minAngle, maxAngle) == Double.NEGATIVE_INFINITY) {
			Robot.sensorSys.addValue(sensor, null);
		} else {
			Robot.sensorSys.addValue(sensor, getAngle(input.getPeriod(), 16, maxPeriod, minAngle, maxAngle));
		}
	}

	private double getAngle(double x, int in_min, int in_max, int out_min, int out_max) {
		return (x * 1000000 - in_min) * (out_max - out_min) / (in_max - in_min) + out_min;
	}
}