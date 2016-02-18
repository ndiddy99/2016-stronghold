package org.usfirst.frc.team2537.robot.input;

import org.usfirst.frc.team2537.robot.Robot;

import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.DigitalOutput;

public class LidarSensor implements SensorInterface {
	//Const
	private static final boolean DEBUG = false;
	//So the lidar communates at 
	//1msec/meter
	//=.001sec/meter
	//=.001sec/100cm
	//=.1sec/cm
	private static final double FACTOR = 10;//1E-3;
	
	//Vars
	private final Counter 		input;
	private final DigitalOutput	trigger;
	
	public LidarSensor(int echoPort, int inputPort){
		// tilt sensor that is a pwm over the dio port in ports
		input = new Counter(inputPort);
		// set the tilt sensor to semiperiod mode. 
		// This means we are only measuring the period of the
		// high pulses. When this is true, it counts just 
		// high pulses.
		//input.setSemiPeriodMode(true);
		// http://wpilib.screenstepslive.com/s/4485/m/13809/l/
		//241874-counters-measuring-rotation-counting-pulses-and-more
		
		//The output.
		trigger = new DigitalOutput(echoPort);
		
		//Set the power to low. This will force the lidar to keep printing values.
		trigger.set(false);
	}
	
	public void getValue() {
		//Value on cm
		trigger.set(false);//make sure this stay's low.
		double value = input.getPeriod()*FACTOR;
		
		if (DEBUG) System.out.println("Lidar sees at an object at " + value + "cm.");
		Robot.sensorSys.addValue(Sensor.SHOOTER_LIDAR, value);
	}
}