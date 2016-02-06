package org.usfirst.frc.team2537.robot.arm;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Command Group that crosses the cheval de frise
 * 
 * @author Alex Taber
 *
 */
public class MagicChevalCommand extends CommandGroup {
	public MagicChevalCommand() {
		addSequential(new PresetArmCommand(ArmPositions.chevalUpPos));
		addSequential(new CrossCommand(Defense.CHEVAL_DE_FRISE));
		addSequential(new PresetArmCommand(ArmPositions.chevalDownPos));
	}
}
