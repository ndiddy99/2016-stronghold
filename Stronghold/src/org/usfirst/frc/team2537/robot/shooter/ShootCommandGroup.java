package org.usfirst.frc.team2537.robot.shooter;

import org.usfirst.frc.team2537.robot.shooter.flywheel.EjectBallCommand;
import org.usfirst.frc.team2537.robot.shooter.flywheel.FlywheelCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class ShootCommandGroup extends CommandGroup {
    public static final double SHOOT_VELOCITY = .5;
    
    public ShootCommandGroup() {
    	addSequential(new FlywheelCommand(SHOOT_VELOCITY));
    	addSequential(new EjectBallCommand());
    }
    
    public ShootCommandGroup(double shootVelocity){
    	addSequential(new FlywheelCommand(shootVelocity));
    	addSequential(new EjectBallCommand());
    }
}
