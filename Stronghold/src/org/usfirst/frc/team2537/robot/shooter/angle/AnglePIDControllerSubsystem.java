package org.usfirst.frc.team2537.robot.shooter.angle;

import org.usfirst.frc.team2537.robot.Ports;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.command.Subsystem;

public class AnglePIDControllerSubsystem extends Subsystem {
	
	private static final double MAX_ANGLE = 28;// degrees (ball park, not right)
	private static final double MIN_ANGLE =  -4.5;// degrees(ball park, not right)
	@SuppressWarnings("unused")
	private static final double MAX_VOLTAGE= 12.0;
	//Difference between the max and min angle.
	public static final double MAX_ANGLE_DIFFERENCE = MAX_ANGLE - MIN_ANGLE;
	@SuppressWarnings("unused")
	private final CANTalon pivotTalon;
	//Debugs
	public static final boolean DEBUG = false;
	
	public AnglePIDControllerSubsystem() {
		pivotTalon = new CANTalon(Ports.SHOOTER_ANGLE_PORT);
	}
	
	@Override
	public void initDefaultCommand() {
	}
	public void setVoltagePercent(double percent) {
		
	}
	
	
	
}
