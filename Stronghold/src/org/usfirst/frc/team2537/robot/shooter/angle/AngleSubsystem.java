package org.usfirst.frc.team2537.robot.shooter.angle;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.PWM;
import java.util.HashMap;
import org.usfirst.frc.team2537.robot.input.HumanInput;
import org.usfirst.frc.team2537.robot.input.Ports;
import org.usfirst.frc.team2537.robot.input.Sensor;
import org.usfirst.frc.team2537.robot.input.SensorListener;

/**
 * @author Matthew Schweiss
 * 
 * This is the base class for anything adjusting the angle of the 
 * shooter.
 *
 */
public class AngleSubsystem extends Subsystem implements SensorListener {
	//Limit Switches
	// TODO determine default states of limit switches
	private static final boolean FWD_LIMIT_SWITCH_NORMALLY_OPEN = true;
	private static final boolean REV_LIMIT_SWITCH_NORMALLY_OPEN = true;
	private static final boolean ENABLE_SOFT_LIMIT = true;
	private static final double ENCODER_TICKS_PER_REV = 1000;
	private final Counter tiltSensor;//tilt sensor that is a pwm over the dio port in ports
	//The angle limits.
	private static final double MAX_ANGLE = 80;//degrees (ball park, not right)
	private static final double MIN_ANGLE = -40;//degrees(ball park, not right)
	
	//Varibles
	private static double currentAngle = 0;
	private final CANTalon angleTalon;
	
	public AngleSubsystem() {
		tiltSensor = new Counter(Ports.TILT_SENSOR_PORT);//tilt sensor that is a pwm over the dio port in ports
		angleTalon = new CANTalon(Ports.SHOOTER_ANGLE_PORT);
		//Start things.
		angleTalon.changeControlMode(CANTalon.TalonControlMode.Voltage);
		//Add limits.
		angleTalon.ConfigFwdLimitSwitchNormallyOpen(FWD_LIMIT_SWITCH_NORMALLY_OPEN);
		angleTalon.ConfigRevLimitSwitchNormallyOpen(REV_LIMIT_SWITCH_NORMALLY_OPEN);
		angleTalon.enableLimitSwitch(true, true);//Now the limit switches are active.
		//Soft limits for a backup.
		angleTalon.setForwardSoftLimit(MAX_ANGLE/360 * ENCODER_TICKS_PER_REV);
		angleTalon.enableForwardSoftLimit(ENABLE_SOFT_LIMIT);
		angleTalon.setReverseSoftLimit(MIN_ANGLE/360 * ENCODER_TICKS_PER_REV);
		angleTalon.enableReverseSoftLimit(ENABLE_SOFT_LIMIT);
		tiltSensor.setSemiPeriodMode(true); //set the tilt sensor to semi period mode. This means we are only measuring the period of the high pulses. When this is true, it counts just high pulses. 
		//http://wpilib.screenstepslive.com/s/4485/m/13809/l/241874-counters-measuring-rotation-counting-pulses-and-more
	}
	
	@Override
	public void initDefaultCommand() {
		//Create the Default command.
		this.setDefaultCommand(new ManualAngleCommand());
	}
	
//	/**
//	 * Get the voltage measures from the talon.
//	 * @return The voltage measure in range [-1, 1]
//	 */
//	public double getAngleSpeed(){
//		//Set the speed of the motor that will change the angle.
//		return this.angleTalon.get();
//		}
	
	/**
	 * Set the speed this should move at.
	 * @param speed A speed between [-1, 1] which is the voltage that will be set.
	 */
	public void setAngleSpeed(double speed){
		//Set the speed of the motor that will change the angle.
		this.angleTalon.set(speed);
		}
	
	
	//Did we hit a limit.
	/**
	 * Checks to see of the top limit switch is activated, showing the angle is at it max.
	 * If the switch is not present, the result will be false.
	 * 
	 * @return boolean if the forward limit switch is activated.
	 */
	public boolean isHighestPosition(){
		return this.angleTalon.isFwdLimitSwitchClosed();
	}
	
	/**
	 * Checks to see of the lower limit switch is activated, showing the angle is at it minimum.
	 * If the switch is not present, the result will be false.
	 * 
	 * @return boolean if the forward limit switch is activated.
	 */
	public boolean isLowestPosition(){
		return this.angleTalon.isRevLimitSwitchClosed();
	}
	
	//And get joystick values.
	/**
	 * Get the value of the Joystick axis.
	 * @return A value in range [-1, 1]
	 */
	public double getJoystickAngle() {
		//The angle Joystick is the left joystick on the XBOX
		return HumanInput.getXboxAxis(HumanInput.xboxController, HumanInput.XBOX_LEFT_STICK_Y_AXIS);
	}
	
	@Override
	/**
	 * This sets the currentAngle based on current readings from sensor hub.
	 * If the sensor is not present, this will take guess at correct values.
	 * 
	 * @param sensorMap A map of the sensors containing the sensor values to look at by look up by the 
	 * 					two character key associated with each sensor.
	 */
	public void receivedValue(HashMap<String, Double> sensorMap) {
		try{
			currentAngle = sensorMap.get(Sensor.SHOOTER_ANGLE);
		} catch (NullPointerException e){
			//Nothing...
			//No way to know the angle.
		}
	}
	
	/**
	 * This gets the current cached angle value.
	 * @return angle in degrees that was cached from the sensors earlier.
	 * 			If the angle is not regularly given, give either 0 or the most recent value
	 * 			however old that maybe. Range [-180, 180] though the should be between 
	 * 			[MIN_ANGLE, MAX_ANGLE] typically.
	 */
	public double getCurrentAngle(){
		return currentAngle;
	}

	public void registerButtons() {
		//Needed but not used.
		
	}
	public double getTiltSensorPeriod(){
		return tiltSensor.getPeriod(); //period will change with the angle. I would assume it would get longer as the angle increases. This returns the time interval of the most recent count.
	}
	public double getTiltSensorAngle() {
		return (tiltSensor.getPeriod() * (-2.083 * Math.pow(10, 6)) + 4184.375); //magic numbers acquired from placing some approximate angles into a graph in google sheets and acquiring the angles for that 
	}
	
}

