package org.usfirst.frc.team2537.robot.input;

import org.usfirst.frc.team2537.robot.Robot;

import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class IMU implements SensorInterface {
	//Const
	private static final double ANGLE_OFFSET = 90;
	private int maxPeriod;
	private int maxAngle;
	private int minAngle;
	private Sensor sensor;
	
	//Vars
	private final Counter input;
	
	public IMU(int inputPort, int minAngle, int maxAngle, int maxPeriod, Sensor sensor){
		this.maxAngle = maxAngle;
		this.minAngle = minAngle;
		this.maxPeriod = maxPeriod;
		this.sensor = sensor;
		input = new Counter(inputPort);
		input.setSemiPeriodMode(true);
	}

	public void getValue() {
		double value = getAngle(input.getPeriod(), 16, maxPeriod, minAngle, maxAngle);
		Robot.sensorSys.addValue(sensor, value);
		SmartDashboard.putNumber("IMU sensor: ", value);
		System.out.println("IMU sensor: " + value);
	}
	
	private double getAngle(double x, int in_min, int in_max, int out_min, int out_max) {
	  return (x * 1000000 - in_min) * (out_max - out_min) / (in_max - in_min) + out_min - ANGLE_OFFSET;
	}
}