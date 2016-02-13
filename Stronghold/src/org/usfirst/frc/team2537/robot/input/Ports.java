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
	
	//Other Talon
	ARM_TALON = 5, //Change when confirmed

	SHOOTER_ANGLE_PORT = 5,
	LEFT_FLYWHEEL_PORT = 1,
	RIGHT_FLYWHEEL_PORT = 2,
	ACTUATOR_PORT = 0,
	//Sensors.
	TILT_SENSOR_PORT	= 1,
	LIDAR_SENSOR_INPUT_PORT	= 2,
	LIDAR_SENSOR_ECHO_PORT	= 3;
	
	
}
