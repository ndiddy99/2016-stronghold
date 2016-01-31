package org.usfirst.frc.team2537.robot.input;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team2537.robot.input.XBoxButtons;

public class HumanInput {
	
	public static Joystick xboxController = new Joystick(Ports.XBOX);
	
	//replace the following 0s with actual numbers once you test them
	public static Button lowerArm = new JoystickButton(xboxController, XBoxButtons.XBOX_A);
	public static Button raiseArm = new JoystickButton(xboxController, XBoxButtons.XBOX_Y);
	public static Button neutralArm = new JoystickButton(xboxController, XBoxButtons.XBOX_B);
	public static final JoystickButton ballShootTrigger = new XboxTrigger(xboxController, XBoxButtons.XBOX_RIGHT_TRIGGERS);

	public static final JoystickButton harvestBallTrigger = new XboxTrigger(xboxController, XBoxButtons.XBOX_LEFT_TRIGGERS);
	
	public static void registerPressedCommand(Button b, Command c) {
		 b.whenPressed(c);
	}
	
	public static double getXboxAxis(Joystick j, int i) {
		return j.getRawAxis(i);
	}
}