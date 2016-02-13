package org.usfirst.frc.team2537.robot.shooter.flywheel;

import java.util.HashMap;

import org.usfirst.frc.team2537.robot.input.HumanInput;
import org.usfirst.frc.team2537.robot.input.Ports;
import org.usfirst.frc.team2537.robot.input.Sensor;
import org.usfirst.frc.team2537.robot.input.SensorListener;
import org.usfirst.frc.team2537.robot.shooter.ShootCommandGroup;
import org.usfirst.frc.team2537.robot.shooter.HarvestCommandGroup;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
/*
 * Important notice! If we use getSoftLimit() it will be in native units.
 */
public class FlywheelSubsystem extends Subsystem implements SensorListener {

	// Constants
	private static final CANTalon.FeedbackDevice ENCODER = CANTalon.FeedbackDevice.QuadEncoder;
	private static final int ENCODER_TICKS_PER_REV = 20;
	private static final double MOTOR_TOP_SPEED = 2000;// RPM, not calibrated yet
	private static final double P = .5, I = .05, D = 0.0;
	private final static boolean TESTING = true;
	private static final double SPEED_PROXIMITY = 5;
	// Vars
	private boolean proximityValue = false;
	private CANTalon leftFlywheelMotor;
	private CANTalon rightFlywheelMotor;
	private double targetLeftSpeed = 0;
	private double targetRightSpeed= 0;

	public FlywheelSubsystem() {
		// Starting motors.
		// Make sure the the mode to velocity so we can modify it.
		leftFlywheelMotor = new CANTalon(Ports.LEFT_FLYWHEEL_PORT);
		rightFlywheelMotor = new CANTalon(Ports.RIGHT_FLYWHEEL_PORT);
		// Set encoder.
		leftFlywheelMotor.setFeedbackDevice(ENCODER);
		leftFlywheelMotor.configEncoderCodesPerRev(ENCODER_TICKS_PER_REV);
		rightFlywheelMotor.setFeedbackDevice(ENCODER);
		rightFlywheelMotor.configEncoderCodesPerRev(ENCODER_TICKS_PER_REV);
		// Make sure the soft limits are disabled.
		leftFlywheelMotor.enableForwardSoftLimit(false);
		leftFlywheelMotor.enableReverseSoftLimit(false);
		rightFlywheelMotor.enableForwardSoftLimit(false);
		rightFlywheelMotor.enableReverseSoftLimit(false);
		// Disable the limit switchs, as they do not exist.
		leftFlywheelMotor.enableLimitSwitch(false, false);
		rightFlywheelMotor.enableLimitSwitch(false, false);
		// Nominal voltages, not sure if this is needed
		leftFlywheelMotor.configNominalOutputVoltage(0.0, 0.0);
		leftFlywheelMotor.configPeakOutputVoltage(12.0, -12.0);
		rightFlywheelMotor.configNominalOutputVoltage(0.0, 0.0);
		rightFlywheelMotor.configPeakOutputVoltage(12.0, -12.0);
		// Set rightFlywheelMotor to be reversed of everything else.
		rightFlywheelMotor.reverseOutput(true);
		rightFlywheelMotor.reverseSensor(true);
		leftFlywheelMotor.reverseOutput(false);
		leftFlywheelMotor.reverseSensor(false);
		// Set PID's
		leftFlywheelMotor.setPID(P, I, D);
		leftFlywheelMotor.setF(0);// No idea what this does, so disable it.
		rightFlywheelMotor.setPID(P, I, D);
		rightFlywheelMotor.setF(0);
		// Make the talon's go to the right control mode.
		// Should be default, not sure if this should be here.
		//Make sure follower mode is correct.
		leftFlywheelMotor.changeControlMode(CANTalon.TalonControlMode.Follower);
		rightFlywheelMotor.changeControlMode(CANTalon.TalonControlMode.Follower); 
		leftFlywheelMotor.set(Ports.RIGHT_FLYWHEEL_PORT);
		rightFlywheelMotor.set(Ports.LEFT_FLYWHEEL_PORT);
		//And flip back to normal running mode.
		leftFlywheelMotor.changeControlMode(CANTalon.TalonControlMode.Speed);
		rightFlywheelMotor.changeControlMode(CANTalon.TalonControlMode.Speed);
	}

