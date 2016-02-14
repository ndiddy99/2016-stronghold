package org.usfirst.frc.team2537.robot;

import java.util.ArrayList;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class TestCommand extends Command {
	public static final long WAIT_TIME = 50; //wait time between print statements.
	public static long rollingTime = 0; //does not initially have to be set
    public TestCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    static ArrayList<Double> values = new ArrayList<Double>();
    protected void execute() {
    	if(System.currentTimeMillis() - rollingTime >= WAIT_TIME) {
    	//System.out.println("-----------------------------------");
    	//System.out.println("Tilt Sensor Period: " +Robot.shooterAngleSys.getTiltSensorPeriod());
    	//System.out.println("Tilt Sensor Angle: " +Robot.shooterAngleSys.getTiltSensorAngle());
    	//System.out.println(Robot.shooterAngleSys.getTiltSensorPeriod());
    	rollingTime = System.currentTimeMillis();
    	}
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
