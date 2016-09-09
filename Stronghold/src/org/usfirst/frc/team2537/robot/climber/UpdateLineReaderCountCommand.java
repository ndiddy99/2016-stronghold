package org.usfirst.frc.team2537.robot.climber;

import org.usfirst.frc.team2537.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;

//this class name is what you get when you enforce naming conventions :^)
//TODO: add this class to the scheduler
public class UpdateLineReaderCountCommand extends Command {
	int counter;
	@Override
	protected void initialize() {
		// TODO Auto-generated method stub
		counter=0;
	}

	@Override
	protected void execute() {
		counter++;
		if (counter % 8 == 0) {
			if (Robot.climberSys.isLimitPressed()) {
				Robot.climberSys.resetEncPos();
			}
		}
		// TODO Auto-generated method stub
		Robot.climberSys.getLineRead();
		// if
		// (Robot.climberSys.getEncPos()>Robot.climberSys.EXTEND_LIMIT_CLIMBER)
		// {
		// System.out.println("soft extend limit reached. Stopping...");
		// Robot.climberSys.stop();
		// }
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	protected void end() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void interrupted() {
		// TODO Auto-generated method stub

	}
}
