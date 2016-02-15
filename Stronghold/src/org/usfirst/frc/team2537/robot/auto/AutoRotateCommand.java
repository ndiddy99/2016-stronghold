package org.usfirst.frc.team2537.robot.auto;

import org.usfirst.frc.team2537.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class AutoRotateCommand extends Command{
	private double speed;
	private double angle; //are we using angle sensors (magnetometer, etc) or distance sensors (encoders)?
	private static final boolean debug = false;
	private static final double DEFAULT_SPEED = 0.5;
	private static final double ROBOT_DIAMETER = 10; //Inches  TODO: Magic numbers are fun
	
	/**
	 * spins 10000000 degrees at default speed
	 * counterclockwise (untested)
	 * Don't know why anybody would want the robot to spin forever
	 */
	public AutoRotateCommand(){
		this(10000000, DEFAULT_SPEED);
	}
	
	/**
	 * Spins [angle] at default speed
	 * @param angle
	 */
	public AutoRotateCommand(double angle){
		requires(Robot.driveSys);
		this.angle = angle;
		if(angle < 0)
			speed = -DEFAULT_SPEED;
		else
			speed = DEFAULT_SPEED;
	}
	
	/**
	 * spins [angle] degrees at [speed]
	 * counterclockwise (untested)
	 * @param angle
	 * @param speed
	 */
	public AutoRotateCommand(double angle, double speed){
		requires(Robot.driveSys);
		this.angle = angle;
		this.speed = speed;
	}
	
	@Override
	protected void initialize() {
		Robot.driveSys.resetEncoders();
		if(debug) System.out.println("[AutoRotateCommand] Initializing. speed: " + speed + " angle: " + angle);
		Robot.driveSys.setDriveMotors(-speed, speed);
	}

	@Override
	protected void execute() {
		if(debug) System.out.println("[AutoRotateCommand] Current angle: " + getCurrentAngle());
	}

	@Override
	protected boolean isFinished() {
		return getCurrentAngle() >= angle;
	}

	@Override
	protected void end() {
		if(debug) System.out.println("[AutoRotateCommand] good end");
		Robot.driveSys.setDriveMotors(0);
	}

	@Override
	protected void interrupted() {
		if(debug) System.out.println("[AutoRotateCommand] bad end");
		Robot.driveSys.setDriveMotors(0);
	}

	private double getCurrentAngle(){
		return 360 * Robot.driveSys.getEncoders()/2/ROBOT_DIAMETER * Math.PI;
	}
}
