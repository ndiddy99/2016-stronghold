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
	final double tolerance = 5;
	private final static double DEFAULT_TIMEOUT = 5;
	Double speed;

	/**
	 * Constructor that sets what angle to move to & command timeout
	 * 
	 * @param angle
	 *            A double to set what angle to move the arm to in encoder ticks
	 * 
	 * @param timeout
	 *            How long the command should be allowed to run for.
	 */
	public PresetArmCommand(double angle, double timeout) {
		super(timeout);
		requires(Robot.armSys);
		angleToMoveTo = angle;
	}

	/**
	 * Constructor that sets what angle to move to. Uses the default timeout of
	 * 5 seconds
	 * 
	 * @param angle
	 *            A double to set what angle to move the arm to in encoder ticks
	 */
	public PresetArmCommand(double angle) {
		this(angle, DEFAULT_TIMEOUT);
	}

	protected void initialize() {
		Robot.armSys.positionMode();
		Robot.armSys.enable();
		if (ArmSubsystem.debug)
			System.out.println("Moving arm!");
		if (ArmSubsystem.debug)
			System.out.println("Control mode: " + Robot.armSys.armMotor.getControlMode());
		if (ArmSubsystem.debug)
			System.out.println("Angle to move to: " + angleToMoveTo);
		if (ArmSubsystem.debug)
			System.out.println("Current angle: " + currentAngle);
		if (ArmSubsystem.debug)
			System.out.println("Ticks to move: " + (angleToMoveTo - currentAngle));
		if (ArmSubsystem.debug)
			System.out.println("Rotations to move: " + (angleToMoveTo) / 1000);
		Robot.armSys.setArmTalon(angleToMoveTo / 1000);
	}

	protected void execute() {
		currentAngle = Robot.armSys.getAngle();
	}

	protected boolean isFinished() {
		if ((currentAngle >= angleToMoveTo - tolerance && angleToMoveTo + tolerance >= currentAngle) || isTimedOut()) {
			return true;
		}
		return false;
	}

	protected void end() {
	}

	protected void interrupted() {
		if (ArmSubsystem.debug)
			System.out.println("[armSys] I've been interrupted!");
	}

}
