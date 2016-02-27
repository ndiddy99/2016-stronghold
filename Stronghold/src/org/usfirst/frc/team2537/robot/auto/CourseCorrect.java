package org.usfirst.frc.team2537.robot.auto;

import org.usfirst.frc.team2537.robot.Robot;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.command.Command;

public class CourseCorrect extends Command {
	private static final double DEFAULT_SPEED = -0.5;
	private static final double CORRECTION_PROPORTION = 180;
	private static final double TOLERANCE = 5;
	private double speed;
	private double startAngle;
	private double distance;
	private AHRS ahrs = Robot.driveSys.getAhrs();
	private boolean slowingDown = false;
	
	/**
	 * Drives distance while correcting for angle
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
	
	@Override
	protected void initialize() {
		ahrs.zeroYaw();
		startAngle = ahrs.getAngle();
		Robot.driveSys.setDriveMotors(speed);
		Robot.driveSys.lencoder.reset();
		Robot.driveSys.rencoder.reset();	
	}

	@Override
	protected void execute() {
		System.out.println("Start angle: " + startAngle + "\tCurrent angle: " + (ahrs.getAngle()));
		System.out.println("Current distance: " + getEncoderAverage() + "\tDestination: " + distance);
		if(!slowingDown && Math.abs(Math.abs(distance) - Math.abs(getEncoderAverage())) < 6){
			speed /= 2;
			slowingDown = true;
		}
		
		double left = speed;
		double right = speed;
		double correction = 0;
		if(Math.abs(ahrs.getAngle() - startAngle) > TOLERANCE)
			correction = (ahrs.getAngle() - startAngle)/CORRECTION_PROPORTION;
		left -= correction;
		right += correction;
		Robot.driveSys.setDriveMotors(left, right);
	}

	@Override
	protected boolean isFinished() {
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
		return ((-Robot.driveSys.lencoder.get() + Robot.driveSys.rencoder.get()) / 2.0) / 250.0 * 12.0 * Math.PI;
	}
}
