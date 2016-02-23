package org.usfirst.frc.team2537.robot.shooter.angle;

//Alex, I hope you don't mind I stole a lot of your code to make this.
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team2537.robot.Robot;

/**
 * MoveToAngleCommand
 * 
 * @author Matthew Schweiss
 *
 *         The point of this command is to put the shooter to a particular
 *         angle. This goes to the angle but slows as it approaches the angle.
 */
public class MoveToAngleCommand extends Command {
	private static final boolean DEBUG = true;
	// Constants
	private static final double TOLERANCE = .5; // How far the shooter can be
												// from the desired angle in
												// degrees.
	private static final double TIMEOUT = 10; // Seconds

	// Variables
	public final double posToMoveTo;
	private double currentPosition;

	/**
	 * This is for the autonomous system to move the shooter. This is used by
	 * passing the desired angle and the system will then adjust.
	 * 
	 * @param angle
	 *            The desired angle for the shooter to move to.
	 */
	public MoveToAngleCommand(double angle) {
		super(TIMEOUT);
		requires(Robot.shooterAngleSys);
		posToMoveTo = angle;
	}

	@Override
	protected void initialize() {
		if (DEBUG)
			System.out.println("Moving Shooter to " + posToMoveTo);
	}

	@Override
	protected void execute() {
		try {
			currentPosition = Robot.shooterAngleSys.getCurrentAngle();
			Robot.shooterAngleSys.setVoltagePercent(-(currentPosition - posToMoveTo) / AngleSubsystem.MAX_ANGLE_DIFFERENCE);
		} catch (NullPointerException e) {
			//Nothing. To do, can't move without sensor.
			if (DEBUG) System.out.println("MoveToAngleCommand stopped because sensor is not present.");
			this.cancel();
		}
	}

	@Override
	protected boolean isFinished() {
		// If we are close enough to the angle to call it.
		return Math.abs(currentPosition - posToMoveTo) <= TOLERANCE;
	}

	@Override
	protected void end() {
		// Stop the motor.
		Robot.shooterAngleSys.setVoltagePercent(0);
	}

	@Override
	protected void interrupted() {
		if (DEBUG)
			System.out.println("Shooter Interrupted");
		// Stop the motor.
		Robot.shooterAngleSys.setVoltagePercent(0);
	}
}
