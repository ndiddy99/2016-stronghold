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
	private static CANTalon leftFlywheelMotor;
	private static CANTalon rightFlywheelMotor;
	
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
		HumanInput.registerPressedCommand(HumanInput.ballShootTrigger, new ShootCommand());
		HumanInput.registerWhileHeldCommand(HumanInput.harvestBallTrigger, new HarvestCommand());
	}
	
	//Let the commands have assess to temperature readings.
	
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

