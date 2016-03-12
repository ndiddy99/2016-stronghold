package org.usfirst.frc.team2537.robot.auto;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class BorDAuto extends CommandGroup {
	
	public BorDAuto() {
		addSequential(new CourseCorrect(15 * 12));
	}
}
