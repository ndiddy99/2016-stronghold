package org.usfirst.frc.team2537.robot.arm;

import org.usfirst.frc.team2537.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class PresetArm extends Command {

	double posToMoveTo;
	double currentPosition;
	boolean move;

	public PresetArm(double position) {
		posToMoveTo = position;
	}

	protected void initialize() {
		currentPosition = Robot.armSys.getAngle();
		System.out.println("Moving arm!");
		if (currentPosition <= posToMoveTo + 5 && currentPosition >= posToMoveTo - 5) {
			System.out.println("ARM ALREADY IN POSITION");
			move = false;
		} else {
			move = true;
		}
	}

	protected void execute() {
		currentPosition = Robot.armSys.getAngle();
		if (move = true) {
			Double speed = (currentPosition - posToMoveTo) / 180;
			Robot.armSys.set(speed);
		}

	}

	protected boolean isFinished() {
		if (currentPosition >= posToMoveTo - 5 && posToMoveTo + 5 >= currentPosition) {
			return true;
		}
		return false;
	}

	protected void end() {
		Robot.armSys.set(0);
	}

	protected void interrupted() {
		System.out.println("[armSys] I've been interrupted!");
		Robot.armSys.set(0);
	}

}
