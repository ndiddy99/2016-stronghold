package org.usfirst.frc.team2537.robot.auto;

import org.usfirst.frc.team2537.robot.Robot;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class AutoRotateCommand extends Command {
	private AHRS ahrs;
	private double destinationAngle;

	private static final double DEFAULT_SPEED = 0.5;
	private static final double TOLERANCE = 5; // degrees

	private double speed;
	private boolean slowingDown = false;

	/**
	 * spins destinationAngle degrees
	 * 
	 * @param destinationAngle
	 *            Destination angle in degrees
	 */
	public AutoRotateCommand(double destinationAngle) {
		requires(Robot.driveSys);
		ahrs = Robot.driveSys.getAhrs();
		this.destinationAngle = destinationAngle;
		speed = DEFAULT_SPEED;
	}

	@Override
	protected void initialize() {
	}

	@Override
	protected void execute() {
		double currentAngle = ahrs.getAngle();
		SmartDashboard.putNumber("Current Angle", currentAngle);
		if (currentAngle <= destinationAngle - TOLERANCE)
			Robot.driveSys.setDriveMotors(-speed, speed);
		if (currentAngle >= destinationAngle + TOLERANCE)
			Robot.driveSys.setDriveMotors(speed, -speed);

		if (!slowingDown && Math.abs(Math.abs(destinationAngle) - Math.abs(currentAngle)) < 45) {
			speed = speed / 2;
			slowingDown = true;
		}
	}

	@Override
	protected boolean isFinished() {
		double currentAngle = Robot.driveSys.getAhrs().getAngle();
		return (currentAngle <= destinationAngle + TOLERANCE && currentAngle >= destinationAngle - TOLERANCE);
	}

	@Override
	protected void end() {
		Robot.driveSys.setDriveMotors(0);
	}

	@Override
	protected void interrupted() {
		Robot.driveSys.setDriveMotors(0);
	}

}