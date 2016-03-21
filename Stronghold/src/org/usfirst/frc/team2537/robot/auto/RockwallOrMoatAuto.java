package org.usfirst.frc.team2537.robot.auto;

import org.usfirst.frc.team2537.robot.arm.CalibrationCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class RockwallOrMoatAuto extends CommandGroup {
	public RockwallOrMoatAuto() {
		addSequential(new CalibrationCommand());
		addSequential(new CourseCorrect(15*12, .750, 3));
	}
}
