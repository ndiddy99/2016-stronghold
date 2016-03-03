package org.usfirst.frc.team2537.robot.shooter.angle;

import org.usfirst.frc.team2537.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Let the angle be adjusted by the xbox joystick.
 * 
 * @author Matthew Schweiss
 *
 */
public class ManualAngleCommand extends Command {
	/*
	 * Adjust the sensitivity of the joystickAngle. Values less than 1 decrease
	 * the maximum speed. Values more than 1 increase the maximum speed.
	 */
	private static final double JOYSTICK_FACTOR = .5;
	private static final double JOYSTICK_DEADZONE = .15;
	private static double setPoint;
//	public static boolean shouldReleaseBrake = true;
	
	/**
	 * Create a ManualAngleCommand. There typically should only be one.
	 */
	public ManualAngleCommand() {
		requires(Robot.shooterAngleSys);
	}

	@Override
	protected void initialize() {
	}
	

	double speed = 0.0;
	@Override
	protected void execute() {
		// Get joystick values.
		speed = Robot.shooterAngleSys.getJoystickAngle();
		if(Math.abs(speed) > JOYSTICK_DEADZONE){
//			Robot.shooterAngleSys.setVoltagePercent(-speed * JOYSTICK_FACTOR);
			System.out.println(speed*JOYSTICK_FACTOR);
			Robot.shooterAngleSys.setSetpoint(Robot.shooterAngleSys.getSetpoint() + speed * JOYSTICK_FACTOR);
				if(speed < 0) {
					System.out.println("Going Down Tele");
				} else {
					System.out.println("Going Up Tele");
				}
			}
		//		} 
		speed = Robot.shooterAngleSys.getJoystickAngle();
		
	
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

	@Override
	protected void end() {
		// Well we better make sure the motors are not moving.
//		Robot.shooterAngleSys.setVoltagePercent(0);
//		shouldReleaseBrake = true;
	}

	@Override
	protected void interrupted() {
		// I was interrupted, ok.
//		if (Robot.shooterAngleSys.DEBUG)
//			System.out.println("ManualAngleCommand was interrupted!");
//		Robot.shooterAngleSys.setVoltagePercent(0);
		
//		shouldReleaseBrake = false;
	}
}