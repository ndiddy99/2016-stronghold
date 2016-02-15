package org.usfirst.frc.team2537.robot.input;

/**
 * List of ports that we will use on the robot. Change as necessary
 * 
 * @author Alex Taber
 *
 */
public final class Ports {
	public static final int 
	//Joystick
	JOYSTICK_LEFT_PORT = 0, 
	JOYSTICK_RIGHT_PORT = 1,
	
	//XBOX Controller
	XBOX = 2,
	
	//Motor Talons
	FRONT_RIGHT_MOTOR_PORT = 1, 
	FRONT_LEFT_MOTOR_PORT = 2,
	BACK_RIGHT_MOTOR_PORT = 3, 
	BACK_LEFT_MOTOR_PORT = 4,
	ARM_TALON = 5, //Change when confirmed
	SHOOTER_ANGLE_PORT		= 2,
	LEFT_FLYWHEEL_PORT		= 3,
	RIGHT_FLYWHEEL_PORT		= 4,
	
	//PWM
	ACTUATOR_PORT = 6,
	//Sensors
	DRIVE_ULTRASONIC_INPUT	= 1,
	DRIVE_ULTRASONIC_ECHO	= 2,
	TILT_SENSOR_PORT		= 0,
	LIDAR_SENSOR_INPUT_PORT	= 6,
	LIDAR_SENSOR_TRIGGER_PORT=7,
	SHOOTER_PROXIMITY_PORT = 5;
	
	
}
