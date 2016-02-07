package org.usfirst.frc.team2537.robot.shooter.flywheel;

import org.usfirst.frc.team2537.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class EjectMotorCommand extends Command {
	private double tolerance = .05;
	private double speed = -.5;
	public EjectMotorCommand(double speed) {
		
	}
	@Override
	protected void initialize() {
		Robot.newActuatorSys.setMotorPower(speed);
		
	}

	@Override
	protected void execute() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected boolean isFinished() {
		if(Robot.newActuatorSys.getMotorPower() <0) {
			return Robot.newActuatorSys.getMotorPower() >= speed - tolerance;
		} else {
			return  Robot.newActuatorSys.getMotorPower() <= speed + tolerance;
		}
	
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
