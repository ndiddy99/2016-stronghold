package org.usfirst.frc.team2537.robot.auto;

import org.usfirst.frc.team2537.robot.arm.ArmPositions;
import org.usfirst.frc.team2537.robot.arm.PresetArmCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class ArmAndDriveAuto extends CommandGroup {
    
    public  ArmAndDriveAuto() {
    	addSequential(new PresetArmCommand(ArmPositions.lowbarPos));
    	addSequential(new AutoTimedDriveCommand(2500));
    }
}
