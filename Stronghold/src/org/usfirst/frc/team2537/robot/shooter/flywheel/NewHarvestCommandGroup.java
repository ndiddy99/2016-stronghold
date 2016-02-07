package org.usfirst.frc.team2537.robot.shooter.flywheel;

import edu.wpi.first.wpilibj.command.CommandGroup;


import org.usfirst.frc.team2537.robot.Robot;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.Scheduler;

public class NewHarvestCommandGroup extends CommandGroup {
	private final static double DEFAULT_HARVEST_SPEED = -.5*1023.0;
	public NewHarvestCommandGroup(){
		this(DEFAULT_HARVEST_SPEED);
	}
	public NewHarvestCommandGroup(double speed){
		addSequential(new NewFlywheelCommand(speed));
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
		Scheduler.getInstance().add(new NewFlywheelCommand(0.0));
	}
	@Override 
	protected void interrupted() {
		Robot.shooterFlywheelSys.setLeftSpeed(0.0);
		Robot.shooterFlywheelSys.setRightSpeed(0.0);
	}
}
