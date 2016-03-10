package org.usfirst.frc.team2537.robot;

/**
 * List of ports that we will use on the robot. Change as necessary
 * 
 * @author Alex Taber
 *
 */
public final class Ports {
	public static final int
	// Joystick
	JOYSTICK_LEFT_PORT = 0, JOYSTICK_RIGHT_PORT = 1,

			// XBOX Controller
			XBOX = 2,

		// Motor Talons
		FRONT_LEFT_MOTOR_PORT = 1, FRONT_RIGHT_MOTOR_PORT = 2,
		BACK_LEFT_MOTOR_PORT = 3, BACK_RIGHT_MOTOR_PORT = 4,
		
		LEFT_FLYWHEEL_PORT = 5, RIGHT_FLYWHEEL_PORT = 6,
		
		SHOOTER_ANGLE_PORT = 7,			

		ARM_TALON = 8,
		
		//PWM Serv
		SHOOTER_SERVO = 1,

		// Sensors
		TILT_SENSOR_PORT = 0,
		ARM_IMU = 1,
		SHOOTER_PROXIMITY_PORT = 2,
		DRIVE_ULTRASONIC_INPUT = 3,
		DRIVE_ULTRASONIC_ECHO = 4,
		SHOOTER_SIDE_LIDAR_SENSOR_INPUT_PORT = 5,
		SHOOTER_SIDE_LIDAR_SENSOR_TRIGGER_PORT = 6,
		RASP_PI_HORIZONTAL = 8,
		RASP_PI_VERTICAL = 9;
	
	public static final String
		SHOOTING_CAMERA = "cam0",
		UPWARD_BREACHING_CAMERA = "cam1",
		DOWNWARD_BREACHING_CAMERA = "cam2";
}
