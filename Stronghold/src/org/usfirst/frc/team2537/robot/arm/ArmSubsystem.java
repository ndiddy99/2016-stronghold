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
	 * Calling this will calibrate the arm (theoretically, assuming we're on
	 * flat ground).
	 * 
	 * Works
	 * 
	 */

	public void registerButtons() {
		HumanInput.registerPressedCommand(HumanInput.lowBarModeEnableButton, new GoDown());
		HumanInput.registerReleasedCommand(HumanInput.lowBarModeEnableButton, new PresetArmCommand(oldPos));
		HumanInput.registerPressedCommand(HumanInput.driveAroundButton, new PresetArmCommand(0));
	}

	public void positionMode() {
		armMotor.changeControlMode(TalonControlMode.Position);
		armMotor.setPID(P, I, D);
	}

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
		return HumanInput.getXboxAxis(HumanInput.xboxController, XboxButtons.XBOX_RIGHT_Y_AXIS);
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

	public void getEncoder() {
		System.out.println(armMotor.getEncPosition());
	}

	public void enable() {
		armMotor.enableControl();
	}

	public double getIMUAngle() {
		return armIMUAngle;
	}
}
