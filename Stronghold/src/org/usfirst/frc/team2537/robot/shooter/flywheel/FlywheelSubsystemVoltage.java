package org.usfirst.frc.team2537.robot.shooter.flywheel;

import java.util.HashMap;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team2537.robot.input.Sensor;
import org.usfirst.frc.team2537.robot.Ports;
import org.usfirst.frc.team2537.robot.input.HumanInput;
import org.usfirst.frc.team2537.robot.input.SensorListener;
import org.usfirst.frc.team2537.robot.shooter.ShootCommandGroup;
import org.usfirst.frc.team2537.robot.shooter.HarvestCommandGroup;
import static edu.wpi.first.wpilibj.CANTalon.FeedbackDevice.QuadEncoder;

/**
 * @author Matthew Schweiss
 * 
 * THIS IS UNTESTED
 * NOT TESTED
 */
public class FlywheelSubsystemVoltage extends Subsystem implements SensorListener {

	// Constants
	public static final boolean DEBUG = false;
	private static final double UNITS_PER_100MS_TO_RPM = 100.0 / 4096 * 1000 * 60;
	private static final double SPEED_TOLERANCE = -ShootCommandGroup.SHOOT_VELOCITY * .05;
	// Max voltage that can be output from the flyweel talons.
	private static final float MAX_VOLTAGE = 12.0f;
	// 5 volts per second ramp rate for the flywheels
	@SuppressWarnings("unused")
	private static final double VOLTAGE_RAMP_RATE = 12;
	// Vars
	private boolean proximityValue = false;
	//The orientation assumes that you are facing the bot
	private CANTalon leftFlywheelMotor;
	private CANTalon rightFlywheelMotor;

	public FlywheelSubsystemVoltage() {
		// Make sure the the mode to velocity so we can modify it.
		leftFlywheelMotor = new CANTalon(Ports.LEFT_FLYWHEEL_PORT);
		rightFlywheelMotor = new CANTalon(Ports.RIGHT_FLYWHEEL_PORT);

		// Make sure the soft limits are disabled.
		leftFlywheelMotor.enableForwardSoftLimit(false);
		leftFlywheelMotor.enableReverseSoftLimit(false);
		rightFlywheelMotor.enableForwardSoftLimit(false);
		rightFlywheelMotor.enableReverseSoftLimit(false);

		// Disable the limit switchs, as they do not exist.
		leftFlywheelMotor.enableLimitSwitch(false, false);
		rightFlywheelMotor.enableLimitSwitch(false, false);

		// Nominal voltages, not sure if this is needed
		// Something found indicated that if the PID is bad,
		// voltage will ramp to the nominal Output, we should try
		// turning these off.
		leftFlywheelMotor.configNominalOutputVoltage(0.0f, 0.0f);
		leftFlywheelMotor.configPeakOutputVoltage(12.0f, -12.0f);
		leftFlywheelMotor.configMaxOutputVoltage(MAX_VOLTAGE);
		rightFlywheelMotor.configNominalOutputVoltage(0.0f, 0.0f);
		rightFlywheelMotor.configPeakOutputVoltage(12.0f, -12.0f);
		rightFlywheelMotor.configMaxOutputVoltage(MAX_VOLTAGE);

		// Make the talons go into the speed control mode.
		leftFlywheelMotor.changeControlMode(CANTalon.TalonControlMode.Voltage);
		rightFlywheelMotor.changeControlMode(CANTalon.TalonControlMode.Voltage);
	}

	@Override
	public void initDefaultCommand() {
	}

	public void registerButtons() {
		Command shootCommand = new ShootCommandGroup();
		HumanInput.registerPressedCommand(HumanInput.ballShootTrigger, shootCommand);
		HumanInput.cancelWhenPressed(HumanInput.shootCancelButton, shootCommand);
		HumanInput.registerWhileHeldCommand(HumanInput.harvestBallTrigger, new HarvestCommandGroup());
	}

