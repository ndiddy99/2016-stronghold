package org.usfirst.frc.team2537.robot.shooter.flywheel;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team2537.robot.Robot;

/**
 * 
 * @author Jacob Barnett
 *
 */
public class EjectBallCommand extends Command {
	//Constants
	private static final float WAIT_TIME = 50;
	private final float START_TIME =System.currentTimeMillis();
	
	private boolean finished = false;
	
	public EjectBallCommand() {
	}

	@Override
	protected void end() {
		Robot.shooterFlywheelSys.retractSolenoid();
	}

	@Override
	protected void execute() {
	finished = ((System.currentTimeMillis() - START_TIME) >= WAIT_TIME); 	

	}

	@Override
	protected void initialize() {
		Robot.shooterFlywheelSys.actuateSolenoid();

	}

	@Override
	protected void interrupted() {
		Robot.shooterFlywheelSys.retractSolenoid();

	}

	@Override
	protected boolean isFinished() {
		return finished;
	}

}
