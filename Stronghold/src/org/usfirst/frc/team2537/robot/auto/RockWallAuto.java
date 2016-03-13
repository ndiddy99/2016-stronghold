package org.usfirst.frc.team2537.robot.auto;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class RockWallAuto extends CommandGroup {
	public RockWallAuto() {
		addSequential(new CourseCorrect(15*12, .750, 3));
	}
}
