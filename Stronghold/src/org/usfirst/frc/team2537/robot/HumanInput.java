package org.usfirst.frc.team2537.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.*;

public class HumanInput {
	public static final int XBOX_LEFT_STICK_Y_AXIS = 1, XBOX_RIGHT_STICK_Y_AXIS = 5;
	public static Joystick xboxController = new Joystick(0);
	public static Button button1 = new JoystickButton(xboxController, 1);
	public static double getJoystickInput(Joystick j, int i) {
		return j.getRawAxis(i);
	}

}
