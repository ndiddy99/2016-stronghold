package org.usfirst.frc.team2537.robot.input;

import org.usfirst.frc.team2537.robot.Robot;

import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.PWM;

public class Lidar implements SensorInterface {
	//Const
	private static final double TILT_SENSOR_MAX_PERIOD = 1990 * Math.pow(10, -6);
	private static final double MAX_DISTANCE = 90;//feet (ball park, not right)
	
	//Vars
	private final Counter input;
	private final PWM	  echo;
	
	public Lidar(int inputPort, int echoPort){
		// tilt sensor that is a pwm over the dio port in ports
		input = new Counter(inputPort);
		// set the tilt sensor to semiperiod mode. 
		// This means we are only measuring the period of the
		// high pulses. When this is true, it counts just 
		// high pulses.
		input.setSemiPeriodMode(true);
		// http://wpilib.screenstepslive.com/s/4485/m/13809/l/
		//241874-counters-measuring-rotation-counting-pulses-and-more
		
		//The output.
		echo = new PWM(echoPort);
		//Set the voltage to high, this should tell the LIDAR to 
		//continue to send values and we can at any time tap in and
		//read the issue.
		echo.setPosition(1);
	}
	
	public void getValue() {
		// period will change with the angle. I
		// would assume it would get longer as
		// the angle increases. This returns the
		// time interval of the most recent count.
		
		//Rough Calibration gathered from Adrian.
		Robot.sensorSys.addValue(Sensor.SHOOTER_LIDAR, input.getPeriod() / 
				TILT_SENSOR_MAX_PERIOD * MAX_DISTANCE);
	}

}
