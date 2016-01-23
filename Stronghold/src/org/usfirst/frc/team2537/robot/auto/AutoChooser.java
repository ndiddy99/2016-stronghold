package org.usfirst.frc.team2537.robot.auto;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class AutoChooser {
	public static SendableChooser autoChoice;
	public AutoChooser(){
		autoChoice= new SendableChooser();
        autoChoice.addDefault("Default Autonomous", new DefaultAutoCommand());
        autoChoice.addObject("Drive Straight Forever", new AutoDriveStraightCommand());
        autoChoice.addObject("Spin forever", new AutoRotateCommand());
        SmartDashboard.putData("Andrew's code (copied and pasted)", autoChoice);
	}
	public Command getAutoChoice(){
		return (Command) autoChoice.getSelected();
	}

}
