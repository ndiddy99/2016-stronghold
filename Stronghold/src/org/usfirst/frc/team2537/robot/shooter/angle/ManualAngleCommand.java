package org.usfirst.frc.team2537.robot.shooter.angle;

import org.usfirst.frc.team2537.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

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
	// private static final double JOYSTICK_FACTOR = -.005;
	private static final double JOYSTICK_FACTOR = .3;
	private static final double JOYSTICK_DEADZONE = .15;
	private static final double VOLTAGE_MULTIPLIER = .05;
	// private static double setpoint = 0.0;
	private boolean initialized = false;

	/**
	 * Create a ManualAngleCommand. There typically should only be one.
	 */
	public ManualAngleCommand() {
		requires(Robot.shooterAngleSys);
	}

	@Override
	protected void initialize() {

		Robot.shooterAngleSys.setVoltage(0.0);
	}

	@Override
	protected void execute() {
		// Get joystick values.

		double joystickValue = Robot.shooterAngleSys.getJoystickAngle();
		if (Math.abs(joystickValue) > JOYSTICK_DEADZONE) {
			Robot.shooterAngleSys.setSetpoint(Robot.shooterAngleSys.getSetpoint() - joystickValue * JOYSTICK_FACTOR);
			// setpoint += joystickValue * JOYSTICK_FACTOR;
			// Robot.shooterAngleSys.setVoltage(setpoint);
		}
		if (Math.abs(
				Robot.shooterAngleSys.getCurrentAngle() - Robot.shooterAngleSys.getSetpoint()) <= Robot.shooterAngleSys
						.getTolerance()) {
			Robot.shooterAngleSys.setVoltage(0.0);
		} else {
			Robot.shooterAngleSys.setVoltage(-VOLTAGE_MULTIPLIER
					* (Robot.shooterAngleSys.getCurrentAngle() - Robot.shooterAngleSys.getSetpoint()));
		}

	}

	@Override
	protected boolean isFinished() {
		return false;
	}

	@Override
	protected void end() {
	}

	@Override
	protected void interrupted() {
	}
}