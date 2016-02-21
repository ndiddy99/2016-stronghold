package org.usfirst.frc.team2537.robot.input;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Joystick.AxisType;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.Command;

/**
 * Class that contains all the button and joystick declarations.
 * 
 * Also contains methods necessary to get joystick and button presses.
 * 
 * @author Alex Taber
 *
 */
public class HumanInput {
	public static final Joystick xboxController	= new Joystick(Ports.XBOX);
	public static final Joystick leftJoystick		= new Joystick(Ports.JOYSTICK_LEFT_PORT);
	public static final Joystick rightJoystick	= new Joystick(Ports.JOYSTICK_RIGHT_PORT);
	
	public static final Button portcullisButton	= new JoystickButton(xboxController, XboxButtons.XBOX_A);
	public static final Button raiseArm			= new JoystickButton(xboxController, XboxButtons.XBOX_Y);
	public static final Button chevalButton		= new JoystickButton(xboxController, XboxButtons.XBOX_B);
	//Shooter things
	public static final Button shootCancelButton= new JoystickButton(xboxController, XboxButtons.XBOX_RB);
	
	public static final JoystickButton ballShootTrigger = 
			new XboxTrigger(xboxController, XboxButtons.XBOX_RIGHT_TRIGGERS);
	public static final JoystickButton harvestBallTrigger = 
			new XboxTrigger(xboxController, XboxButtons.XBOX_LEFT_TRIGGERS);
	
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
	
	/**
	 * register a command for when the button is released
	 * 
	 * @param b
	 *            the button to be registered found as a static Button in the
	 *            HumanInput class.
	 * @param cmd
	 *            the user provided Command to be registered
	 */
	public static void registerWhenReleasedCommand(Button b, Command cmd) {
		b.whenReleased(cmd);
	}

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
	
	/**
	 * Toggles the command whenever the button is pressed (on then off then on)
	 *
	 * @param b 	The button to register to.
	 * @param cmd 	The command to be linked.
	 */
	public static void toggleWhenPressed(Button b, Command cmd) {
		b.toggleWhenPressed(cmd);
	}
	
	/**
	 * Cancels the command whenever the button is pressed.
	 * @param b
	 * @param cmd
	 */
	public static void cancelWhenPressed(Button b, Command cmd) {
		b.cancelWhenPressed(cmd);
	}

	/**
	 * Method to get the value of a non-Xbox joystick
	 * 
	 * @param joystick
	 * @param ax
	 * @return
	 */
	public static double getJoystickAxis(Joystick joystick, AxisType ax) {
		return joystick.getAxis(ax);
	}

}
