package org.usfirst.frc.team2537.robot.shooter;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class ShooterCommandGroup extends CommandGroup {
    
    public  ShooterCommandGroup() {
    	addSequential(new SpinUpWheelsCommand());
    	addSequential(new EjectBallCommand());
    }
}
