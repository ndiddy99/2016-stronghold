package org.usfirst.frc.team2537.robot.shooter.flywheel;

import org.usfirst.frc.team2537.robot.Robot;

/**
 *
 */
public class ShooterSpinDownCommand extends SyncFlywheelCommand {
    public ShooterSpinDownCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	super(0);
    }
    
    @Override
    // Called once after isFinished returns true
    protected void end() {
    	//Ending Double check that the speed is 0.
    	Robot.shooterFlywheelSubsystem.setLeftFlywheelSpeed(0);
    	Robot.shooterFlywheelSubsystem.setRightFlywheelSpeed(0);
    }
}
