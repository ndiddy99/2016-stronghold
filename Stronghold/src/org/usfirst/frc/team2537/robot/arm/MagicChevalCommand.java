package org.usfirst.frc.team2537.robot.arm;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class MagicChevalCommand extends CommandGroup {
	public MagicChevalCommand() {
		addSequential(new PresetArmCommand(ArmPositions.chevalUpPos));
		addSequential(new CrossCommand(Defense.CHEVAL_DE_FRISE));
		addSequential(new PresetArmCommand(ArmPositions.chevalDownPos));
	}
}
