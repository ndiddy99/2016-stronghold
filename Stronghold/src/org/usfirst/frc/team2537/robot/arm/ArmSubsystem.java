package org.usfirst.frc.team2537.robot.arm;

import org.usfirst.frc.team2537.robot.input.HumanInput;
import org.usfirst.frc.team2537.robot.input.NineDegreesOfFreedom;
import org.usfirst.frc.team2537.robot.input.Ports;
import org.usfirst.frc.team2537.robot.input.SensorEnum;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.command.Subsystem;

public class ArmSubsystem extends Subsystem {

	private CANTalon armMotor;
	private NineDegreesOfFreedom armNDOF = NineDegreesOfFreedom.getInstance();

	public ArmSubsystem() {
		armMotor = new CANTalon(Ports.ARM_TALON);
	}

	public void initDefaultCommand() {
		// TODO Auto-generated method stub
		ArmCommand manual = new ArmCommand();
		this.setDefaultCommand(manual);
	}

	public void registerButtons() {
		HumanInput.registerPressedCommand(HumanInput.lowerArm, new PresetArmCommand(ArmPositions.downPos));
		HumanInput.registerPressedCommand(HumanInput.neutralArm, new PresetArmCommand(ArmPositions.neutralPos));
		HumanInput.registerPressedCommand(HumanInput.raiseArm, new PresetArmCommand(ArmPositions.upPos));
	}

	public double getAngle() {
		return armNDOF.getAngle(SensorEnum.ARM_NINE_DEGREES_OF_FREEDOM);
	}

	public void set(double s) {
		armMotor.set(s);
	}

	public double getRightJoystick() {
		return HumanInput.getXboxAxis(HumanInput.xboxController, HumanInput.XBOX_RIGHT_STICK_Y_AXIS);
	}
}
