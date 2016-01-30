package org.usfirst.frc.team2537.robot.shooter;

import org.usfirst.frc.team2537.robot.shooter.flywheel.ShooterSubsystem;
import org.usfirst.frc.team2537.robot.shooter.flywheel.SyncFlywheelCommand;

import edu.wpi.first.wpilibj.command.Command;

public class ShootCommand extends Command {
	//Speed
	private static final double SHOOT_SPEED = .5;
	private static final double OFF_SPEED = 0;
	private static boolean isFinished = false;

    public ShootCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	//super(FlywheelShootSpeed);
    	//if (flywheelSpeed < 0) System.out.println("Negative speed of " + speed + "given to flywheel spin up.");
    }
    
    @Override
    public boolean isFinished(){
    	//As of right now, this is never finished.
    	return isFinished;
    }

	@Override
	protected void end() {
		ShooterSubsystem.setLeftFlywheelSpeed(OFF_SPEED);
		ShooterSubsystem.setRightFlywheelSpeed(OFF_SPEED);
		
	}

	@Override
	protected void execute() {
	ShooterSubsystem.setLeftFlywheelSpeed(SHOOT_SPEED);
	ShooterSubsystem.setRightFlywheelSpeed(SHOOT_SPEED);
	ShooterSubsystem.actuateSolenoid();
	isFinished = true;
	}

	@Override
	protected void initialize() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void interrupted() {
		ShooterSubsystem.setLeftFlywheelSpeed(OFF_SPEED);
		ShooterSubsystem.setRightFlywheelSpeed(OFF_SPEED);
		
	}
}
