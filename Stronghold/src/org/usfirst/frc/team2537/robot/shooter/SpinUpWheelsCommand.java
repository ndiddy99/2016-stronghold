//package org.usfirst.frc.team2537.robot.shooter;
//
//import org.usfirst.frc.team2537.robot.Robot;
//import org.usfirst.frc.team2537.robot.shooter.flywheel.ShooterSubsystem;
//import org.usfirst.frc.team2537.robot.shooter.flywheel.FlywheelCommand;
//
//import edu.wpi.first.wpilibj.command.Command;
//
//public class SpinUpWheelsCommand extends Command {
//	//Velocity
//	private static final double SHOOT_SPEED = 5;
//	private static final double OFF_SPEED = 0;
//	private boolean isFinished = false;
//	private static final double SPEED_INCREMENT = .05;
//	private static final double SPEED_PROXIMITY = .5;
//	private double currentLeftFlywheelVelocity = 0.0;
//	private double currentRightFlywheelVelocity = 0.0;
//    public SpinUpWheelsCommand() {
//        // Use requires() here to declare subsystem dependencies
//        // eg. requires(chassis);
//    	//super(FlywheelShootVelocity);
//    	//if (flywheelVelocity < 0) System.out.println("Negative velocity of " + velocity + "given to flywheel spin up.");
//    }
//   
//    @Override
//    public boolean isFinished(){
//   
//    	return (isInRange(ShooterSubsystem.getLeftFlywheelVelocity()) && isInRange(ShooterSubsystem.getRightFlywheelVelocity()));
//
//    }
//
//	@Override
//	protected void end() {
//		
//		ShooterSubsystem.setLeftFlywheelVelocity(OFF_SPEED);
//		ShooterSubsystem.setRightFlywheelVelocity(OFF_SPEED);
//		
//	}
//
//	@Override
//	protected void execute() {
//		currentLeftFlywheelVelocity = ShooterSubsystem.getLeftFlywheelVelocity();
//		currentLeftFlywheelVelocity = incrementTowardsRange(currentLeftFlywheelVelocity);
//		ShooterSubsystem.setLeftFlywheelVelocity(currentLeftFlywheelVelocity);
//		currentRightFlywheelVelocity = ShooterSubsystem.getRightFlywheelVelocity();
//		currentRightFlywheelVelocity = incrementTowardsRange(currentRightFlywheelVelocity);
//		ShooterSubsystem.setRightFlywheelVelocity(currentRightFlywheelVelocity);
//			
//		
//
//	
//	}
//
//	@Override
//	protected void initialize() {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	protected void interrupted() {
//		ShooterSubsystem.setLeftFlywheelVelocity(OFF_SPEED);
//		ShooterSubsystem.setRightFlywheelVelocity(OFF_SPEED);
//		
//	}
//	private double incrementTowardsRange(double velocity) {
//		if(velocity < SHOOT_SPEED - SPEED_PROXIMITY) {
//			 return velocity + SPEED_INCREMENT;
//			 
//		} else if(velocity > SHOOT_SPEED + SPEED_PROXIMITY) {
//			 return velocity - SPEED_INCREMENT;
//		}
//		return velocity;
//	}
//	private boolean isInRange(double velocity) {
//		if(velocity < SHOOT_SPEED - SPEED_PROXIMITY) {
//			 return false;
//		} else if(velocity > SHOOT_SPEED + SPEED_PROXIMITY) {
//			 return false;
//		}
//		return true;
//		
//	}
//}
