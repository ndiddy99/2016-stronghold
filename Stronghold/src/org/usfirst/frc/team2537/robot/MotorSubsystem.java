package org.usfirst.frc.team2537.robot;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.command.Subsystem;

public class MotorSubsystem extends Subsystem {
	public CANTalon rightBack;
	public CANTalon rightFront;
	public CANTalon leftBack;
	public CANTalon leftFront;

	public MotorSubsystem() {
		rightFront = new CANTalon(3);
		rightBack = new CANTalon(4);
		leftFront = new CANTalon(2);
		leftBack = new CANTalon(1);
	}
	
	public void set(CANTalon t, double speed) {
		t.set(speed);
	}

	@Override
	protected void initDefaultCommand() {
		TalonCommand tc = new TalonCommand();
		this.setDefaultCommand(tc);
	}
	
}
