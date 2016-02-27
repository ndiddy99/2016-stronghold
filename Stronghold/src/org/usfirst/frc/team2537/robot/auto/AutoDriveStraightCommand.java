package org.usfirst.frc.team2537.robot.auto;

import org.usfirst.frc.team2537.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Untested
 * 
 * @author Arden Zhang
 *
 */
public class AutoDriveStraightCommand extends Command {
	private double speed;
	private double distance;
	private static final boolean debug = true;
	private static final double DEFAULT_SPEED = -0.5;
	private boolean slowingDown = false;
	
	/**
	 * drives forward nowhere
	 */
	public AutoDriveStraightCommand() {
		this(0);
	}

	/**
	 * Drives [distance] at the default speed
	 * 
	 * @param distance
	 *            in inches
	 */
	public AutoDriveStraightCommand(double distance) {
		requires(Robot.driveSys);
		this.distance = distance;
		if (distance < 0)
			speed = -DEFAULT_SPEED;
		else
			speed = DEFAULT_SPEED;
	}

	/**
	 * Drives [distance] at [speed]
	 * 
	 * @param distance
	 *            distance in inches
	 * @param speed
	 *            speed from -1.0 to 1.0
	 */
	public AutoDriveStraightCommand(double distance, double speed) {
		requires(Robot.driveSys);
		this.distance = distance;
		this.speed = speed;
	}

	@Override
	protected void initialize() {

		if (debug) {

			System.out.println("[AutoDriveStraightCommand] Driving " + distance + " at " + speed);
		}
		Robot.driveSys.setDriveMotors(speed);
		// Robot.driveSys.resetEncoders();

		// ATLAS
		Robot.driveSys.lencoder.reset();
		Robot.driveSys.rencoder.reset();
	}

	@Override
	protected void execute() {
		if (debug) {
			// System.out.println("[AutoDriveStraightCommand] Current distance:
			// "
			// + Robot.driveSys.getEncoderAverage() + "\tTarget distance: " +
			// distance);

			// ATLAS
			System.out.println("[AutoDriveStraightCommand] Current distance: " + getEncoderAverage()
					+ "\tTarget distance: " + distance);
			// System.out.println("[AutoDriveStraightCommand] Left: " +
			// -Robot.driveSys.lencoder.get() + "\tRight: "
			// + Robot.driveSys.rencoder.get());
		}

		if(!slowingDown && Math.abs(distance) - Math.abs(getEncoderAverage()) < 6){
			if(debug) System.out.println("[AutoDriveStraightCommand] Slowing down...");
			Robot.driveSys.setDriveMotors(speed/2);
			slowingDown = true;
		}
	
	}

	@Override
	protected boolean isFinished() {
		// if (distance < 0) {
		// return (Robot.driveSys.getEncoderAverage() <= distance);
		// }
		// return (Robot.driveSys.getEncoderAverage() >= distance);

		// ATLAS
		if (distance < 0) {
			return (getEncoderAverage() <= distance);
		}
		return (getEncoderAverage() >= distance);

	}

	@Override
	protected void end() {
		if (debug) {
			System.out.println("[AutoDriveStraightCommand] good end");
		}
		Robot.driveSys.setDriveMotors(0);
	}

	@Override
	protected void interrupted() {
		if (debug) {
			System.out.println("[AutoDriveStraightCommand] bad end");
		}
		Robot.driveSys.setDriveMotors(0);
	}

	// ATLAS
	// 1 tick * (1 rotation / 250.0 ticks) * (6*pi in / 1 rotation)
	// ATLAS wheel is 6 in Diameter
	// WaifuBot has 10 in Diameter
	/**
	 * average encoder distance in inches
	 * 
	 * @return
	 */
	private double getEncoderAverage() {
		return ((-Robot.driveSys.lencoder.get() + Robot.driveSys.rencoder.get()) / 2.0) / 250.0 * 12.0 * Math.PI;
		// return (-Robot.driveSys.lencoder.get() +
		// Robot.driveSys.rencoder.get()) / 2.0;
	}
}
