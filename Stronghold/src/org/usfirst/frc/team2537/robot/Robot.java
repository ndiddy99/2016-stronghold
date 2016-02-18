package org.usfirst.frc.team2537.robot;

import org.usfirst.frc.team2537.robot.arm.ArmSubsystem;
import org.usfirst.frc.team2537.robot.camera.CameraFeeds;
import org.usfirst.frc.team2537.robot.camera.Config;
import org.usfirst.frc.team2537.robot.camera.Controller;
import org.usfirst.frc.team2537.robot.drive.DriveSubsystem;
import org.usfirst.frc.team2537.robot.input.Sensors;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
//	private AutoChooser autoChooser;
//	private Command autoCommand;
//	private final String defaultAuto = "Default";
//	private final String customAuto = "My Auto";
//	private String autoSelected;
	public static Sensors sensorSys;
	public static ArmSubsystem armSys;
	public static DriveSubsystem driveSys;
	private Controller contr = new Controller(Config.Controller.chn, Config.Controller.maxButtons, Config.Controller.linearity);
    private CameraFeeds cameraFeeds = new CameraFeeds(contr);
    
	public void robotInit() {
		sensorSys = new Sensors();
		sensorSys.init();
		
		driveSys = new DriveSubsystem();
		driveSys.registerButtons();
		driveSys.initDefaultCommand();
		
		armSys = new ArmSubsystem();
		armSys.initDefaultCommand();
		armSys.registerButtons();
		sensorSys.registerListener(armSys);
//		autoChooser = new AutoChooser();
	
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
//		autoCommand = autoChooser.getAutoChoice();
//		Scheduler.getInstance().add(autoCommand);
		

	}

	/**
	 * This function is called periodically during autonomous
	 */
	public void autonomousPeriodic() {
	}

    /**
     * This function is called periodically during operator control
     */

	/**
	 * 
	 */
	public void teleopInit(){
		cameraFeeds.init();
	}
	/**
	 * This function is called periodically during operator control
	 */
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		contr.update();
		cameraFeeds.run();
		
		sensorSys.handleEvents();
	}

	/**
	 * This function is called periodically during test mode
	 */
	public void testPeriodic() {
		cameraFeeds.end();
	}

}
