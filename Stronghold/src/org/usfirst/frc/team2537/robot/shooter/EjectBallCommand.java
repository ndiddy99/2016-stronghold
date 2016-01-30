package org.usfirst.frc.team2537.robot.shooter;

import org.usfirst.frc.team2537.robot.shooter.flywheel.ShooterSubsystem;

import edu.wpi.first.wpilibj.command.Command;

public class EjectBallCommand extends Command {
	private static final float WAIT_TIME = 100;
	private final float START_TIME =System.currentTimeMillis();
	private boolean finished = false;
	public EjectBallCommand() {
		
	}

	@Override
	protected void end() {
		ShooterSubsystem.retractSolenoid();
	}

	@Override
	protected void execute() {
	finished = ((System.currentTimeMillis() - START_TIME) >= WAIT_TIME); 	

	}

	@Override
	protected void initialize() {
		ShooterSubsystem.actuateSolenoid();

	}

	@Override
	protected void interrupted() {
		ShooterSubsystem.retractSolenoid();

	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return finished;
	}

}
