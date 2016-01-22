package org.usfirst.frc.team2537.robot.auto;

import org.usfirst.frc.team2537.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class AutoRotateCommand extends Command{
	private double speed;
	
	public AutoRotateCommand(double speed){
		requires(Robot.driveSys);
		this.speed = speed;
	}
	
	@Override
	protected void initialize() {
		Robot.driveSys.setDriveMotors(-speed, speed);
	}

	@Override
	protected void execute() {
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

	@Override
	protected void end() {
		Robot.driveSys.setDriveMotors(0);
	}

	@Override
	protected void interrupted() {
		System.out.println("[AutoRotateCommand] bad end");
		Robot.driveSys.setDriveMotors(0);
	}

}
