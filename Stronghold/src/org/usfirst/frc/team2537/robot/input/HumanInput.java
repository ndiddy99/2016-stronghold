package org.usfirst.frc.team2537.robot.input;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Joystick.AxisType;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team2537.robot.input.XBoxButtons;

public class HumanInput {
	public static final int XBOX_LEFT_STICK_X_AXIS = 0,
			XBOX_LEFT_STICK_Y_AXIS = 1, XBOX_LEFT_TRIGGER_AXIS = 2,
			XBOX_RIGHT_TRIGGER_AXIS = 3, XBOX_RIGHT_STICK_X_AXIS = 4,
			XBOX_RIGHT_STICK_Y_AXIS = 5, XBOX_DIRECTIONAL_PAD = 6;

	public static Joystick xboxController = new Joystick(Ports.XBOX);
	public static Joystick leftJoystick = new Joystick(Ports.JOYSTICK_ONE_PORT);
	public static Joystick rightJoystick = new Joystick(Ports.JOYSTICK_TWO_PORT);

	public static Button driveStraight = new JoystickButton(rightJoystick, 0);
	public static Button driveSensetivityToggle = new JoystickButton(xboxController, 0);
	public static Button lowerArm = new JoystickButton(xboxController, XBoxButtons.XBOX_A);
	public static Button raiseArm = new JoystickButton(xboxController, XBoxButtons.XBOX_Y);
	public static Button neutralArm = new JoystickButton(xboxController, XBoxButtons.XBOX_B);

	public static void registerPressedCommand(Button b, Command c) {
		b.whenPressed(c);
	}
	
	public static void registerReleasedCommand(Button b, Command c) {
		b.whenReleased(c);
	}

	public static double getXboxAxis(Joystick j, int i) {
		return j.getRawAxis(i);
	}
	
	public static boolean getLeftTriggerState() {
		if (getXboxAxis(xboxController, XBoxButtons.XBOX_TRIGGERS) > 0.5) {
			return true;
		} else {
			return false;
		}
	}
	
	public static boolean getRightTriggerState() {
		if (getXboxAxis(xboxController, XBoxButtons.XBOX_TRIGGERS) < -0.5) {
			return true;
		} else {
			return false;
		}
	}
	public static double getJoystickAxis(Joystick j, AxisType ax) {
		return j.getAxis(ax);
	}
	
}
