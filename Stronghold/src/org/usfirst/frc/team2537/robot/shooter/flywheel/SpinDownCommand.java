package org.usfirst.frc.team2537.robot.shooter.flywheel;

import org.usfirst.frc.team2537.robot.Robot;
/**
 *
 */
public class SpinDownCommand extends FlywheelCommand {
	
    public SpinDownCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	super(0);
    }
    
    @Override
    // Called once after isFinished returns true
    protected void end() {
    	//Ending Double check that the speed is 0.
    	Robot.shooterFlywheelSys.setLeftFlywheelVelocity(0);
    	Robot.shooterFlywheelSys.setRightFlywheelVelocity(0);
    }
    
    @Override
    protected void interrupted() {
    	//If interrupted
    	Robot.shooterFlywheelSys.setLeftFlywheelVelocity(0);
    	Robot.shooterFlywheelSys.setRightFlywheelVelocity(0);
    	
    }
}
