package org.usfirst.frc.team2537.robot;

import edu.wpi.first.wpilibj.Joystick;

public class HumanInput {
	public static final int
			XBOX_RIGHT_STICK_X_AXIS = 4,
			XBOX_LEFT_STICK_Y_AXIS = 1,
			XBOX_RIGHT_STICK_Y_AXIS = 5;
	public static Joystick xboxController = new Joystick(0);
	
	public static double getJoystickInput(Joystick j, int i) {
		return j.getRawAxis(i);
	}
	
	
}
