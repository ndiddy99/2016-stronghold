package org.usfirst.frc.team2537.robot;

import edu.wpi.first.wpilibj.command.Command;

public class TalonCommand extends Command {
	public TalonCommand() {
		requires(Robot.driveSys);
	}

	@Override
	protected void end() {
		Robot.driveSys.set(Robot.driveSys.rightBack, 0);
		Robot.driveSys.set(Robot.driveSys.rightFront, 0);
		Robot.driveSys.set(Robot.driveSys.leftBack, 0);
		Robot.driveSys.set(Robot.driveSys.leftFront, 0);
	}

	@Override
	protected void execute() {
		double yAxisValLeft = HumanInput.getJoystickInput(HumanInput.xboxController, HumanInput.XBOX_LEFT_STICK_Y_AXIS) * -1;
		double yAxisValRight = HumanInput.getJoystickInput(HumanInput.xboxController, HumanInput.XBOX_RIGHT_STICK_Y_AXIS);
		Robot.driveSys.set(Robot.driveSys.leftFront, yAxisValLeft);
		Robot.driveSys.set(Robot.driveSys.leftBack, yAxisValLeft);
		Robot.driveSys.set(Robot.driveSys.rightFront, yAxisValRight);
		Robot.driveSys.set(Robot.driveSys.rightBack, yAxisValRight);
	}

	@Override
	protected void initialize() {
	}

	@Override
	protected void interrupted() {
		Robot.driveSys.set(Robot.driveSys.rightBack, 0);
		Robot.driveSys.set(Robot.driveSys.rightFront, 0);
		Robot.driveSys.set(Robot.driveSys.leftBack, 0);
		Robot.driveSys.set(Robot.driveSys.leftFront, 0);
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

}
