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
 */
public class AngleSubsystem extends Subsystem implements SensorListener {
	//All of the motors.
	private static final CANTalon angleTalon = new CANTalon(Ports.TALON_SHOOTER_ANGLE_PORT);
	//Limit Switches
	// TODO determine default states of limit switches
	private static final boolean FWD_LIMIT_SWITCH_NORMALLY_OPEN = true;
	private static final boolean REV_LIMIT_SWITCH_NORMALLY_OPEN = true;
	
	//The angle limits.
	public static final double MAX_ANGLE = 80;//degrees (ball park, not right)
	public static final double MIN_ANGLE = -40;//degrees(ball park, not right)
	private static double currentAngle = 0;
	
	public AngleSubsystem() {
		//Start things.
		angleTalon.changeControlMode(CANTalon.TalonControlMode.Voltage);
		//Add limits.
		angleTalon.ConfigFwdLimitSwitchNormallyOpen(FWD_LIMIT_SWITCH_NORMALLY_OPEN);
		angleTalon.ConfigRevLimitSwitchNormallyOpen(REV_LIMIT_SWITCH_NORMALLY_OPEN);
		angleTalon.enableLimitSwitch(true, true);//Now the limit switches are active.
	}
	
	@Override
	protected void initDefaultCommand() {
		//Create the Default command.
		this.setDefaultCommand(new ManualAngleCommand());
	}
	
	//Shooter angle controls.
	/**
	 * Get the angle of the shooter.
	 * @return An angle that the shooter is at. This will be in degrees. Range [-180, 180] though
	 * 			The should be between [MIN_ANGLE, MAX_ANGLE] typically.
	 */
	
	/**
	 * Get the voltage measures from the talon.
	 * @return The voltage measure in range [-1, 1]
	 */
	public double getAngleSpeed(){
		//Set the speed of the motor that will change the angle.
		return this.angleTalon.get();
		}
	
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

	public void receivedValue(HashMap<String, Double> sensorMap) {
		currentAngle = sensorMap.get(Sensor.SHOOTER_ANGLE);
	}
	public static double getCurrentAngle() {
		return currentAngle;
	}
}