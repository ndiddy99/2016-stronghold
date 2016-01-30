package org.usfirst.frc.team2537.robot.shooter.angle;
//Alex, I hope you don't mind I stole a lot of your code to make this.
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team2537.robot.Robot;
import java.lang.Math;

public class MoveToAngleCommand extends Command {
	
	public double posToMoveTo;
	private double currentPosition;
	private boolean move;
	private static final double ACCURACY = 5;
	private static final double SPEED = 180;//Max change speed.
	
	/*
	 * This is for the autonomous system to move the shooter.
	 * This is used by passing the desired angle and the system will
	 * then adjust.
	 */
	public MoveToAngleCommand(double angle) {
		//The main constructor.
		super();
		this.requires(Robot.shooterAngleSys);//ALEX USE "REQUIRES"!!!!
		this.posToMoveTo = angle;
	}
/* This will be used, but only if we need to support timeout.
	public PresetShooterAngleCommand(short position, double timeout){
		super(timeout);
		// TODO Auto-generated constructor stub
	}
*/

	@Override
	protected void initialize() {
		//Start up the system. Get ready for iteration
		System.out.println("Moving Shooter to " + this.posToMoveTo);
		updateAngle();
		/*if (shouldMove()){
			System.out.println("Shooter was already at "+ this.currentPosition);
		}*///Debug code but should not be needed.
	}

	@Override
	protected void execute() {
		//This is the main loop, lets do IT!!
		updateAngle();
		move = shouldMove();
		if (this.move){
			Robot.shooterAngleSys.setAngleSpeed((currentPosition - posToMoveTo) / SPEED);
		} /*else {
			stop();
		}*///This segment should not be needed. If we have lag problems this should be turned on.
	}

	@Override
	protected boolean isFinished() {
		//If this is finished.
		return move;
	}

	@Override
	protected void end() {
		//Yeah, I have been stopped and I'm done with this!
		stop();
	}

	@Override
	protected void interrupted() {
		//HEY, That isn't nice.
		System.out.println("Shooter Interrupted");
		stop();
	}
	//A few little tasks.
	private void updateAngle(){
		//Get the angle.
		currentPosition = Robot.shooterAngleSys.getCurrentAngle();
	}
	
	protected boolean shouldMove(){
		//If this should move.
		return Math.abs(currentPosition - posToMoveTo) > ACCURACY;
	}
	
	private void stop(){
		//STOP MOVING
		Robot.shooterAngleSys.setAngleSpeed(0);
	}

}
