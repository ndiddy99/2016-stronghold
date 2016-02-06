package org.usfirst.frc.team2537.robot.drive;

import org.usfirst.frc.team2537.robot.input.HumanInput;
import org.usfirst.frc.team2537.robot.input.Ports;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;
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
	
	public static final double WHEEL_DIAMETER = 9; //Inches TODO: Magic numbers are fun
	public static final double PulsesPerRevolution = 20; //for encoders
	private double initialLeftEncoders = 0; //Inches to subtract (for resetEncoders) 
	private double initialRightEncoders = 0; //Inches to subtract (for resetEncoders)
	
	public DriveSubsystem() {
		talonFrontLeft = new CANTalon(Ports.FRONT_LEFT_MOTOR_PORT);
		talonFrontRight = new CANTalon(Ports.FRONT_RIGHT_MOTOR_PORT);
		talonBackLeft = new CANTalon(Ports.BACK_LEFT_MOTOR_PORT);
		talonBackRight = new CANTalon(Ports.BACK_RIGHT_MOTOR_PORT);
		setDriveControlMode(TalonControlMode.Speed);
		
		
		driveType = DriveType.doubleJoystick; //TODO: set this
		drivingStraight = false;
		driveLowerSpeed = false;
	}

	/**
	 * Sets the output of a CANTalon
	 * 
	 * @param outputValue 
	 * 		in speed mode, speed of talon in inches/second
	 * 		in other modes, consult the javadocs
	 * @param talon The CANTalon to set the speed on
	 * 
	 */
	public void set(double outputValue, CANTalon talon) {
		// inches/second to pulses/tenMS
		// (x pulses)/ (radius*pi tenMS)

		switch(talon.getControlMode()){
		case Speed: talon.set(outputValue / (WHEEL_DIAMETER/2 * Math.PI));
		default: talon.set(outputValue);
		}
	}

	/**
	 * sets the left drive motors to a value
	 * corrects for inverted motor speeds.
	 * @param value
	 */
	public void setLeftDriveMotors(double value){
		set(-value, talonFrontLeft);
		set(-value, talonBackLeft);
	}
	
	/**
	 * sets the right motors to a certain value
	 * @param value
	 */
	public void setRightDriveMotors(double value){
		set(value, talonFrontRight);
		set(value, talonBackRight);	
	}
	
	/**
	 * sets all drive motors to the same value
	 * corrects for inverted left motor
	 * @param value
	 */
	public void setDriveMotors(double value){
		setDriveMotors(value, value);
	}
	
	/**
	 * sets the drive motors.
	 * Corrects for inverted left motor
	 * @param left
	 * @param right
	 */
	public void setDriveMotors(double left, double right){
		setLeftDriveMotors(left);
		setRightDriveMotors(right);
	}
	
	public double get(CANTalon talon){
		return talon.get();
	}
	
	/**
	 * returns the average between all the encoders
	 */
	public double getEncoders(){
		return (getLeftEncoders() + getRightEncoders())/2;
	}
	
	/**
	 * Gets the average value of the left drive encoders
	 * compensates for negative left values
	 * @return the average of the front left and back left encoders in inches
	 */
	public double getLeftEncoders(){
		return -(talonBackLeft.getEncPosition() + talonFrontLeft.getEncPosition())/2/PulsesPerRevolution * WHEEL_DIAMETER * Math.PI - initialLeftEncoders;
	}
	
	/**
	 * Gets the average value of the right drive encoders
	 * @return the average of the front right and back right encoders in inches
	 */
	public double getRightEncoders(){
		return (talonBackRight.getEncPosition() + talonFrontRight.getEncPosition())/2/PulsesPerRevolution * WHEEL_DIAMETER * Math.PI - initialRightEncoders;
	}
	
	/**
	 * Sets the encoders to 0 (basically).
	 */
	public void resetEncoders(){
		initialLeftEncoders += getLeftEncoders();
		initialRightEncoders += getRightEncoders();
	}
	
	public void setDriveControlMode(TalonControlMode mode){
		talonFrontLeft.changeControlMode(mode);
		talonBackLeft.changeControlMode(mode);
		talonFrontRight.changeControlMode(mode);
		talonBackRight.changeControlMode(mode);
	}

	/**
	 * sets the default command to JoystickControlCommand
	 */
	public void initDefaultCommand() {
		DriveCommand drive = new DriveCommand();
		this.setDefaultCommand(drive);
	}

	
	public void registerButtons() {
		HumanInput.registerPressedCommand(HumanInput.driveStraight, new Command(){
			@Override protected void initialize() {drivingStraight = true;}
			@Override protected void execute() {}
			@Override protected boolean isFinished() {return true;}			
			@Override protected void end() {}
			@Override protected void interrupted() {}
		});
		
		HumanInput.registerReleasedCommand(HumanInput.driveStraight, new Command(){
			@Override protected void initialize() {drivingStraight = false;}
			@Override protected void execute() {}
			@Override protected boolean isFinished() {return true;}
			@Override protected void end() {}
			@Override protected void interrupted() {}
		});
		
		HumanInput.registerPressedCommand(HumanInput.driveSensetivityToggle, new Command(){
			@Override protected void initialize() {driveLowerSpeed = !driveLowerSpeed;}
			@Override protected void execute() {}
			@Override protected boolean isFinished() {return true;}
			@Override protected void end() {}
			@Override protected void interrupted() {}
		});
		
		HumanInput.registerPressedCommand(HumanInput.driveTypeToggle, new Command(){
			@Override protected void initialize() {driveType = driveType.getNext();}
			@Override protected void execute() {}
			@Override protected boolean isFinished() {return true;}
			@Override protected void end() {}
			@Override protected void interrupted() {}
		});
	}

}