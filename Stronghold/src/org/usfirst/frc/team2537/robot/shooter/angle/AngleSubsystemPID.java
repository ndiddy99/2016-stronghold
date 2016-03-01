package org.usfirst.frc.team2537.robot.shooter.angle;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.CANTalon;
import java.util.HashMap;

import org.usfirst.frc.team2537.robot.Ports;
import org.usfirst.frc.team2537.robot.Robot;
import org.usfirst.frc.team2537.robot.input.HumanInput;
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
public class AngleSubsystemPID extends PIDSubsystem implements SensorListener {
	
	// The angle limits.
//	private static final double MAX_ANGLE = 28;// degrees (ball park, not right)
//	private static final double MIN_ANGLE =  -4.5;// degrees(ball park, not right)
//	private static final double MAX_VOLTAGE= 12.0;
	private static final float P = 1, I = 0, D = 0;
//	private static final float PID_PERIOD = 50;//ms
	private static final float TOLERANCE  = 15;
	//Difference between the max and min angle.
//	public static final double MAX_ANGLE_DIFFERENCE = MAX_ANGLE - MIN_ANGLE; 
	//Debugs
	public static final boolean DEBUG = true;

	// Varibles
	private Double currentAngle = null;
	private final CANTalon angleMotor;

	public AngleSubsystemPID() {
		super(P, I, D);
		angleMotor = new CANTalon(Ports.SHOOTER_ANGLE_PORT);
		// Change control mode of the angleTalon to percent Vbus.
//		angleMotor.changeControlMode(CANTalon.TalonControlMode.PercentVbus);
		// Add limits.
//		angleMotor.ConfigFwdLimitSwitchNormallyOpen(true);
//		angleMotor.ConfigRevLimitSwitchNormallyOpen(true);
//		angleMotor.enableLimitSwitch(true, true);// Now the limit switches are
													// active.
		// Soft limits for a backup.
//		angleMotor.enableForwardSoftLimit(false);
//		angleMotor.enableReverseSoftLimit(false);
		
		//The motor will backdrive if it does not get current.
		//Set a electric break.
//		angleMotor.enableBrakeMode(true);
		
		//We don't want this going so fast.
//		angleMotor.configMaxOutputVoltage(MAX_VOLTAGE);
		setPercentTolerance(TOLERANCE);
//		setInputRange(-90, 90);
//		setOutputRange(-1, 1);
		this.getPIDController().setContinuous(false);
		
		enable();
	}

	@Override
	public void initDefaultCommand() {
		// Create the Default command.
		setDefaultCommand(new ManualAngleCommand());
	}

	/**
	 * Set the speed of the motor that will change the angle.
	 * @param angle
	 *            A speed between [-1, 1] which is the voltage that will be set.
	 */
	public void setAngle(double angle) {
		System.out.println("Angle set to: " + angle);
		setSetpoint(angle);
	}

//	// Did we hit a limit.
//	/**
//	 * Checks to see of the top limit switch is activated, showing the angle is
//	 * at it max. If the switch is not present, the result will be false.
//	 * 
//	 * @return boolean if the forward limit switch is activated.
//	 */
//	public boolean isHighestPosition() {
//		if (currentAngle != null && currentAngle >= MAX_ANGLE)
//			return true;
//		return angleMotor.isFwdLimitSwitchClosed();
//	}

//	/**
//	 * Checks to see of the lower limit switch is activated, showing the angle
//	 * is at it minimum. If the switch is not present, the result will be false.
//	 * 
//	 * @return boolean if the forward limit switch is activated.
//	 */
//	public boolean isLowestPosition() {
//		if (currentAngle != null && currentAngle <= MIN_ANGLE)
//			return true;
//		return angleMotor.isRevLimitSwitchClosed();
//	}

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
		this.getPIDController().
		
		Double value = sensorMap.get(Sensor.SHOOTER_ANGLE);
		if (value == null) {
			currentAngle = 0.0;
		} else {
			value = (value-90);
			if (value > -90 && value < 0) {
				currentAngle = -(value)/2;//Set the value.
			} else if (value < -90) {
				currentAngle = -(value + 180) / 2;
			} else {
				currentAngle = null;
			}
		}
		//if (DEBUG) System.out.println("Shooter Angle: " + currentAngle);
		
		//SOFT LIMITS
//		if (isHighestPosition() || isLowestPosition()){
//			//TOO High or low stop motor.
////			System.out.println("Position Is at Max or Min.");
//		}
	}

	/**
	 * This gets the current cached angle value.
	 * 
	 * @return angle in degrees that was cached from the sensors earlier. If the
	 *         angle is not regularly given, give either 0 or the most recent
	 *         value however old that maybe. Range [-180, 180] though the should
	 *         be between [MIN_ANGLE, MAX_ANGLE] typically. Will return null if sensor is
	 *         not present.
	 */
	public Double getCurrentAngle() {
		System.out.println("Current Angle gotten: " + currentAngle);
		return currentAngle;
	}

	public void registerButtons() {
		// Needed but not used.
		HumanInput.registerPressedCommand(HumanInput.changeCameraButton, new MoveToAnglePIDTESTCommand(0));
	}

	@Override
	protected double returnPIDInput() {
		double angle = Robot.sensorSys.tilt.getCurrentAngle();
		System.out.println("returnPIDInput() called, returned " + angle);
		return angle;
	}

	@Override
	protected void usePIDOutput(double output) {
		System.out.println("Output is: "+ output);
		angleMotor.set(output);
	}
//	public double averageError() {
//		return getPIDController().getAvgError();
//	}
//	public double PIDValue() {
//		return getPIDController().get();
//	}
//	public double getPIDControllerSetpoint() {
//		return getPIDController().getSetpoint();
//	}
//	public double getSubsystemSetpoint() {
//		return getSetpoint();
//	}
//	public double getError() {
//		return getPIDController().getError();
//	}
//	public boolean isAtAngle() {
//		return getPIDController().onTarget();
//	}
}