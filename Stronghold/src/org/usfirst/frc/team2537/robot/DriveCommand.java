package org.usfirst.frc.team2537.robot;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;

public class DriveCommand extends Command {

	public void DriveCommand() {

	}

	@Override
	protected void initialize() {
	}

	@Override
	protected void execute() {
		Robot.drivetrain.move();
	}

	@Override
	protected boolean isFinished() {

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