	@Override
	public void initDefaultCommand() {

		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
		// setDefaultCommand(new ShotBallCommand());
		// There is not a default command.
	}

	public void registerButtons() {
		HumanInput.registerPressedCommand(HumanInput.ballShootTrigger, new ShootCommandGroup());
		HumanInput.registerWhileHeldCommand(HumanInput.harvestBallTrigger, new HarvestCommandGroup());
	}
	
	//First some lower lever functions to help with the main functions.
	/**
	 * A little secret enum. This is what mode the system is running in.
	 * @author Matthew Schweiss
	 */
	private enum FlywheelControlMode {
		PID_MODE,//Everything is normal
		RIGHT_FOLLOWER_MODE,//Right motor does not have an encoder
		LEFT_FOLLOWER_MODE, //Left  motor does not have an encoder
		VOLTAGE_MODE,		//NO ENCODERS!!
	}
	
	/**
	 * Get the current state of the shooter state.
	 */
	private FlywheelControlMode getControlMode(){
		boolean right = rightFlywheelMotor.isSensorPresent(ENCODER)== CANTalon.FeedbackDeviceStatus.FeedbackStatusPresent;
		boolean left  = leftFlywheelMotor.isSensorPresent(ENCODER) == CANTalon.FeedbackDeviceStatus.FeedbackStatusPresent;
		
		if (right && left){//BOTH ENCODERs
			//All is right with the world.
			if (leftFlywheelMotor.getControlMode() != CANTalon.TalonControlMode.Speed) leftFlywheelMotor.changeControlMode(CANTalon.TalonControlMode.Speed);
			if (rightFlywheelMotor.getControlMode()!= CANTalon.TalonControlMode.Speed)rightFlywheelMotor.changeControlMode(CANTalon.TalonControlMode.Speed);
			return FlywheelControlMode.PID_MODE;
		} else if (right){//ONLY RIGHT ENCODER
			//No left encoder.
			System.err.println("Left flywheel sensor is not present.");
			//Left is the slave!!
			//Make sure the left is a follower.
			if (leftFlywheelMotor.getControlMode() != CANTalon.TalonControlMode.Follower) leftFlywheelMotor.changeControlMode(CANTalon.TalonControlMode.Follower);
			if (rightFlywheelMotor.getControlMode() != CANTalon.TalonControlMode.Speed) rightFlywheelMotor.changeControlMode(CANTalon.TalonControlMode.Speed);
			return FlywheelControlMode.LEFT_FOLLOWER_MODE;
			
		} else if (left){//ONLY LEFT ENCODER
				System.err.println("Right flywheel sensor is not present.");
				//Left encoder,
				//Right's the slave
				if (rightFlywheelMotor.getControlMode() != CANTalon.TalonControlMode.Follower)rightFlywheelMotor.changeControlMode(CANTalon.TalonControlMode.Follower);
				if (leftFlywheelMotor.getControlMode() != CANTalon.TalonControlMode.Speed)leftFlywheelMotor.changeControlMode(CANTalon.TalonControlMode.Speed);
				return FlywheelControlMode.RIGHT_FOLLOWER_MODE;
		} else {//NO ENCODERS
			System.err.println("No flywheel sensors are present.");
			if (leftFlywheelMotor.getControlMode() != CANTalon.TalonControlMode.PercentVbus)leftFlywheelMotor.changeControlMode(CANTalon.TalonControlMode.PercentVbus);
			if (rightFlywheelMotor.getControlMode() != CANTalon.TalonControlMode.PercentVbus)rightFlywheelMotor.changeControlMode(CANTalon.TalonControlMode.PercentVbus);
			
			return FlywheelControlMode.VOLTAGE_MODE;//Goin back to the good old days.
		}
	}
	
	// Set and get speed
	/**
	 * Set the speed of the motors.
	 * 
	 * @param speed The speed in RPM if each if the motors.
	 */
	public void setSpeed(double speed) {
		FlywheelControlMode mode = getControlMode();
		setLeftSpeed(speed, mode);
		setRightSpeed(speed, mode);
	}
	
