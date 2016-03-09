package org.usfirst.frc.team2537.robot;

import org.usfirst.frc.team2537.robot.input.Sensors;
import org.usfirst.frc.team2537.robot.shooter.actuator.ActuatorSubsystem;
import org.usfirst.frc.team2537.robot.shooter.angle.AngleSubsystemPID;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
	// private AutoChooser autoChooser;
	// private Command autoCommand;
	// private final String defaultAuto = "Default";
	// private final String customAuto = "My Auto";
	// private String autoSelected;
	// AutoChooser autoChooser;
	// Command autoCommand;
	final String defaultAuto = "Default";
	final String customAuto = "My Auto";
	String autoSelected;
	// public static DriveSubsystem driveSys;
	// public static CameraFeeds feeds;
	// private Controller contr = new Controller(Config.Controller.chn,
	// Config.Controller.maxButtons, Config.Controller.linearity);
	// private CameraFeeds cameraFeeds = new CameraFeeds(contr);

	SendableChooser chooser;
	// My stuff
	public static Sensors sensorSys;
	// public static FlywheelSubsystem shooterFlywheelSys;
	// public static AngleSubsystem shooterAngleSys;
	public static ActuatorSubsystem shooterActuatorSys;
	public static AngleSubsystemPID shooterAngleSys;
	// public static ArmSubsystem armSys;

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

		// driveSys = new DriveSubsystem();
		// driveSys.registerButtons();
		// driveSys.initDefaultCommand();

		// armSys = new ArmSubsystem();
		// armSys.initDefaultCommand();
		// armSys.registerButtons();
		//
		// autoChooser = new AutoChooser();

		// shooterFlywheelSys = new FlywheelSubsystem();
		sensorSys = new Sensors();
		sensorSys.init();

		// sensorSys.registerListener(armSys);
		shooterAngleSys = new AngleSubsystemPID();

		sensorSys.registerListener(shooterAngleSys);

		shooterActuatorSys = new ActuatorSubsystem();
		// shooterFlywheelSys.initDefaultCommand();
		// shooterFlywheelSys.registerButtons();
		// Shooter Angle
		shooterAngleSys.initDefaultCommand();
		shooterAngleSys.registerButtons();

		// Shooter Actuator
		shooterActuatorSys.initDefaultCommand();
		shooterActuatorSys.registerButtons();

		// sensorSys.registerListener(shooterFlywheelSys);
		// sensorSys.registerListener(shooterA);

		// feeds = new CameraFeeds();

	}

	@Override
	/**
	 * This function is called periodically during autonomous
	 */
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	public void autonomousInit() {
		// Robot.armSys.init();
	}

	public void teleopInit() {
		// feeds.init();
		sensorSys.handleEvents();
		if (shooterAngleSys != null && shooterAngleSys.getCurrentAngle() != null) {
			shooterAngleSys.setSetpoint(shooterAngleSys.getCurrentAngle());
		} else {
			shooterAngleSys.setSetpoint(0.0);
		}

	}

	/**
	 * This function is called periodically during operator control
	 */
	public void teleopPeriodic() {
		sensorSys.handleEvents();
		// sensorSys.updateSmartDashboardValues();
		// feeds.run();
		Scheduler.getInstance().run();
		try {
			SmartDashboard.putString("Current Read Angle ", shooterAngleSys.getCurrentAngle().toString());
			SmartDashboard.putNumber("Setpoint ", shooterAngleSys.getSetpoint());
			SmartDashboard.putNumber("Error ", shooterAngleSys.getPIDController().getError());
			SmartDashboard.putNumber("Motor Voltage Percentage ", shooterAngleSys.getPIDController().get());
		} catch (NullPointerException e) {
		}

		SmartDashboard.putData(shooterAngleSys);

		// SmartDashboard.putNumber("Arm IMU", armSys.getIMUAngle());
		// Double shooterAngle = shooterAngleSys.getCurrentAngle();
		// SmartDashboard.putString("Shooter IMU",
		// shooterAngle==null?"null":shooterAngle.toString());
		// SmartDashboard.putNumber("Arm Encoder", armSys.getAngle());
		// SmartDashboard.putBoolean("Is Fwd limit switch enabled",
		// Robot.armSys.armMotor.isFwdLimitSwitchClosed());
		// SmartDashboard.putBoolean("Is Rev limit switch enabled",
		// Robot.armSys.armMotor.isRevLimitSwitchClosed());
	}

	public void testInit() {
		sensorSys.handleEvents();
	}

	@Override
	/**
	 * This function is called periodically during test mode
	 */
	public void testPeriodic() {
		sensorSys.handleEvents();
		// sensorSys.updateSmartDashboardValues();
		// feeds.run();
		Scheduler.getInstance().run();
		try {
			SmartDashboard.putString("Current Read Angle ", shooterAngleSys.getCurrentAngle().toString());
			SmartDashboard.putNumber("Setpoint ", shooterAngleSys.getSetpoint());
			SmartDashboard.putNumber("Error ", shooterAngleSys.getPIDController().getError());
			SmartDashboard.putNumber("Motor Voltage Percentage ", shooterAngleSys.getPIDController().get());
		} catch (NullPointerException e) {
			e.printStackTrace();
		}

		SmartDashboard.putData(shooterAngleSys);

	}

	@Override
	public void disabledInit() {
		// System.out.println(System.currentTimeMillis()-START_TIME);
	}

}
