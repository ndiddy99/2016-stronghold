package org.usfirst.frc.team2537.robot.arm;

import org.usfirst.frc.team2537.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Command that moves the arm to a preset position
 * 
 * @author Alex Taber
 *
 */
public class PresetArmCommand extends Command {

	double angleToMoveTo;
	double currentAngle;
	boolean move;
	final double tolerance = 5;
	Double speed;
	/**
	 * Constructor that sets what angle to move to
	 * 
	 * @param	angle	A double to set what angle to move the arm to
	 */
	public PresetArmCommand(double angle) {
		requires(Robot.armSys); 
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
			if (currentAngle < angleToMoveTo) {
				speed = (.25);
			} else {
				speed = (-.25);
			}
			Robot.armSys.setArmTalon(speed);
		}

	}

	protected boolean isFinished() {
		if ((currentAngle >= angleToMoveTo - tolerance && angleToMoveTo + tolerance >= currentAngle) || (!move)) {
			return true;
		}
		return false;
	}

	protected void end() {
		Robot.armSys.setArmTalon(0);
	}

	protected void interrupted() {
		if (ArmSubsystem.debug) System.out.println("[armSys] I've been interrupted!");
		Robot.armSys.setArmTalon(0);
	}

}