	// LEFT
	 /**
	 * Set the speed of the left motor.
	 *
	 * @param speed in RPM.
	 */
	public void setLeftSpeed(double speed){
		setLeftSpeed(speed, getControlMode());
	}
	private synchronized void setLeftSpeed(double speed, FlywheelControlMode mode){
		targetLeftSpeed = speed;
		switch (mode){
		case PID_MODE:
			//Simple
			leftFlywheelMotor.set(speed);
			break;
		case LEFT_FOLLOWER_MODE:
			//none, nothing to do. I am a follower!
			break;
		case RIGHT_FOLLOWER_MODE:
			//I'm in charge!!
			leftFlywheelMotor.set(speed);
			break;
		case VOLTAGE_MODE:
			//Well this is brilliant!
			leftFlywheelMotor.set(speed/MOTOR_TOP_SPEED);
			break;
		}
	}

	/**
	 * Get the current speed of the left flywheel.
	 * 
	 * @return flywheel speed in RPM
	 */
	public double getLeftSpeed() {
		return getLeftSpeed(getControlMode());
	}
	private double getLeftSpeed(FlywheelControlMode mode){
		switch (mode){
		case PID_MODE:
			return leftFlywheelMotor.getSpeed();
		case LEFT_FOLLOWER_MODE:
			//No left encoder...
			return rightFlywheelMotor.getSpeed();//Why not? If the encoder is missing, why check the speed?
		case RIGHT_FOLLOWER_MODE:
			return leftFlywheelMotor.getSpeed();
		case VOLTAGE_MODE:
			//Brilliant.
			return leftFlywheelMotor.getOutputVoltage() / leftFlywheelMotor.getBusVoltage() * MOTOR_TOP_SPEED;
		default:
			//Should never happen.
			System.err.println("getMotorSpeed got null");
			return leftFlywheelMotor.getSpeed();
		}
	}

	/**
	 * Get the error of the left flywheel. This is the distance from the wanted
	 * speed and the current speed.
	 * 
	 * @return Difference between current speed and wanted speed. In RPM
	 */
	public double getLeftError() {
		// We want to change this from native units (units per 100ms) to RPM.
		// return leftFlywheelMotor.getError()/100/4096*1000*60;

		// Simplified for speed.
		return getLeftError(getControlMode());
	}
	private double getLeftError(FlywheelControlMode mode){
		switch(mode){
		case PID_MODE:
			//Special Numbers....
			return leftFlywheelMotor.getError() * 75 / 512;
		case LEFT_FOLLOWER_MODE:
			return rightFlywheelMotor.getError() * 75 / 512;
		case RIGHT_FOLLOWER_MODE:
			return leftFlywheelMotor.getError() * 75 / 512;
		case VOLTAGE_MODE:
			//Kind of cheating.
			return Math.abs(targetLeftSpeed - getLeftSpeed(mode));//RPM we are good.
		default:
			System.out.println("getLeftError(mode) was passed a null");
			return 0;
		}
	}

	//RIGHT
	/**
	 * Set the speed of the right motor.
	 *
	 * @param speed in RPM.
	 */
	public void setRightSpeed(double speed){
		setRightSpeed(speed, getControlMode());
	 }
	private synchronized void setRightSpeed(double speed, FlywheelControlMode mode){
		targetLeftSpeed = speed;
		switch(mode){
		case PID_MODE:
			rightFlywheelMotor.set(speed);
			break;
		case LEFT_FOLLOWER_MODE:
			rightFlywheelMotor.set(speed);
			 break;
		case RIGHT_FOLLOWER_MODE:
			//nothing...
			break;
		case VOLTAGE_MODE:
			leftFlywheelMotor.set(speed/MOTOR_TOP_SPEED);
			break;
		 }
	 }

	/**
	 * Get the current speed of the left flywheel.
	 * 
	 * @return flywheel speed in RPM
	 */
	public double getRightSpeed() {
		return getRightSpeed(getControlMode());
		}
	private double getRightSpeed(FlywheelControlMode mode) {
		switch (mode){
		case PID_MODE:
			return rightFlywheelMotor.getSpeed();
		case LEFT_FOLLOWER_MODE:
			//No left encoder...
			return leftFlywheelMotor.getSpeed();//Why not? If the encoder is missing, why check the speed?
		case RIGHT_FOLLOWER_MODE:
			return rightFlywheelMotor.getSpeed();
		case VOLTAGE_MODE:
			//Brilliant.
			return rightFlywheelMotor.getOutputVoltage() / rightFlywheelMotor.getBusVoltage() * MOTOR_TOP_SPEED;
		default:
			//Should never happen.
			System.err.println("getMotorSpeed got null");
			return rightFlywheelMotor.getSpeed();
		}
		
	}

