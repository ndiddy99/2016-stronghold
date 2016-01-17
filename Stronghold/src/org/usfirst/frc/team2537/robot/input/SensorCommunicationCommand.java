package org.usfirst.frc.team2537.robot.input;

import org.usfirst.frc.team2537.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class SensorCommunicationCommand extends Command {
	
	public SensorCommunicationCommand() {
		requires(Robot.sensorSys);
	}

	protected void initialize() {
	}

	protected void execute() {
		SerialCommunication.getInstance().handleEvents();
	}

	protected boolean isFinished() {
		return false;
	}

	protected void end() {
	}

	protected void interrupted() {
	}

}
