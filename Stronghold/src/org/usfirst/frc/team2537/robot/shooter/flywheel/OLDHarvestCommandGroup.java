package org.usfirst.frc.team2537.robot.shooter.flywheel;

import edu.wpi.first.wpilibj.command.CommandGroup;


import org.usfirst.frc.team2537.robot.Robot;
import org.usfirst.frc.team2537.robot.shooter.angle.MoveToAngleCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.Scheduler;

public class OLDHarvestCommandGroup extends CommandGroup {
	private final static double DEFAULT_HARVEST_SPEED = -.5*1023.0;
	private final static double HARVEST_ANGLE = -10.0;
	public OLDHarvestCommandGroup(){
		this(DEFAULT_HARVEST_SPEED);
	}
	
	public OLDHarvestCommandGroup(double speed){
		addSequential(new FlywheelCommand(speed));
		addParallel(new MoveToAngleCommand(HARVEST_ANGLE));
		System.out.println("Right Shoot Speed:" +Robot.shooterFlywheelSys.getRightSpeed());
		System.out.println("Left shoot Speed:" +Robot.shooterFlywheelSys.getLeftSpeed());
		
	}
	@Override
	protected boolean isFinished() {
		if(Robot.shooterFlywheelSys.isBall()) {
			return true;
		}
		return false;
	}
	@Override
	protected void end() {
		Scheduler.getInstance().add(new FlywheelCommand(0.0));
	}
	@Override 
	protected void interrupted() {
		Robot.shooterFlywheelSys.setLeftSpeed(0.0);
		Robot.shooterFlywheelSys.setRightSpeed(0.0);
	}
}
