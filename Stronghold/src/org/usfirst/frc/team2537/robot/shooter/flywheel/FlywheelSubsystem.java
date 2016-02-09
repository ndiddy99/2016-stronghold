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
 * Important notice!
 * If we use getSoftLimit() it will be in native units.
 */
public class FlywheelSubsystem extends Subsystem implements SensorListener {
    
	//Constants
	private static final CANTalon.FeedbackDevice ENCODER = CANTalon.FeedbackDevice.QuadEncoder;
	private static final int ENCODER_TICKS_PER_REV = 20;
	private static final double MOTOR_TOP_SPEED = 2000;//RPM
	private static final double P = .5, I = .05, D = 0.0;
	//Vars
	private boolean proximityValue = false;
	private CANTalon leftFlywheelMotor;
	private CANTalon rightFlywheelMotor;
	private final static boolean TESTING = true;
	
	public FlywheelSubsystem() {
		//Starting motors.
		//Make sure the the mode to velocity so we can modify it.
		leftFlywheelMotor 	= new CANTalon(Ports.LEFT_FLYWHEEL_PORT);
		rightFlywheelMotor 	= new CANTalon(Ports.RIGHT_FLYWHEEL_PORT);
		//Set encoder.
		leftFlywheelMotor.setFeedbackDevice(ENCODER);
		leftFlywheelMotor.configEncoderCodesPerRev(ENCODER_TICKS_PER_REV);
		rightFlywheelMotor.setFeedbackDevice(ENCODER);
		rightFlywheelMotor.configEncoderCodesPerRev(ENCODER_TICKS_PER_REV);
		//Make sure the soft limits are disabled.
		leftFlywheelMotor.enableForwardSoftLimit(false);
		leftFlywheelMotor.enableReverseSoftLimit(false);
		rightFlywheelMotor.enableForwardSoftLimit(false);
		rightFlywheelMotor.enableReverseSoftLimit(false);
		//Disable the limit switchs, as they do not exist.
		leftFlywheelMotor.enableLimitSwitch(false, false);
		rightFlywheelMotor.enableLimitSwitch(false, false);
		//Nominal voltages, not sure if this is needed
		leftFlywheelMotor.configNominalOutputVoltage(0.0, 0.0);
    	leftFlywheelMotor.configPeakOutputVoltage(12.0, -12.0);
		rightFlywheelMotor.configNominalOutputVoltage(0.0, 0.0);
    	rightFlywheelMotor.configPeakOutputVoltage(12.0, -12.0);
    	//Set rightFlywheelMotor to be reversed of everything else.
    	rightFlywheelMotor.reverseOutput(true);
    	rightFlywheelMotor.reverseSensor(true);
    	leftFlywheelMotor.reverseOutput(false);
    	leftFlywheelMotor.reverseSensor(false);
		//Set PID's
		leftFlywheelMotor.setPID(P, I, D);
		leftFlywheelMotor.setF(0);//No idea what this does, so disable it.
		rightFlywheelMotor.setPID(P, I, D);
		rightFlywheelMotor.setF(0);
		//Make the talon's go to the right control mode.
		//Should be default, not sure if this should be here.
		leftFlywheelMotor.changeControlMode(CANTalon.TalonControlMode.Speed);
		rightFlywheelMotor.changeControlMode(CANTalon.TalonControlMode.Follower);
		rightFlywheelMotor.set(Ports.LEFT_FLYWHEEL_PORT);
	}
	
	@Override
    public void initDefaultCommand() {
		
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	//setDefaultCommand(new ShotBallCommand());
    	//There is not a default command.
    }
	
	public void registerButtons() {
		HumanInput.registerPressedCommand(HumanInput.ballShootTrigger,		new ShootCommandGroup());
		HumanInput.registerWhileHeldCommand(HumanInput.harvestBallTrigger,	new HarvestCommandGroup());
	}
	
