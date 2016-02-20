package org.usfirst.frc.team2537.robot;

import org.usfirst.frc.team2537.robot.arm.ArmSubsystem;
import org.usfirst.frc.team2537.robot.auto.AutoChooser;
import org.usfirst.frc.team2537.robot.drive.DriveSubsystem;
import org.usfirst.frc.team2537.robot.input.Sensors;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
	AutoChooser autoChooser;
	Command autoCommand;
	final String defaultAuto = "Default";
	final String customAuto = "My Auto";
	String autoSelected;
	public static DriveSubsystem driveSys;
	// My stuff

	/**	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	public void robotInit() {
		driveSys = new DriveSubsystem();
		driveSys.initDefaultCommand();
		driveSys.registerButtons();
		autoChooser = new AutoChooser();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString line to get the auto name from the text box below the Gyro
	 *
	 * You can add additional auto modes by adding additional comparisons to the
	 * switch structure below with additional strings. If using the
	 * SendableChooser make sure to add them to the chooser code above as well.
	 */
	public void autonomousInit() {
		autoCommand = autoChooser.getAutoChoice();
		Scheduler.getInstance().add(autoCommand);
		
	}

	/**
	 * This function is called periodically during autonomous
	 */
	
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
		sensorSys.handleEvents();
		switch (autoSelected) {
		case customAuto:
			// Put custom auto code here
			break;
		case defaultAuto:
		default:
			// Put default auto code here
			break;
		}
	}

	/**
	 * 
	 */
	public void teleopInit(){
		System.out.println("Teleop init");
		Scheduler.getInstance().add(new RobotInit());
		if(autoCommand != null)
			autoCommand.cancel();
	}
	/**
	 * This function is called periodically during operator control
	 */
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		sensorSys.handleEvents();
		// Scheduler.getInstance().add(new driveCommand());
		// System.out.println("hi");

	}

	/**
	 * This function is called periodically during test mode
	 */
	public void testPeriodic() {
		sensorSys.handleEvents();

	}

}
