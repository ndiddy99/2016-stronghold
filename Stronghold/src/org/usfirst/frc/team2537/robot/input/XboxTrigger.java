package org.usfirst.frc.team2537.robot.input;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class XboxTrigger extends JoystickButton {
	private int triggerTypeValue = 0;

	/**
	 * @param joystick
	 *            Which joystick to use. Should be the xbox joystick.
	 * @param trigger
	 *            Which trigger, either the left or right trigger. Get from
	 *            xboxbuttons.
	 */
	public XboxTrigger(GenericHID joystick, int trigger) {
		super(joystick, 0);
		triggerTypeValue = trigger;
	}

	@Override
	/**
	 * @return boolean if trigger is pressed
	 */
	public boolean get() {

		double triggerVal = HumanInput.getXboxAxis(HumanInput.xboxController, triggerTypeValue);
		if (triggerVal >= 0.85) {
			return true;
		}
		return false;
	}

}