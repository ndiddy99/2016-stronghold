package org.usfirst.frc.team2537.robot.shooter.angle;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.CANTalon;
import java.util.HashMap;

//import org.usfirst.frc.team2537.robot.input.HumanInput;
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
	
	//The angle limits.
	public static final double MAX_ANGLE = 80;//degrees (ball park, not right)
	public static final double MIN_ANGLE = -40;//degrees(ball park, not right)
	
	//Varibles
	private static double currentAngle = 0;
	private final CANTalon angleTalon;
	
	public AngleSubsystem() {
		angleTalon = new CANTalon(Ports.TALON_SHOOTER_ANGLE_PORT);
		//Start things.
		angleTalon.changeControlMode(CANTalon.TalonControlMode.Voltage);
		//Add limits.
		angleTalon.ConfigFwdLimitSwitchNormallyOpen(FWD_LIMIT_SWITCH_NORMALLY_OPEN);
		angleTalon.ConfigRevLimitSwitchNormallyOpen(REV_LIMIT_SWITCH_NORMALLY_OPEN);
		angleTalon.enableLimitSwitch(true, true);//Now the limit switches are active.
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
	 * @return boolean if the forward limit switch is activated.
	 */
	public boolean isHighestPosition(){
		return this.angleTalon.isFwdLimitSwitchClosed();
	}
	
	/**
	 * Checks to see of the lower limit switch is activated, showing the angle is at it minimum.
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
	 * 
	 * @param sensorMap A map of the sensors containing the sensor values to look at by look up by the 
	 * 					two character key associated with each sensor.
	 */
	public void receivedValue(HashMap<String, Double> sensorMap) {
		try{
			currentAngle = sensorMap.get(Sensor.SHOOTER_ANGLE);
		} catch (NullPointerException e){
			//Nothing...
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
}