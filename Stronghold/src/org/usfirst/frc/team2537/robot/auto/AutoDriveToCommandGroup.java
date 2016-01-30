package org.usfirst.frc.team2537.robot.auto;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoDriveToCommandGroup extends CommandGroup{
	//TODO: make setCurrentX() and setCurrentY() somewhere
	private double currentX = 0;
	private double currentY = 0;
	private double currentAngle = 0;
	
	public AutoDriveToCommandGroup(double x, double y){
		double angle = Math.atan2(y, x) - currentAngle;
		while(angle > 180) angle -= 180;
		while(angle < -180) angle += 180;
		addSequential(new AutoRotateCommand(angle));
		addSequential(new AutoDriveStraightCommand(Math.sqrt(Math.pow(x - currentX, 2) + Math.pow(y - currentY, 2))));
	}
	
}
