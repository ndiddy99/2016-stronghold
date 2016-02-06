package org.usfirst.frc.team2537.robot.shooter.flywheel;

import org.usfirst.frc.team2537.robot.Robot;
/**
 * Spin the motors down to zero.
 * This is to address an issue in FlywheelCommand where
 * FlywheelCommand(0);
 * 
 * May result in the talons still engaged at low speed.
 * This makes sure they are off.
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
    	Robot.shooterFlywheelSys.setLeftFlywheelVelocity(0);
    	Robot.shooterFlywheelSys.setRightFlywheelVelocity(0);
    }
    
    @Override
    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.shooterFlywheelSys.setLeftFlywheelVelocity(0);
    	Robot.shooterFlywheelSys.setRightFlywheelVelocity(0);
    }
}
