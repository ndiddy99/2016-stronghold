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
	public void inputRecieved(HumanInputEvent event) {
		joystickOneXValue = event.getJoyOneXValue();
		joystickTwoXValue = event.getJoyTwoXValue();
		joystickOneYValue = event.getJoyOneYValue();
		joystickTwoYValue = event.getJoyTwoYValue();
	}

	@Override
	protected void initDefaultCommand() {
		this.setDefaultCommand(command);
	}

}
