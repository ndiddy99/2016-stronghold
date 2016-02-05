//package org.usfirst.frc.team2537.robot.shooter.actuator;
//
//import edu.wpi.first.wpilibj.command.Command;
//import org.usfirst.frc.team2537.robot.Robot;
//import java.time.Instant;
//
///**
// *
// */
//public class SolenoidCommand extends Command {
//	//Constant
//	private static final boolean WAIT_TO_COMPLETE = true;
//	private static final double WAIT_TIME = 3.1;//Seconds TODO find real time.
//	//Varibles
//	private boolean extended;
//	private boolean waitToComplete;
//	private Instant endTime;
//	
//	public SolenoidCommand(boolean extended){
//		this(extended, WAIT_TO_COMPLETE);
//	}
//	
//    public SolenoidCommand(boolean extended, boolean waitToComplete) {
//        // Use requires() here to declare subsystem dependencies
//        // eg. requires(chassis);
//    	requires(Robot.shooterActuatorSys);
//    	this.extended = extended;
//    	this.waitToComplete = waitToComplete;
//    }
//    
//    @Override
//    // Called just before this Command runs the first time
//    protected void initialize() {
//    	//Fire the solenoid
//    	Robot.shooterActuatorSys.setSolenoid(extended);
//    	if (waitToComplete){
//    		endTime = Instant.now().plusMillis((long) (WAIT_TIME * 1000));
//    	} else {
//    		//Don't wait.
//    		endTime = Instant.MIN;
//    	}
//    }
//    
//    @Override
//    // Called repeatedly when this Command is scheduled to run
//    protected void execute() {
//    	//What for the time.
//    }
//    
//    @Override
//    // Make this return true when this Command no longer needs to run execute()
//    protected boolean isFinished() {
//        return Instant.now().isAfter(endTime);
//    }
//    
//    @Override
//    // Called once after isFinished returns true
//    protected void end() {
//    }
//    
//    @Override
//    // Called when another command which requires one or more of the same
//    // subsystems is scheduled to run
//    protected void interrupted() {
//    }
//}
