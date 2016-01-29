package org.usfirst.frc.team2537.robot.shooter;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team2537.robot.shooter.flywheel.SyncFlywheelCommand;
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
	private static final double HARVEST_ANGLE = -20;
	//We need a command to place the angle down to 0.
	private final Command angleCommand;
	private final Command flywheelCommand;

	public HarvestCommand() {
		//Also requires the Angle commands.
		this.angleCommand = new PresetAngleCommand(HARVEST_ANGLE);
		this.flywheelCommand = new SyncFlywheelCommand(HARVEST_SPEED);
	}
	
	@Override
	protected void initialize(){
		//Put the angleCommand on the stack.
		this.angleCommand.start();
		this.flywheelCommand.start();
	}

	@Override
	protected void execute() {
		//Check if anything was canceled.
		
		//Check if the 
		
		if (this.angleCommand.isRunning()){
			//We have not reached harvest angle yet.
		}
		
		if (this.flywheelCommand.isRunning()){
			//We have not reached harvest angle yet.
		}
		
	}

	@Override
	protected boolean isFinished() {
		//Check if this can keep running.
		//Check if anything was canceled.
		if (this.angleCommand.isCanceled()){
			//Drat. Abort.
			return true;
		}
		
		if (this.flywheelCommand.isCanceled()){
			//Drat. Abort.
			return true;
		}
		
		//Check if the sensor senses a boulder.
		/*TODO Put code in to connect to sensor.
		 * 
		 * 
		 * 
		 */
		
		//Everthing was checked, nothing went wrong...
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
