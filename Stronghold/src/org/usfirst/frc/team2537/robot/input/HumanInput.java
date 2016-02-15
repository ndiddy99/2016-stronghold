package org.usfirst.frc.team2537.robot.input;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Joystick.AxisType;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team2537.robot.input.XBoxButtons;

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
//	public static Joystick leftJoystick = new Joystick(Ports.JOYSTICK_ONE_PORT);
//	public static Joystick rightJoystick = new Joystick(Ports.JOYSTICK_TWO_PORT);

	// replace the following 0s with actual numbers once you test them
	public static Button lowerArm = new JoystickButton(xboxController, XBoxButtons.XBOX_A);
	public static Button raiseArm = new JoystickButton(xboxController, XBoxButtons.XBOX_Y);
	public static Button chevalButton = new JoystickButton(xboxController, XBoxButtons.XBOX_B);
	public static final JoystickButton ballShootTrigger = new XboxTrigger(xboxController,
			XBoxButtons.XBOX_RIGHT_TRIGGERS);

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
	}
	
	/**
	 * Method to get the value of an XBox joystick
	 * 
	 * @param	joystick	Joystick, most likely HumanInput.xboxController
	 * @param	axis		Axis from the XBoxButtons.java class
	 * @return
	 */
	public static double getXboxAxis(Joystick joystick, int axis) {
		return joystick.getRawAxis(axis);
	}

	/**
	 * Method to get the value of a non-XBox joystick
	 * 
	 * @param	joystick
	 * @param ax
	 * @return
	 */
	public static double getJoystickAxis(Joystick joystick, AxisType ax) {
		return joystick.getAxis(ax);
	}

}
