package org.usfirst.frc.team2537.robot.shooter.flywheel;

import org.usfirst.frc.team2537.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class NewFlywheelCommand extends Command {
	private double speed = .5 *1023.0;
	private static final double ALLOWED_ERROR = 10.0; // error in percent
														// allowed

	public NewFlywheelCommand(double speed) {
		this.speed = speed;
	}

	@Override
	protected void initialize() {
		Robot.newFlySys.setLeftSpeed(speed);
		Robot.newFlySys.setRightSpeed(speed);

	}

	@Override
	protected void execute() {

	}

	@Override
	protected boolean isFinished() {

		return (Robot.newFlySys.getLeftError() >= speed * Math.abs(speed * (ALLOWED_ERROR / 100.0))
				|| Robot.newFlySys.getRightError() >= Math.abs(speed * (ALLOWED_ERROR / 100.0))
				|| Math.abs(speed - Robot.newFlySys.getRightSpeed()) >=Math.abs(speed * (ALLOWED_ERROR / 100.0)))
				|| Math.abs(speed - Robot.newFlySys.getLeftSpeed()) >= Math.abs(speed * (ALLOWED_ERROR / 100.0));
	}

	@Override
	protected void end() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void interrupted() {
		Robot.newFlySys.setLeftSpeed(0.0);
		Robot.newFlySys.setRightSpeed(0.0);

	}

}
