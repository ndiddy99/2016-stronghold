package org.usfirst.frc.team2537.robot.shooter;

import org.usfirst.frc.team2537.robot.Robot;
import org.usfirst.frc.team2537.robot.shooter.flywheel.ShooterSubsystem;
import org.usfirst.frc.team2537.robot.shooter.flywheel.FlywheelCommand;

import edu.wpi.first.wpilibj.command.Command;

public class SpinUpWheelsCommand extends Command {
	//Speed
	private static final double SHOOT_SPEED = 5;
	private static final double OFF_SPEED = 0;
	private boolean isFinished = false;
	private static final double SPEED_INCREMENT = .05;
	private static final double SPEED_PROXIMITY = .5;
	private double currentLeftFlywheelSpeed = 0.0;
	private double currentRightFlywheelSpeed = 0.0;
    public SpinUpWheelsCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	//super(FlywheelShootSpeed);
    	//if (flywheelSpeed < 0) System.out.println("Negative speed of " + speed + "given to flywheel spin up.");
    }
   
    @Override
    public boolean isFinished(){
   
    	return (isInRange(ShooterSubsystem.getLeftFlywheelSpeed()) && isInRange(ShooterSubsystem.getRightFlywheelSpeed()));

    }

	@Override
	protected void end() {
		
		ShooterSubsystem.setLeftFlywheelSpeed(OFF_SPEED);
		ShooterSubsystem.setRightFlywheelSpeed(OFF_SPEED);
		
	}

	@Override
	protected void execute() {
		currentLeftFlywheelSpeed = ShooterSubsystem.getLeftFlywheelSpeed();
		currentLeftFlywheelSpeed = incrementTowardsRange(currentLeftFlywheelSpeed);
		ShooterSubsystem.setLeftFlywheelSpeed(currentLeftFlywheelSpeed);
		currentRightFlywheelSpeed = ShooterSubsystem.getRightFlywheelSpeed();
		currentRightFlywheelSpeed = incrementTowardsRange(currentRightFlywheelSpeed);
		ShooterSubsystem.setRightFlywheelSpeed(currentRightFlywheelSpeed);
			
		

	
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
	private double incrementTowardsRange(double speed) {
		if(speed < SHOOT_SPEED - SPEED_PROXIMITY) {
			 return speed + SPEED_INCREMENT;
			 
		} else if(speed > SHOOT_SPEED + SPEED_PROXIMITY) {
			 return speed - SPEED_INCREMENT;
		}
		return speed;
	}
	private boolean isInRange(double speed) {
		if(speed < SHOOT_SPEED - SPEED_PROXIMITY) {
			 return false;
		} else if(speed > SHOOT_SPEED + SPEED_PROXIMITY) {
			 return false;
		}
		return true;
		
	}
}
