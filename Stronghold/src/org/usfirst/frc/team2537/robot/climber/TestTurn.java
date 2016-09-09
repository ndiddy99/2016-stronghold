//command that changes the angle of the tape

package org.usfirst.frc.team2537.robot.climber;

import org.usfirst.frc.team2537.robot.Robot;
import org.usfirst.frc.team2537.robot.input.HumanInput;

import java.lang.Math;
import edu.wpi.first.wpilibj.command.Command;

public class TestTurn extends Command {
	double angle;
	public TestTurn() {
		requires(Robot.climberSys);
		this.angle=0;
	}
	private long startTime;

	@Override
	protected void end() {
		// TODO Auto-generated method stub
		
		
		//Robot.climberSys.setAngle(0);
	}

	/** makes the climber turn to ANGLE FOR SERVO */
	@Override
	protected void execute() {
		// TODO Auto-generated method stub
	
		Robot.climberSys.setAngle(0);
			
		
	}

	@Override
	protected void initialize() {
		// TODO Auto-generated method stub
		startTime = System.currentTimeMillis();
	}

	@Override
	protected void interrupted() {
		// TODO Auto-generated method stub

	}

	@Override /** stawps turning the tape after 5 sec */
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return System.currentTimeMillis() > (startTime + 5000);
		
	}

}
