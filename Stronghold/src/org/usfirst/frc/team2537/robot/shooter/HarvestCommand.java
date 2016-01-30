package org.usfirst.frc.team2537.robot.shooter;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.usfirst.frc.team2537.robot.shooter.angle.PresetAngleCommand;

/**
 *
 */
public class HarvestCommand extends CommandGroup {
	private static final double HARVEST_ANGLE = -10;
	private static final double HARVEST_SPEED = -.1;
    
    public  HarvestCommand() {
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    	addParallel(new PresetAngleCommand(HARVEST_ANGLE));
    	addSequential(new FlywheelCommand(HARVEST_SPEED));
    }
}
