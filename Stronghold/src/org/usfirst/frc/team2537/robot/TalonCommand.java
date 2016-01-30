package org.usfirst.frc.team2537.robot;

import edu.wpi.first.wpilibj.command.Command;

public class TalonCommand extends Command {
	public TalonCommand() {
		requires(Robot.driveSys);
	}

	@Override
	protected void end() {
		Robot.motorSys.set(Robot.motorSys.rightBack, 0);
		Robot.motorSys.set(Robot.motorSys.rightFront, 0);
		Robot.motorSys.set(Robot.motorSys.leftBack, 0);
		Robot.motorSys.set(Robot.motorSys.leftFront, 0);
	}

	@Override
	protected void execute() {
		double yAxisValLeft = HumanInput.getJoystickInput(HumanInput.xboxController, HumanInput.XBOX_LEFT_STICK_Y_AXIS) * -1;
		double yAxisValRight = HumanInput.getJoystickInput(HumanInput.xboxController, HumanInput.XBOX_RIGHT_STICK_Y_AXIS);
		Robot.motorSys.set(Robot.motorSys.leftFront, yAxisValLeft);
		Robot.motorSys.set(Robot.motorSys.leftBack, yAxisValLeft);
		Robot.motorSys.set(Robot.motorSys.rightFront, yAxisValRight);
		Robot.motorSys.set(Robot.motorSys.rightBack, yAxisValRight);
	}

	@Override
	protected void initialize() {
	}

	@Override
	protected void interrupted() {
		Robot.motorSys.set(Robot.motorSys.rightBack, 0);
		Robot.motorSys.set(Robot.motorSys.rightFront, 0);
		Robot.motorSys.set(Robot.motorSys.leftBack, 0);
		Robot.motorSys.set(Robot.motorSys.leftFront, 0);
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

}
