package org.usfirst.frc.team2537.robot;

/**
 * List of port numbers that we will use on the robot. Change as necessary in
 * accordance with the IO sheet.
 * 
 * @author Alex Taber
 */
public final class Ports {
	public static final int
	
	    /////// Joysticks ///////
	    JOYSTICK_LEFT_PORT = 0, JOYSTICK_RIGHT_PORT = 1,

		/////// XBOX Controller ///////
		XBOX = 2,

		/////// CAN Talons ///////
		// Drive
		FRONT_LEFT_MOTOR_PORT = 1, FRONT_RIGHT_MOTOR_PORT = 2,
		BACK_LEFT_MOTOR_PORT = 3, BACK_RIGHT_MOTOR_PORT = 4,
		
		// Shooter
		LEFT_FLYWHEEL_PORT = 5, RIGHT_FLYWHEEL_PORT = 6,
		
		// Shooter Pivot
		SHOOTER_ANGLE_PORT = 7,			

        // Breaching Arm
		ARM_TALON = 8,
		
		// Climber motors
		CLIMBER_MOTOR_1 = 9,
		CLIMBER_MOTOR_2 = 10,
		
		/////// PWM Output ///////
		SHOOTER_SERVO = 1,
		CLIMBER_SERVO = 0,

		/////// DIO Input (Sensors) ///////
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
	
	    /////// Cameras ///////
		SHOOTING_CAMERA = "cam0",
		UPWARD_BREACHING_CAMERA = "cam1",
		DOWNWARD_BREACHING_CAMERA = "cam2";
}
