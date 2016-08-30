package org.usfirst.frc.team2537.robot.climber;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class Climb extends CommandGroup {
    
    public Climb() {
        addSequential(command); //TODO: Extend tape command
        addSequential(new ServoTurnCommand(ServoAngles.finalAngle));
        addSequential(new Sleep(0.5));
        addSequential(command); //TODO: Retract tape command
        addSequential(new ServoTurnCommand(ServoAngles.startAngle));
        addSequential(new Sleep(0.5));
        addSequential(command); //TODO: Retract tape command
    }
}
