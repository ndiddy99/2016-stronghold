package org.usfirst.frc.team2537.robot.shooter.angle;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.CANTalon;
import java.util.HashMap;
import org.usfirst.frc.team2537.robot.input.HumanInput;
import org.usfirst.frc.team2537.robot.input.Ports;
import org.usfirst.frc.team2537.robot.input.Sensor;
import org.usfirst.frc.team2537.robot.input.SensorListener;
import org.usfirst.frc.team2537.robot.input.XboxButtons;

/**
 * @author Matthew Schweiss
 * 
 *         This is the base class for anything adjusting the angle of the
 *         shooter.
 *
 */
public class AngleSubsystem extends Subsystem implements SensorListener {
	
	// The angle limits.
	private static final double MAX_ANGLE = 20;// degrees (ball park, not right)
	private static final double MIN_ANGLE = 0;// degrees(ball park, not right)
	private static final double MAX_VOLTAGE = 6;
	//Difference between the max and min angle.
	public static final double MAX_ANGLE_DIFFERENCE = MAX_ANGLE - MIN_ANGLE; 
	//Debugs
	public final boolean DEBUG = false;

	// Varibles
	private static double currentAngle = 0;
	private final CANTalon angleMotor;

	public AngleSubsystem() {
		angleMotor = new CANTalon(Ports.SHOOTER_ANGLE_PORT);
		// Change control mode of the angleTalon to percent Vbus.
		angleMotor.changeControlMode(CANTalon.TalonControlMode.PercentVbus);
		// Add limits.
		angleMotor.ConfigFwdLimitSwitchNormallyOpen(true);
		angleMotor.ConfigRevLimitSwitchNormallyOpen(true);
		angleMotor.enableLimitSwitch(true, true);// Now the limit switches are
													// active.
		// Soft limits for a backup.
		angleMotor.enableForwardSoftLimit(false);
		angleMotor.enableReverseSoftLimit(false);
		
		//The motor will backdrive if it does not get current.
		//Set a electric break.
		angleMotor.enableBrakeMode(true);
		
		//We don't want this going so fast.
		angleMotor.configMaxOutputVoltage(MAX_VOLTAGE);
	}

	@Override
	public void initDefaultCommand() {
		// Create the Default command.
		this.setDefaultCommand(new ManualAngleCommand());
	}

	/**
	 * Set the speed of the motor that will change the angle.
	 * @param percent
	 *            A speed between [-1, 1] which is the voltage that will be set.
	 */
	public void setVoltagePercent(double percent) {
		if (isHighestPosition() && (percent > 0)) return;//Don't set
		if (isLowestPosition() && (percent < 0)) return;//Don't set
		//else
		angleMotor.set(percent);
	}

	// Did we hit a limit.
	/**
	 * Checks to see of the top limit switch is activated, showing the angle is
	 * at it max. If the switch is not present, the result will be false.
	 * 
	 * @return boolean if the forward limit switch is activated.
	 */
	public boolean isHighestPosition() {
		return currentAngle >= MAX_ANGLE || angleMotor.isFwdLimitSwitchClosed();
	}

	/**
	 * Checks to see of the lower limit switch is activated, showing the angle
	 * is at it minimum. If the switch is not present, the result will be false.
	 * 
	 * @return boolean if the forward limit switch is activated.
	 */
	public boolean isLowestPosition() {
		return currentAngle <= MIN_ANGLE || angleMotor.isRevLimitSwitchClosed();
	}

	// And get joystick values.
	/**
	 * Get the value of the xbox left stick Y axis.
	 * 
	 * @return A value in range [-1, 1]
	 */
	public double getJoystickAngle() {
		// The angle Joystick is the left joystick on the XBOX
		return HumanInput.getXboxAxis(HumanInput.xboxController, XboxButtons.XBOX_LEFT_Y_AXIS);
	}

	@Override
	/**
	 * This sets the currentAngle based on current readings from sensor hub. If
	 * the sensor is not present, this will take guess at correct values.
	 * 
	 * @param sensorMap
	 *            A map of the sensors containing the sensor values to look at
	 *            by look up by the two character key associated with each
	 *            sensor.
	 */
	public void receivedValue(HashMap<Sensor, Double> sensorMap) {
		Double value = sensorMap.get(Sensor.SHOOTER_ANGLE);
		if (value != null){
			currentAngle = value;
		}
		if (DEBUG) System.out.println("Shooter Angle: " + value);
		
		//SOFT LIMITS
		if (currentAngle <= MIN_ANGLE || currentAngle >= MAX_ANGLE){
			//TOO High or low stop motor.
			System.out.println("Position Is at Max.");
			angleMotor.set(0);
		}
	}

	/**
	 * This gets the current cached angle value.
	 * 
	 * @return angle in degrees that was cached from the sensors earlier. If the
	 *         angle is not regularly given, give either 0 or the most recent
	 *         value however old that maybe. Range [-180, 180] though the should
	 *         be between [MIN_ANGLE, MAX_ANGLE] typically.
	 */
	public double getCurrentAngle() {
		return currentAngle;
	}

	public void registerButtons() {
		// Needed but not used.

	}
}