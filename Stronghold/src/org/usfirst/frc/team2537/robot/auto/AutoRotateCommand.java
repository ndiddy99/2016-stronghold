package org.usfirst.frc.team2537.robot.auto;

import org.usfirst.frc.team2537.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class AutoRotateCommand extends Command{
	private double speed;
	private static final boolean debug = false;
	private static final double DEFAULT_SPEED = 0.5;
	
	/**
	 * spins forever at default speed
	 * counterclockwise (untested)
	 */
	public AutoRotateCommand(){
		this(DEFAULT_SPEED);
	}
	
	/**
	 * spins forever at [speed]
	 * counterclockwise (untested)
	 * @param speed
	 */
	public AutoRotateCommand(double speed){
		requires(Robot.driveSys);
		this.speed = speed;
	}
	
	@Override
	protected void initialize() {
		if(debug) System.out.println("[AutoRotateCommand] Initializing. speed: " + speed);
		Robot.driveSys.setDriveMotors(-speed, speed);
	}

	@Override
	protected void execute() {
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

	@Override
	protected void end() {
		if(debug) System.out.println("[AutoRotateCommand] good end");
		Robot.driveSys.setDriveMotors(0);
	}

	@Override
	protected void interrupted() {
		if(debug) System.out.println("[AutoRotateCommand] bad end");
		Robot.driveSys.setDriveMotors(0);
	}

}
