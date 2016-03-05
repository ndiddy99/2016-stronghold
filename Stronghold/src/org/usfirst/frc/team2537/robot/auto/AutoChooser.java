package org.usfirst.frc.team2537.robot.auto;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class AutoChooser {
	public static SendableChooser autoChoice;
	
	/**
	 * Creates a set of radio buttons in java smartdashboard for autonomous choices
	 */
	public AutoChooser(){
		autoChoice= new SendableChooser();
		autoChoice.addDefault("Do nothing", null);
        autoChoice.addObject("Low Bar Auto", new LowBarAuto());
        autoChoice.addObject("B and D auto", new BorDAuto());
        autoChoice.addObject("AutoShootCommand prints", new AutoShootCommand());
        SmartDashboard.putData("AutoChooser", autoChoice);
	}
	
	/**
	 * Returns the selected choice
	 * @return autonomous strategy choice
	 */
	public Command getAutoChoice(){
		return (Command) autoChoice.getSelected();
	}

}
