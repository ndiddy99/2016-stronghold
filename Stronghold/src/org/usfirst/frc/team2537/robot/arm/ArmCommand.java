package org.usfirst.frc.team2537.robot.arm;

import org.usfirst.frc.team2537.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;

public class ArmCommand extends Command {
	
	public ArmCommand() {
		this.requires(Robot.armSys);
	}

	protected void initialize() {
		System.out.println("Moving Arm!");
	}

	protected void execute() {
		Robot.armSys.set(-Robot.armSys.getRightJoystick());
	}

	protected boolean isFinished() {
		return false;
	}

	protected void end() {
		Robot.armSys.set(0);
	}

	protected void interrupted() {
		Robot.armSys.set(0);
	}

}