	// Set and get speed
	/**
	 * Set the speed of the motors.
	 *
	 * @param speed
	 *            in RPM.
	 */
	public void setSpeed(double speed) {
		SmartDashboard.putString("Left Talon Mode", leftFlywheelMotor.getControlMode().toString());
		SmartDashboard.putString("Right Talon Mode", rightFlywheelMotor.getControlMode().toString());
		leftFlywheelMotor.set(-speed);
		rightFlywheelMotor.set(speed);
	}
	
	public void stop(){
		rightFlywheelMotor.set(0);
		leftFlywheelMotor.set(0);
	}
	
	

	// LEFT
	@Deprecated //We no longer watch the encoders.
	/**
	 * Get the current speed of the left flywheel.
	 * 
	 * @return flywheel speed in RPM
	 */
	public double getLeftSpeed() {
		if (DEBUG) {
			System.out.println("Left Flywheel Speed: " + leftFlywheelMotor.getSpeed());
			System.out.println("Right Flywheel Speed: " + rightFlywheelMotor.getSpeed());
		}
		return leftFlywheelMotor.getSpeed();
	}
	
	@Deprecated //We no longer watch the encoders.
	/**
	 * Get the error of the left flywheel. This is the distance from the wanted
	 * speed and the current speed.
	 * 
	 * @return Difference between current speed and wanted speed. In RPM
	 */
	public double getLeftError() {
		// We want to change this from native units (units per 100ms) to RPM.
		// return leftFlywheelMotor.getError()/100/4096*1000*60;
		/*
		 * Here is how I came to this conversion factor.
		 * 
		 */
		// Simplified for speed.
		return leftFlywheelMotor.getError() * UNITS_PER_100MS_TO_RPM;
	}

	// RIGHT
	@Deprecated //We no longer watch the encoders.
	/**
	 * Get the current speed of the left flywheel.
	 * 
	 * @return flywheel speed in RPM
	 */
	public double getRightSpeed() {
		if (DEBUG) System.out.println("Right Flywheel Speed: " + rightFlywheelMotor.getSpeed());
		return rightFlywheelMotor.getSpeed();
	}
	
	@Deprecated //We no longer watch the encoders.
	/**
	 * Get the error of the left flywheel. This is the distance from the wanted
	 * speed and the current speed.
	 * 
	 * @return Difference between current speed and wanted speed in RPM.
	 */
	public double getRightError() {
		return rightFlywheelMotor.getError() * UNITS_PER_100MS_TO_RPM;
	}
	
	@Deprecated //To my understanding, was never set up.
	/**
	 * Check if a ball is in the shooter.
	 * 
	 * @return if the proximity sensor is returning true to seeing something.
	 */
	public boolean isBallPresent() {
		return proximityValue;
	}
	
	// Counter is only used for is at speed to determine how many consecutive
	// measurments are at the speed that we want to be at.
	int counter = 0;
	@Deprecated //This shouldn't be used any more because we can't rely on encoders.
	public boolean isAtSpeed(double speed) {
		if (DEBUG) System.out.println("Good speeds sampled: " + counter);
		if (Math.abs(Math.abs(getRightSpeed()) - Math.abs(speed)) <= SPEED_TOLERANCE
				&& Math.abs(Math.abs(getLeftSpeed()) - Math.abs(speed)) <= SPEED_TOLERANCE) {
			counter++;
		} else {
			counter = 0;
		}
		if (counter == 1) {
			counter = 0; 
			return true;
		}
		return false;
	}

	@Override
	// Proximity
	public void receivedValue(HashMap<Sensor, Double> sensorMap) {
		Double value = sensorMap.get(Sensor.SHOOTER_PROXIMITY);
		if (value != null)
			proximityValue = (value == 1);

		// This is routenly called regardless of motor activation, should use
		// this to
		// put debug code.
		if (DEBUG) {
			System.out.print("Flywheel ");
			System.out.print("L Speed: " + getLeftSpeed());
			System.out.print("L Error: " + getLeftError());
			System.out.print("R Speed: " + getRightSpeed());
			System.out.println("R Error: " + getRightError());
		}
	}
	public void disableFlywheels() {
		leftFlywheelMotor.disable();
		rightFlywheelMotor.disable();
	}
}