package org.usfirst.frc.team2537.robot.arm;

import org.usfirst.frc.team2537.robot.Robot;

import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;
import edu.wpi.first.wpilibj.command.Command;

/**
 * Default command for arm subsystem. Gets values of joystick and sets talon speed accordingly.
 * 
 * @author Alex Taber
 *
 */
public class ArmManualMovementCommand extends Command {
	
	public ArmManualMovementCommand() {
		this.requires(Robot.armSys);
	}

	protected void initialize() {
//		if (ArmSubsystem.debug) System.out.println("Moving Arm!");
		Robot.armSys.armMotor.changeControlMode(TalonControlMode.PercentVbus);
	}

	protected void execute() {
		Robot.armSys.setArmTalonSpeed(Robot.armSys.getRightJoystick() * .75);
//		if (ArmSubsystem.debug) Robot.armSys.getEncoder();
	}

	protected boolean isFinished() {
		return true;
	}

	protected void end() {
//		if (ArmSubsystem.debug) System.out.println("Arm movement completed!");
	}

	protected void interrupted() {
		Robot.armSys.setArmTalonSpeed(0);
	}

}
