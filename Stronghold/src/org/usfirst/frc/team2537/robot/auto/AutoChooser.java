package org.usfirst.frc.team2537.robot.auto;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class AutoChooser {
	public static SendableChooser autoChoice;
	public AutoChooser(){
		autoChoice= new SendableChooser();
        autoChoice.addDefault("Default Autonomous", new DefaultAutoCommand());
        autoChoice.addObject("Drive Straight Forever", new AutoDriveStraightCommand());
        autoChoice.addObject("Spin forever", new AutoRotateCommand());
        autoChoice.addObject("DriveToFun", new CommandGroup(){
        	{
        		addSequential(new AutoDriveToCommandGroup(5, 5));
        		addSequential(new AutoDriveToCommandGroup(8, 5));
        		addSequential(new AutoDriveToCommandGroup(2, 10));
        		addSequential(new AutoDriveToCommandGroup(20, 8));
        	}
        });
        SmartDashboard.putData("point to point stuff", autoChoice);
	}
	public Command getAutoChoice(){
		return (Command) autoChoice.getSelected();
	}

}
