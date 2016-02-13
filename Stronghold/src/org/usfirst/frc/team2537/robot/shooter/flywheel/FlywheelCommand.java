package org.usfirst.frc.team2537.robot.shooter.flywheel;

import org.usfirst.frc.team2537.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;

/**
 * Change the speed of the flywheels to a particular speed.
 * 
 * @author Matthew Schweiss
 */
/*
 * This will be overhauled because, now the PID is used in the flywheel
 * subsystem.
 */
public class FlywheelCommand extends Command {
	// constants
	private static final long DEFAULT_TIMEOUT = 2;// seconds
	// vars
	private final double TARGET_SPEED;

	/**
	 * Create the flywheel command. Move the flywheels to speed. If timeout is
	 * less than or equal to zero, timeout will never happen.
	 * 
	 * @param speed
	 *            The speed in RPM that will reached.
	 * @param timeout
	 *            The max time for this to run. (t<=0 means no timeout).
	 */
	public FlywheelCommand(double speed, double timeout) {
		super(timeout);
		requires(Robot.shooterFlywheelSys);
		TARGET_SPEED = speed;
	}

	/**
	 * Make a command to spin up the flywheels to a speed.
	 * 
	 * @param speed
	 *            The speed to set the flywheels to.
	 */
	public FlywheelCommand(double speed) {
		this(speed, DEFAULT_TIMEOUT);
	}

	@Override
	protected void initialize() {
		// Get the motor values to start with.
		Robot.shooterFlywheelSys.setSpeed(TARGET_SPEED);
	}

	@Override
	public boolean isFinished() {
		// Check if the flywheels are at the target speed
		return Robot.shooterFlywheelSys.isAtSpeed(TARGET_SPEED);
	}

	@Override
	protected void end() {
		// Nothing to finish with.
		// Keep wheels spinning.
	}

	@Override
	protected void execute() {
		// Nothing needs to be done because it auto ramps.
	}

	@Override
	protected void interrupted() {
		// Interruped, stop the wheels
		Robot.shooterFlywheelSys.setSpeed(0.0);
	}
}
