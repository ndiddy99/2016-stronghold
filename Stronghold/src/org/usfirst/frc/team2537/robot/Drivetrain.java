package org.usfirst.frc.team2537.robot;

//import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Drivetrain extends Subsystem implements HumanInputListener {
	Double joystickOneXValue = null;
	Double joystickTwoXValue = null;
	Double joystickOneYValue = null;
	Double joystickTwoYValue = null;
	DriveCommand command = new DriveCommand();

	public void DriveTrain() {

	}

	@Override
	public void inputRecieved(HumanInputEvent h) {
		joystickOneXValue = h.getJoyOneXValue();
		joystickTwoXValue = h.getJoyTwoXValue();
		joystickOneYValue = h.getJoyOneYValue();
		joystickTwoYValue = h.getJoyTwoYValue();
	}

	@Override
	protected void initDefaultCommand() {
		this.setDefaultCommand(command);
	}

}
