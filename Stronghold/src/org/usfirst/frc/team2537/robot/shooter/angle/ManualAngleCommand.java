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
	private static final double JOYSTICK_FACTOR_UP = .5;
	private static final double JOYSTICK_FACTOR_DOWN = .4;
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
			System.out.println(speed);
				if(speed > 0) {
					System.out.println("Going Up Tele");
					Robot.shooterAngleSys.setSetpoint(.1);
				} else {
					System.out.println("Going Down Tele");
					Robot.shooterAngleSys.setSetpoint(-.1);
				}
			}
		//		} 
		speed = Robot.shooterAngleSys.getJoystickAngle();
		
	
	}

	@Override
	protected boolean isFinished() {
		return (Math.abs(speed) < JOYSTICK_DEADZONE);
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