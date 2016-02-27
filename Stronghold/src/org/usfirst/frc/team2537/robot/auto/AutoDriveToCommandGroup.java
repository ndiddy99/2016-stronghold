//package org.usfirst.frc.team2537.robot.auto;
//
//import com.kauailabs.navx.frc.AHRS;
//import edu.wpi.first.wpilibj.command.CommandGroup;
//
//public class AutoDriveToCommandGroup extends CommandGroup{
//	//TODO: make setCurrentX() and setCurrentY() somewhere
//	private double currentX = 0; //inches
//	private double currentY = 0; //inches
//	private double currentAngle = 0; //degrees
//	AHRS ahrs;
//	
//	/**
//	 * drives to x y pos in inches
//	 * @param x
//	 * @param y
//	 */
//	public AutoDriveToCommandGroup(double x, double y){
///*		System.out.println("I ran");
//		System.out.println(ahrs);
//		currentX = ahrs.getDisplacementX();
//		System.out.println(currentX);
//		currentY = ahrs.getDisplacementY();
//		System.out.println(currentY);
//		currentAngle = ahrs.getAngle();
//		System.out.println(currentAngle);*/
//
//		double angle = Math.toDegrees(Math.atan2(y, x)) - currentAngle;
//		while(angle > 180) angle -= 180;
//		while(angle < -180) angle += 180;
//		addSequential(new AutoRotateCommand(angle));
//		addSequential(new AutoDriveStraightCommand(Math.sqrt(Math.pow(x - currentX, 2) + Math.pow(y - currentY, 2))));
//	}
//	
//}
