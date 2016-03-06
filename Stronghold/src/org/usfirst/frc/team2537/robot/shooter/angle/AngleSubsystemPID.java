package org.usfirst.frc.team2537.robot.shooter.angle;

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
	public static final double MAX_ANGLE = 17.0;// degrees
	public static final double MIN_ANGLE = -15.0;// degrees
	//private static final double P = .04, I = 0.001, D = 0.7;
	private static final double P = 0.5, I = 0.0, D = 0.0;
	private static final double PID_PERIOD = .005;//seconds
	private static final double TOLERANCE = 2.0;
	public static final boolean DEBUG = true;
	

	// Variables
	private Double currentAngle = null;
	private final CANTalon angleMotor;

	public AngleSubsystemPID() {
		super(P, I, D, PID_PERIOD);
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

		// The motor will backdrive if it does not get current.
		// Set a electric break.
		angleMotor.enableBrakeMode(false);

		// We don't want this going so fast.
		// angleMotor.configMaxOutputVoltage(MAX_VOLTAGE);
		// setPercentTolerance(TOLERANCE);
		setAbsoluteTolerance(TOLERANCE);
		setInputRange(MIN_ANGLE, MAX_ANGLE);
		setOutputRange(-.3, .3);
		getPIDController().setContinuous(false);

		enable();
	}

	@Override
	public void initDefaultCommand() {
		// Create the Default command.
		setDefaultCommand(new ManualAngleCommand());
	}

	/**
	 * Set the speed of the motor that will change the angle.
	 * 
	 * @param angle
	 *            A speed between [-1, 1] which is the voltage that will be set.
	 */
	public void setAngle(double angle) {
		// System.out.println("Angle set to: " + angle);
		setSetpoint(angle);
	}

	@Override
	public void setSetpoint(double setpoint) {
		if (MIN_ANGLE <= setpoint && setpoint <= MAX_ANGLE) {
			super.setSetpoint(setpoint);
		}
	}
	
	public void setVoltage(double voltage){
		angleMotor.set(-voltage);
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
		if (value != null) {
			currentAngle = value;
		}

		System.out.println("Angle " + getCurrentAngle() + "\tSetpoint " + getSetpoint() + "\tError "
				+ getPIDController().getError() + "\tMotor Voltage Percentage " + getPIDController().get()
				+ "\tVoltage: " + angleMotor.getOutputVoltage());
	}

	/**
	 * This gets the current cached angle value.
	 * 
	 * @return angle in degrees that was cached from the sensors earlier. If the
	 *         angle is not regularly given, give either 0 or the most recent
	 *         value however old that maybe. Range [-180, 180] though the should
	 *         be between [MIN_ANGLE, MAX_ANGLE] typically. Will return null if
	 *         sensor is not present.
	 */
	public Double getCurrentAngle() {
		// System.out.println("Current Angle gotten: " + currentAngle);
//		try {
//			return Robot.sensorSys.tilt.getCurrentAngle();
//		} catch (NullPointerException e){
//			//First time it runs it will run into the getCurrentAngle
//			return null;
//		}
		return currentAngle;
	}

	public void registerButtons() {
		HumanInput.registerPressedCommand(HumanInput.changeCameraButton,	new MoveToAngleCommand(30));
		HumanInput.registerPressedCommand(HumanInput.portcullisButton,		new MoveToAngleCommand(0));
		HumanInput.registerPressedCommand(HumanInput.chevalButton,			new MoveToAngleCommand(-15));
	}

	@Override
	protected double returnPIDInput() {
		Double angle = getCurrentAngle();
		// System.out.println("returnPIDInput() called, returned " + angle);
		if (angle == null) {
			return 0;
		}
		return angle;		
	}

	@Override
	protected void usePIDOutput(double output) {
		// System.out.println("Output is: "+ output);
		if (!this.onTarget()) {
			angleMotor.set(-output);
		} else {
			angleMotor.set(0);
		}
	}
}