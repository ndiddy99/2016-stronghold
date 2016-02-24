//package org.usfirst.frc.team2537.robot.input;
//
//import edu.wpi.first.wpilibj.Counter;
//import org.usfirst.frc.team2537.robot.Robot;
//
//public class TiltSensor implements SensorInterface {
//	//Const
//	private static final boolean DEBUG = true;
////	private static final double TILT_SENSOR_MAX_PERIOD = 1990 * Math.pow(10, -6);
////	private static final double MAX_ANGLE = 180;// degrees (ball park, not right)
//	private static final double ANGLE_OFFSET = 90;//90 degrees from the sensor actually means 0
//	
//	private static final double INPUT_MIN_VALUE = 0;
//	private static final double INPUT_MAX_VALUE = 1990;
//	
//	private static final double OUTPUT_MIN_VALUE = 0;
//	private static final double OUTPUT_MAX_VALUE = 180;
//	//Vars
//	private final Counter input;
//	
//	public TiltSensor(int inputPort){
//		// tilt sensor that is a pwm over the dio port in ports
//		input = new Counter(inputPort);
//		// set the tilt sensor to semiperiod mode. 
//		// This means we are only measuring the period of the
//		// high pulses. When this is true, it counts just 
//		// high pulses.
//		input.setSemiPeriodMode(true);
//		// http://wpilib.screenstepslive.com/s/4485/m/13809/l/
//		//241874-counters-measuring-rotation-counting-pulses-and-more
//	}
//	
//	@Override
//	public void getValue() {
//		// period will change with the angle. I
//		// would assume it would get longer as
//		// the angle increases. This returns the
//		// time interval of the most recent count.
//		
//		//Rough Calibration gathered from Adrian.
//		//double value = input.getPeriod() * MAX_ANGLE / TILT_SENSOR_MAX_PERIOD - ANGLE_OFFSET;
//		//A rather complicated general conversion algorthm given by Adrian
//		double value = (input.getPeriod() - INPUT_MIN_VALUE) * (OUTPUT_MAX_VALUE - OUTPUT_MIN_VALUE) 
//				/ (INPUT_MAX_VALUE - INPUT_MIN_VALUE) + OUTPUT_MIN_VALUE - ANGLE_OFFSET;
//		
//		
//		if (DEBUG) System.out.println("Tilt sensor angle: " + value);
//		
//		Robot.sensorSys.addValue(Sensor.SHOOTER_ANGLE, value);
//	}
//}
