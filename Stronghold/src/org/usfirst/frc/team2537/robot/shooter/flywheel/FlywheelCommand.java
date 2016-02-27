//package org.usfirst.frc.team2537.robot.shooter.flywheel;
//
//import org.usfirst.frc.team2537.robot.Robot;
//import edu.wpi.first.wpilibj.command.Command;
//
///**
// * Change the speed of the flywheels to a particular speed.
// * 
// * @author Matthew Schweiss
// */
///*
// * This will be overhauled because, now the PID is used in the flywheel
// * subsystem.
// */
//public class FlywheelCommand extends Command {
//	// constants
//	private static final boolean DEBUG = false;
//	private static final double DEFAULT_TIMEOUT = 10;// seconds
//	// vars
//	private final double targetSpeed;
//
//	/**
//	 * Create the flywheel command. Move the flywheels to speed. If timeout is
//	 * less than or equal to zero, timeout will never happen.
//	 * 
//	 * @param speed
//	 *            The speed in RPM that will reached.
//	 * @param timeout
//	 *            The max time for this to run. (t<=0 means no timeout).
//	 */
//	public FlywheelCommand(double speed, double timeout) {
//		super(timeout);
//		requires(Robot.shooterFlywheelSys);
//		targetSpeed = speed;
//	}
//
//	/**
//	 * Make a command to spin up the flywheels to a speed.
//	 * 
//	 * @param speed
//	 *            The speed to set the flywheels to.
//	 */
//	public FlywheelCommand(double speed) {
//		this(speed, DEFAULT_TIMEOUT);
//	}
//
//	@Override
//	protected void initialize() {
//		if (DEBUG) System.out.println("Flywheel Command Started");
//		Robot.shooterFlywheelSys.setSpeed(targetSpeed);
//		// Get the motor values to start with.
//		
//	}
//
//	@Override
//	public boolean isFinished() {
//		// Check if the flywheels are at the target speed
//		return Robot.shooterFlywheelSys.isAtSpeed(targetSpeed) || isTimedOut();
//	}
//
//	@Override
//	protected void end() {
//		System.out.println("Flywheel Command Finished");
//		// Nothing to finish with.
//		// Keep wheels spinning.
//	}
//
//	@Override
//	protected void execute() {
//		// Nothing needs to be done because it auto ramps.
//	}
//
//	@Override
//	protected void interrupted() {
//		// Interruped, stop the wheels
//		if (DEBUG) System.out.println("Flywheel Command was Interrupted");
//		Robot.shooterFlywheelSys.setSpeed(0.0);
//	}
//}
