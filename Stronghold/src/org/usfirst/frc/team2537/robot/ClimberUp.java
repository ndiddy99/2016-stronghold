package org.usfirst.frc.team2537.robot;

import edu.wpi.first.wpilibj.command.Command;

public class ClimberUp extends Command {
	public ClimberUp(){
		requires(Robot.Climb);
		requires(Robot.driveSys);
	}

	@Override
	protected void end() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void execute() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void initialize() {
		// TODO Auto-generated method stub
		Robot.Climb.extend();
		Robot.motorSys.set(Robot.motorSys.leftFront, 1);
		Robot.motorSys.set(Robot.motorSys.leftBack, 1);
		Robot.motorSys.set(Robot.motorSys.rightFront, 1);
		Robot.motorSys.set(Robot.motorSys.rightBack, 1);
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Robot.motorSys.set(Robot.motorSys.leftFront, 0);
		Robot.motorSys.set(Robot.motorSys.leftBack, 0);
		Robot.motorSys.set(Robot.motorSys.rightFront,0);
		Robot.motorSys.set(Robot.motorSys.rightBack, 0);
		Robot.Climb.retract();
	}

	@Override
	protected void interrupted() {
		// TODO Auto-generated method stub
		end();
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

}
