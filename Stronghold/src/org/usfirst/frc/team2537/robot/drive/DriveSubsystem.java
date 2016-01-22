package org.usfirst.frc.team2537.robot.drive;

import org.usfirst.frc.team2537.robot.input.Ports;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.command.Subsystem;


public class DriveSubsystem extends Subsystem{
	public CANTalon talonFrontLeft;
	public CANTalon talonFrontRight;
	public CANTalon talonBackRight;
	public CANTalon talonBackLeft;
	protected DriveType driveType;

	public DriveSubsystem() {
		talonFrontLeft = new CANTalon(Ports.FRONT_LEFT_MOTOR_PORT);
		talonFrontRight = new CANTalon(Ports.FRONT_RIGHT_MOTOR_PORT);
		talonBackLeft = new CANTalon(Ports.BACK_LEFT_MOTOR_PORT);
		talonBackRight = new CANTalon(Ports.BACK_RIGHT_MOTOR_PORT);
		driveType = DriveType.doubleJoystick;
	}

	/**
	 * Sets the speed of a CANTalon
	 * 
	 * @param speed Speed to set the CANTalon
	 * 
	 * @param talon The CANTalon to set the speed on
	 * 
	 */
	public void set(double speed, CANTalon talon) {
		talon.set(speed);
	}

	public void setDriveType(DriveType t){
		driveType = t;
	}
	
	public double get(CANTalon talon){
		return talon.get();
	}
	
	/**
	 * sets the default command to JoystickControlCommand
	 */
	public void initDefaultCommand() {
		DriveCommand drive = new DriveCommand();
		this.setDefaultCommand(drive);
	}
	
}
