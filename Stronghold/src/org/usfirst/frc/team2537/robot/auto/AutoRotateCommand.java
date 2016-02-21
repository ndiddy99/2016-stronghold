package org.usfirst.frc.team2537.robot.auto;

import org.usfirst.frc.team2537.robot.Robot;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.SPI.Port;
import edu.wpi.first.wpilibj.command.Command;

public class AutoRotateCommand extends Command implements PIDOutput {
	private double speed;
	private double gangle, angle; // are we using angle sensors (magnetometer,
									// etc) or distance sensors (encoders)?
	private static final boolean debug = true;
	private static final double DEFAULT_SPEED = 0.5;
	 // Inches TODO: Magic
														// numbers are fun
	private AHRS ahrs;
	static final double kP = 0.50;
	static final double kI = 0.10;
	static final double kD = 0.5;
	static final double kF = 0.00;

	static final double kToleranceDegrees = 2.0f;

	RobotDrive myRobot;

	PIDController turnController;
	double rotateToAngleRate;

	/**
	 * spins 10000000 degrees at default speed counterclockwise (untested) Don't
	 * know why anybody would want the robot to spin forever
	 */

	/**
	 * Spins [angle] at default speed
	 * 
	 * @param angle
	 */
	public AutoRotateCommand(double angle) {
		this(angle,DEFAULT_SPEED);
	}

	/**
	 * spins [angle] degrees at [speed] counterclockwise (untested)
	 * 
	 * @param angle]
	 * @param speed
	 */
	public AutoRotateCommand(double angle, double speed) {
		
		ahrs = Robot.driveSys.getAhrs();
		requires(Robot.driveSys);
		this.angle = angle;
		myRobot = new RobotDrive(0, 1);
	
		turnController = new PIDController(kP, kI, kD, kF, ahrs, this);
		turnController.setInputRange(-180.0f, 180.0f);//LIMITS IN ANGLES
		turnController.setOutputRange(-1.0, 1.0);//SPEED LIMITS
		turnController.setAbsoluteTolerance(kToleranceDegrees);//maximum error
		turnController.setContinuous(true);//this means that it wraps from 180 to -180 degrees

		this.speed = speed;
		if (gangle < angle)
			this.speed = -Math.abs(speed);
		else
			this.speed = Math.abs(speed);
	}

	@Override
	protected void initialize() {
		ahrs.zeroYaw();
		if (debug)
			System.out.println("[AutoRotateCommand] Initializing. speed: " + speed + " angle: " + gangle);
		Robot.driveSys.setDriveMotors(-speed, speed);
	}

	@Override
	protected void execute() {
		myRobot = new RobotDrive(0, 1);
	      myRobot.setExpiration(15);
		System.out.println("Desired"+angle);
		System.out.println("NAVX"+Robot.driveSys.getAhrs().getAngle());
	}

	@Override
	protected boolean isFinished() {
		return true;
//		boolean val = false;
//		if (angle <= 0 && Robot.driveSys.getAhrs().getAngle() >= angle) {
//			val = true;
//		} else if (angle >= 0 && Robot.driveSys.getAhrs().getAngle() <= angle) {
//			val = true;
//		}
//		return val;
	}

	@Override
	protected void end() {
		if (debug)
			System.out.println("[AutoRotateCommand] good end");
		Robot.driveSys.setDriveMotors(0);
	}

	@Override
	protected void interrupted() {
		if (debug)
			System.out.println("[AutoRotateCommand] bad end");
		Robot.driveSys.setDriveMotors(0);
	}



	@Override
	public void pidWrite(double output) {
		
		

	}

}
