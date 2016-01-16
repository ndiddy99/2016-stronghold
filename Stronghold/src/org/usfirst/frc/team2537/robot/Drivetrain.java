package org.usfirst.frc.team2537.robot;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.command.Subsystem;


public class Drivetrain extends Subsystem implements HumanInputListener {
	private Double joystickOneXValue = 0.0;
	private Double joystickTwoXValue = 0.0;
	private Double joystickOneYValue = 0.0;
	private Double joystickTwoYValue = 0.0;
	private CANTalon talonFrontLeft;
	private CANTalon talonFrontRight;
	private CANTalon talonBackRight;
	private CANTalon talonBackLeft;


	public Drivetrain() {
		talonFrontLeft = new CANTalon(Ports.FRONT_LEFT_MOTOR_PORT);
		talonFrontRight = new CANTalon(Ports.FRONT_RIGHT_MOTOR_PORT);
		talonBackLeft = new CANTalon(Ports.BACK_LEFT_MOTOR_PORT);
		talonBackRight = new CANTalon(Ports.BACK_RIGHT_MOTOR_PORT);
	}

	@Override
	public void inputRecieved(HumanInputEvent event) {
		if (event.getJoyOneYValue() != null || event.getJoyTwoYValue() != null) {
			joystickOneYValue = event.getJoyOneYValue();
			joystickTwoYValue  = event.getJoyTwoYValue();
			Scheduler.getInstance().add(new DriveCommand());
		}

	}
/**
 * Moves horray!
 */
	public void move() {
		talonFrontLeft.set(joystickOneYValue);
		talonBackLeft.set(joystickOneYValue);
		talonFrontRight.set(joystickTwoYValue);
		talonBackRight.set(joystickTwoYValue);
	}
	@Override
	protected void initDefaultCommand() {
	}
}
