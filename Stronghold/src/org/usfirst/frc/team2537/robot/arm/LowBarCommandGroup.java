//package org.usfirst.frc.team2537.robot.arm;
//
//import org.usfirst.frc.team2537.robot.Robot;
//
//import edu.wpi.first.wpilibj.command.CommandGroup;
//
//public class LowBarCommandGroup extends CommandGroup {
//
//	private static double startingAngle;
//	
//	public LowBarCommandGroup(boolean goToLow) {
//		if (goToLow) {
//			startingAngle = Robot.armSys.getAngle();
//			addSequential(new PresetArmCommand(ArmPositions.lowbarPos));
//		} else {
//			addSequential(new PresetArmCommand(startingAngle));
//		}
//		
//	}
//
//}
