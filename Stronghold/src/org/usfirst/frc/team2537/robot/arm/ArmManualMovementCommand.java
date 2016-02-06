package org.usfirst.frc.team2537.robot.arm;

import org.usfirst.frc.team2537.robot.Robot;
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
		if (ArmSubsystem.debug) System.out.println("Moving Arm!");
	}

	protected void execute() {
		Robot.armSys.setArmTalonSpeed(-Robot.armSys.getRightJoystick());
	}

	protected boolean isFinished() {
		return false;
	}

	protected void end() {
		Robot.armSys.setArmTalonSpeed(0);
		if (ArmSubsystem.debug) System.out.println("Arm movement completed!");
	}

	protected void interrupted() {
		Robot.armSys.setArmTalonSpeed(0);
	}

}
