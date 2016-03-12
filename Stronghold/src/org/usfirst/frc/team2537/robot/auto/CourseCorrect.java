package org.usfirst.frc.team2537.robot.auto;

import org.usfirst.frc.team2537.robot.Robot;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.command.Command;

public class CourseCorrect extends Command {
	private static final double DEFAULT_SPEED = 0.5;
	private static final double CORRECTION_PROPORTION = 45;
	private static final double TOLERANCE = 1;
	private static final boolean debug = false;
	private double speed;
	private double startAngle;
	private double distance;
	private AHRS ahrs = Robot.driveSys.getAhrs();
	private boolean slowingDown = false;
	private double pos = 0;
	private double vel = 0;
	private long prevTime;
	public static final double TIMEOUT = 7;

	/**
	 * Drives &lt;distance&gt; while correcting for angle
	 * 
	 * @param distance
	 *            distance in inches
	 */
	public CourseCorrect(double distance) {
		super(TIMEOUT);
		requires(Robot.driveSys);
		this.distance = distance;
		if (distance < 0)
			speed = -DEFAULT_SPEED;
		else
			speed = DEFAULT_SPEED;
	}

	/**
	 * Drives &lt;distance&gt; at &lt;speed&gt;
	 * 
	 * @param distance
	 *            Distance in inched
	 * @param speed
	 *            Speed in voltage percent
	 */
	public CourseCorrect(double distance, double speed) {
		requires(Robot.driveSys);
		this.distance = distance;
		this.speed = speed;
		// double angle = ahrs.getAngle();
		// while(angle > 180) angle -= 180;
		// while(angle < -180) angle += 180;
		// addSequential(new AutoRotateCommand(angle));
		// addSequential(new AutoDriveStraightCommand(Math.sqrt(Math.pow(x -
		// currentX, 2) + Math.pow(y - currentY, 2))));

	}

	@Override
	protected void initialize() {
		startAngle = ahrs.getAngle();
		if (startAngle > 180)
			startAngle -= 360;
		Robot.driveSys.resetEncoders();
		prevTime = System.currentTimeMillis();
	}

	@Override
	protected void execute() {
		double currentAngle = ahrs.getAngle();
		if (!slowingDown && Math.abs(Math.abs(distance) - Math.abs(Robot.driveSys.getEncoderAverage())) < 6) {
			speed /= 2;
			slowingDown = true;
		}

		double left = speed;
		double right = speed;
		double correction = 0;

		double angleDiff = currentAngle - startAngle;
		if (Math.abs(currentAngle - 360 - startAngle) < Math.abs(angleDiff))
			angleDiff = currentAngle - 360 - startAngle;

		if (Math.abs(angleDiff) > TOLERANCE)
			correction = angleDiff / CORRECTION_PROPORTION;

		left += correction;
		right -= correction;

		// if(debug && Math.abs(ahrs.getWorldLinearAccelZ()) > 0.1)
		// System.out.println("Going over obstacle");

		double timeDiff = (System.currentTimeMillis() - prevTime) / 1000.0;
		vel += ahrs.getWorldLinearAccelX() * timeDiff; // m/s^2 * s
		pos += vel * timeDiff * 3.28084; // m/s * s * 3.28ft/m
		if (debug)
			System.out.println("accel: " + ahrs.getWorldLinearAccelX() + "\tvel: " + vel + "\tpos: " + pos + "\ttime: "
					+ timeDiff);
		prevTime = System.currentTimeMillis();

		Robot.driveSys.setDriveMotors(left, right);
		System.out.println(ahrs.getDisplacementX());
	}

	@Override
	protected boolean isFinished() {
		if (isTimedOut()) return true;
		if (distance < 0)
			return Robot.driveSys.getEncoderAverage() <= distance;
		return (Robot.driveSys.getEncoderAverage() >= distance);
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
