package org.usfirst.frc.team2537.robot.input;

import org.usfirst.frc.team2537.robot.Robot;

import edu.wpi.first.wpilibj.Counter;

public class TiltSensor implements SensorInterface {
	//Const
	private static final double TILT_SENSOR_MAX_PERIOD = 1990 * Math.pow(10, -6);
	private static final double MAX_ANGLE = 90;// degrees (ball park, not right)
	
	//Vars
	private final Counter input;
	
	public TiltSensor(int inputPort){
		// tilt sensor that is a pwm over the dio port in ports
		input = new Counter(inputPort);
		// set the tilt sensor to semiperiod mode. 
		// This means we are only measuring the period of the
		// high pulses. When this is true, it counts just 
		// high pulses.
		input.setSemiPeriodMode(true);
		// http://wpilib.screenstepslive.com/s/4485/m/13809/l/
		//241874-counters-measuring-rotation-counting-pulses-and-more
	}
	
	@Override
	public void getValue() {
		// period will change with the angle. I
		// would assume it would get longer as
		// the angle increases. This returns the
		// time interval of the most recent count.
		
		//Rough Calibration gathered from Adrian.
		Robot.sensorSys.addValue(Sensor.SHOOTER_ANGLE, (input.getPeriod() / 
				TILT_SENSOR_MAX_PERIOD * MAX_ANGLE));
	}
}
