package org.usfirst.frc.team2537.robot.shooter;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team2537.robot.Robot;

/**
 * Wait until the ball is in the robot. The point of this is to have a wait to
 * be used by command group's later on. By using this command, a command group
 * can wait for a ball, and so other events can happen.
 *
 * @author Matthew Schweiss
 */
public class BallDetectionCommand extends Command {
	private static final boolean DEBUG = false;

	private static final double DEFAULT_TIMEOUT = 1.0;

	final boolean waitUntilEnters;

	/**
	 * This command basically waits until the proximity sensor detecting the
	 * ball changes to the desired state.
	 * 
	 * @param waitUntilEnters
	 *            Whether or not the command waits until the proximity sensor
	 *            detects when the ball enters or leaves the shooter. True -
	 *            Wait until the ball is present. False - Wait until the ball is
	 *            not present.
	 */
	public BallDetectionCommand(boolean waitUntilEnters) {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		// We do need a time out.
		super(DEFAULT_TIMEOUT);
		this.waitUntilEnters = waitUntilEnters;
		requires(Robot.shooterFlywheelSys);
	}

	// Required commands
	@Override
	// Called just before this Command runs the first time
	protected void initialize() {
		if (DEBUG)
			System.out.print("BallDetectionCommand started (waitForBall = " + waitUntilEnters + ")");
	}

	@Override
	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		System.out.println(Robot.shooterFlywheelSys.isBallPresent());
	}

	@Override
	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		if (waitUntilEnters) {
			return (Robot.shooterFlywheelSys.isBallPresent() || isTimedOut());
		} else {
			return !Robot.shooterFlywheelSys.isBallPresent()||isTimedOut();
		}
	}

	@Override
	// Called once after isFinished returns true
	protected void end() {
		if (DEBUG)
			System.out.println("BallDetectionCommand ended. (waitForBall = " + waitUntilEnters + ")");
	}

	@Override
	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		if (DEBUG)
			System.out.println("BallDetectionCommand was canceled. (waitForBall = " + waitUntilEnters + ")");
	}
}
