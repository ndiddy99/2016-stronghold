package org.usfirst.frc.team2537.robot.shooter;

import org.usfirst.frc.team2537.robot.shooter.actuator.EjectBallCommand;
import org.usfirst.frc.team2537.robot.shooter.flywheel.FlywheelCommand;

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
    	addSequential(new FlywheelCommand(shootVelocity));
    	addSequential(new EjectBallCommand());
    }
}
