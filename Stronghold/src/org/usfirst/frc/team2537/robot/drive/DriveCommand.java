package org.usfirst.frc.team2537.robot.drive;

import org.usfirst.frc.team2537.robot.Robot;
import org.usfirst.frc.team2537.robot.input.HumanInput;

import edu.wpi.first.wpilibj.Joystick.AxisType;
//import edu.wpi.first.wpilibj.CANTalon;
//import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class DriveCommand extends Command {
	private static final boolean debug = false;
	private static final double DEADZONE = 0.05;
	
	/**
	 * Takes joystick input depending on Robot.driveSys.driveType
	 * Omnipotenet drive command. Does EVERYTHING
	 * Why have multiple drive commands when you can have one?
	 * 
	 */
	public DriveCommand() {
		requires(Robot.driveSys);
	}

	@Override
	protected void initialize() {
		if(debug) System.out.println("[DriveCommand] Initializing... drivetype: " + Robot.driveSys.driveType);
	}

	@Override
	protected void execute() {
		Double left = null;
		Double right = null;
		
		switch(Robot.driveSys.driveType){
		case doubleJoystick:
			left = HumanInput.getJoystickAxis(HumanInput.leftJoystick,
					AxisType.kY);
			right = HumanInput.getJoystickAxis(HumanInput.rightJoystick,
					AxisType.kY);
			break;
		case singleJoystick:
			left = HumanInput.getJoystickAxis(HumanInput.leftJoystick,
					AxisType.kY);
			right = left;
			
			left += HumanInput.getJoystickAxis(HumanInput.leftJoystick, AxisType.kX);
			right -= HumanInput.getJoystickAxis(HumanInput.leftJoystick, AxisType.kX);
			break;
		case doubleJoystickXbox:
			left = HumanInput.getXboxAxis(HumanInput.xboxController, HumanInput.XBOX_LEFT_STICK_Y_AXIS);
			right = HumanInput.getXboxAxis(HumanInput.xboxController, HumanInput.XBOX_RIGHT_STICK_Y_AXIS);
			break;
		case singleJoystickXbox:
			left = HumanInput.getXboxAxis(HumanInput.xboxController, HumanInput.XBOX_LEFT_STICK_Y_AXIS);
			right = left;
			
			left += HumanInput.getJoystickAxis(HumanInput.leftJoystick, AxisType.kX);
			right -= HumanInput.getJoystickAxis(HumanInput.leftJoystick, AxisType.kX);			
			break;
		}
		
		if (left == null || right == null) {
			if(debug) System.out.println("[DriveCommand] left/right is null");
			return;
		}
		
		if(Robot.driveSys.drivingStraight){
			if(Math.abs(left) > Math.abs(right)){
				right = left;
			}else{
				left = right;
			}
		}
		
		if(Math.abs(left) < DEADZONE) left = 0.0;
		if(Math.abs(right) < DEADZONE) right = 0.0;
		
		if(Robot.driveSys.driveLowerSpeed){
			right /= 2;
			left /= 2;
		}
		
		if(debug) System.out.println("[DriveCommand] left: " + left + "\tright: " + right);
		Robot.driveSys.setDriveMotors(left, right);
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

	@Override
	protected void end(){
		if(debug) System.out.println("[DriveCommand] It's over");
		Robot.driveSys.setDriveMotors(0);
	}

	@Override
	protected void interrupted() {
		if(debug) System.out.println("[DriveCommand] Interruptions aren't fun.");
		Robot.driveSys.setDriveMotors(0);
	}
}
