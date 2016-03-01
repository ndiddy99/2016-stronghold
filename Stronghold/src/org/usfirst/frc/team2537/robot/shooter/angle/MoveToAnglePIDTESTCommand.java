package org.usfirst.frc.team2537.robot.shooter.angle;

import org.usfirst.frc.team2537.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class MoveToAnglePIDTESTCommand extends Command {
	
	private double angle;
	
	public MoveToAnglePIDTESTCommand(double angle){
		this.angle = angle;
		requires(Robot.shooterAngleSys);
		
	}

	@Override
	protected void initialize() {
		// TODO Auto-generated method stub
		System.out.println("MoveToAngle: " + angle);
		Robot.shooterAngleSys.setAngle(angle);
		

	}

	@Override
	protected void execute() {
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
