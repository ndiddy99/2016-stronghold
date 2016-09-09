//command that changes the angle of the tape

package org.usfirst.frc.team2537.robot.climber;

import org.usfirst.frc.team2537.robot.Robot;
import edu.wpi.first.wpilibj.Timer;
import java.lang.Math;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class TurnTheTapeCommand extends Command {
	Timer timer=new Timer();
	private double angle;
	private int finishAngle;
	double rate;
	double rateRate;
	int iterations = 0;
	double tolerance  = 0.5;
	
	boolean isAngle100=false;

	TurnTheTapeCommand(int angle, double rampRateRampRate) {
		requires(Robot.climberSys);
		finishAngle = angle;
		rateRate = rampRateRampRate;
	}

	@Override
	protected void end() {
		// TODO Auto-generated method stub

		System.out.println("Stopped turning");
		
		iterations = 0;
		// Robot.climberSys.setAngle(0);
	}

	/** makes the climber turn to ANGLE FOR SERVO */
	@Override
	protected void execute() {
		Double curAngle = Robot.climberSys.getAngle();
		if(curAngle >0 ){
			//Robot.climberSys.setAngle(Robot.climberSys.getAngle() - 1);
			curAngle -= 0.5 * rateRate * Math.pow(++iterations, 2);
			Robot.climberSys.setAngle(Math.max(curAngle, finishAngle));
		}
//	if((int) Robot.climberSys.getAngle() == (int) finishAngle ||(int) Robot.climberSys.getAngle() == (int) finishAngle -1){	
//		if (angle < finishAngle) {
//			angle += 0.5 * rateRate * Math.pow(++iterations, 2);
//			angle = Math.min(angle, finishAngle);
//			iterations++;
//			angle += rateRate;
//		} else if (angle > finishAngle) {
//			angle -= 0.5 * rateRate * Math.pow(++iterations, 2);
//			angle = Math.max(angle, finishAngle);
//			iterations++;
//			angle-=rateRate;
//		}
//		Robot.climberSys.setAngle(angle);
		System.out.println("current angle " + Robot.climberSys.getAngle());
		System.out.println("angle to move to " + finishAngle + tolerance + "       ");
//		SmartDashboard.putNumber("Current Angle", Robot.climberSys.getAngle());
//		SmartDashboard.putNumber("Angle to move to", finishAngle);
	}
	
	@Override
	protected void initialize() {
		// TODO Auto-generated method stub
		// Robot.climberSys.electromagnetOff();
		
		Robot.climberSys.setAngle(100);
		iterations=0;
		
	}

	@Override
	protected void interrupted() {
		// TODO Auto-generated method stub

	}

	@Override /** stawps turning the tape after 5 sec */
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return   (iterations > 360) ||(Robot.climberSys.getAngle() <= finishAngle+1);

	}

}
