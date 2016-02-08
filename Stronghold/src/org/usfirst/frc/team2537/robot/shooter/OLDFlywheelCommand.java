package org.usfirst.frc.team2537.robot.shooter;

import org.usfirst.frc.team2537.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class OLDFlywheelCommand extends Command {
	private double speed = .5 *1023.0;
	private static final double ALLOWED_ERROR = 10.0; // error in percent
														// allowed

	public OLDFlywheelCommand(double speed) {
		this.speed = speed;
	}

	@Override
	protected void initialize() {
		Robot.shooterFlywheelSys.setLeftSpeed(speed);
		Robot.shooterFlywheelSys.setRightSpeed(speed);

	}

	@Override
	protected void execute() {

	}

	@Override
	protected boolean isFinished() {

		return (Robot.shooterFlywheelSys.getLeftError() >= speed * Math.abs(speed * (ALLOWED_ERROR / 100.0))
				|| Robot.shooterFlywheelSys.getRightError() >= Math.abs(speed * (ALLOWED_ERROR / 100.0))
				|| Math.abs(speed - Robot.shooterFlywheelSys.getRightSpeed()) >=Math.abs(speed * (ALLOWED_ERROR / 100.0)))
				|| Math.abs(speed - Robot.shooterFlywheelSys.getLeftSpeed()) >= Math.abs(speed * (ALLOWED_ERROR / 100.0));
	}

	@Override
	protected void end() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void interrupted() {
		Robot.shooterFlywheelSys.setLeftSpeed(0.0);
		Robot.shooterFlywheelSys.setRightSpeed(0.0);

	}

}
