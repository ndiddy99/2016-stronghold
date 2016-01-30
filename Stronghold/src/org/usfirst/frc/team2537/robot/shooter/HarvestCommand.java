package org.usfirst.frc.team2537.robot.shooter;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team2537.robot.shooter.flywheel.SyncFlywheelCommand;
import org.usfirst.frc.team2537.robot.shooter.angle.AngleSubsystem;
import org.usfirst.frc.team2537.robot.shooter.angle.PresetAngleCommand;
import org.usfirst.frc.team2537.robot.input.HumanInput;

/**
 * Harvest a ball from the shooter.
 * This lowers the shooting mechanism and spins the flywheels to speed.
 * 
 * @author Matthew Schweiss
 *
 */
public class HarvestCommand extends Command {
	public static final int HARVEST_TRIGGER = HumanInput.XBOX_LEFT_TRIGGER_AXIS;
	//Harvest Speed
	private static final double HARVEST_SPEED = -.1;
	private static final double DESIRED_HARVEST_ANGLE = -20;
	//We need a command to place the angle down to 0.

	public HarvestCommand() {
		//Also requires the Angle commands.
		
	}
	
	@Override
	protected void initialize(){
		//Put the angleCommand on the stack.

	}

	@Override
	protected void execute() {
		
		Math.abs(AngleSubsystem.getCurrentAngle()) - Math.abs(DESIRED_HARVEST_ANGLE);
		
	
	}

	@Override
	protected boolean isFinished() {
		//Check if this can keep running.
		//Check if anything was canceled.
	
		return false;
	}

	@Override
	protected void end() {
		//End the program.
		//Stop all of the programs.
		stop();
	
		
	}

	@Override
	protected void interrupted() {
		//I was interruped
	}
	
	private void stop(){
		//Stop the program from executing.
		
	}
}
