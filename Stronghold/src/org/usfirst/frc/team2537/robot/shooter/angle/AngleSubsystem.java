package org.usfirst.frc.team2537.robot.shooter.angle;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.PWM;
import java.util.HashMap;
import org.usfirst.frc.team2537.robot.input.HumanInput;
import org.usfirst.frc.team2537.robot.input.Ports;
import org.usfirst.frc.team2537.robot.input.SensorEnum;
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
	//How many times we get a reading from the encoder every revolution.
	private static final double ENCODER_TICKS_PER_REV = 1000;
	//TODO Place the tilt sensor in it's own class.
	private final Counter tiltSensor;// tilt sensor that is a pwm over the dio
										// port in ports
	// The angle limits.
	private static final double MAX_ANGLE = 90;// degrees (ball park, not right)
	private static final double MIN_ANGLE = 0;// degrees(ball park, not right)
	//Difference between the max and min angle.
	public static final double MAX_ANGLE_DIFFERENCE = MAX_ANGLE - MIN_ANGLE; 
	//Debugs
	public static final boolean DEBUG = true;

	// Varibles
	private static double currentAngle = 0;
	private final CANTalon angleTalon;
	//This is in seconds
	private static final double TILT_SENSOR_MAX_PERIOD = 1990 * Math.pow(10, -6); 

	public AngleSubsystem() {
		tiltSensor = new Counter(Ports.TILT_SENSOR_PORT);// tilt sensor that is
															// a pwm over the
															// dio port in ports
		tiltSensor.setSemiPeriodMode(true); // set the tilt sensor to semi
											// period mode. This means we are
											// only measuring the period of the
											// high pulses. When this is true,
											// it counts just high pulses.
		// http://wpilib.screenstepslive.com/s/4485/m/13809/l/241874-counters-measuring-rotation-counting-pulses-and-more

		angleTalon = new CANTalon(Ports.SHOOTER_ANGLE_PORT);
		// Change control mode of the angleTalon to percent Vbus.
		angleTalon.changeControlMode(CANTalon.TalonControlMode.PercentVbus);
		// Add limits.
		angleTalon.ConfigFwdLimitSwitchNormallyOpen(true);
		angleTalon.ConfigRevLimitSwitchNormallyOpen(true);
		angleTalon.enableLimitSwitch(true, true);// Now the limit switches are
													// active.
		// Soft limits for a backup.
		angleTalon.setForwardSoftLimit(MAX_ANGLE / 360 * ENCODER_TICKS_PER_REV);
		angleTalon.enableForwardSoftLimit(true);
		angleTalon.setReverseSoftLimit(MIN_ANGLE / 360 * ENCODER_TICKS_PER_REV);
		angleTalon.enableReverseSoftLimit(true);

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
		angleTalon.set(percent);
	}

	// Did we hit a limit.
	/**
	 * Checks to see of the top limit switch is activated, showing the angle is
	 * at it max. If the switch is not present, the result will be false.
	 * 
	 * @return boolean if the forward limit switch is activated.
	 */
	public boolean isHighestPosition() {
		return angleTalon.isFwdLimitSwitchClosed();
	}

	/**
	 * Checks to see of the lower limit switch is activated, showing the angle
	 * is at it minimum. If the switch is not present, the result will be false.
	 * 
	 * @return boolean if the forward limit switch is activated.
	 */
	public boolean isLowestPosition() {
		return angleTalon.isRevLimitSwitchClosed();
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
	public void receivedValue(HashMap<SensorEnum, Double> sensorMap) {
		Double value = sensorMap.get(SensorEnum.SHOOTER_ANGLE);
		if (value != null){
			currentAngle = value;
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

	public double getTiltSensorPeriod() {
		return tiltSensor.getPeriod(); // period will change with the angle. I
										// would assume it would get longer as
										// the angle increases. This returns the
										// time interval of the most recent
										// count.
	}

	public double getTiltSensorAngle() {
		// TODO Test, only rough calibration.
		//Rough Calibration gathered from Adrian.
		return (tiltSensor.getPeriod() / TILT_SENSOR_MAX_PERIOD * MAX_ANGLE); 

}
}