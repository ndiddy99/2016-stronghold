package org.usfirst.frc.team2537.robot.shooter.angle;

import java.util.HashMap;

import org.usfirst.frc.team2537.robot.Ports;
import org.usfirst.frc.team2537.robot.input.HumanInput;
import org.usfirst.frc.team2537.robot.input.Sensor;
import org.usfirst.frc.team2537.robot.input.SensorListener;
import org.usfirst.frc.team2537.robot.input.XboxButtons;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.command.PIDSubsystem;

public class AlexAnglePIDTest extends PIDSubsystem implements SensorListener {
	
	private final CANTalon talon = new CANTalon(2);
	private Double currentAngle;
	private double setpoint = 0;
	
	public AlexAnglePIDTest() {
		super(1.0,0,0);
		
//		setOutputRange(0,.30);
		setSetpoint(setpoint);
		getPIDController().setContinuous();
		
		enable();
	}
//	private static final double p = 1.0;
//	private static final double i = 0.0;
//	private static final double d = 0.0;
//	private Double currentAngle;
//	public static Double setPoint;
//	
//	private CANTalon talon = new CANTalon(Ports.SHOOTER_ANGLE_PORT);
//
//	public AlexAnglePIDTest() {
//		super(p, i, d);
//		setAbsoluteTolerance(.05);
//		setPoint = 0.0;
//		setSetpoint(setPoint);
//		enable();
//	}
//	
	double getJoystickAngle() {
		return HumanInput.getXboxAxis(HumanInput.xboxController, XboxButtons.XBOX_LEFT_Y_AXIS);
	}

	public void initDefaultCommand() {
		ManualAngleCommand mac = new ManualAngleCommand();
		this.setDefaultCommand(mac);
	}

	protected double returnPIDInput() {
		return currentAngle / 90;
	}

	protected void usePIDOutput(double output) {
		talon.set(output);
		System.out.println("Output val " + output);
	}
	
	void setSetPoint(double change) {
		setpoint += change;
		System.out.println("Set point: " + 	getSetpoint());
		setSetpoint(setpoint);
	}
//	
//	void changeSetPoint(double change) {
//		setPoint += change;
//	}
//
	public void receivedValue(HashMap<Sensor, Double> sensorMap) {
		Double value = sensorMap.get(Sensor.SHOOTER_ANGLE);
		if (value == null) {
			currentAngle = 0.0;
		} else {
			value = (value-90);
			if (value > -90 && value < 0) {
				currentAngle = -(value)/2;//Set the value.
			} else if (value < -90) {
				currentAngle = -(value + 180) / 2;
			} else {
				currentAngle = null;
			}
		}
	}	
}
