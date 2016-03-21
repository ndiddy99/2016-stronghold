package org.usfirst.frc.team2537.robot.auto;

import org.usfirst.frc.team2537.robot.arm.CalibrationCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class RampartsOrRoughTerrianAuto extends CommandGroup {
	
	public RampartsOrRoughTerrianAuto() {
		addSequential(new CalibrationCommand());
		addSequential(new CourseCorrect(15 * 12));
	}
}
