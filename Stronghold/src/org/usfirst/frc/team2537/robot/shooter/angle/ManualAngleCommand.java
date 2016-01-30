package org.usfirst.frc.team2537.robot.shooter.angle;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team2537.robot.Robot;

/**
 *
 */
public class ManualAngleCommand extends Command {
	//How sensative should the speed be?
	/**
	 * Adjust the sensitivity of the joystickAngle.
	 * Values less than 1 decrease the maximum speed.
	 * Values more than 1 increase the maximum speed.
	 */
	private static final double speedSensitivity = .5;
	

    public ManualAngleCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.shooterAngleSys);
    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
    	//Get joystick values.
    	double speed = Robot.shooterAngleSys.getJoystickAngle();
    	Robot.shooterAngleSys.setAngleSpeed(speed * speedSensitivity);
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
        return false;//A robots work is never done.
    }

    // Called once after isFinished returns true
    @Override
    protected void end() {
    	//Well we better make sure the motors are not moving.
    	Robot.shooterAngleSys.setAngleSpeed(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    @Override
    protected void interrupted() {
    	//I was interrupted, ok.
    	System.out.println("ManualAngleCommand was interrupted!");
    	Robot.shooterAngleSys.setAngleSpeed(0);
    }
}