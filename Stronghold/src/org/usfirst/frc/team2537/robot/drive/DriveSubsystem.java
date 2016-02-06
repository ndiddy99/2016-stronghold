package org.usfirst.frc.team2537.robot.drive;

import org.usfirst.frc.team2537.robot.input.HumanInput;
import org.usfirst.frc.team2537.robot.input.Ports;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;


public class DriveSubsystem extends Subsystem{
	public CANTalon talonFrontLeft;
	public CANTalon talonFrontRight;
	public CANTalon talonBackRight;
	public CANTalon talonBackLeft;
	protected DriveType driveType;
	protected boolean drivingStraight;
	protected boolean driveLowerSpeed;

	public DriveSubsystem() {
		talonFrontLeft = new CANTalon(Ports.FRONT_LEFT_MOTOR_PORT);
		talonFrontRight = new CANTalon(Ports.FRONT_RIGHT_MOTOR_PORT);
		talonBackLeft = new CANTalon(Ports.BACK_LEFT_MOTOR_PORT);
		talonBackRight = new CANTalon(Ports.BACK_RIGHT_MOTOR_PORT);
		driveType = DriveType.doubleJoystick; //TODO: set this
		drivingStraight = false;
		driveLowerSpeed = false;
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

	/**
	 * sets the left drive motors to a certain speed.
	 * corrects for inverted motor speeds.
	 * @param speed
	 */
	public void setLeftDriveMotors(double speed){
		set(-speed, talonFrontLeft);
		set(-speed, talonBackLeft);
	}
	
	/**
	 * sets the right motors to a certain speed
	 * @param speed
	 */
	public void setRightDriveMotors(double speed){
		set(speed, talonFrontRight);
		set(speed, talonBackRight);		
	}
	
	/**
	 * sets all drive motors to the same speed
	 * corrects for inverted left motor
	 * @param speed
	 */
	public void setDriveMotors(double speed){
		setDriveMotors(speed, speed);
	}
	
	/**
	 * sets the drive motors.
	 * Corrects for inverted left motor
	 * @param left
	 * @param right
	 */
	public void setDriveMotors(double left, double right){
		setLeftDriveMotors(left);
		setRightDriveMotors(left);
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
	
	public void registerButtons() {
//		HumanInput.registerPressedCommand(HumanInput.driveStraight, new Command(){
//			@Override protected void initialize() {drivingStraight = true;}
//			@Override protected void execute() {}
//			@Override protected boolean isFinished() {return true;}			
//			@Override protected void end() {}
//			@Override protected void interrupted() {}
//		});
//		
//		HumanInput.registerReleasedCommand(HumanInput.driveStraight, new Command(){
//			@Override protected void initialize() {drivingStraight = false;}
//			@Override protected void execute() {}
//			@Override protected boolean isFinished() {return true;}
//			@Override protected void end() {}
//			@Override protected void interrupted() {}
//		});
//		
//		HumanInput.registerPressedCommand(HumanInput.driveSensetivityToggle, new Command(){
//			@Override protected void initialize() {driveLowerSpeed = !driveLowerSpeed;}
//			@Override protected void execute() {}
//			@Override protected boolean isFinished() {return true;}
//			@Override protected void end() {}
//			@Override protected void interrupted() {}
//		});
	}
	
}
