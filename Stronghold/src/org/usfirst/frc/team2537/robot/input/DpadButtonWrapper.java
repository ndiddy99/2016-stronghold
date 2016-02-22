package org.usfirst.frc.team2537.robot.input;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;

public class DpadButtonWrapper extends Button {
	private Joystick j;
	private int port = XboxButtons.DPAD_AXIS;
	private boolean right = false;
	private final double THRESHOLD=0.75;
	
	public DpadButtonWrapper(boolean right){
		j = HumanInput.xboxController;
		this.right= right;
	}
	@Override
	public boolean get() {
		if(right && j.getRawAxis(port)>=THRESHOLD){
			return true;
		}
		else if(!right && j.getRawAxis(port)<=-THRESHOLD){
			return true;
		}
		return false;
	}

}
