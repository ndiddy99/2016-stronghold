package org.usfirst.frc.team2537.robot.shooter;

import org.usfirst.frc.team2537.robot.shooter.flywheel.FlywheelCommand;
import org.usfirst.frc.team2537.robot.shooter.flywheel.SpinDownCommand;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

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
public class ShootCommand extends CommandGroup {
	//The default velocity.
    public static final double SHOOT_VELOCITY = .5;
    
    //Using default velocity.
    public ShootCommand() {
    	this(SHOOT_VELOCITY);
    }
    
    //Using a specified speed.
    public ShootCommand(double shootVelocity){
    	//First make sure we are not running already.
    	addSequential(new FlywheelCommand(shootVelocity));
    	addSequential(new WaitCommand(2.0));
    	addSequential(new SpinDownCommand());
    	System.out.println("Shoot Command Group is Running");
//    	addSequential(new EjectBallCommand());
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
