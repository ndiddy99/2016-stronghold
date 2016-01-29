package org.usfirst.frc.team2537.robot;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.FeedbackDevice;
import edu.wpi.first.wpilibj.command.Subsystem;

public class MotorSubsystem extends Subsystem {
	public CANTalon rightBack;
	public CANTalon rightFront;
	public CANTalon leftBack;
	public CANTalon leftFront;
	public static CANTalon climberF;
	public static CANTalon climberB;
	

	public MotorSubsystem() {
		rightFront = new CANTalon(3);
		rightBack = new CANTalon(4);
		leftFront = new CANTalon(2);
		leftBack = new CANTalon(1);
		climberF = new CANTalon(5);
		climberB = new CANTalon(6);
		climberF.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		climberB.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		climberF.configEncoderCodesPerRev(20);
		climberB.configEncoderCodesPerRev(20);
		climberF.reverseSensor(false);
		climberB.reverseSensor(false);
		climberF.setPosition(0);
		climberB.setPosition(0);
		climberF.setForwardSoftLimit(+15);//Replace with the max rotations that the tape measure can go forward
		climberB.setForwardSoftLimit(-15);//Replace with the max rotations that the tape measure can go forward
	}
	
	public static void set(CANTalon t, double speed) {
		t.set(speed);
	}
	public static void getEncValue(CANTalon t){
		t.getEncPosition();
		
	}

	@Override
	protected void initDefaultCommand() {
		TalonCommand tc = new TalonCommand();
		this.setDefaultCommand(tc);
	}
	
}
