package org.usfirst.frc.team2537.robot.shooter.flywheel;

import org.usfirst.frc.team2537.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ShooterSpinDownCommand extends Command {
	
    public ShooterSpinDownCommand() {
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
	protected void execute() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void initialize() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void interrupted() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}
}
