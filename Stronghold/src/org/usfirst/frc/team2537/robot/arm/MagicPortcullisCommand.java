package org.usfirst.frc.team2537.robot.arm;

import org.usfirst.frc.team2537.robot.auto.AutoDriveStraightCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Command Group that crosses the portcullis
 * 
 * WIP
 * 
 * @author Alex Taber
 * @deprecated
 * 
 */
public class MagicPortcullisCommand extends CommandGroup {
	
	public MagicPortcullisCommand() {
		addSequential(new PresetArmCommand(ArmPositions.portcullisDownPos));
		addSequential(new CrossCommand(Defense.PORTCULLIS));
		addSequential(new PresetArmCommand(ArmPositions.portcullisUpPos));
		addSequential(new AutoDriveStraightCommand(48));
	}
}
