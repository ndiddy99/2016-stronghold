package org.usfirst.frc.team2537.robot.arm;

import org.usfirst.frc.team2537.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Command used for the crossing of defenses
 * 
 * @author Alex Taber
 *
 */
public class CrossCommand extends Command {
	Defense defense;
	Double nearRange = null;
	Double farRange = null;
	
	/**
	 * Constructor that initializes the crosscommand
	 * 
	 * @param	defense	Enum that determines what defense to cross
	 */
	public CrossCommand(Defense defense) {
		this.defense = defense;
	}

	protected void initialize() {
		switch (defense) {
		case PORTCULLIS:
			nearRange = 0.0;
			farRange = 0.0;
			break;
		case SALLY_PORT:
			nearRange = 0.0;
			farRange = 0.0;
			break;
		case CHEVAL_DE_FRISE:
			nearRange = 0.0;
			farRange = 0.0;
			break;
		case DRAWBRIDGE:
			nearRange = 0.0;
			farRange = 0.0;
			break;
		default:
			nearRange = null;
			farRange = null;
			System.err.println("Arm abort!");
			break;
		}
		if (nearRange != null && farRange != null ) {
			Robot.driveSys.setDriveMotors(.25);
		}
	}

	protected void execute() {
		if (ArmSubsystem.debug) System.err.print("idk lol");
	}

	protected boolean isFinished() {
		if (Robot.armSys.getUltrasonicDistance() > farRange) {
			return false;
		} else if (Robot.armSys.getUltrasonicDistance() < farRange && Robot.armSys.getUltrasonicDistance() > nearRange) {
			return true;
		} else if (Robot.armSys.getUltrasonicDistance() < nearRange){
			Robot.driveSys.setDriveMotors(-.1);
			return false;
		} else {
			return false;
		}
	}

	protected void end() {
		Robot.driveSys.setDriveMotors(0);
	}

	protected void interrupted() {
		Robot.driveSys.setDriveMotors(0);
	}

}
