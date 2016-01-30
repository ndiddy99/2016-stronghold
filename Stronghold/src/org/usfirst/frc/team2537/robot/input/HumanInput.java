package org.usfirst.frc.team2537.robot.input;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team2537.robot.input.XboxButtons;

public class HumanInput {
	public static final int
		XBOX_LEFT_STICK_X_AXIS = 0,
		XBOX_LEFT_STICK_Y_AXIS = 1,
		XBOX_LEFT_TRIGGER_AXIS = 2,
		XBOX_RIGHT_TRIGGER_AXIS = 3,
		XBOX_RIGHT_STICK_X_AXIS = 4,
		XBOX_RIGHT_STICK_Y_AXIS = 5,
		XBOX_DIRECTIONAL_PAD = 6;
	
	public static Joystick xboxController = new Joystick(Ports.XBOX);
	
	//replace the following 0s with actual numbers once you test them
	public static Button lowerArm = new JoystickButton(xboxController, XboxButtons.XBOX_A);
	public static Button raiseArm = new JoystickButton(xboxController, XboxButtons.XBOX_Y);
	public static Button neutralArm = new JoystickButton(xboxController, XboxButtons.XBOX_B);
	//Shooter things
	public static final JoystickButton ballShootTrigger = new XboxTriggerRight(xboxController, XboxButtons.XBOX_TRIGGERS);

	public static final JoystickButton harvestBallTrigger = new XboxTriggerLeft(xboxController, XboxButtons.XBOX_TRIGGERS);
	
	public static void registerPressedCommand(Button b, Command c) {
		 b.whenPressed(c);
	}
	
	public static double getXboxAxis(Joystick j, int i) {
		return j.getRawAxis(i);
	}
}