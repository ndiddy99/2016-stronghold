package org.usfirst.frc.team2537.robot.input;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Joystick.AxisType;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team2537.robot.input.xBoxButtons;

public class HumanInput {

	public static Joystick xboxController = new Joystick(Ports.XBOX);
	public static Joystick leftJoystick = new Joystick(Ports.JOYSTICK_ONE_PORT);
	public static Joystick rightJoystick = new Joystick(Ports.JOYSTICK_TWO_PORT);
	

	public static Button driveStraight = new JoystickButton(leftJoystick, 0);
	public static Button driveSensetivityToggle = new JoystickButton(xboxController, 0);
	public static Button lowerArm = new JoystickButton(xboxController, xBoxButtons.XBOX_A);
	public static Button raiseArm = new JoystickButton(xboxController, xBoxButtons.XBOX_Y);
	public static Button neutralArm = new JoystickButton(xboxController, xBoxButtons.XBOX_B);
	public static Button driveTypeToggle = new JoystickButton(rightJoystick, 0);

	public static void registerPressedCommand(Button b, Command c) {
		b.whenPressed(c);
	}
	
	public static void registerReleasedCommand(Button b, Command c) {
		b.whenReleased(c);
	}

	public static double getXboxAxis(Joystick j, int i) {
		return j.getRawAxis(i);
	}
	
	public static double getJoystickAxis(Joystick j, AxisType ax) {
		return j.getAxis(ax);
	}
	
}
