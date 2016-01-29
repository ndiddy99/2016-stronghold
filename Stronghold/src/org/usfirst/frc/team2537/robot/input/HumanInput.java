package org.usfirst.frc.team2537.robot.input;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team2537.robot.input.xBoxButtons;

public class HumanInput {
	
	public static Joystick xboxController = new Joystick(Ports.XBOX);
	
	//replace the following 0s with actual numbers once you test them
	public static Button lowerArm = new JoystickButton(xboxController, xBoxButtons.XBOX_A);
	public static Button raiseArm = new JoystickButton(xboxController, xBoxButtons.XBOX_Y);
	public static Button neutralArm = new JoystickButton(xboxController, xBoxButtons.XBOX_B);
	
	public static void registerPressedCommand(Button b, Command c) {
		 b.whenPressed(c);
	}
	
	public static double getXboxAxis(Joystick j, int i) {
		return j.getRawAxis(i);
	}
	
	public static boolean getLeftTriggerState() {
		if (getXboxAxis(xboxController, xBoxButtons.XBOX_TRIGGERS) > 0.5) {
			return true;
		} else {
			return false;
		}
	}
	
	public static boolean getRightTriggerState() {
		if (getXboxAxis(xboxController, xBoxButtons.XBOX_TRIGGERS) < -0.5) {
			return true;
		} else {
			return false;
		}
	}
}
