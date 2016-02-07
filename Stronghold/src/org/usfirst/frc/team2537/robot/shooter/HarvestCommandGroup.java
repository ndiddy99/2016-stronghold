package org.usfirst.frc.team2537.robot.shooter;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.usfirst.frc.team2537.robot.shooter.angle.MoveToAngleCommand;
import org.usfirst.frc.team2537.robot.shooter.flywheel.FlywheelCommand;
import org.usfirst.frc.team2537.robot.shooter.flywheel.SpinDownCommand;

/**
 * This is the command that will be used to harvest the ball from in front of the robot.
 * By holding the left trigger on the XBOX, this command will be launched.
 * 
 * @author Matthew Schweiss
 */
public class HarvestCommandGroup extends CommandGroup {
	private static final double HARVEST_ANGLE = -10;
	private static final double HARVEST_SPEED = -.5;
    
    public HarvestCommandGroup() {
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
    	addParallel(new MoveToAngleCommand(HARVEST_ANGLE));
    	System.out.println("Harvest command is running");
    	addSequential(new FlywheelCommand(HARVEST_SPEED));
    	//Wait until we get a ball.
    	addSequential(new UntilBallCommand());
    	addSequential(new SpinDownCommand());
    }
    
    @Override
    protected void interrupted() {
    	new SpinDownCommand().start();
    }
    
    @Override
    protected void end() {
    }
}
