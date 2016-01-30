package org.usfirst.frc.team2537.robot.shooter.flywheel;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team2537.robot.Robot;
/**
 *
 */
public class SpinDownCommand extends Command {

    public SpinDownCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }
    
    @Override
    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.shooterFlywheelSys.setLeftFlywheelVelocity(0);
    	Robot.shooterFlywheelSys.setRightFlywheelVelocity(0);
    }
    
    @Override
    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }
    
    @Override
    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Robot.shooterFlywheelSys.getLeftFlywheelVelocity() == 0 
        		&&
        		Robot.shooterFlywheelSys.getRightFlywheelVelocity() == 0;
    }
    
    @Override
    // Called once after isFinished returns true
    protected void end() {
    }
    
    @Override
    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
