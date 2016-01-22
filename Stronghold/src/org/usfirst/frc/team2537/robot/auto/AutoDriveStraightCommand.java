package org.usfirst.frc.team2537.robot.auto;

import org.usfirst.frc.team2537.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class AutoDriveStraightCommand extends Command{
	private double speed;
	private double distance;
	
	public AutoDriveStraightCommand(double distance, double speed){
		requires(Robot.driveSys);
		this.distance = distance;
		this.speed = speed;
	}
	
	@Override
	protected void initialize() {
		Robot.driveSys.set(-speed, Robot.driveSys.talonFrontLeft);
		Robot.driveSys.set(-speed, Robot.driveSys.talonBackLeft);
		Robot.driveSys.set(speed, Robot.driveSys.talonFrontRight);
		Robot.driveSys.set(speed, Robot.driveSys.talonBackRight);		
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
		Robot.driveSys.set(0, Robot.driveSys.talonFrontLeft);
		Robot.driveSys.set(0, Robot.driveSys.talonBackLeft);
		Robot.driveSys.set(0, Robot.driveSys.talonFrontRight);
		Robot.driveSys.set(0, Robot.driveSys.talonBackRight);
	}

	@Override
	protected void interrupted() {
		System.out.println("[AutoDriveStraight] bad end");
		Robot.driveSys.set(0, Robot.driveSys.talonFrontLeft);
		Robot.driveSys.set(0, Robot.driveSys.talonBackLeft);
		Robot.driveSys.set(0, Robot.driveSys.talonFrontRight);
		Robot.driveSys.set(0, Robot.driveSys.talonBackRight);
	}

}
