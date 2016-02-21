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
	public static Joystick xboxController = new Joystick(Ports.XBOX);
	public static Joystick leftJoystick = new Joystick(Ports.JOYSTICK_ONE_PORT);
	public static Joystick rightJoystick = new Joystick(Ports.JOYSTICK_TWO_PORT);

	// replace the following 0s with actual numbers once you test them
	public static Button aButton = new JoystickButton(xboxController, XBoxButtons.XBOX_A);
	public static Button yButton = new JoystickButton(xboxController, XBoxButtons.XBOX_Y);
	public static Button bButton = new JoystickButton(xboxController, XBoxButtons.XBOX_B);
	public static Button lBumper = new JoystickButton(xboxController, XBoxButtons.XBOX_LB);
	public static final JoystickButton ballShootTrigger = new XboxTrigger(xboxController,
			XBoxButtons.XBOX_RIGHT_TRIGGERS);
	public static Button driveStraight = new JoystickButton(leftJoystick, 0);
	public static Button driveSensetivityToggle = new JoystickButton(xboxController, 0);
	public static Button reverseDrive = new JoystickButton(leftJoystick, 6);
	public static Button lowerArm = new JoystickButton(xboxController, XBoxButtons.XBOX_A);
	public static Button raiseArm = new JoystickButton(xboxController, XBoxButtons.XBOX_Y);
	public static Button neutralArm = new JoystickButton(xboxController, XBoxButtons.XBOX_B);
	public static Button driveTypeToggle = new JoystickButton(rightJoystick, 0);
	public static final JoystickButton harvestBallTrigger = new XboxTrigger(xboxController,
			XBoxButtons.XBOX_LEFT_TRIGGERS);

	/**
	 * Method to register a pressed button command. When you use this method it
	 * will set the button to trigger a command when pressed down
	 * 
	 * @param button
	 *            Button to register command to
	 * @param command
	 *            Command to register to button
	 */
	public static void registerPressedCommand(Button button, Command command) {
		button.whenPressed(command);
	}

	/**
	 * Method to register a released button command. When you use this method it
	 * will set the button to trigger a command when released
	 * 
	 * @param button
	 *            Button to register command to
	 * @param command
	 *            Command to register to button
	 */
	public static void registerReleasedCommand(Button b, Command c) {
		b.whenReleased(c);
	public static Joystick xboxController	= new Joystick(Ports.XBOX);
	public static Joystick leftJoystick		= new Joystick(Ports.JOYSTICK_LEFT_PORT);
	public static Joystick rightJoystick	= new Joystick(Ports.JOYSTICK_RIGHT_PORT);
	
	public static Button portcullisButton	= new JoystickButton(xboxController, XboxButtons.XBOX_A);
	public static Button raiseArm			= new JoystickButton(xboxController, XboxButtons.XBOX_Y);
	public static Button chevalButton		= new JoystickButton(xboxController, XboxButtons.XBOX_B);
	//Shooter things
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
	 * Method to get the value of an XBox joystick
	 * 
	 * @param joystick
	 *            Joystick, most likely HumanInput.xboxController
	 * @param axis
	 *            Axis from the XBoxButtons.java class
	 * @return
	 */
	public static double getXboxAxis(Joystick joystick, int axis) {
		return joystick.getRawAxis(axis);
	
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
	 * Method to get the value of a non-XBox joystick
	 * 
	 * @param joystick
	 * @param ax
	 * @return
	 */
	public static double getJoystickAxis(Joystick joystick, AxisType ax) {
		return joystick.getAxis(ax);
	
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
