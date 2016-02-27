//package org.usfirst.frc.team2537.robot.arm;
//
//import org.usfirst.frc.team2537.robot.Robot;
//
//import edu.wpi.first.wpilibj.command.Command;
//
//public class ResetEncoders extends Command {
//
//	@Override
//	protected void initialize() {
//		// TODO Auto-generated method stub
//		System.out.println(Robot.armSys.armMotor.getEncPosition());
//		Robot.armSys.armMotor.setEncPosition(0);
//	}
//
//	@Override
//	protected void execute() {
//		// TODO Auto-generated method stub
//
//	}
//
//	@Override
//	protected boolean isFinished() {
//		// TODO Auto-generated method stub
//		return true;
//	}
//
//	@Override
//	protected void end() {
//		// TODO Auto-generated method stub
//		try {
//			wait(200);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		System.out.println(Robot.armSys.armMotor.getEncPosition());
//	}
//
//	@Override
//	protected void interrupted() {
//		// TODO Auto-generated method stub
//
//	}
//
//}
