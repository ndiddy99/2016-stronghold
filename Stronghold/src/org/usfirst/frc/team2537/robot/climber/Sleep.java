package org.usfirst.frc.team2537.robot.climber;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.Timer;

public class Sleep extends Command {
	Timer timer=new Timer();
	private double secs;
	private long startTime;
	public Sleep(double sec){
		secs= sec;
	}

	@Override
	protected void initialize() {
		// TODO Auto-generated method stub
		startTime=System.currentTimeMillis();
		timer.delay(secs);
	}

	@Override
	protected void execute() {
		System.out.println(System.currentTimeMillis()-startTime);
		// TODO Auto-generated method stub
		
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
