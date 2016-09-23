package org.usfirst.frc.team2537.robot.input;

import org.usfirst.frc.team2537.robot.Ports;

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
	public static final Joystick xboxController = new Joystick(Ports.XBOX);
	public static final Joystick leftJoystick = new Joystick(Ports.JOYSTICK_LEFT_PORT);
	public static final Joystick rightJoystick = new Joystick(Ports.JOYSTICK_RIGHT_PORT);
	// X-Box buttons
	public static final Button changeCameraButton = new JoystickButton(xboxController, XboxButtons.XBOX_X);
	public static final JoystickButton shootPos = new JoystickButton(xboxController, XboxButtons.XBOX_Y);
	public static final Button lowBarModeEnableButton = new JoystickButton(xboxController, XboxButtons.XBOX_LB);
	public static final Button shootCancelButton = new JoystickButton(xboxController, XboxButtons.XBOX_RB);
	public static final Button climberButton = new JoystickButton(xboxController, XboxButtons.XBOX_B);
	public static final JoystickButton harvestBallTrigger = new XboxTrigger(xboxController,
			XboxButtons.XBOX_LEFT_TRIGGERS);
	public static final JoystickButton ballShootTrigger = new XboxTrigger(xboxController,
			XboxButtons.XBOX_RIGHT_TRIGGERS);
	// Left Joystick Buttons
	public static final Button driveStraight = new JoystickButton(leftJoystick, 1);
	public static final Button driveSensetivityToggle = new JoystickButton(leftJoystick, 2);
	public static final Button reverseDrive = new JoystickButton(leftJoystick, 3);
	public static final Button driveTypeToggle = new JoystickButton(leftJoystick, 4);
	//public static final Button driveAroundButton = new JoystickButton(xboxController, XboxButtons.XBOX_B);
	// Right Joystick buttons
	public static final Button changeCameraButton2 = new JoystickButton(rightJoystick, 3);

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
	public static void registerReleasedCommand(Button button, Command command) {
		button.whenReleased(command);
	}

	/**
	 * Get the value of the Xbox axis in question.
	 * 
	 * @param j
	 *            The joystick to get axis measures from.
	 * @param i
	 *            The axis you want to get values for [0-6]
	 * @param deadzone
	 *            The value the joystick should surpass to have something
	 *            returned
	 * @return A double in the range [-1,1] in the amount the lever is
	 *         depressed.
	 */
	public static double getXboxAxis(Joystick j, int i, double deadzone) {
		if (j.getRawAxis(i) < -deadzone || deadzone < j.getRawAxis(i))
			return j.getRawAxis(i);
		return 0;
	}
	
	public static double getXboxAxis(Joystick j, int i) {
		return HumanInput.getXboxAxis(j, i, 0.2);
	}

	/**
	 * Get a boolean value as to if one of the xbox triggers has been triggered.
	 * 
	 * @param j
	 *            The joystick to get values on.
	 * @param i
	 *            The axis number to get values for. If i is not a trigger, but
	 *            a joystick, any activation on the joystick will make this
	 *            return true.
	 * 
	 * @return If the trigger has been activated.
	 */
	public static boolean getXboxTrigger(Joystick j, int i) {
		return j.getRawAxis(i) != 0;
	}

	/**
	 * Method to get the value of a non-XBox joystick
	 * 
	 * @param joystick
	 *            Joystick to get the value of
	 * @param ax
	 *            Axis to get the value of
	 * @return A value between -1 and 1 representing the position of the
	 *         joystick
	 */
	public static double getJoystickAxis(Joystick joystick, AxisType ax) {
		return joystick.getAxis(ax);
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
	 * @param b
	 *            The button to register to.
	 * @param cmd
	 *            The command to be linked.
	 */
	public static void toggleWhenPressed(Button b, Command cmd) {
		b.toggleWhenPressed(cmd);
	}

	/**
	 * Cancels the command whenever the button is pressed.
	 * 
	 * @param b
	 *            The button to register to
	 * @param cmd
	 *            The command to be linked
	 */
	public static void cancelWhenPressed(Button b, Command cmd) {
		b.cancelWhenPressed(cmd);
	}
}