	//Set and get speed
	/**
	 * Set the speed of the motors.
	 * @param speed The speed in RPM if each if the motors.
	 */
	public void setSpeed(double speed){
		if (leftFlywheelMotor.isSensorPresent(ENCODER) == CANTalon.FeedbackDeviceStatus.FeedbackStatusPresent 
				&& 
			rightFlywheelMotor.isSensorPresent(ENCODER) == CANTalon.FeedbackDeviceStatus.FeedbackStatusPresent){
			//Both sensors are present.
			leftFlywheelMotor.set(speed);
			//Set the right only if it is not follower mode.
			if (rightFlywheelMotor.getControlMode() != CANTalon.TalonControlMode.Follower){
				rightFlywheelMotor.set(speed);
			}
			
		} else {
			//Print error
			if (leftFlywheelMotor.isSensorPresent(ENCODER) == CANTalon.FeedbackDeviceStatus.FeedbackStatusPresent){
				//Left sensor is code, right must be bad.
				System.err.println("Right flywheel sensor is not present.");
			} else {
				System.err.println("Left flywheel sensor is not present.");
			}
			//Make something up!!
			CANTalon.TalonControlMode rightMode = rightFlywheelMotor.getControlMode();
			CANTalon.TalonControlMode leftMode  = leftFlywheelMotor.getControlMode();
			leftFlywheelMotor.changeControlMode(CANTalon.TalonControlMode.PercentVbus);
			leftFlywheelMotor.set(speed / MOTOR_TOP_SPEED);//Speed divided Top speed is voltage
			leftFlywheelMotor.changeControlMode(leftMode);
			
			//Only set right if it is not follower mode.
			if (rightMode != CANTalon.TalonControlMode.Follower){
				rightFlywheelMotor.changeControlMode(CANTalon.TalonControlMode.PercentVbus);
				rightFlywheelMotor.set(speed / MOTOR_TOP_SPEED);
				rightFlywheelMotor.changeControlMode(rightMode);
			}
		}
	}
	//LEFT
//	/**
//	 * Set the speed of the left motor.
//	 * 
//	 * @param speed in RPM.
//	 */
//	public void setLeftSpeed(double speed){
//		switch (leftFlywheelMotor.isSensorPresent(ENCODER)){
//		
//		case FeedbackStatusPresent:
//			//Yes sensor is present.
//			leftFlywheelMotor.set(speed);
//			
//		case FeedbackStatusUnknown:
//			//I don't know, assuming it is not there.
//			System.err.println("Left Encoder may not be present.");
//			
//		case FeedbackStatusNotPresent:
//			System.err.println("Left Encoder is not present.");
//			
//		default:
//			//Make up something!!!
//			//Ok, so speed will be [-1023, 1023]
//			//Voltage is [-1, 1]
//			//So in theory, speed/1023 = voltage.
//			leftFlywheelMotor.changeControlMode(CANTalon.TalonControlMode.PercentVbus);
//			leftFlywheelMotor.set(speed/1023);
//			leftFlywheelMotor.changeControlMode(CANTalon.TalonControlMode.Speed);
//		}
//	}
	
	
	/**
	 * Get the current speed of the left flywheel.
	 * 
	 * @return flywheel speed in RPM
	 */
	public double getLeftSpeed(){
		switch (leftFlywheelMotor.isSensorPresent(ENCODER)){
		
		case FeedbackStatusPresent:
			//Yes sensor is present.
			return leftFlywheelMotor.getSpeed();
			
		case FeedbackStatusUnknown:
			//I don't know, assuming it is not there.
			System.err.println("Right Encoder may not be present.");
			
		case FeedbackStatusNotPresent:
			System.err.println("Right Encoder is not present.");
			
		default:
			//Make up something!!!
			//return leftFlywheelMotor.get();
			return leftFlywheelMotor.getOutputVoltage() / leftFlywheelMotor.getBusVoltage() * MOTOR_TOP_SPEED;
		}
	}
	
	/**
	 * Get the error of the left flywheel.
	 * This is the distance from the wanted speed and the current speed.
	 * @return Difference between current speed and wanted speed. In RPM
	 */
	public double getLeftError(){
		//We want to change this from native units (units per 100ms) to RPM.
		//return leftFlywheelMotor.getError()/100/4096*1000*60;
		
		//Simplified for speed.
		return leftFlywheelMotor.getError()*75/512;
	}
	
