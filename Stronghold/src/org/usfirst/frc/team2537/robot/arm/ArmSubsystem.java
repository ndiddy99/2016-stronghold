package org.usfirst.frc.team2537.robot.arm;

import java.util.HashMap;

import org.usfirst.frc.team2537.robot.input.HumanInput;
import org.usfirst.frc.team2537.robot.input.Ports;
import org.usfirst.frc.team2537.robot.input.Sensor;
import org.usfirst.frc.team2537.robot.input.SensorListener;
import org.usfirst.frc.team2537.robot.input.XBoxButtons;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.FeedbackDevice;
import edu.wpi.first.wpilibj.command.Subsystem;
/**
 * The main Arm Subsystem
 * 
 * @author Alex Taber
 * 
 */
public class ArmSubsystem extends Subsystem implements SensorListener {

	public CANTalon armMotor;
	static final boolean debug = true;
	double currentAngle;
	double currentDist;

	public ArmSubsystem() {
		armMotor = new CANTalon(Ports.ARM_TALON);
		armMotor.ConfigFwdLimitSwitchNormallyOpen(true);
		armMotor.ConfigRevLimitSwitchNormallyOpen(true);
		armMotor.enableBrakeMode(true);
		armMotor.enableForwardSoftLimit(false);
		armMotor.enableReverseSoftLimit(false);
		armMotor.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		armMotor.configEncoderCodesPerRev(80);
		armMotor.setEncPosition(0);
	}

	public void initDefaultCommand() {
		ArmManualMovementCommand manual = new ArmManualMovementCommand();
		this.setDefaultCommand(manual);
	}

	public void registerButtons() {
		HumanInput.registerPressedCommand(HumanInput.portcullisButton, new MagicPortcullisCommand());
		HumanInput.registerPressedCommand(HumanInput.chevalButton, new MagicChevalCommand());
		HumanInput.registerPressedCommand(HumanInput.raiseArm, new InterruptCommand());
	}
	
	/**
	 * Used to get the angle that the manipulator arm is currently in
	 * 
	 * @return	double of the arm angle
	 */
	public double getAngle() {
		return currentAngle;
	}
	
	/**
	 * Used to get the distance from the ultrasonic sensor
	 * 
	 * @return 	double of the robot's distance
	 */
	public double getUltrasonicDistance() {
		return currentDist;
	}

	/**
	 * Used to set the speed of the arm talon
	 * 
	 * @param outputval	Voltage to set the talon to
	 */
	public void setArmTalon(double outputval) {
		armMotor.set(outputval);
	}
	
	/**
	 * Used to get the value of the right Joystick Y Axis
	 * 
	 * @return	double of the right joystick
	 */
	public double getRightJoystick() {
		return HumanInput.getXboxAxis(HumanInput.xboxController, XBoxButtons.XBOX_RIGHT_Y_AXIS);
	}
	
	/**
	 * An implemented method for SensorListener interface
	 */
	public void receivedValue(HashMap<String, Double> e) {
		try {
			currentAngle = e.get(Sensor.ARM_ANGLE);
		} catch(Exception error) {
			if (debug) System.out.println("Bad Angle Sensor");
		}
		try {
			currentDist = e.get(Sensor.ULTRASONIC_DISTANCE);
		} catch (Exception error) {
			if (debug) System.out.println("Bad Ultrasonic Sensor");
		}
		if (debug) System.out.println(currentDist);
	}
}
