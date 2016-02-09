package org.usfirst.frc.team2537.robot.shooter;

import org.usfirst.frc.team2537.robot.shooter.flywheel.FlywheelCommand;
import org.usfirst.frc.team2537.robot.shooter.flywheel.SpinDownCommand;
import org.usfirst.frc.team2537.robot.shooter.actuator.ActuatorCommand;
import edu.wpi.first.wpilibj.command.CommandGroup;

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
 * TODO Figure out the unit of the SHOOT_VELOCITY.
 */
public class ShootCommandGroup extends CommandGroup {
	//The default velocity.
    public static final double SHOOT_VELOCITY = .5;
    
    //Using default velocity.
    public ShootCommandGroup() {
    	this(SHOOT_VELOCITY);
    }
    
    //Using a specified speed.
    public ShootCommandGroup(double shootVelocity){
    	//First make sure we are not running already.
    	addSequential(new FlywheelCommand(shootVelocity));
    	addSequential(new ActuatorCommand(true));//extend
    	addSequential(new UntilBallCommand());
    	addParallel(new ActuatorCommand(false));//rectract
    	addSequential(new SpinDownCommand());
    	System.out.println("Shoot Command Group is Running");
    }
	
    @Override
    protected void interrupted() {
    	new SpinDownCommand().start();
    }
    
    @Override
    protected void end() {
    	new SpinDownCommand().start();
    }
    
    @Override
    /*
     * We want to only run the code the first time the button is pushed.
     * If the second time the button is pushed, this is running, I want to terminate the program.
     */
    /**
     * Start the program.
     * 
     * This will start the command running except if it is already running. If it is already running, the program will be 
     * terminated.
     */
    public void start(){
    	if (isRunning()){
    		//I am already running, no a won't go.
    		cancel();
    	} else {
    		//I'm not running, lets start!
    		super.start();
    	}
    }
    
}
