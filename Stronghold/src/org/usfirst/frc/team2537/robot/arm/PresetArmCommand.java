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
	private static final double P = 200d;
	private static final double I = 0d;
	private static final double D = 0d;
	
	private double angleToMoveTo;
	private double currentAngle;
	private final double tolerance = 10;
	private final static double DEFAULT_TIMEOUT = 5;

	/**
	 * Constructor that sets what angle to move to and command timeout
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
		
		if (ArmSubsystem.debug) {
			System.out.println("Moving arm!");
			System.out.println("Control mode: " + Robot.armSys.armMotor.getControlMode());
			System.out.println("Angle to move to: " + angleToMoveTo);
			System.out.println("Current angle: " + currentAngle);
			System.out.println("Ticks to move: " + (angleToMoveTo - currentAngle));
			System.out.println("Rotations to move: " + (angleToMoveTo) / 1000);
		}
		
		Robot.armSys.setArmPosition(angleToMoveTo / 1000, P, I, D);
	}

	protected void execute() {
		currentAngle = Robot.armSys.getAngle();
	}

	protected boolean isFinished() {
		return (currentAngle >= angleToMoveTo - tolerance && angleToMoveTo + tolerance >= currentAngle) || isTimedOut();
	}

	protected void end() {
	}

	protected void interrupted() {
		if (ArmSubsystem.debug)
			System.out.println("[armSys] I've been interrupted!");
	}

}
