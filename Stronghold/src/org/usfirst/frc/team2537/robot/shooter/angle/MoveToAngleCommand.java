package org.usfirst.frc.team2537.robot.shooter.angle;

//Alex, I hope you don't mind I stole a lot of your code to make this.
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team2537.robot.Robot;

/**
 * MoveToAngleCommand
 * @author matthewschweiss
 *
 * The point of this command is to put the shooter to a 
 * particular angle. This goes to the angle but slows as it approaches the angle.
 */
public class MoveToAngleCommand extends Command {
	//Constants
	private static final double ACCURACY = 5;
	private static final double SPEED = 180;//Max change speed.
	private static final double TIMEOUT = 10;
	
	//Variables
	public double posToMoveTo;
	private double currentPosition;
	
	/**
	 * This is for the autonomous system to move the shooter.
	 * This is used by passing the desired angle and the system will
	 * then adjust.
	 * 
	 * @author Matthew Schweiss
	 */
	public MoveToAngleCommand(double angle) {
		//The main constructor.
		super(TIMEOUT);
		requires(Robot.shooterAngleSys);
		posToMoveTo = angle;
	}

	@Override
	protected void initialize() {
		//Start up the system. Get ready for iteration
		System.out.println("Moving Shooter to " + this.posToMoveTo);
		currentPosition = Robot.shooterAngleSys.getCurrentAngle();
	}

	@Override
	protected void execute() {
		//This is the main loop, lets do IT!!
		currentPosition = Robot.shooterAngleSys.getCurrentAngle();
		Robot.shooterAngleSys.setAngleSpeed((currentPosition - posToMoveTo) / SPEED);
		}

	@Override
	protected boolean isFinished() {
		//If this is finished.
		return Math.abs(currentPosition - posToMoveTo) > ACCURACY;//If we are close enough to the angle to call it.
	}

	@Override
	protected void end() {
		//Yeah, I have been stopped and I'm done with this!
		Robot.shooterAngleSys.setAngleSpeed(0);//Stop the motor.
	}

	@Override
	protected void interrupted() {
		//HEY, That isn't nice.
		System.out.println("Shooter Interrupted");
		Robot.shooterAngleSys.setAngleSpeed(0);//Stop the motor.
	}
}
