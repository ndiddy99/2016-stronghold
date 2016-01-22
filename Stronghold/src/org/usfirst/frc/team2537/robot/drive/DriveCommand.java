package org.usfirst.frc.team2537.robot.drive;

import org.usfirst.frc.team2537.robot.Robot;
import org.usfirst.frc.team2537.robot.input.HumanInput;

import edu.wpi.first.wpilibj.Joystick.AxisType;
//import edu.wpi.first.wpilibj.CANTalon;
//import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class DriveCommand extends Command {

	public DriveCommand() {
		requires(Robot.driveSys);
	}

	@Override
	protected void initialize() {
		
	}

	@Override
	protected void execute() {
		Double left = null;
		Double right = null;
		
		switch(Robot.driveSys.driveType){
		case doubleJoystick:
			left = -HumanInput.getJoystickAxis(HumanInput.leftJoystick,
					AxisType.kY);
			right = HumanInput.getJoystickAxis(HumanInput.rightJoystick,
					AxisType.kY);
			break;
		case singleJoystick:
			left = -HumanInput.getJoystickAxis(HumanInput.leftJoystick,
					AxisType.kY);
			right = -left;
			
			left += HumanInput.getJoystickAxis(HumanInput.leftJoystick, AxisType.kX);
			right += HumanInput.getJoystickAxis(HumanInput.leftJoystick, AxisType.kX);
			break;
		case doubleJoystickXbox:
			left = -HumanInput.getXboxAxis(HumanInput.xboxController, HumanInput.XBOX_LEFT_STICK_Y_AXIS);
			right = HumanInput.getXboxAxis(HumanInput.xboxController, HumanInput.XBOX_RIGHT_STICK_Y_AXIS);
			break;
		case singleJoystickXbox:
			left = -HumanInput.getXboxAxis(HumanInput.xboxController, HumanInput.XBOX_LEFT_STICK_Y_AXIS);
			right = -left;
			
			left += HumanInput.getJoystickAxis(HumanInput.leftJoystick, AxisType.kX);
			right += HumanInput.getJoystickAxis(HumanInput.leftJoystick, AxisType.kX);			
			break;
		}
		
		if (left == null || right == null) {
			System.err.println("[DriveSys] Something's not right here...");
			return;
		}
		Robot.driveSys.set(left, Robot.driveSys.talonFrontLeft);
		Robot.driveSys.set(left, Robot.driveSys.talonBackLeft);
		Robot.driveSys.set(right, Robot.driveSys.talonFrontRight);
		Robot.driveSys.set(right, Robot.driveSys.talonBackRight);			

	}

	@Override
	protected boolean isFinished() {
		return false;
	}

	@Override
	protected void end(){
		Robot.driveSys.set(0, Robot.driveSys.talonFrontLeft);
		Robot.driveSys.set(0, Robot.driveSys.talonBackLeft);
		Robot.driveSys.set(0, Robot.driveSys.talonFrontRight);
		Robot.driveSys.set(0, Robot.driveSys.talonBackRight);			
	}

	@Override
	protected void interrupted() {
		System.out.println("[DriveSys] Interruptions aren't fun.");
		Robot.driveSys.set(0, Robot.driveSys.talonFrontLeft);
		Robot.driveSys.set(0, Robot.driveSys.talonBackLeft);
		Robot.driveSys.set(0, Robot.driveSys.talonFrontRight);
		Robot.driveSys.set(0, Robot.driveSys.talonBackRight);			
	}
}
