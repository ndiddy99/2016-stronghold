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
	public static final JoystickButton ballShootTrigger = new XboxTrigger(xboxController, XboxButtons.XBOX_RIGHT_TRIGGERS);
	public static final JoystickButton harvestBallTrigger = new XboxTrigger(xboxController, XboxButtons.XBOX_LEFT_TRIGGERS);
	
	/**
	 * Get the value of the Xbox axis in question.
	 * @param j The joystick to get axis measures from.
	 * @param i The axis you want to get values for [0-6]
	 * @return A double in the range [-1,1] in the amount the lever is depressed.
	 */
	public static double getXboxAxis(Joystick j, int i) {
		return j.getRawAxis(i);
	}
	
	/** 
	 * Get a boolean value as to if one of the xbox triggers has been triggered.
	 * 
	 * @param j The joystick to get values on.
	 * @param i The axis number to get values for. If i is not a trigger, but a joystick, 
	 * 			any activation on the joystick will make this return true.
	 * 
	 * @return  If the trigger has been activated.
	 */
	public static boolean getXboxTrigger(Joystick j, int i){
		return j.getRawAxis(i) != 0;
	}
		
	/**
	 * register a command for when the button is pressed
	 * 
	 * @param b
	 *            the button to be registered found as a static Button in the
	 *            HumanInput class.
	 * @param cmd
	 *            the user provided Command to be registered
	 */
	//Registers
	public static void registerPressedCommand(Button b, Command cmd) {
		 b.whenPressed(cmd);
	}
	
//	/**
//	 * register a command for when the button is released
//	 * 
//	 * @param b
//	 *            the button to be registered found as a static Button in the
//	 *            HumanInput class.
//	 * @param cmd
//	 *            the user provided Command to be registered
//	 */
//	public static void registerWhenReleasedCommand(Button b, Command cmd) {
//		b.whenReleased(cmd);
//	}

	/**
	 * register a command to be run as long as the button is held
	 * 
	 * @param b
	 *            the button to be registered found as a static Button in the
	 *            HumanInput class.
	 * @param cmd
	 *            the user provided Command to be registered
	 */
	public static void registerWhileHeldCommand(Button b, Command cmd) {
		b.whileHeld(cmd);
	}
}