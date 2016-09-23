//package org.usfirst.frc.team2537.robot.climber;
//
//import org.usfirst.frc.team2537.robot.Robot;
//
//import edu.wpi.first.wpilibj.command.Command;
//
///**
// *
// */
//public class RetractButtonCommand extends Command {
//
//    public RetractButtonCommand() {
//        // Use requires() here to declare subsystem dependencies
//        // eg. requires(chassis);
//    }
//
//    // Called just before this Command runs the first time
//    protected void initialize() {
//    	Robot.climberSys.PercentageMode();
//    }
//
//    // Called repeatedly when this Command is scheduled to run
//    protected void execute() {
//    	if (Robot.climberSys.isRetractButtonHeld()) {
//    		Robot.climberSys.moveB();
//    	}
//    }
//
//    // Make this return true when this Command no longer needs to run execute()
//    protected boolean isFinished() {
//    	if (!Robot.climberSys.isRetractButtonHeld()) {
//    		return true;
//    	}
//        return false;
//    }
//
//    // Called once after isFinished returns true
//    protected void end() {
//    	Robot.climberSys.positionStop();
//    }
//
//    // Called when another command which requires one or more of the same
//    // subsystems is scheduled to run
//    protected void interrupted() {
//    }
//}
