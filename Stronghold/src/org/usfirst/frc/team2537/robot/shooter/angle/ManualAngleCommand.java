package org.usfirst.frc.team2537.robot.shooter.angle;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team2537.robot.Robot;

/**
 * Let the angle be adjusted by the xbox joystick.
 * 
 * @author Matthew Schweiss
 *
 */
public class ManualAngleCommand extends Command {
	/*
	 * Adjust the sensitivity of the joystickAngle. Values less than 1 decrease
	 * the maximum speed. Values more than 1 increase the maximum speed.
	 */
	private static final double JOYSTICK_FACTOR = 1;

	/**
	 * Create a ManualAngleCommand. There typically should only be one.
	 */
	public ManualAngleCommand() {
		requires(Robot.shooterAngleSys);
	}

	@Override
	protected void initialize() {
	}

	@Override
	protected void execute() {
		// Get joystick values.
		double speed = Robot.shooterAngleSys.getJoystickAngle();
		Robot.shooterAngleSys.setVoltagePercent(speed * JOYSTICK_FACTOR);
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

	@Override
	protected void end() {
		// Well we better make sure the motors are not moving.
		Robot.shooterAngleSys.setVoltagePercent(0);
	}

	@Override
	protected void interrupted() {
		// I was interrupted, ok.
		if (Robot.shooterAngleSys.DEBUG)
			System.out.println("ManualAngleCommand was interrupted!");
		Robot.shooterAngleSys.setVoltagePercent(0);
	}
}