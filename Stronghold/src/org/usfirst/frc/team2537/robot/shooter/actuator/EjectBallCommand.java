//package org.usfirst.frc.team2537.robot.shooter.actuator;
//
//import edu.wpi.first.wpilibj.command.Command;
//import org.usfirst.frc.team2537.robot.Robot;
//
///**
// * 
// * @author Jacob Barnett
// *
// */
//public class EjectBallCommand extends Command {
//	//Constants
//	private static final float WAIT_TIME = 50;
//	private final float START_TIME =System.currentTimeMillis();
//	
//	private boolean finished = false;
//	
//	public EjectBallCommand() {
//	}
//
//	@Override
//	protected void end() {
//		Robot.shooterActuatorSys.retractSolenoid();
//	}
//
//	@Override
//	protected void execute() {
//	finished = ((System.currentTimeMillis() - START_TIME) >= WAIT_TIME); 	
//
//	}
//
//	@Override
//	protected void initialize() {
//		Robot.shooterActuatorSys.actuateSolenoid();
//
//	}
//
//	@Override
//	protected void interrupted() {
//		Robot.shooterActuatorSys.retractSolenoid();
//
//	}
//
//	@Override
//	protected boolean isFinished() {
//		return finished;
//	}
//
//}
