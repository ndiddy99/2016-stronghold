package oldCode;

import org.usfirst.frc.team2537.robot.Ports;

import edu.wpi.first.wpilibj.Joystick;

public class HumanInputEvent {
	private Joystick joyLeft = new Joystick(Ports.JOYSTICK_LEFT_PORT);
	private Joystick joyRight = new Joystick(Ports.JOYSTICK_RIGHT_PORT);
	/*//There appears not to be a point to storing the values.
	private Double joyOneXValue = null;
	private Double joyTwoXValue = null;
	private Double joyOneYValue = null;
	private Double joyTwoYValue = null;
	*/

	public HumanInputEvent() {
		/*
		joyOneXValue = joyOne.getX();
		joyTwoXValue = joyTwo.getX();
		joyOneYValue = joyOne.getY();
		joyTwoYValue = joyTwo.getY();
		*/
	}

	public Double getJoyLeftYValue() {
		/*joyOneYValue = joyOne.getY();
		return joyOneYValue;
		*/
		return joyLeft.getY();
	}

	public Double getJoyRightYValue() {
		/*joyTwoYValue = joyTwo.getY();
		return joyTwoYValue;
		*/
		return joyRight.getY();
	}

	public Double getJoyLeftXValue() {
		/*
		joyOneXValue = joyOne.getX();
		return joyOneXValue;
		*/
		return joyRight.getX();
	}

	public Double getJoyRightXValue() {
		/*joyTwoXValue = joyTwo.getX();
		return joyTwoXValue;
		*/
		return joyLeft.getY();
	}
}
