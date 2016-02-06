package org.usfirst.frc.team2537.robot.shooter.actuator;

import edu.wpi.first.wpilibj.command.Subsystem;

import org.usfirst.frc.team2537.robot.input.Ports;

import edu.wpi.first.wpilibj.CANTalon;

/**
 *
 */
public class NewActuatorSubsystem extends Subsystem {
	//Constants
	private static final boolean FWD_LIMIT_SWITCH_NORMALLY_OPEN = true;
	private static final boolean REV_LIMIT_SWITCH_NORMALLY_OPEN = true;
	private static final double ENCODER_TICKS_PER_REV = 1000;
	
	private static final boolean ENABLE_SOFT_LIMIT = false;
	private static final double MAX_ANGLE = 90;//degrees (ball park, not right)
	private static final double MIN_ANGLE = 0;//degrees(ball park, not right)
	
	//Max current, over which we say it is a fault.
	private static final double MAX_CURRENT = 4;//Ampere's
	//Vars
	private CANTalon actuatorMotor;
	
	public NewActuatorSubsystem() {
		actuatorMotor = new CANTalon(Ports.TALON_ACTUATOR_PORT);
		//Start things.
		actuatorMotor.changeControlMode(CANTalon.TalonControlMode.PercentVbus);
		//Add limits.
		actuatorMotor.ConfigFwdLimitSwitchNormallyOpen(FWD_LIMIT_SWITCH_NORMALLY_OPEN);
		actuatorMotor.ConfigRevLimitSwitchNormallyOpen(REV_LIMIT_SWITCH_NORMALLY_OPEN);
		actuatorMotor.enableLimitSwitch(true, true);//Now the limit switches are active.
		//Soft limits for a backup.
		actuatorMotor.setForwardSoftLimit(MAX_ANGLE/360 * ENCODER_TICKS_PER_REV);
		actuatorMotor.enableForwardSoftLimit(ENABLE_SOFT_LIMIT);
		actuatorMotor.setReverseSoftLimit(MIN_ANGLE/360 * ENCODER_TICKS_PER_REV);
		actuatorMotor.enableReverseSoftLimit(ENABLE_SOFT_LIMIT);
	}
	
	//Did we hit a limit.
	/**
	 * Checks to see of the top limit switch is activated, showing the angle is at it max.
	 * If the switch is not present, this will check if the current pull is over a limit, if so
	 * the result will be true.
	 * 
	 * @return boolean if the forward limit switch is activated.
	 */
	public boolean isHighestPosition(){
		return actuatorMotor.isFwdLimitSwitchClosed() //Motor is against Forward Limit Switch
				|| //or
				(
				Math.abs(actuatorMotor.getOutputCurrent()) >= MAX_CURRENT//current pull is over the expected values
				&& //and
				actuatorMotor.getOutputVoltage() > 0 //Voltage is in positive direction
				);
				
	}
	
	/**
	 * Checks to see of the lower limit switch is activated, showing the angle is at it minimum.
	 * If the switch is not present, this will check if the current pull is over a limit, if so
	 * the result will be true.
	 * 
	 * @return boolean if the forward limit switch is activated.
	 */
	public boolean isLowestPosition(){
		return actuatorMotor.isRevLimitSwitchClosed()
				|| //or
				(
				Math.abs(actuatorMotor.getOutputCurrent()) >= MAX_CURRENT//current pull is over the expected values
				&& //and
				actuatorMotor.getOutputVoltage() < 0 //Voltage is in negative direction
				);
	}
	
	/**
	 * Set the power of the actuator motor.
	 * 
	 * @param power The percentage of power to apply to the motor. 
	 * 				This should be [-1, 1]
	 */
	public void setMotorPower(double power){
		actuatorMotor.set(power);
	}
	
	/**
	 * Get the power the motor is moving at.
	 * @return A double power percentage as [-1, 1]
	 */
	public double getMotorPower(){
		return actuatorMotor.getOutputVoltage();
	}
	
	/**
	 * Get the angle from the encoder.
	 * @return a double of the angle the encoder is at.
	 */
	public double getMotorPosition(){
		return actuatorMotor.getEncPosition();
	}
	
	@Override
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	//No defualt command.
    }
	
	public void registerButtons() {
		//Needed but not used.
		
	}
}

