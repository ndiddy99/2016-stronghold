package org.usfirst.frc.team2537.robot.auto;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;

public class AutoChooser {
	public static SendableChooser autoChoice;
	
	/**
	 * Creates a set of radio buttons in java smartdashboard for autonomous choices
	 */
	public AutoChooser(){
		autoChoice= new SendableChooser();
        autoChoice.addDefault("Default Autonomous", new CourseCorrect(100));
        autoChoice.addObject("Drive 5 inches (hopefully)", new AutoDriveStraightCommand(5));
//        autoChoice.addObject("Drive Straight 5 inches", new AutoDriveStraightCommand(5));
//        autoChoice.addObject("CourseCorrectAuto", new CourseCorrect(100));
//        autoChoice.addObject("90 degree turn", new AutoRotateCommand(90));
//        autoChoice.addObject("DriveToFun", new CommandGroup(){
//        	{
//        		addSequential(new AutoDriveToCommandGroup(5, 5));
//        		addSequential(new AutoDriveToCommandGroup(8, 5));
//        		addSequential(new AutoDriveToCommandGroup(2, 10));
//        		addSequential(new AutoDriveToCommandGroup(20, 8));
//        	}
//        });
//        autoChoice.addObject("Prints", new CourseCorrect(100));
//        SmartDashboard.putData("point to point stuff", autoChoice);
	}
	
	/**
	 * Returns the selected choice
	 * @return autonomous strategy choice
	 */
	public Command getAutoChoice(){
		return (Command) autoChoice.getSelected();
	}

}
