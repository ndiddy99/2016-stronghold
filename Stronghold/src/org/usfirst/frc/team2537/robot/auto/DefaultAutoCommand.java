package org.usfirst.frc.team2537.robot.auto;

import edu.wpi.first.wpilibj.command.Command;

public class DefaultAutoCommand extends Command{

	@Override
	protected void initialize() {
		System.out.println("[DefaultAuto] Initializing...");
	}

	@Override
	protected void execute() {
		
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

	@Override
	protected void end() {
		System.out.println("[DefaultAuto] Happy ending.");
	}

	@Override
	protected void interrupted() {
		System.out.println("[DefaultAuto] Bad end.");
	}

}
