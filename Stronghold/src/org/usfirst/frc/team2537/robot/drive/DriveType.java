package org.usfirst.frc.team2537.robot.drive;

public enum DriveType {
	doubleJoystick, singleJoystick, doubleJoystickXbox, singleJoystickXbox;
	
	private DriveType next;
	
	static{
		doubleJoystick.next = singleJoystick;
		singleJoystick.next = doubleJoystickXbox;
		doubleJoystickXbox.next = singleJoystickXbox;
		singleJoystickXbox.next = doubleJoystick;
	}
	
	public DriveType getNextDriveTypeInEnum(){
		return next;
	}
}