	/**
	 * Get the error of the left flywheel. This is the distance from the wanted
	 * speed and the current speed.
	 * 
	 * @return Difference between current speed and wanted speed in RPM.
	 */
	public double getRightError() {
		return getRightError(getControlMode());
	}
	private double getRightError(FlywheelControlMode mode){
		switch(mode){
		case PID_MODE:
			//Special Numbers....
			return rightFlywheelMotor.getError() * 75 / 512;
		case LEFT_FOLLOWER_MODE:
			return rightFlywheelMotor.getError() * 75 / 512;
		case RIGHT_FOLLOWER_MODE:
			return leftFlywheelMotor.getError() * 75 / 512;
		case VOLTAGE_MODE:
			//Kind of cheating.
			return Math.abs(targetRightSpeed - getRightSpeed(mode));
		default:
			System.out.println("getRightError(mode) was passed a null");
			return 0;
		}
	}

	/**
	 * Find if the flywheels are spinning at thier intended speed.
	 * @return true if the flywheels are spinning at ~5 rpm of thier target.
	 */
	public boolean isAtSpeed(){
		FlywheelControlMode mode = getControlMode();
		switch (mode){
		case PID_MODE:
			//Compare error
			return getLeftError(mode) <= SPEED_PROXIMITY && getRightError(mode) <= SPEED_PROXIMITY;
		case LEFT_FOLLOWER_MODE:
			//Right error only.
			return getRightError(mode) <= SPEED_PROXIMITY;
		case RIGHT_FOLLOWER_MODE:
			return getLeftError(mode) <= SPEED_PROXIMITY;
		case VOLTAGE_MODE:
			//Try to check speed.
			return Math.abs(getRightSpeed(mode) - targetRightSpeed) <= SPEED_PROXIMITY && 
					Math.abs(getLeftSpeed(mode) - targetLeftSpeed) <= SPEED_PROXIMITY;
		default:
			//HOW DID I GET HERE.
			System.out.println("THIS IS TIME TO PANIC!");
			return false;//Because why not!
		}
	}
	/**
	 * Emergency stops the motors. This directly sets voltage to 0.
	 */
	public void stop() {
		// Left wheel stop.
		leftFlywheelMotor.changeControlMode(CANTalon.TalonControlMode.PercentVbus);
		leftFlywheelMotor.set(0);// STOP
		leftFlywheelMotor.changeControlMode(CANTalon.TalonControlMode.Speed);

		// Right wheel stop.
		rightFlywheelMotor.changeControlMode(CANTalon.TalonControlMode.PercentVbus);
		rightFlywheelMotor.set(0);
		rightFlywheelMotor.changeControlMode(CANTalon.TalonControlMode.Speed);
	}

	public void go() {
		// Left wheel stop.
		leftFlywheelMotor.changeControlMode(CANTalon.TalonControlMode.PercentVbus);
		leftFlywheelMotor.set(1);// STOP
		leftFlywheelMotor.changeControlMode(CANTalon.TalonControlMode.Speed);

		// Right wheel stop.
		rightFlywheelMotor.changeControlMode(CANTalon.TalonControlMode.PercentVbus);
		rightFlywheelMotor.set(1);
		rightFlywheelMotor.changeControlMode(CANTalon.TalonControlMode.Speed);
	}

	@Override
	// Proximity
	public void receivedValue(HashMap<String, Double> sensorMap) {
		// BEWARE OF NullPointer
		if (!TESTING) {
			try {
				proximityValue = sensorMap.get(Sensor.SHOOTER_BALL) == 1;
			} catch (NullPointerException e) {
				// We have an error.
				// We don't actually care.
				System.err.println("FlywheelSys recieved null proximity value.");
				// Not known, make something up...
				// Though with that, we can't tell if there is a ball or not.
				proximityValue = false;// I don't see a ball...
			}
		} else {
			proximityValue = rightFlywheelMotor.isFwdLimitSwitchClosed();
		}
	}

	/**
	 * Check if a ball is in the shooter.
	 * 
	 * @return if the proximity sensor is returning true to seeing something.
	 */
	public boolean isBall() {
		return proximityValue;
	}
}
