//package org.usfirst.frc.team2537.robot.auto;
//
//import org.usfirst.frc.team2537.robot.Robot;
//
//import edu.wpi.first.wpilibj.command.Command;
//
///**
// * Untested
// * @author Arden Zhang
// *
// */
//public class AutoDriveStraightCommand extends Command {
//	private double speed;
//	private double distance;
//	private static final boolean debug = false;
//	private static final double DEFAULT_SPEED = 0.5;
//
//	/**
//	 * drives forward nowhere
//	 */
//	public AutoDriveStraightCommand() {
//		this(0);
//	}
//
//	/**
//	 * Drives [distance] at the default speed
//	 * 
//	 * @param distance
//	 *            in inches
//	 */
//	public AutoDriveStraightCommand(double distance) {
//		requires(Robot.driveSys);
//		this.distance = distance;
//		if (distance < 0)
//			speed = -DEFAULT_SPEED;
//		else
//			speed = DEFAULT_SPEED;
//	}
//
//	/**
//	 * Drives [distance] at [speed]
//	 * 
//	 * @param distance
//	 *            distance in inches
//	 * @param speed
//	 *            speed from -1.0 to 1.0
//	 */
//	public AutoDriveStraightCommand(double distance, double speed) {
//		requires(Robot.driveSys);
//		this.distance = distance;
//		this.speed = speed;
//	}
//
//	@Override
//	protected void initialize() {
//		if (debug) {
//			System.out.println("[AutoDriveStraightCommand] Driving " + distance
//					+ " at " + speed);
//		}
//		Robot.driveSys.setDriveMotors(speed);
//		Robot.driveSys.resetEncoders();
//	}
//
//	@Override
//	protected void execute() {
//		if (debug) {
//			System.out.println("[AutoDriveStraightCommand] Current distance: "
//					+ Robot.driveSys.getEncoderAverage());
//		}
//	}
//
//	@Override
//	protected boolean isFinished() {
//		if (distance < 0) {
//			return (Robot.driveSys.getEncoderAverage() <= distance);
//		}
//		return (Robot.driveSys.getEncoderAverage() >= distance);
//	}
//
//	@Override
//	protected void end() {
//		if (debug){
//			System.out.println("[AutoDriveStraightCommand] good end");
//		}
//		Robot.driveSys.setDriveMotors(0);
//	}
//
//	@Override
//	protected void interrupted() {
//		if (debug){
//			System.out.println("[AutoDriveStraightCommand] bad end");
//		}
//		Robot.driveSys.setDriveMotors(0);
//	}
//
//}
