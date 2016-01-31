package org.usfirst.frc.team2537.robot.shooter.flywheel;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team2537.robot.Robot;
import java.time.Instant;

/**
 *
 */
public class SolenoidCommand extends Command {
	//Constant
	private static final boolean WAIT_TO_COMPLETE = true;
	private static final long WAIT_TIME = 3100;//Milliseconds TODO find real time.
	//Varibles
	private boolean extended;
	private Instant start_time;
	private Instant end_time;
	
	public SolenoidCommand(boolean extended){
		this(extended, WAIT_TO_COMPLETE);
	}
    public SolenoidCommand(boolean extended, boolean waitToComplete) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.shooterFlywheelSys);
    	this.extended = extended;
    	start_time = Instant.now();
    	end_time = start_time.plusMillis(WAIT_TIME);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	//Fire the solenoid
    	Robot.shooterFlywheelSys.setSolenoid(extended);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	//What for the time.
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
