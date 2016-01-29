package org.usfirst.frc.team2537.robot.shooter;

import org.usfirst.frc.team2537.robot.shooter.flywheel.SyncFlywheelCommand;

public class ShootCommand extends SyncFlywheelCommand {
	//Speed
	private static final double FlywheelShootSpeed = 9;

    public ShootCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	super(FlywheelShootSpeed);
    	//if (flywheelSpeed < 0) System.out.println("Negative speed of " + speed + "given to flywheel spin up.");
    }
    
    @Override
    public boolean isFinished(){
    	//As of right now, this is never finished.
    	return false;
    }
}
