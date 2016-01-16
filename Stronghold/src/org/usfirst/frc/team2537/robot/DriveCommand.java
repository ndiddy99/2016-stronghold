package org.usfirst.frc.team2537.robot;

import edu.wpi.first.wpilibj.CANTalon;
//import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
//import edu.wpi.first.wpilibj.command.Subsystem;

public class DriveCommand extends Command {
	private CANTalon talonFrontLeft;
	private CANTalon talonFrontRight;
	private CANTalon talonBackRight;
	private CANTalon talonBackLeft;
	private boolean finished = false;

	public DriveCommand() {
		talonFrontLeft = new CANTalon(Ports.FRONT_LEFT_MOTOR_PORT);
		talonFrontRight = new CANTalon(Ports.FRONT_RIGHT_MOTOR_PORT);
		talonBackLeft = new CANTalon(Ports.BACK_LEFT_MOTOR_PORT);
		talonBackRight = new CANTalon(Ports.BACK_RIGHT_MOTOR_PORT);
	}

	@Override
	protected void initialize() {
	}

	@Override
	protected void execute() {
		talonFrontLeft.set(Robot.drivetrain.joystickOneXValue);
		talonFrontRight.set(Robot.drivetrain.joystickTwoXValue);
		talonBackLeft.set(Robot.drivetrain.joystickOneYValue);
		talonBackRight.set(Robot.drivetrain.joystickTwoYValue);
		finished = true;
	}

	@Override
	protected boolean isFinished() {

		return finished;
	}

	@Override
	protected void end() {

	}

	@Override
	protected void interrupted() {

	}

}
