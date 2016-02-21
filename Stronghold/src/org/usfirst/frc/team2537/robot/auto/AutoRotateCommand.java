package org.usfirst.frc.team2537.robot.auto;

import org.usfirst.frc.team2537.robot.Robot;
import org.usfirst.frc.team2537.robot.drive.DriveSubsystem;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

public class AutoRotateCommand extends Command {
	private AHRS ahrs;
	private RobotDrive myRobot;
	private PIDController turnController;
	private double rotateToAngleRate;
	private double gangle;
	private double angle;

	private static final double DEFAULT_SPEED = 0.5;

	double speed = DEFAULT_SPEED;

	/**
	 * spins [angle] degrees at [speed] counterclockwise (untested)
	 * 
	 * @param angle]
	 * @param speed
	 */
	public AutoRotateCommand(double angle) {
		requires(Robot.driveSys);
		ahrs = Robot.driveSys.getAhrs();
		this.angle = angle;
		gangle = Robot.driveSys.getAhrs().getAngle();

	}

	@Override
	protected void initialize() {

	}

	@Override

	protected void execute() {
		requires(Robot.driveSys);
		ahrs = Robot.driveSys.getAhrs();
		double gangle = ahrs.getAngle();
		turnController.setSetpoint(angle);
		while (gangle < angle || gangle > angle) {
			Robot.driveSys.setDriveMotors(-speed, speed);
			if (gangle == angle) {
				Robot.driveSys.setDriveMotors(0);
				break;
			}
		}
		;

	}

	@Override
	protected boolean isFinished() {
		boolean val = false;
		if (angle <= 0 && Robot.driveSys.getAhrs().getAngle() >= angle) {
			val = true;
		} else if (angle >= 0 && Robot.driveSys.getAhrs().getAngle() <= angle) {
			val = true;
			System.out.println("done");
		}
		return val;

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