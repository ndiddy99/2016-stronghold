package org.usfirst.frc.team2537.robot.shooter.flywheel;

import edu.wpi.first.wpilibj.command.Command;

public class EjectBallCommand extends Command {
	private static final float WAIT_TIME = 100;
	private final float START_TIME =System.currentTimeMillis();
	private boolean finished = false;
	public EjectBallCommand() {
		
	}

	@Override
	protected void end() {
		FlywheelSubsystem.retractSolenoid();
	}

	@Override
	protected void execute() {
	finished = ((System.currentTimeMillis() - START_TIME) >= WAIT_TIME); 	

	}

	@Override
	protected void initialize() {
		FlywheelSubsystem.actuateSolenoid();

	}

	@Override
	protected void interrupted() {
		FlywheelSubsystem.retractSolenoid();

	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return finished;
	}

}
