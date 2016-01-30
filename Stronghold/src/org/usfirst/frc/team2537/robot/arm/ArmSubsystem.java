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

	public ArmSubsystem() {
		armMotor = new CANTalon(Ports.ARM_TALON);
	}

	public void initDefaultCommand() {
		ArmManualMovementCommand manual = new ArmManualMovementCommand();
		this.setDefaultCommand(manual);
	}

	public void registerButtons() {
		HumanInput.registerPressedCommand(HumanInput.lowerArm, new PresetArmCommand(ArmPositions.downPos));
		HumanInput.registerPressedCommand(HumanInput.neutralArm, new PresetArmCommand(ArmPositions.neutralPos));
		HumanInput.registerPressedCommand(HumanInput.raiseArm, new PresetArmCommand(ArmPositions.upPos));
	}

	public double getAngle() {
		return currentAngle;
	}

	public void setArmTalonSpeed(double s) {
		armMotor.set(s);
	}

	public double getRightJoystick() {
		return HumanInput.getXboxAxis(HumanInput.xboxController, XBoxButtons.XBOX_RIGHT_Y_AXIS);
	}

	public void receivedValue(HashMap<String, Double> e) {
		currentAngle = e.get(Sensor.ARM_ANGLE);
	}
}
