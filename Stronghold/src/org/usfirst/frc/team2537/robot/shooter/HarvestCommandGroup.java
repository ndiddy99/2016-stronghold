package org.usfirst.frc.team2537.robot.shooter;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.usfirst.frc.team2537.robot.shooter.angle.MoveToAngleCommand;
import org.usfirst.frc.team2537.robot.shooter.flywheel.FlywheelCommand;

/**
 * This is the command that will be used to harvest the ball from in front of
 * the robot. By holding the left trigger on the XBOX, this command will be
 * launched.
 * 
 * @author Matthew Schweiss
 */
public class HarvestCommandGroup extends CommandGroup {
	private static final double HARVEST_ANGLE = -10;
	private static final double HARVEST_SPEED = -500;
	private static final boolean DEBUG = false;

	public HarvestCommandGroup() {
		if (DEBUG) {
			System.out.println("Harvest command constructed");
		}
		addParallel(new MoveToAngleCommand(HARVEST_ANGLE));
		addSequential(new FlywheelCommand(HARVEST_SPEED));
		// Wait until we get a ball.
		addSequential(new BallDetectionCommand(true));
		addSequential(new FlywheelCommand(0));
	}

	@Override
	protected void interrupted() {
		new FlywheelCommand(0).start();
	}

	@Override
	protected void end() {
	}
}
