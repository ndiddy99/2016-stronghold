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
	private static final boolean DEBUG = true;
	private static final double HARVEST_ANGLE = -10;
	private static final double HARVEST_SPEED = 6;

	public HarvestCommandGroup() {
//		addParallel(new MoveToAngleCommand(HARVEST_ANGLE));
		addSequential(new FlywheelCommand(HARVEST_SPEED));
		// Wait until we get a ball.
		addSequential(new BallDetectionCommand(true));
		addSequential(new FlywheelCommand(0));
	}
	
	@Override
	protected void initialize() {
		if (DEBUG) System.out.println("HarvestCommandGroup has started.");
		//I know super will not do anything but the future may change 
		//the implementation.
		super.initialize();
	}
	@Override
	protected void interrupted() {
		if (DEBUG) System.out.println("HarvestCommandGroup was canceled.");
		new FlywheelCommand(0).start();
	}

	@Override
	protected void end() {
		if (DEBUG) System.out.println("HarvestCommandGroup finished.");
		//new FlywheelCommand(0).start();
	}
}
