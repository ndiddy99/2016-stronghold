package org.usfirst.frc.team2537.robot.auto;

import org.usfirst.frc.team2537.robot.arm.GoDown;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class LowBarAuto extends CommandGroup {
	public LowBarAuto() {
		addSequential(new GoDown());
		addSequential(new CourseCorrect(25*12)); //25 FEET
	}
}
