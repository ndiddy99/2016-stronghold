package org.usfirst.frc.team2537.robot;

import edu.wpi.first.wpilibj.Joystick;

public class HumanInputEvent {
	private Joystick joyOne = new Joystick(Ports.JOYSTICK_ONE_PORT);
	private Joystick joyTwo = new Joystick(Ports.JOYSTICK_TWO_PORT);
	private Double joyOneXValue = null;
	private Double joyTwoXValue = null;
	private Double joyOneYValue = null;
	private Double joyTwoYValue = null;

	public HumanInputEvent(Double inputValue) {
		joyOneXValue = joyOne.getX();
		joyTwoXValue = joyTwo.getX();
		joyOneYValue = joyOne.getY();
		joyTwoYValue = joyTwo.getY();
	}

	public Double getJoyOneYValue() {
		joyOneYValue = joyOne.getY();
		return joyOneYValue;
	}

	public Double getJoyTwoYValue() {
		joyTwoYValue = joyTwo.getY();
		return joyTwoYValue;
	}

	public Double getJoyOneXValue() {
		joyOneXValue = joyOne.getX();
		return joyOneXValue;
	}

	public Double getJoyTwoXValue() {
		joyTwoXValue = joyTwo.getX();
		return joyTwoXValue;
	}
}
