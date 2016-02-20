package org.usfirst.frc.team2537.robot.drive;

import org.usfirst.frc.team2537.robot.input.HumanInput;
import org.usfirst.frc.team2537.robot.input.Ports;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;
import edu.wpi.first.wpilibj.command.Subsystem;


public class DriveSubsystem extends Subsystem{
	private CANTalon talonFrontLeft;
	private CANTalon talonFrontRight;
	private CANTalon talonBackRight;
	private CANTalon talonBackLeft;
	private DriveType driveType;
	private boolean drivingStraight;
	private boolean driveLowerSpeed;
	private boolean reversed;

	public static final double WHEEL_DIAMETER = 9; //Inches TODO: Magic numbers are fun
	public static final double PulsesPerRevolution = 20; //for encoders
	private double initialLeftEncoders = 0; //Inches to subtract (for resetEncoders) 
	private double initialRightEncoders = 0; //Inches to subtract (for resetEncoders)
	
	public DriveSubsystem() {
		talonFrontLeft = new CANTalon(Ports.FRONT_LEFT_MOTOR_PORT);
		talonFrontRight = new CANTalon(Ports.FRONT_RIGHT_MOTOR_PORT);
		talonBackLeft = new CANTalon(Ports.BACK_LEFT_MOTOR_PORT);
		talonBackRight = new CANTalon(Ports.BACK_RIGHT_MOTOR_PORT);
//		SPEED MODE CODE
	//	setDriveControlMode(TalonControlMode.Speed);
		
		driveType = DriveType.doubleJoystickXbox;
		drivingStraight = false;
		driveLowerSpeed = false;
		reversed = false;
		enableForwardSoftLimit(false);
		enableReverseSoftLimit(false);
	}

	/**
	 * Sets the output of a CANTalon
	 * 
	 * @param outputValue consult talon javadocs
	 * @param talon The CANTalon to set the speed on
	 * 
	 */
	private void set(double outputValue, CANTalon talon) {
//		SPEED MODE CODE
//		switch(talon.getControlMode()){
//		case Speed: talon.set(1500 * outputValue); break;
//		default: if(outputValue != 0) System.out.println(talonFrontRight.getEncPosition()); talon.set(outputValue);
//		}
		
		talon.set(outputValue);
	
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
	
	/**
	 * returns the average between all the encoders
	 */
	public double getEncoderAverage(){
		return (getLeftEncoders() + getRightEncoders())/2;
	}
	
	/**
	 * Gets the average value of the left drive encoders
	 * compensates for negative left values
	 * @return the average of the front left and back left encoders in inches
	 */
	public double getLeftEncoders(){
		//gets average encoder value, converts to revolutions, 
		//converts to distance in inches using circumfrense of the wheel, 
		//subtracts from original position to find distance 
		//multiply by -1 because left motors are wired backwards
		return -(talonBackLeft.getEncPosition() + talonFrontLeft.getEncPosition())/2/PulsesPerRevolution * WHEEL_DIAMETER * Math.PI - initialLeftEncoders;
	}
	
	/**
	 * Gets the average value of the right drive encoders
	 * @return the average of the front right and back right encoders in inches
	 */
	public double getRightEncoders(){
		//gets average encoder value, converts to revolutions, 
		//converts to distance in inches using circumfrense of the wheel, 
		//subtracts from original position to find distance 
		return (talonBackRight.getEncPosition() + talonFrontRight.getEncPosition())/2/PulsesPerRevolution * WHEEL_DIAMETER * Math.PI - initialRightEncoders;
	}
	
	/**
	 * Sets the encoder position to 0.
	 */
	public void resetEncoders(){
		initialLeftEncoders += getLeftEncoders();
		initialRightEncoders += getRightEncoders();
	}
	
	private void setDriveTalonControlMode(TalonControlMode mode){
		talonFrontLeft.changeControlMode(mode);
		talonBackLeft.changeControlMode(mode);
		talonFrontRight.changeControlMode(mode);
		talonBackRight.changeControlMode(mode);
	}
	
	private void enableForwardSoftLimit(boolean b){
		talonFrontRight.enableForwardSoftLimit(b);
		talonFrontLeft.enableForwardSoftLimit(b);
		talonBackRight.enableForwardSoftLimit(b);
		talonBackLeft.enableForwardSoftLimit(b);
	}

	private void enableReverseSoftLimit(boolean b){
		talonFrontRight.enableReverseSoftLimit(b);
		talonFrontLeft.enableReverseSoftLimit(b);
		talonBackRight.enableReverseSoftLimit(b);
		talonBackLeft.enableReverseSoftLimit(b);
	}


	public void initDefaultCommand() {
		DriveCommand drive = new DriveCommand();
		this.setDefaultCommand(drive);
	}
	
	protected void setDriveStraight(boolean b) {
		drivingStraight = b;
	}
	
	public boolean getDrivingStraight() {
		return drivingStraight;
	}
	
	protected void setLowSpeed(boolean b) {
		driveLowerSpeed = b;
	}
	
	protected boolean getLowSpeed() {
		return driveLowerSpeed;
	}

	protected DriveType getDriveType() {
		return driveType;
	}

	protected void setDriveType(DriveType driveType) {
		this.driveType = driveType;
	}
	
	protected boolean getReversed() {
		return reversed;
	}

	protected void setReversed(boolean reversed) {
		this.reversed = reversed;
	}
		public void registerButtons() {
		HumanInput.registerPressedCommand(HumanInput.driveStraight, new DriveStraightToggleCommand(true));
		HumanInput.registerReleasedCommand(HumanInput.driveStraight, new DriveStraightToggleCommand(false));
		HumanInput.registerPressedCommand(HumanInput.driveSensetivityToggle, new DriveLowerSpeedToggleCommand());
		HumanInput.registerPressedCommand(HumanInput.driveTypeToggle, new DriveTypeToggleCommand());		
		HumanInput.registerPressedCommand(HumanInput.reverseDrive, new DriveReverseToggleCommand());
	}


}
		
