package org.usfirst.frc.team2537.robot.arm;

import org.usfirst.frc.team2537.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class CalibrationCommand extends Command {
	
	private static final int TIMEOUT = 5;
	
	public CalibrationCommand() {
		super(TIMEOUT);
		this.requires(Robot.armSys);
	}

	@Override
	protected void initialize() {
	}

	@Override
	protected void execute() {
		if (!Robot.armSys.armMotor.isForwardSoftLimitEnabled())
			Robot.armSys.armMotor.set(.2);
	}

	@Override
	protected boolean isFinished() {
		return (Robot.armSys.armMotor.isFwdLimitSwitchClosed() || isTimedOut());
	}

	@Override
	protected void end() {
		Robot.armSys.armMotor.set(0);
		Robot.armSys.armMotor.setEncPosition(0);
	}

	@Override
	protected void interrupted() {
		Robot.armSys.armMotor.set(0);
	}

}
