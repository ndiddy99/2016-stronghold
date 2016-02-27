package org.usfirst.frc.team2537.robot.auto;

import org.usfirst.frc.team2537.robot.Robot;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.command.Command;

public class CourseCorrect extends Command {
	private static final double DEFAULT_SPEED = -0.25;
	private static final double CORRECTION_PROPORTION = 45;
	private static final double TOLERANCE = 1;
	private double speed;
	private double startAngle;
	private double distance;
	private AHRS ahrs = Robot.driveSys.getAhrs();
	private boolean slowingDown = false;
	
	/**
	 * Drives &lt;distance&gt; while correcting for angle
	 * @param distance distance in inches
	 */
	public CourseCorrect(double distance){
		requires(Robot.driveSys);
		this.distance = distance;
		if(distance < 0)
			speed = -DEFAULT_SPEED;
		else
			speed = DEFAULT_SPEED;
	}
	
	/**
	 * Drives &lt;distance&gt; at &lt;speed&gt;
	 * @param distance
	 * @param speed
	 */
	public CourseCorrect(double distance, double speed){
		requires(Robot.driveSys);
		this.distance = distance;
		this.speed = speed;
	}
	
	@Override
	protected void initialize() {
		startAngle = ahrs.getAngle();
		if(startAngle > 180)
			startAngle -= 360;
		System.out.println("Init: start: " + startAngle + "\t current: " + ahrs.getAngle());
		Robot.driveSys.lencoder.reset();
		Robot.driveSys.rencoder.reset();
	}

	@Override
	protected void execute() {
		double currentAngle = ahrs.getAngle();
		if(currentAngle > 180)
			currentAngle -= 360;
		System.out.println("Start angle: " + startAngle + "\tCurrent angle: " + currentAngle);
		if(!slowingDown && Math.abs(Math.abs(distance) - Math.abs(getEncoderAverage())) < 6){
			speed /= 2;
			slowingDown = true;
		}
		
		double left = speed;
		double right = speed;
		double correction = 0;
		if(Math.abs(currentAngle - startAngle) > TOLERANCE)
			if(Math.abs(currentAngle - startAngle) > TOLERANCE)
				correction = (currentAngle - startAngle)/CORRECTION_PROPORTION;
		left += correction;
		right -= correction;
		Robot.driveSys.setDriveMotors(left, right);
	}

	@Override
	protected boolean isFinished() {
		if(distance < 0)
			return getEncoderAverage() <= distance;
		return (getEncoderAverage() >= distance);
	}

	@Override
	protected void end() {
		Robot.driveSys.setDriveMotors(0);
	}

	@Override
	protected void interrupted() {
		Robot.driveSys.setDriveMotors(0);
	}

	private double getEncoderAverage(){
		return ((-Robot.driveSys.lencoder.get() + Robot.driveSys.rencoder.get()) / 2.0) / 250.0 * 6.0 * Math.PI;
	}
}
