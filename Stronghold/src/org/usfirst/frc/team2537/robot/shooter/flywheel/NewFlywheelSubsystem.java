package org.usfirst.frc.team2537.robot.shooter.flywheel;

import java.util.HashMap;

import org.usfirst.frc.team2537.robot.input.HumanInput;
import org.usfirst.frc.team2537.robot.input.Ports;
import org.usfirst.frc.team2537.robot.input.Sensor;
import org.usfirst.frc.team2537.robot.input.SensorListener;
import org.usfirst.frc.team2537.robot.shooter.HarvestCommand;
import org.usfirst.frc.team2537.robot.shooter.ShootCommand;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class NewFlywheelSubsystem extends Subsystem implements SensorListener {
    
	//Constants
	private static final CANTalon.FeedbackDevice ENCODER = CANTalon.FeedbackDevice.QuadEncoder;
	private static final int ENCODER_TICKS_PER_REV = 20;
	private static final double P = .5, I = .05, D = 0.0;
	//Vars
	private boolean proximityValue = false;
	private CANTalon leftFlywheelMotor;
	private CANTalon rightFlywheelMotor;
	
	public NewFlywheelSubsystem() {
		//Starting motors.
		//Make sure the the mode to velocity so we can modify it.
		leftFlywheelMotor 	= new CANTalon(Ports.TALON_LEFT_FLYWHEEL_PORT);
		rightFlywheelMotor 	= new CANTalon(Ports.TALON_RIGHT_FLYWHEEL_PORT);
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
		//Set PID's
		leftFlywheelMotor.setPID(P, I, D);
		leftFlywheelMotor.setF(0);//No idea what this does, so disable it.
		rightFlywheelMotor.setPID(P, I, D);
		rightFlywheelMotor.setF(0);
		//Make the talon's go to the right control mode.
		//Should be default, not sure if this should be here.
		leftFlywheelMotor.changeControlMode(CANTalon.TalonControlMode.Speed);
		rightFlywheelMotor.changeControlMode(CANTalon.TalonControlMode.Speed);
	}
	
	@Override
    public void initDefaultCommand() {
		
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	//setDefaultCommand(new ShotBallCommand());
    	//There is not a default command.
    }
	
	public void registerButtons() {
		HumanInput.registerPressedCommand(HumanInput.ballShootTrigger, new NewShooterCommandGroup());
		HumanInput.registerWhileHeldCommand(HumanInput.harvestBallTrigger,new NewHarvestCommandGroup());
	}
	
	//Set and get speed
	//LEFT
	/**
	 * Set the speed of the left motor.
	 * 
	 * @param speed in RPM.
	 */
	public void setLeftSpeed(double speed){
		leftFlywheelMotor.set(speed);
	}
	
	/**
	 * Get the current speed of the left flywheel.
	 * 
	 * @return flywheel speed in RPM
	 */
	public double getLeftSpeed(){
		return leftFlywheelMotor.getSpeed();
	}
	
	/**
	 * Get the error of the left flywheel.
	 * This is the distance from the wanted speed and the current speed.
	 * @return Difference between current speed and wanted speed.
	 * 			TODO find unit.
	 */
	public double getLeftError(){
		return leftFlywheelMotor.getError();
	}
	
	//RIGHT
	/**
	 * Set the speed of the right motor.
	 * 
	 * @param speed in RPM.
	 */
	public void setRightSpeed(double speed){
		rightFlywheelMotor.set(speed);
	}
	
	/**
	 * Get the current speed of the left flywheel.
	 * 
	 * @return flywheel speed in RPM
	 */
	public double getRightSpeed(){
		return rightFlywheelMotor.getSpeed();
	}
	
	/**
	 * Get the error of the left flywheel.
	 * This is the distance from the wanted speed and the current speed.
	 * @return Difference between current speed and wanted speed.
	 * 			TODO find unit.
	 */
	public double getRightError(){
		return rightFlywheelMotor.getError();
	}
	
	
	
	@Override
	//Proximity
	public void receivedValue(HashMap<String, Double> sensorMap) {
		//BEWARE OF NullPointer
		try {
			proximityValue = sensorMap.get(Sensor.SHOOTER_BALL) == 1;
		} catch (NullPointerException e){
			//We have an error.
			//We don't actually care.
			System.err.println("FlywheelSys recieved null proximity value.");
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

