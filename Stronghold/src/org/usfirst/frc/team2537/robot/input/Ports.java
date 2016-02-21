package org.usfirst.frc.team2537.robot.input;

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
			FRONT_RIGHT_MOTOR_PORT = 2,

			FRONT_LEFT_MOTOR_PORT = 1, BACK_RIGHT_MOTOR_PORT = 4, BACK_LEFT_MOTOR_PORT = 3,
			SHOOTER_ANGLE_PORT = 4, LEFT_FLYWHEEL_PORT = 2, RIGHT_FLYWHEEL_PORT = 1,

			// Other Talon
			ARM_TALON = 8, // Change when confirmed

			// Sensors
			ARM_IMU = 1,
			// PWM
			ACTUATOR_PORT = 1,
			// Sensors
			DRIVE_ULTRASONIC_INPUT = 3, DRIVE_ULTRASONIC_ECHO = 4, TILT_SENSOR_PORT = 0,
			SHOOTER_SIDE_LIDAR_SENSOR_INPUT_PORT = 5, SHOOTER_SIDE_LIDAR_SENSOR_TRIGGER_PORT = 6,
			SHOOTER_PROXIMITY_PORT = 2;

}
