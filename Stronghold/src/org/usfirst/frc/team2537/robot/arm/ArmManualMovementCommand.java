//package org.usfirst.frc.team2537.robot.arm;
//
//import org.usfirst.frc.team2537.robot.Robot;
//
//import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;
//import edu.wpi.first.wpilibj.command.Command;
//
///**
// * Default command for arm subsystem. Gets values of joystick and sets talon speed accordingly.
// * 
// * @author Alex Taber
// *
// */
//public class ArmManualMovementCommand extends Command {
//	
//	public ArmManualMovementCommand() {
//		this.requires(Robot.armSys);
//	}
//
//	protected void initialize() {
//		Robot.armSys.armMotor.changeControlMode(TalonControlMode.PercentVbus);
//	}
//
//	protected void execute() {
//		Robot.armSys.setArmTalon(Robot.armSys.getRightJoystick());
//		if (ArmSubsystem.debug) System.out.println("Setting arm to " + (Robot.armSys.getRightJoystick()));
//	}
//
//	protected boolean isFinished() {
//		return true;
//	}
//
//	protected void end() {
//	}
//
//	protected void interrupted() {
//		
//	}
//}
