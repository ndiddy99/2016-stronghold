package org.usfirst.frc.team2537.robot.auto;

import org.usfirst.frc.team2537.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class AutoTimedDriveCommand extends Command {
	private long startTime;
	private long duration;
	private static final double DEFAULT_SPEED = 0.75;
	private static final boolean debug = true;

	/**
	 * Drives time in milliseconds
	 * 
	 * @param time
	 *            Time to drive
	 */
	public AutoTimedDriveCommand(long time) {
		requires(Robot.driveSys);
		duration = time;
	}

	@Override
	protected void initialize() {
		startTime = System.currentTimeMillis();
		Robot.driveSys.setDriveMotors(DEFAULT_SPEED);
	}

	@Override
	protected void execute() {
		if (debug)
			System.out.println("[AutoTimedDriveCommand] Current Time: " + System.currentTimeMillis() + "\tEnd time: "
					+ (startTime + duration));
	}

	@Override
	protected boolean isFinished() {
		return (System.currentTimeMillis() >= startTime + duration);
	}

	@Override
	protected void end() {
		System.out.println("[AutoTimedDriveCommand] Done");
	}

	@Override
	protected void interrupted() {
		System.out.println("[AutoTimedDriveCommand] D:");
	}

}
