package org.usfirst.frc.team2537.robot.arm;

import java.util.HashMap;

import org.usfirst.frc.team2537.robot.input.HumanInput;
import org.usfirst.frc.team2537.robot.input.Ports;
import org.usfirst.frc.team2537.robot.input.Sensor;
import org.usfirst.frc.team2537.robot.input.SensorListener;
import org.usfirst.frc.team2537.robot.input.XBoxButtons;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.command.Subsystem;

public class ArmSubsystem extends Subsystem implements SensorListener {

	private CANTalon armMotor;
	static final boolean debug = true;
	double currentAngle;
	double currentDist;

	public ArmSubsystem() {
		armMotor = new CANTalon(Ports.ARM_TALON);
	}

	public void initDefaultCommand() {
		ArmManualMovementCommand manual = new ArmManualMovementCommand();
		this.setDefaultCommand(manual);
	}

	public void registerButtons() {
		HumanInput.registerPressedCommand(HumanInput.portcullisButton, new MagicPortcullisCommand());
		HumanInput.registerPressedCommand(HumanInput.chevalButton, new MagicChevalCommand());
//		HumanInput.registerPressedCommand(HumanInput.raiseArm, new PresetArmCommand(ArmPositions.upPos));
	}

	public double getAngle() {
		return currentAngle;
	}
	
	public double getUltrasonicDistance() {
		return currentDist;
	}

	public void setArmTalonSpeed(double s) {
		armMotor.set(s);
	}

	public double getRightJoystick() {
		return HumanInput.getXboxAxis(HumanInput.xboxController, XBoxButtons.XBOX_RIGHT_Y_AXIS);
	}

	public void receivedValue(HashMap<String, Double> e) {
		currentAngle = e.get(Sensor.ARM_ANGLE);
		currentDist = e.get(Sensor.ULTRASONIC_DISTANCE);
	}
}
