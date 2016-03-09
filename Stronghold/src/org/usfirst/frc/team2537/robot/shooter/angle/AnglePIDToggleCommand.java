package org.usfirst.frc.team2537.robot.shooter.angle;

import org.usfirst.frc.team2537.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class AnglePIDToggleCommand extends Command {
	boolean finished = false;

	@Override
	protected void initialize() {

		AngleSubsystemPID.setPID_MODE(!AngleSubsystemPID.isPID_MODE());
		if (AngleSubsystemPID.isPID_MODE()) {
			Robot.shooterAngleSys.enable();
		} else {
			Robot.shooterAngleSys.disable();
		}

	}

	@Override
	protected void execute() {

	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	protected void end() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void interrupted() {
		// TODO Auto-generated method stub

	}

}
