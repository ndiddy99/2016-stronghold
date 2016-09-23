package org.usfirst.frc.team2537.robot.arm;

import java.util.HashMap;

import org.usfirst.frc.team2537.robot.Ports;
import org.usfirst.frc.team2537.robot.input.HumanInput;
import org.usfirst.frc.team2537.robot.input.Sensor;
import org.usfirst.frc.team2537.robot.input.SensorListener;
import org.usfirst.frc.team2537.robot.input.XboxButtons;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.FeedbackDevice;
import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * The main Arm Subsystem
 * 
 * @author Alex Taber
 * 
 */
public class ArmSubsystem extends Subsystem implements SensorListener {

	public CANTalon armMotor;
	static final boolean debug = false;
	double currentAngle;
	double currentDist;
	double armIMUAngle;
	final int encoderTicksPerRev = 250;
	double oldPos;

	public static final double P = 100.0, I = 0.0, D = 0.0;

	public ArmSubsystem() {
		armMotor = new CANTalon(Ports.ARM_TALON);
		armMotor.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		armMotor.configEncoderCodesPerRev(encoderTicksPerRev);
	}

	public void initDefaultCommand() {
		ArmManualMovementCommand manual = new ArmManualMovementCommand();
		this.setDefaultCommand(manual);
	}

	/**
	 * Register human input buttons
	 */
	public void registerButtons() {
		HumanInput.registerPressedCommand(HumanInput.lowBarModeEnableButton, new GoDown());
		HumanInput.registerReleasedCommand(HumanInput.lowBarModeEnableButton, new PresetArmCommand(oldPos));
		//HumanInput.registerPressedCommand(HumanInput.driveAroundButton, new PresetArmCommand(0));
	}

	/**
	 * Set the arm motor into position mode rather than voltage mode; also sets
	 * PID values
	 */
	public void positionMode() {
		armMotor.changeControlMode(TalonControlMode.Position);
		armMotor.setPID(P, I, D);
	}

	/**
	 * Set the old position for the arm for use in low bar mode
	 * 
	 * @deprecated
	 * @param oldPos
	 */
	public void setOldPos(double oldPos) {
		this.oldPos = oldPos;
	}

	/**
	 * Used to get the angle that the manipulator arm is currently in
	 * 
	 * @return double of the arm angle
	 */
	public double getAngle() {
		return armMotor.getEncPosition();
	}

	/**
	 * Used to get the distance from the ultrasonic sensor
	 * 
	 * @return double of the robot's distance
	 */
	public double getUltrasonicDistance() {
		return currentDist;
	}

	/**
	 * Used to set the speed of the arm talon
	 * 
	 * @param position
	 *            Voltage to set the talon to
	 */
	public void setArmPosition(double position) {
		armMotor.setPID(P, I, D);
		armMotor.set(position);
	}

	/**
	 * Sets the arm's position, given a PID value and a position
	 * 
	 * @param position
	 *            in encoder ticks
	 * @param newP
	 * @param newI
	 * @param newD
	 */
	public void setArmPosition(double position, double newP, double newI, double newD) {
		armMotor.setPID(newP, newI, newD);
		armMotor.set(position);
	}

	/**
	 * Used to get the value of the right Joystick Y Axis
	 * 
	 * @return double of the right joystick
	 */
	public double getRightJoystick() {
		return HumanInput.getXboxAxis(HumanInput.xboxController, XboxButtons.XBOX_RIGHT_Y_AXIS, 0.2);
	}

	/**
	 * An implemented method for SensorListener interface
	 */
	public void receivedValue(HashMap<Sensor, Double> e) {
		try {
			armIMUAngle = e.get(Sensor.ARM_ANGLE);
		} catch (NullPointerException error) {
			if (debug)
				System.out.println("Bad Angle Sensor");
		}
		try {
			currentDist = e.get(Sensor.ULTRASONIC_DISTANCE);
		} catch (NullPointerException error) {
			if (debug)
				System.out.println("Bad Ultrasonic Sensor");
		}
	}

	/**
	 * Get number of encoder ticks on arm motor
	 */
	public void getEncoder() {
		System.out.println(armMotor.getEncPosition());
	}

	/**
	 * Enable control mode
	 */
	public void enable() {
		armMotor.enableControl();
	}

	/**
	 * Get position of IMU on arm
	 * 
	 * @return angle of arm
	 */
	public double getIMUAngle() {
		return armIMUAngle;
	}
}
