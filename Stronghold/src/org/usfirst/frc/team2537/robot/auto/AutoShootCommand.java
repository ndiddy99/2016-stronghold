package org.usfirst.frc.team2537.robot.auto;

import edu.wpi.first.wpilibj.command.Command;

public class AutoShootCommand extends Command {
	private static final boolean debug = true;
	
	/**
	 * Autonomous shot
	 */
	public AutoShootCommand() {

	}

	@Override
	protected void initialize() {
		if(debug) System.out.println("[AutoShootCommand] キタ━━━(゜∀゜)━━━!!!!! ");
	}

	@Override
	protected void execute() {
		if(debug) System.out.println("[AutoShootCommand] ヽ(^o^)丿");
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

	@Override
	protected void end() {
		if(debug) System.out.println("[AutoShootCommand] <コ:彡");
	}

	@Override
	protected void interrupted() {
		if(debug) System.out.println("[AutoShootCommand] (╯°□°）╯︵ ┻━┻");
	}

}
