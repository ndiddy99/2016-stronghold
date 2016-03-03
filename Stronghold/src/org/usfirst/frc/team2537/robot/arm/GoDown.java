package org.usfirst.frc.team2537.robot.arm;

import org.usfirst.frc.team2537.robot.Robot;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class GoDown extends CommandGroup {

	public GoDown() {
		Robot.armSys.setOldPos(Robot.armSys.getAngle());
		addSequential(new PresetArmCommand(ArmPositions.lowbarPos));
	}
}
