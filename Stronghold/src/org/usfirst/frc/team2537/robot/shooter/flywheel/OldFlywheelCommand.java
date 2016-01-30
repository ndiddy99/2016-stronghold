package org.usfirst.frc.team2537.robot.shooter.flywheel;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team2537.robot.Robot;
import java.lang.Math;

public class OldFlywheelCommand extends Command {
	//The varibles.
	protected final double speed;
	protected double leftSpeed = 0;
	protected double rightSpeed = 0;
	protected static final double CHANGE_SPEED = 5;//MAX_SPEED change.
	protected static final double ACCURACY = 5;
	
    public OldFlywheelCommand(double speed) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	super();
    	this.requires(Robot.shooterFlywheelSys);
    	this.speed = speed;
    }

    @Override
    // Called just before this Command runs the first time
    protected void initialize() {
    	//Starting the flywheels.
    	System.out.println("Spinning wheels up to " + speed);
    }
    
    @Override
    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	//Before anything else happens. Check temperature and make sure we are not on fire.
    	if (Robot.shooterFlywheelSys.isTemperatureFault()) {
    		//THIS IS BAD.
    		//A MOTOR IS ON FIRE!!
    		System.out.println("A FLYWHEEL MOTOR IS ON FIRE!!!");
    		System.err.println("A FLYWHEEL MOTOR IS ON FIRE!!!");
    	}
    	//Left Flywheel
    	//Should Speed up.
    	double diffLeftSpeed = Robot.shooterFlywheelSys.getLeftFlywheelSpeed() - this.speed;
    	if (ACCURACY < Math.abs(diffLeftSpeed)){
    		//Speed is too slow OR to fast.
    		this.leftSpeed += diffLeftSpeed / CHANGE_SPEED;
    		Robot.shooterFlywheelSys.setLeftFlywheelSpeed(leftSpeed);
    	}
    	//Right Flywheel
    	double diffRightSpeed = Robot.shooterFlywheelSys.getRightFlywheelSpeed() - this.speed;
    	if (ACCURACY < Math.abs(diffRightSpeed)){
    		//Speed is too slow OR to fast.
    		this.rightSpeed += diffRightSpeed / CHANGE_SPEED;
    	}
    }
    
    @Override
    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return ACCURACY >= Math.abs(Robot.shooterFlywheelSys.getLeftFlywheelSpeed() - this.speed)
        		//Left in range.
        		&& 
        		//Right in range.
        		ACCURACY >= Math.abs(Robot.shooterFlywheelSys.getRightFlywheelSpeed() - this.speed);
    }
    
    @Override
    // Called once after isFinished returns true
    protected void end() {
    	//Let the wheels keep spinning!!!
    	//Set the wheels to the exact speed
    }
    
    @Override
    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	//What should I do here!!!
    	System.out.println("Flywheels interupted for " + this.getClass().toString() + ". Going to emergancy shutdown.");
    	Robot.shooterFlywheelSys.setLeftFlywheelSpeed(0);
    	Robot.shooterFlywheelSys.setRightFlywheelSpeed(0);
    }
}
