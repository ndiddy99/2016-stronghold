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
	private final static boolean TESTING = true;
	private static final CANTalon.FeedbackDevice ENCODER = CANTalon.FeedbackDevice.QuadEncoder;
	private static final int ENCODER_TICKS_PER_REV = 20;
	private static final double ERROR_PROXIMITY = 5;//rpm
	private static final double MOTOR_TOP_SPEED = 2000;// RPM, not calibrated yet
	private static final double P = .5, I = .05, D = 0.0;
	// Vars
	private boolean proximityValue = false;
	private double targetSpeed = 0;
	private CANTalon leftFlywheelMotor;
	private CANTalon rightFlywheelMotor;

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
		leftFlywheelMotor.changeControlMode(CANTalon.TalonControlMode.Speed);
		rightFlywheelMotor.changeControlMode(CANTalon.TalonControlMode.Speed); 
		
		//Lidar
		//lidarSensor = new Ultrasonic
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

	// Set and get speed
	/**
	 * Set the speed of the motors.
	 * 
	 * @param speed
	 *            The speed in RPM if each if the motors.
	 */
	public void setSpeed(double speed) {
		targetSpeed = speed;
		switch (getSystemState()){
		case PID_MODE:
			//Encoders present, Modes have already been checked.
			leftFlywheelMotor.set(speed);
			rightFlywheelMotor.set(speed);
			break;
			
		case LEFT_FOLLOWER_MODE:
			rightFlywheelMotor.set(speed);
			//Other is moved by being stuck.
			break;
		case RIGHT_FOLLOWER_MODE:
			leftFlywheelMotor.set(speed);
			break;
		case VOLTAGE_MODE:
			double sv = speed / MOTOR_TOP_SPEED;
			leftFlywheelMotor.set(sv);// Speed divided Top
			rightFlywheelMotor.set(sv);
			break;
		}
	}
	
	public boolean isAtSpeed(){
		switch (getSystemState()){
		case PID_MODE:
			//Compare error
			return getLeftError() <= ERROR_PROXIMITY && getRightError() <= ERROR_PROXIMITY;
		case LEFT_FOLLOWER_MODE:
			//Right error only.
			return getRightError() <= ERROR_PROXIMITY;
		case RIGHT_FOLLOWER_MODE:
			return getLeftError() <= ERROR_PROXIMITY;
		case VOLTAGE_MODE:
			//Try to check speed.
			return Math.abs(getRightSpeed() - targetSpeed) <= ERROR_PROXIMITY && 
					Math.abs(getLeftSpeed() - targetSpeed) <= ERROR_PROXIMITY;
		default:
			//HOW DID I GET HERE.
			System.out.println("THIS IS TIME TO PANIC!");
			return true;//Because why not!
		}
	}
	// LEFT
	// /**
	// * Set the speed of the left motor.
	// *
	// * @param speed in RPM.
	// */
	// public void setLeftSpeed(double speed){
	// switch (leftFlywheelMotor.isSensorPresent(ENCODER)){
	//
	// case FeedbackStatusPresent:
	// //Yes sensor is present.
	// leftFlywheelMotor.set(speed);
	//
	// case FeedbackStatusUnknown:
	// //I don't know, assuming it is not there.
	// System.err.println("Left Encoder may not be present.");
	//
	// case FeedbackStatusNotPresent:
	// System.err.println("Left Encoder is not present.");
	//
	// default:
	// //Make up something!!!
	// //Ok, so speed will be [-1023, 1023]
	// //Voltage is [-1, 1]
	// //So in theory, speed/1023 = voltage.
	// leftFlywheelMotor.changeControlMode(CANTalon.TalonControlMode.PercentVbus);
	// leftFlywheelMotor.set(speed/1023);
	// leftFlywheelMotor.changeControlMode(CANTalon.TalonControlMode.Speed);
	// }
	// }

	/**
	 * Get the current speed of the left flywheel.
	 * 
	 * @return flywheel speed in RPM
	 */
	public double getLeftSpeed() {
		switch (leftFlywheelMotor.isSensorPresent(ENCODER)) {

		case FeedbackStatusPresent:
			// Yes sensor is present.
			return leftFlywheelMotor.getSpeed();

		case FeedbackStatusUnknown:
			// I don't know, assuming it is not there.
			System.err.println("Right Encoder may not be present.");

		case FeedbackStatusNotPresent:
			System.err.println("Right Encoder is not present.");

		default:
			// Make up something!!!
			// return leftFlywheelMotor.get();
			return leftFlywheelMotor.getOutputVoltage() / leftFlywheelMotor.getBusVoltage() * MOTOR_TOP_SPEED;
		}
	}

	/**
	 * Get the error of the left flywheel. This is the distance from the wanted
	 * speed and the current speed.
	 * 
	 * @return Difference between current speed and wanted speed. In RPM
	 */
	private double getLeftError() {
		// We want to change this from native units (units per 100ms) to RPM.
		// return leftFlywheelMotor.getError()/100/4096*1000*60;

		// Simplified for speed.
		return leftFlywheelMotor.getError() * 75 / 512;
	}

	// RIGHT
	// /**
	// * Set the speed of the right motor.
	// *
	// * @param speed in RPM.
	// */
	// public void setRightSpeed(double speed){
	// switch (leftFlywheelMotor.isSensorPresent(ENCODER)){
	//
	// case FeedbackStatusPresent:
	// //Yes sensor is present.
	// rightFlywheelMotor.set(speed);
	//
	// case FeedbackStatusUnknown:
	// //I don't know, assuming it is not there.
	// System.err.println("Right Encoder may not be present.");
	//
	// case FeedbackStatusNotPresent:
	// System.err.println("Right Encoder is not present.");
	//
	// default:
	// //Make up something!!!
	// //Ok, so speed will be [-1023, 1023]
	// //Voltage is [-1, 1]
	// //So in theory, speed/1023 = voltage.
	// rightFlywheelMotor.changeControlMode(CANTalon.TalonControlMode.PercentVbus);
	// rightFlywheelMotor.set(speed/1023);
	// rightFlywheelMotor.changeControlMode(CANTalon.TalonControlMode.Speed);
	// }
	// }

	/**
	 * Get the current speed of the left flywheel.
	 * 
	 * @return flywheel speed in RPM
	 */
	public double getRightSpeed() {
		switch (rightFlywheelMotor.isSensorPresent(ENCODER)) {

		case FeedbackStatusPresent:
			// Yes sensor is present.
			return rightFlywheelMotor.getSpeed();

		case FeedbackStatusUnknown:
			// I don't know, assuming it is not there.
			System.err.println("Right Encoder may not be present.");

		case FeedbackStatusNotPresent:
			System.err.println("Right Encoder is not present.");

		default:
			// Make up something!!!
			// return rightFlywheelMotor.get();
			// Alternatively
			return rightFlywheelMotor.getOutputVoltage() / rightFlywheelMotor.getBusVoltage() * MOTOR_TOP_SPEED;

		}
	}

	/**
	 * Get the error of the left flywheel. This is the distance from the wanted
	 * speed and the current speed.
	 * 
	 * @return Difference between current speed and wanted speed in RPM.
	 */
	private double getRightError() {
		return rightFlywheelMotor.getError() * 75 / 512;
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
	
	private enum FlywheelSubsystemMode {
		PID_MODE,//Everything is normal
		RIGHT_FOLLOWER_MODE,//Right motor does not have an encoder
		LEFT_FOLLOWER_MODE, //Left  motor does not have an encoder
		VOLTAGE_MODE,		//NO ENCODERS!!
	}
	/**
	 * Get the current state of the shooter state.
	 */
	private FlywheelSubsystemMode getSystemState(){
		boolean right = rightFlywheelMotor.isSensorPresent(ENCODER) == CANTalon.FeedbackDeviceStatus.FeedbackStatusPresent;
		boolean left  = leftFlywheelMotor.isSensorPresent(ENCODER) == CANTalon.FeedbackDeviceStatus.FeedbackStatusPresent;
		if (right){
			//Right Encoder
			if (left){
				//Left Encoder
				//All is right with the world.
				if (leftFlywheelMotor.getControlMode() != CANTalon.TalonControlMode.Speed){
					leftFlywheelMotor.changeControlMode(CANTalon.TalonControlMode.Speed);
				}
				if (rightFlywheelMotor.getControlMode() != CANTalon.TalonControlMode.Speed){
					rightFlywheelMotor.changeControlMode(CANTalon.TalonControlMode.Speed);
				}
				return FlywheelSubsystemMode.PID_MODE;
			} else {
				//No left encoder.
				System.err.println("Left flywheel sensor is not present.");
				//Left is the slave!!
				//Make sure the left is a follower.
				if (leftFlywheelMotor.getControlMode() != CANTalon.TalonControlMode.Follower){
					leftFlywheelMotor.changeControlMode(CANTalon.TalonControlMode.Follower);
					rightFlywheelMotor.set(Ports.RIGHT_FLYWHEEL_PORT);
				}
				return FlywheelSubsystemMode.LEFT_FOLLOWER_MODE;
			}
		} else {
			//No right encoder.
			System.err.println("Right flywheel sensor is not present.");
			if (left){
				//Left encoder,
				//Right's the slave
				if (rightFlywheelMotor.getControlMode() != CANTalon.TalonControlMode.Follower){
					rightFlywheelMotor.changeControlMode(CANTalon.TalonControlMode.Follower);
					rightFlywheelMotor.set(Ports.LEFT_FLYWHEEL_PORT);
				}
				return FlywheelSubsystemMode.RIGHT_FOLLOWER_MODE;
			} else {
				//NO SENSORS, NOOOOOO
				System.err.println("Left flywheel sensor is not present.");
				if (leftFlywheelMotor.getControlMode() != CANTalon.TalonControlMode.PercentVbus){
					leftFlywheelMotor.changeControlMode(CANTalon.TalonControlMode.PercentVbus);
				}
				if (rightFlywheelMotor.getControlMode() != CANTalon.TalonControlMode.PercentVbus){
					rightFlywheelMotor.changeControlMode(CANTalon.TalonControlMode.PercentVbus);
				}
				return FlywheelSubsystemMode.VOLTAGE_MODE;//Goin back to the good old days.
			}
		}
	}
}
