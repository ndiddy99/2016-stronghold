package org.usfirst.frc.team2537.robot.arm;

import org.usfirst.frc.team2537.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * I was having some problems so when you call this command it interrupts both
 * drive sys and arm sys
 * 
 * Get memed drive team
 * 
 * @author Alex Taber
 *
 */
public class InterruptCommand extends Command {

	public InterruptCommand() {
		requires(Robot.driveSys);
		requires(Robot.armSys);
	}

	protected void end() {

	}

	protected void execute() {

	}

	protected void initialize() {
		System.out.println("Interrupting");
	}

	protected void interrupted() {

	}

	protected boolean isFinished() {
		return true;
	}

}
