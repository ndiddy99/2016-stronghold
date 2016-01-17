package org.usfirst.frc.team2537.robot;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.command.Command;
//apparently i can't write raw data to the talons
public class TalonCommand extends Command {
	public TalonCommand() {
		requires(Robot.motorTest);
	}

	@Override
	protected void end() {
		Robot.motorTest.set(0);		
	}

	@Override
	protected void execute() {
		for (double i = 0; i <= 1; i=i+0.01) {
			Robot.motorTest.set(i);
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		for (double i = 1; i >= 0; i=i-0.01) {
			Robot.motorTest.set(i);
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

	@Override
	protected void initialize() {
		System.out.println("Test");
	}

	@Override
	protected void interrupted() {
		Robot.motorTest.set(0);
	}

	@Override
	protected boolean isFinished() {
		return false;
	}
	
}
