package org.usfirst.frc.team2537.robot;
import edu.wpi.first.wpilibj.command.Command;

/**
 *wew lad
 */
public class Motor extends Command {
	TalonCommand tc=new TalonCommand();
	boolean runs=false;
    public Motor() {

        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	for (double i=-1; i < 1; i=i+ 0.1) {
			tc.talonSet(i);
		}
		for (double j=1; j > -1; j=j- 0.1) {
			tc.talonSet(j);
		}
		runs=true;
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if (runs==true) {
        return true;
    	}
    	else {
    		return false;
    	}	
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	System.out.println("wew lad");
    }
}
