package org.usfirst.frc.team2537.robot.input;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class XboxTriggerRight extends JoystickButton {
	private int triggerTypeValue = 0;

	/**
	 * 
	 * @param joystick
	 *            to go on always xbox.
	 * @param triggerConstant
	 *            which trigger to register pulled, from human input axis.
	 */
	public XboxTriggerRight(GenericHID joystick, int triggerConstant) {
		super(joystick, 0);
		triggerTypeValue = triggerConstant;
	}

	@Override
	/**
	 * @return trigger value
	 */
	public boolean get() {

		double triggerVal = HumanInput.getXboxAxis(HumanInput.xboxController, triggerTypeValue);
		return (triggerVal <= -0.85);
	}

}