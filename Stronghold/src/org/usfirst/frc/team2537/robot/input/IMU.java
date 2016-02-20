package org.usfirst.frc.team2537.robot.input;

import org.usfirst.frc.team2537.robot.Robot;

import edu.wpi.first.wpilibj.Counter;

public class IMU implements SensorInterface {
	//Const
	private double maxPeriod;
	private double maxAngle;
	private Sensor sensor;
	
	//Vars
	private final Counter input;
	
	public IMU(int inputPort, int maxAngle, int maxPeriod, Sensor sensor){
		this.maxAngle = maxAngle;
		this.maxPeriod = maxPeriod;
		this.sensor = sensor;
		input = new Counter(inputPort);
		input.setSemiPeriodMode(true);
	}
	
	public double getAngle() {
		return (input.getPeriod() / maxPeriod * maxAngle);
	}

	public void getValue() {
		Robot.sensorSys.addValue(sensor, getAngle());
	}
}