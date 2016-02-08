package org.usfirst.frc.team2537.robot.shooter;

import org.usfirst.frc.team2537.robot.Robot;
import org.usfirst.frc.team2537.robot.shooter.flywheel.EjectMotorCommand;
import org.usfirst.frc.team2537.robot.shooter.flywheel.FlywheelCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.Scheduler;

public class OLDShooterCommandGroup extends CommandGroup {
	private final static double DEFAULT_SHOOT_SPEED = .5*1023;
	private final static int WAIT_AFTER_EJECT = 1000; //wait time after ball ejects in mills
	float startWaitTime = 0;
	public OLDShooterCommandGroup(){
		this(DEFAULT_SHOOT_SPEED);
	}
	public OLDShooterCommandGroup(double speed){
		addSequential(new FlywheelCommand(speed));
		addSequential(new EjectMotorCommand(-.5*1023));
		startWaitTime = System.currentTimeMillis();
		System.out.println("Right Shoot Speed:" +Robot.shooterFlywheelSys.getRightSpeed());
		System.out.println("Left shoot Speed:" +Robot.shooterFlywheelSys.getLeftSpeed());
		
	}
	@Override
	protected boolean isFinished() {
		if(!Robot.shooterFlywheelSys.isBall() && System.currentTimeMillis() - startWaitTime >= WAIT_AFTER_EJECT) {
			return true;
		}
		return false;
	}
	@Override
	protected void end() {
		Scheduler.getInstance().add(new FlywheelCommand(0.0));
	}
	@Override 
	protected void interrupted() {
		Robot.shooterFlywheelSys.setLeftSpeed(0.0);
		Robot.shooterFlywheelSys.setRightSpeed(0.0);
	}
}
