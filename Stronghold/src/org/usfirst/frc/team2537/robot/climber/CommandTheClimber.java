package org.usfirst.frc.team2537.robot.climber;

import org.usfirst.frc.team2537.robot.Robot;


//import org.usfirst.frc.team2537.robot.auto.AutoDriveStraightCommand;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class CommandTheClimber extends CommandGroup {
	/** the command group for the events that the climber must performs */
	public CommandTheClimber() {
		// addSequential(new
		// AutoDriveStraightCommand(09 -Climber.DISTANCE_TO_MdOVE_BACK));
		//System.out.println(Robot.climberSys.isLimitPressed());
//		if (!Robot.climberSys.isLimitPressed()|| Robot.climberSys.getEncPos() <= 0){
//			System.out.println(Robot.climberSys.isLimitPressed());
//			addSequential(new ExtendTheTapeCommand(2));
//			addSequential(new RetractTheTapeCommand());
//     	}
		//addSequential(new TurnTheTapeCommand(100, Climber.RAMP_RATE_RAMP_RATE_SERVO));
		addSequential(new ExtendTheTapeCommand(Climber.DISTANCE_TO_EXTEND));
		if (Climber.debug) System.out.println("extending");
		addSequential(new TurnTheTapeCommand(0, Climber.RAMP_RATE_RAMP_RATE_SERVO));
		addSequential(new Sleep(.5));

		addSequential(new RetractTheTapeCommand(-20, 3));

		addSequential(new ResetServo());
		addSequential(new Sleep(.5));

		System.out.println("turning");
		addSequential(new RetractTheTapeCommand((int)Climber.DISTANCE_TO_MOVE_BACK,12));
		System.out.println("retracting");
		
	}
}
