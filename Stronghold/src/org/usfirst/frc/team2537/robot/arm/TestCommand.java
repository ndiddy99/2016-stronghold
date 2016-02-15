package org.usfirst.frc.team2537.robot.arm;

import org.usfirst.frc.team2537.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class TestCommand extends Command {
	
	public TestCommand() {
		requires(Robot.armSys);
	}
	
	double currentAngle;

 	protected void initialize() {
 		currentAngle = Robot.armSys.getAngle();
 		Robot.armSys.positionMode();
 		Robot.armSys.enable();
 		Robot.armSys.setArmTalon(1F);
//		Robot.armSys.setArmTalon(currentAngle + 0.5);
 	}

 	protected void execute() {
 		
 	}

 	protected boolean isFinished() {
 		if (Robot.armSys.getAngle() <= 255 && Robot.armSys.getAngle() >= 245) {
 			return true;
 		} else {
 			return false;
 		}
	}

 	protected void end() {
 		
	}

 	protected void interrupted() {
 		
	}

}
