package org.usfirst.frc.team2537.robot.input;

import org.usfirst.frc.team2537.robot.Robot;

import edu.wpi.first.wpilibj.Counter;

public class IMU implements SensorInterface {
	// Const
	private int maxPeriod;
	private int maxAngle;
	private int minAngle;
	private Sensor sensor;
	private static final int MIN_PERIOD = 8;		
	private final static boolean DEBUG = false;

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
	
	public void setSamplesToAverage(int samplesToAverage){
		input.setSamplesToAverage(samplesToAverage);
	}
	
	public int getSamplesToAverage(){
		return input.getSamplesToAverage();
	}
	
	public Double getCurrentAngle(){
		if (getAngle(input.getPeriod(), MIN_PERIOD, maxPeriod, minAngle, maxAngle) == Double.POSITIVE_INFINITY || getAngle(input.getPeriod(), MIN_PERIOD, maxPeriod, minAngle, maxAngle) == Double.NEGATIVE_INFINITY) {
			return null;
		} else {
			return getAngle(input.getPeriod(), MIN_PERIOD, maxPeriod, minAngle, maxAngle);
		}
	}
	public static long lastTimeValue = System.currentTimeMillis();
	public void getValue() {
		if (getAngle(input.getPeriod(), MIN_PERIOD, maxPeriod, minAngle, maxAngle) == Double.POSITIVE_INFINITY || getAngle(input.getPeriod(), MIN_PERIOD, maxPeriod, minAngle, maxAngle) == Double.NEGATIVE_INFINITY) {
			Robot.sensorSys.addValue(sensor, null);
		} else {
			Robot.sensorSys.addValue(sensor, getAngle(input.getPeriod(), MIN_PERIOD, maxPeriod, minAngle, maxAngle));
		}
		if(DEBUG) {
			if(System.currentTimeMillis() - lastTimeValue >= 100) {
				System.out.println("IMU Period: "+(input.getPeriod()* 1000000) +" us");
				System.out.println("IMU Angle: " +getCurrentAngle());
				lastTimeValue = System.currentTimeMillis();
			}
		
		}
	}

	private double getAngle(double x, int in_min, int in_max, int out_min, int out_max) {
//		if (DEBUG) System.out.println(x);
		return (x * 1000000 - in_min) * (out_max - out_min) / (in_max - in_min) + out_min;
	}
}