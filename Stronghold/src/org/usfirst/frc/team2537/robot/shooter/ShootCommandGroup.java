package org.usfirst.frc.team2537.robot.shooter;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.usfirst.frc.team2537.robot.shooter.actuator.ActuatorCommand;
import org.usfirst.frc.team2537.robot.shooter.flywheel.FlywheelCommand;
import org.usfirst.frc.team2537.robot.Robot;

/**
 * This is the class to launch the ball.
 * There is a default speed, but a different speed maybe used later on.
 * By pressing the right trigger, this is executed
 * 
 *@author Jacob Barnett
 *
 */
/*
 * documentation done by Matthew Schweiss
 */
public class ShootCommandGroup extends CommandGroup {
	private static final boolean DEBUG = false;
	//The default velocity.
    public static final double SHOOT_VELOCITY = 500;
    
    //Using default velocity.
    public ShootCommandGroup() {
    	this(SHOOT_VELOCITY);
    }
    
    //Using a specified speed.
    public ShootCommandGroup(double shootVelocity){
    	//First make sure we are not running already.
    	addSequential(new FlywheelCommand(shootVelocity));
    	addSequential(new ActuatorCommand(true));//extend
    	addSequential(new BallDetectionCommand(false));//Wait until ball is gone.
    	addParallel(new ActuatorCommand(false));//retract
    	addSequential(new FlywheelCommand(0.0));
    }
	
    @Override
    protected void initialize(){
    	//Yes, CommandGroup.initialize() is blank but in the future it may change
    	//and things like that. Besides, it would be could anyway and therefore can
    	//not possibly hurt.
    	if (DEBUG) System.out.println("ShootCommandGroup was started.");
    	super.initialize();
    }
    
    @Override
    protected void interrupted() {
    	if (DEBUG) System.out.println("ShootCommandGroup Interrupted");
    	new ActuatorCommand(false).start();//retract
    	//new FlywheelCommand(0).start();//stop wheels.
    	//Robot.shooterFlywheelSys.setSpeed(0.0);
    	Robot.shooterFlywheelSys.stop();
    }
    
    @Override
    protected void end() {
    }
    
//    @Override
//    /*
//     * We want to only run the code the first time the button is pushed.
//     * If the second time the button is pushed, this is running, I want to terminate the program.
//     */
//    /**
//     * Start the program.
//     * 
//     * This will start the command running except if it is already running. If it is already running, the program will be 
//     * terminated.
//     */
//    public void start(){
//    	if (isRunning()){
//    		if (DEBUG) System.out.println("ShootCommandGroup.start() called while running; Canceling.");
//    		//I am already running, no a won't go.
//    		cancel();
//    	} else {
//    		if (DEBUG) System.out.println("ShootCommandGroup.start() called.");
//    		//I'm not running, lets start!
//    		super.start();
//    	}
//    }
}
