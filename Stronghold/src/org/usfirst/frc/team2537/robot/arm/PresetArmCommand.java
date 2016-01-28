package org.usfirst.frc.team2537.robot.arm;

import org.usfirst.frc.team2537.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class PresetArmCommand extends Command {

	double angleToMoveTo;
	double currentAngle;
	boolean move;
	final double tolerance = 5;

	public PresetArmCommand(double angle) {
		this.requires(Robot.armSys); 
		angleToMoveTo = angle;
	}

	protected void initialize() {
		currentAngle = Robot.armSys.getAngle();
		if (ArmSubsystem.debug) System.out.println("Moving arm!");
		if (currentAngle <= angleToMoveTo + tolerance && currentAngle >= angleToMoveTo - tolerance) {
			if (ArmSubsystem.debug) System.out.println("ARM ALREADY IN POSITION");
			move = false;
		} else {
			move = true;
		}
	}

	protected void execute() { 
		currentAngle = Robot.armSys.getAngle();
		if (move) {
			Double speed = (currentAngle - angleToMoveTo) / 180;
			Robot.armSys.setArmTalonSpeed(speed);
		}

	}

	protected boolean isFinished() {
		if ((currentAngle >= angleToMoveTo - tolerance && angleToMoveTo + tolerance >= currentAngle) || (!move)) {
			return true;
		}
		return false;
	}

	protected void end() {
		Robot.armSys.setArmTalonSpeed(0);
	}

	protected void interrupted() {
		if (ArmSubsystem.debug) System.out.println("[armSys] I've been interrupted!");
		Robot.armSys.setArmTalonSpeed(0);
	}

}
