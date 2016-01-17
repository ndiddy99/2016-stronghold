package org.usfirst.frc.team2537.robot;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.command.Subsystem;

public class MotorSubsystem extends Subsystem {
	public CANTalon test;

	public MotorSubsystem() {
		test = new CANTalon(4);
	}
	
	public void set(double speed) {
		test.set(speed);
	}

	@Override
	protected void initDefaultCommand() {
		TalonCommand tc = new TalonCommand();
		this.setDefaultCommand(tc);
	}
	
}
