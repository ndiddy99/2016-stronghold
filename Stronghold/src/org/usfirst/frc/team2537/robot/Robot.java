package org.usfirst.frc.team2537.robot;

import org.usfirst.frc.team2537.robot.arm.ArmSubsystem;
import org.usfirst.frc.team2537.robot.auto.AutoChooser;
import org.usfirst.frc.team2537.robot.camera.CameraFeeds;
import org.usfirst.frc.team2537.robot.drive.DriveSubsystem;
import org.usfirst.frc.team2537.robot.input.Sensors;
import org.usfirst.frc.team2537.robot.shooter.actuator.ActuatorSubsystem;
import org.usfirst.frc.team2537.robot.shooter.angle.AngleSubsystem;
import org.usfirst.frc.team2537.robot.shooter.angle.AngleSubsystemPID;
import org.usfirst.frc.team2537.robot.shooter.flywheel.FlywheelSubsystem;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
	private Command autoCommand;
	private final String defaultAuto = "Default";
	private final String customAuto = "My Auto";
	private String autoSelected;
	AutoChooser autoChooser;
	public static DriveSubsystem driveSys;
	public static CameraFeeds feeds;
	private CameraFeeds cameraFeeds = new CameraFeeds();
	public static ArmSubsystem armSys;

	SendableChooser chooser;
	// My stuff
	public static Sensors sensorSys;
	public static FlywheelSubsystem shooterFlywheelSys;
	public static ActuatorSubsystem shooterActuatorSys;
	public static AngleSubsystemPID shooterAngleSys;

	@Override
	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	public void robotInit() {
		// SaberMessage.printMessage();
		// Dashboard
		/*
		 * chooser = new SendableChooser(); chooser.addDefault("Default Auto",
		 * defaultAuto); chooser.addObject("My Auto", customAuto);
		 * SmartDashboard.putData("Auto choices", chooser);
		 */

		// Initalize Everything

		driveSys = new DriveSubsystem();
		driveSys.registerButtons();
		driveSys.initDefaultCommand();

		armSys = new ArmSubsystem();
		armSys.initDefaultCommand();
		armSys.registerButtons();
		//
		autoChooser = new AutoChooser();

		shooterFlywheelSys = new FlywheelSubsystem();
		shooterAngleSys = new AngleSubsystemPID();
		shooterActuatorSys = new ActuatorSubsystem();
		shooterFlywheelSys.initDefaultCommand();
		shooterFlywheelSys.registerButtons();
		// Shooter Angle
		shooterAngleSys.initDefaultCommand();
		shooterAngleSys.registerButtons();

		// Shooter Actuator
		shooterActuatorSys.initDefaultCommand();
		shooterActuatorSys.registerButtons();

		sensorSys = new Sensors();
		sensorSys.init();

		sensorSys.registerListener(armSys);
		sensorSys.registerListener(shooterAngleSys);
		sensorSys.registerListener(shooterFlywheelSys);

		feeds = new CameraFeeds();
	}

	@Override
	/**
	 * This function is called periodically during autonomous
	 */
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	public void autonomousInit() {
		Robot.armSys.init();
	}

	public void teleopInit() {
		feeds.init();
		sensorSys.handleEvents();

	}

	/**
	 * This function is called periodically during operator control
	 */
	public void teleopPeriodic() {
		sensorSys.handleEvents();
		sensorSys.updateSmartDashboardValues();
		feeds.run();
		Scheduler.getInstance().run();
	}

	@Override
	/**
	 * This function is called periodically during test mode
	 */
	public void testPeriodic() {
		sensorSys.handleEvents();
		Scheduler.getInstance().run();

	}

	@Override
	public void disabledInit() {
	}

}
