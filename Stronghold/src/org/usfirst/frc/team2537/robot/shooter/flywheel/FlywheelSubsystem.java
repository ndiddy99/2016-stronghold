package org.usfirst.frc.team2537.robot.shooter.flywheel;

import java.util.HashMap;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.usfirst.frc.team2537.robot.input.Ports;
import org.usfirst.frc.team2537.robot.input.Sensor;
import org.usfirst.frc.team2537.robot.input.HumanInput;
import org.usfirst.frc.team2537.robot.input.SensorListener;
import org.usfirst.frc.team2537.robot.shooter.ShootCommand;
import org.usfirst.frc.team2537.robot.shooter.HarvestCommand;

public class FlywheelSubsystem extends Subsystem implements SensorListener {	
	//Constants
	private static final boolean CHECK_TEMPERATURE	= true;
	private static final double  MAX_TEMPERATURE	= 100;//celsuis
	private static final CANTalon.FeedbackDevice ENCODER = CANTalon.FeedbackDevice.QuadEncoder;
	//Vars
	private boolean proximityValue = false;
	private static CANTalon leftFlywheelMotor;
	private static CANTalon rightFlywheelMotor;
	
	public FlywheelSubsystem() {
		//Starting motors.
		//Make sure the the mode to velocity so we can modify it.
		leftFlywheelMotor 	= new CANTalon(Ports.TALON_LEFT_FLYWHEEL_PORT);
		rightFlywheelMotor 	= new CANTalon(Ports.TALON_RIGHT_FLYWHEEL_PORT);
				//Make the talon's go to the right control mode.
		//Should be default, not sure if this should be here.
		leftFlywheelMotor.changeControlMode(CANTalon.TalonControlMode.PercentVbus);
		rightFlywheelMotor.changeControlMode(CANTalon.TalonControlMode.PercentVbus);
	}
	
	@Override
    public void initDefaultCommand() {
		
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	//setDefaultCommand(new ShotBallCommand());
    	//There is not a default command.
    }
	
	public void registerButtons() {
		HumanInput.toggleWhenPressed(HumanInput.ballShootTrigger, new ShootCommand());
		HumanInput.registerWhileHeldCommand(HumanInput.harvestBallTrigger, new HarvestCommand());
		
	}
	//Shooter Left Flywheel controls.
	public double getLeftFlywheelVelocity() {
		switch (leftFlywheelMotor.isSensorPresent(ENCODER)){
		case FeedbackStatusPresent://Why are they in the main frame like this?
			//It's there! this is easy.
			return -leftFlywheelMotor.getEncVelocity();
			
		case FeedbackStatusUnknown:
			//WE don't know if the sensor is there.
			System.err.println("FlywheelSubsystem does not know if there is a left Encoder.");
			
		default:// FeedbackStatusNotPresent
			//We know it is not there, so we can make up a value!
			return -leftFlywheelMotor.getOutputVoltage()/12.0;
		}
	}
	
	/**
	 * Set the velocity of the left flywheel.
	 * 
	 * @param velocity The voltage amount the wheel will be set to. 
	 * 				This should be [-1, 1].
	 */

	public void setLeftFlywheelVelocity(double velocity) {
		System.out.println("L Speed = " + velocity);
		leftFlywheelMotor.set(-velocity);
	}
	
	//Shooter Right Flywheel controls.
	public double getRightFlywheelVelocity() {
		switch (rightFlywheelMotor.isSensorPresent(ENCODER)){
		case FeedbackStatusPresent://Why are they in the main frame like this?
			//It's there! this is easy.
			return rightFlywheelMotor.getEncVelocity();
			
		case FeedbackStatusUnknown:
			//WE don't know if the sensor is there.
			System.err.println("FlywheelSubsystem does not know if there is a right Encoder.");
			
		default:// FeedbackStatusNotPresent
			//We know it is not there, so we can make up a value!
			return rightFlywheelMotor.getOutputVoltage()/12.0;
		}
	}
	
	/**
	 * Set the velocity of the right flywheel.
	 * 
	 * @param velocity
	 */
	public void setRightFlywheelVelocity(double velocity) {
		System.out.println("R Speed = " + velocity);
		rightFlywheelMotor.set(velocity);
	}
	
	//Let the commands have assess to temperature readings.
	public boolean isTemperatureFault(){
		//Check to make sure I'm not on fire!!
		return CHECK_TEMPERATURE && (leftFlywheelMotor.getTemperature() >= MAX_TEMPERATURE || 
				rightFlywheelMotor.getTemperature() >= MAX_TEMPERATURE);
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
	
	public boolean proximityValue(){
		return proximityValue;
	}
	public void setFlywheelsRampRate(double voltageValue) {
		rightFlywheelMotor.setVoltageRampRate(voltageValue);
		leftFlywheelMotor.setVoltageRampRate(voltageValue);
	}
}
