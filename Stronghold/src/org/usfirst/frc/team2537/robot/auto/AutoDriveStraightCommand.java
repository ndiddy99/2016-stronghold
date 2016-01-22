package org.usfirst.frc.team2537.robot.auto;

import org.usfirst.frc.team2537.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class AutoDriveStraightCommand extends Command{
	private double speed;
	private double distance;
	private static final boolean debug = false;
	
	/**
	 * Drives [distance] at [speed]
	 * @param distance
	 * @param speed
	 */
	public AutoDriveStraightCommand(double distance, double speed){
		requires(Robot.driveSys);
		this.distance = distance;
		this.speed = speed;
	}
	
	@Override
	protected void initialize() {
		if(debug) System.out.println("[AutoDriveStraightCommand] Driving " + distance + " at " + speed);
		Robot.driveSys.setDriveMotors(speed);
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
		if(debug) System.out.println("[AutoDriveStraightCommand] good end");
		Robot.driveSys.setDriveMotors(0);
	}

	@Override
	protected void interrupted() {
		if (debug) System.out.println("[AutoDriveStraightCommand] bad end");
		Robot.driveSys.setDriveMotors(0);
	}

}
