package org.usfirst.frc.team2537.robot.auto;

import org.usfirst.frc.team2537.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class AutoStayStillCommand extends Command{
	private double leftEncoder;
	private double rightEncoder;
	private long startTime;
	private long time;
	private static final double TOLERANCE = 0.5; //inches
	private static final boolean debug = false;

	/**
	 * sits still for a period of time
	 * @param time Time to stay still in milliseconds
	 */
	public AutoStayStillCommand(long time){
		requires(Robot.driveSys);
		this.time = time;
	}
	
	@Override
	protected void initialize() {
		leftEncoder = Robot.driveSys.getLeftEncoders();
		rightEncoder = Robot.driveSys.getRightEncoders();
		if(debug) System.out.println("[AutoStayStillCommand] Initializing with time: " + time);
		startTime = System.currentTimeMillis();
	}

	@Override
	protected void execute() {
		//Left motors
		if(Math.abs(Robot.driveSys.getLeftEncoders() - leftEncoder) >= TOLERANCE){
			Robot.driveSys.setLeftDriveMotors(leftEncoder - Robot.driveSys.getLeftEncoders());
		}else{
			Robot.driveSys.setLeftDriveMotors(0);
		}
		
		//right motors
		if(Math.abs(Robot.driveSys.getRightEncoders() - rightEncoder) >= TOLERANCE){
			Robot.driveSys.setRightDriveMotors(rightEncoder - Robot.driveSys.getRightEncoders());
		}else{
			Robot.driveSys.setRightDriveMotors(0);
		}
		
		if(debug) System.out.println("[AutoStayStillCommand] Current time: " + System.currentTimeMillis() + "  end time: " + (startTime + time));
	}

	@Override
	protected boolean isFinished() {
		return System.currentTimeMillis() >= (startTime + time);
	}

	@Override
	protected void end() {
		Robot.driveSys.setDriveMotors(0);
	}

	@Override
	protected void interrupted() {
		Robot.driveSys.setDriveMotors(0);		
	}

}
