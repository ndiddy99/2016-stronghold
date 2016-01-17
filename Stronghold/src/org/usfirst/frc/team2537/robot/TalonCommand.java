package org.usfirst.frc.team2537.robot;

import edu.wpi.first.wpilibj.command.Command;

//apparently i can't write raw data to the talons
public class TalonCommand extends Command {
	public TalonCommand() {
		requires(Robot.motorTest);
	}

	@Override
	protected void end() {
		Robot.motorTest.set(Robot.motorTest.rightBack, 0);
		Robot.motorTest.set(Robot.motorTest.rightFront, 0);
		Robot.motorTest.set(Robot.motorTest.leftBack, 0);
		Robot.motorTest.set(Robot.motorTest.leftFront, 0);
	}

	@Override
	protected void execute() {
		// for (double i = 0; i <= 1; i=i+0.01) {
		// Robot.motorTest.set(i);
		// try {
		// Thread.sleep(100);
		// } catch (InterruptedException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		// }
		// for (double i = 1; i >= 0; i=i-0.01) {
		// Robot.motorTest.set(i);
		// try {
		// Thread.sleep(100);
		// } catch (InterruptedException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		// }
		//
		// for (double i = 0; i >= -1; i=i-0.01) {
		// Robot.motorTest.set(i);
		// try {
		// Thread.sleep(100);
		// } catch (InterruptedException e) {
		// e.printStackTrace();
		// }
		// }
		//
		// for (double i = -1; i <= 0; i=i+0.01) {
		// Robot.motorTest.set(i);
		// try {
		// Thread.sleep(100);
		// } catch (InterruptedException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		// }
		double xAxisValLeft = HumanInput.getJoystickInput(HumanInput.xboxController, HumanInput.XBOX_LEFT_STICK_Y_AXIS) * -1;
		double xAxisValRight = HumanInput.getJoystickInput(HumanInput.xboxController, HumanInput.XBOX_RIGHT_STICK_Y_AXIS);
		Robot.motorTest.set(Robot.motorTest.leftFront, xAxisValLeft);
		Robot.motorTest.set(Robot.motorTest.leftBack, xAxisValLeft);
		Robot.motorTest.set(Robot.motorTest.rightFront, xAxisValRight);
		Robot.motorTest.set(Robot.motorTest.rightBack, xAxisValRight);
	}

	@Override
	protected void initialize() {
		System.out.println("Test");
	}

	@Override
	protected void interrupted() {
		Robot.motorTest.set(Robot.motorTest.rightBack, 0);
		Robot.motorTest.set(Robot.motorTest.rightFront, 0);
		Robot.motorTest.set(Robot.motorTest.leftBack, 0);
		Robot.motorTest.set(Robot.motorTest.leftFront, 0);
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

}
