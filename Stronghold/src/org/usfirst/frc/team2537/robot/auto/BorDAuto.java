package org.usfirst.frc.team2537.robot.auto;

import org.usfirst.frc.team2537.robot.arm.PresetArmCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class BorDAuto extends CommandGroup {
	
	public BorDAuto() {
		addSequential(new PresetArmCommand(45));
		addSequential(new CourseCorrect(Double.POSITIVE_INFINITY));
	}
}
