package org.usfirst.frc.team2537.robot.auto;

import org.usfirst.frc.team2537.robot.Robot;

import com.kauailabs.navx.frc.AHRS;


import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SPI.Port;
import edu.wpi.first.wpilibj.command.Command;

public class AutoRotateCommand extends Command{
	private double speed;
	private double gangle; //are we using angle sensors (magnetometer, etc) or distance sensors (encoders)?
	private static final boolean debug = false;
	private static final double DEFAULT_SPEED = 0.5;
	private static final double ROBOT_DIAMETER = 10; //Inches  TODO: Magic numbers are fun
	 AHRS ahrs;
	  RobotDrive myRobot;
	  
	  PIDController turnController;
	  double rotateToAngleRate;
	  
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
		AHRS ahrs = new AHRS(Port.kMXP);
		requires(Robot.driveSys);
		double gangle = ahrs.getAngle();	
		if(gangle < angle)
			speed = -DEFAULT_SPEED;
		else
			speed = DEFAULT_SPEED;
	}
	 
	  
	/**
	 * spins [angle] degrees at [speed]
	 * counterclockwise (untested)
	 * @param angle]
	 * @param speed
	 */
	public AutoRotateCommand(double angle, double speed){
		AHRS ahrs = new AHRS(Port.kMXP);
		requires(Robot.driveSys);
		angle = ahrs.getAngle();
		this.speed = speed;
	}
	
	@Override
	protected void initialize() {
		Robot.driveSys.resetEncoders();
		if(debug) System.out.println("[AutoRotateCommand] Initializing. speed: " + speed + " angle: " + gangle);
		Robot.driveSys.setDriveMotors(-speed, speed);
	}

	@Override
	protected void execute() {
		if(debug) System.out.println("[AutoRotateCommand] Current angle: " + getCurrentAngle());
	}

	@Override
	protected boolean isFinished() {
		return getCurrentAngle() >= gangle;
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
		return 360 * Robot.driveSys.getEncoderAverage()/2/ROBOT_DIAMETER * Math.PI;
	}


}
