package org.usfirst.frc.team2537.robot.drive;

import org.usfirst.frc.team2537.robot.Robot;
import org.usfirst.frc.team2537.robot.input.HumanInput;
import org.usfirst.frc.team2537.robot.input.xBoxButtons;

import edu.wpi.first.wpilibj.Joystick.AxisType;
//import edu.wpi.first.wpilibj.CANTalon;
//import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class DriveCommand extends Command {
	private static final boolean debug = false;// set to true if ya want to
												// print extra information
	private static final double DEADZONE = 0.05;
	private static final double ROTATE_SCALE = 6.0; // scales down rotation from
													// xbox controller during
													// joystick drive

	/**
	 * Takes joystick input depending on Robot.driveSys.driveType
	 */
	public DriveCommand() {
		requires(Robot.driveSys);
	}

	@Override
	protected void initialize() {
		if (debug) {
			System.out.println("[DriveCommand] Initializing... drivetype: "
					+ Robot.driveSys.getDriveType());
		}
	}

	@Override
	protected void execute() {
		Double left = null;
		Double right = null;

		// double/single joystick/xbox driving
		switch (Robot.driveSys.getDriveType()) {
		case doubleJoystick:
			// basic double joystick
			left = HumanInput.getJoystickAxis(HumanInput.leftJoystick,
					AxisType.kY);
			right = HumanInput.getJoystickAxis(HumanInput.rightJoystick,
					AxisType.kY);

			// uses xbox x axis for rotation
			left -= HumanInput.getXboxAxis(HumanInput.xboxController,
					xBoxButtons.XBOX_LEFT_X_AXIS) / ROTATE_SCALE;
			right += HumanInput.getXboxAxis(HumanInput.xboxController,
					xBoxButtons.XBOX_LEFT_X_AXIS) / ROTATE_SCALE;
			break;
		case singleJoystick:
			left = HumanInput.getJoystickAxis(HumanInput.leftJoystick,
					AxisType.kY);
			right = left;

			left -= HumanInput.getJoystickAxis(HumanInput.leftJoystick,
					AxisType.kX);
			right += HumanInput.getJoystickAxis(HumanInput.leftJoystick,
					AxisType.kX);

			// uses xbox x axis for rotation
			left -= HumanInput.getXboxAxis(HumanInput.xboxController,
					xBoxButtons.XBOX_LEFT_X_AXIS) / ROTATE_SCALE;
			right += HumanInput.getXboxAxis(HumanInput.xboxController,
					xBoxButtons.XBOX_LEFT_X_AXIS) / ROTATE_SCALE;

			break;
		case doubleJoystickXbox:
			left = HumanInput.getXboxAxis(HumanInput.xboxController,
					xBoxButtons.XBOX_LEFT_Y_AXIS);
			right = HumanInput.getXboxAxis(HumanInput.xboxController,
					xBoxButtons.XBOX_RIGHT_Y_AXIS);
			break;
		case singleJoystickXbox:
			left = HumanInput.getXboxAxis(HumanInput.xboxController,
					xBoxButtons.XBOX_LEFT_Y_AXIS);
			right = left;

			left -= HumanInput.getXboxAxis(HumanInput.xboxController,
					xBoxButtons.XBOX_LEFT_X_AXIS);
			right += HumanInput.getXboxAxis(HumanInput.xboxController,
					xBoxButtons.XBOX_LEFT_X_AXIS);
			break;
		}

		if (left == null || right == null) {
			if (debug) {
				System.out.println("[DriveCommand] left/right is null");
			}
			Robot.driveSys.setDriveMotors(0);
			return;
		}

		// Imperfect straight driving, tested
		if (Robot.driveSys.getDrivingStraight()) {
			if (Math.abs(left) > Math.abs(right)) {
				right = left;
			} else {
				left = right;
			}
		}

		// Deadzones, not tested
		if (Math.abs(left) < DEADZONE)
			left = 0.0;
		if (Math.abs(right) < DEADZONE)
			right = 0.0;

		// Lower speed, tested
		if (Robot.driveSys.getLowSpeed()) {
			right /= 2;
			left /= 2;
		}

		// swap left and right, reverse left and right values, tested
		if (Robot.driveSys.getReversed()) {
			double tmp = left;
			left = right;
			right = tmp;

			left = -left;
			right = -right;
		}

		if (debug) {
			System.out.println("[DriveCommand] left: " + left + "\tright: "
					+ right);
		}

		Robot.driveSys.setDriveMotors(left, right);
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

	@Override
	protected void end() {
		if (debug) {
			System.out.println("[DriveCommand] GAME OVER MAN! GAME OVER!");
		}

		Robot.driveSys.setDriveMotors(0);
	}

	@Override
	protected void interrupted() {
		if (debug) {
			System.out.println("[DriveCommand] Interruptions aren't fun.");
		}
		Robot.driveSys.setDriveMotors(0);
	}
}
