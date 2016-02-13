package org.usfirst.frc.team2537.robot.arm;

import org.usfirst.frc.team2537.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class TestCommand2 extends Command {
	
	double currentAngle;
	
	public TestCommand2() {
		requires(Robot.armSys);
	}
	
 	protected void initialize() {
 		currentAngle = Robot.armSys.getAngle();
 		Robot.armSys.positionMode();
 		Robot.armSys.enable();
 		Robot.armSys.setArmTalon(0F);
//		Robot.armSys.setArmTalon(currentAngle + 0.5);
 	}

 	protected void execute() {
 		
 	}

 	protected boolean isFinished() {
 		if (Robot.armSys.getAngle() <= 0 && Robot.armSys.getAngle() >= 0) {
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
