package org.usfirst.frc.team2537.robot.climber;

import org.usfirst.frc.team2537.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ServoTurnCommand extends Command {
	
	private double angle;
	private double curAngle;
	private int iterations;
    double acceleration = .001;

    public ServoTurnCommand(double angle) {
        requires(Robot.climberSys);
        this.angle = angle;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.climberSys.setServoPosition(ServoAngles.startAngle);
    	iterations = 0;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
		curAngle = Robot.climberSys.getServoPosition();
    	if (curAngle > angle) {
    		curAngle -= 0.5 * acceleration * Math.pow(iterations++, 2);
    		Robot.climberSys.setServoPosition(Math.max(curAngle, angle));
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return (iterations > 360) || (Robot.climberSys.getServoPosition() <= angle + 1);
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