	//RIGHT
//	/**
//	 * Set the speed of the right motor.
//	 * 
//	 * @param speed in RPM.
//	 */
//	public void setRightSpeed(double speed){
//		switch (leftFlywheelMotor.isSensorPresent(ENCODER)){
//		
//		case FeedbackStatusPresent:
//			//Yes sensor is present.
//			rightFlywheelMotor.set(speed);
//			
//		case FeedbackStatusUnknown:
//			//I don't know, assuming it is not there.
//			System.err.println("Right Encoder may not be present.");
//			
//		case FeedbackStatusNotPresent:
//			System.err.println("Right Encoder is not present.");
//			
//		default:
//			//Make up something!!!
//			//Ok, so speed will be [-1023, 1023]
//			//Voltage is [-1, 1]
//			//So in theory, speed/1023 = voltage.
//			rightFlywheelMotor.changeControlMode(CANTalon.TalonControlMode.PercentVbus);
//			rightFlywheelMotor.set(speed/1023);
//			rightFlywheelMotor.changeControlMode(CANTalon.TalonControlMode.Speed);
//		}
//	}
	
	/**
	 * Get the current speed of the left flywheel.
	 * 
	 * @return flywheel speed in RPM
	 */
	public double getRightSpeed(){
		switch (rightFlywheelMotor.isSensorPresent(ENCODER)){
		
		case FeedbackStatusPresent:
			//Yes sensor is present.
			return rightFlywheelMotor.getSpeed();
			
		case FeedbackStatusUnknown:
			//I don't know, assuming it is not there.
			System.err.println("Right Encoder may not be present.");
			
		case FeedbackStatusNotPresent:
			System.err.println("Right Encoder is not present.");
			
		default:
			//Make up something!!!
			//return rightFlywheelMotor.get();
			//Alternatively
			return rightFlywheelMotor.getOutputVoltage() / rightFlywheelMotor.getBusVoltage() * MOTOR_TOP_SPEED;
			
		}
	}
	
	/**
	 * Get the error of the left flywheel.
	 * This is the distance from the wanted speed and the current speed.
	 * @return Difference between current speed and wanted speed in RPM.
	 */
	public double getRightError(){
		return rightFlywheelMotor.getError()*75/512;
	}
	
	/**
	 * Emergency stops the motors.
	 * This directly sets voltage to 0.
	 */
	public void stop(){
		//Left wheel stop.
		leftFlywheelMotor.changeControlMode(CANTalon.TalonControlMode.PercentVbus);
		leftFlywheelMotor.set(0);//STOP
		leftFlywheelMotor.changeControlMode(CANTalon.TalonControlMode.Speed);
		
		//Right wheel stop.
		rightFlywheelMotor.changeControlMode(CANTalon.TalonControlMode.PercentVbus);
		rightFlywheelMotor.set(0);
		rightFlywheelMotor.changeControlMode(CANTalon.TalonControlMode.Speed);	
	}
	
	public void go(){
		//Left wheel stop.
		leftFlywheelMotor.changeControlMode(CANTalon.TalonControlMode.PercentVbus);
		leftFlywheelMotor.set(1);//STOP
		leftFlywheelMotor.changeControlMode(CANTalon.TalonControlMode.Speed);
		
		//Right wheel stop.
		rightFlywheelMotor.changeControlMode(CANTalon.TalonControlMode.PercentVbus);
		rightFlywheelMotor.set(1);
		rightFlywheelMotor.changeControlMode(CANTalon.TalonControlMode.Speed);	
	}
	
	
	@Override
	//Proximity
	public void receivedValue(HashMap<String, Double> sensorMap) {
		//BEWARE OF NullPointer
		if(!TESTING) {
		try {
			proximityValue = sensorMap.get(Sensor.SHOOTER_BALL) == 1;
		} catch (NullPointerException e){
			//We have an error.
			//We don't actually care.
			System.err.println("FlywheelSys recieved null proximity value.");
			//Not known, make something up...
			//Though with that, we can't tell if there is a ball or not.
			proximityValue = false;//I don't see a ball...
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
	public boolean isBall(){
		return proximityValue;
	}
}

