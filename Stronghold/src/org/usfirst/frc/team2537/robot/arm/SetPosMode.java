package org.usfirst.frc.team2537.robot.arm;

import org.usfirst.frc.team2537.robot.Robot;
import org.usfirst.frc.team2537.robot.input.Ports;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.FeedbackDevice;
import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;
import edu.wpi.first.wpilibj.command.Command;

public class SetPosMode extends Command {

	@Override
	protected void initialize() {
		// TODO Auto-generated method stub
		Robot.armSys.armMotor = new CANTalon(Ports.ARM_TALON);
		Robot.armSys.armMotor.ConfigFwdLimitSwitchNormallyOpen(true);
		Robot.armSys.armMotor.ConfigRevLimitSwitchNormallyOpen(true);
		Robot.armSys.armMotor.enableBrakeMode(true);
		Robot.armSys.armMotor.enableForwardSoftLimit(false);
		Robot.armSys.armMotor.enableReverseSoftLimit(false);
//		armMotor.setPID(1, 0, 0);
		Robot.armSys.armMotor.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		Robot.armSys.armMotor.configEncoderCodesPerRev(1000);
		Robot.armSys.armMotor.setEncPosition(0);
		Robot.armSys.armMotor.reverseSensor(true);
		Robot.armSys.armMotor.enableControl();
		Robot.armSys.armMotor.changeControlMode(TalonControlMode.Position);
		Robot.armSys.armMotor.reverseSensor(true);
	}

	@Override
	protected void execute() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	protected void end() {
		// TODO Auto-generated method stub
	
	}

	@Override
	protected void interrupted() {
		// TODO Auto-generated method stub
		
	}
	
}
