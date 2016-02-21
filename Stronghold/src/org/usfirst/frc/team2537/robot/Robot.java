package org.usfirst.frc.team2537.robot;

import org.usfirst.frc.team2537.robot.arm.ArmSubsystem;
import org.usfirst.frc.team2537.robot.auto.AutoChooser;
import org.usfirst.frc.team2537.robot.drive.DriveSubsystem;
import org.usfirst.frc.team2537.robot.input.Sensor;
import org.usfirst.frc.team2537.robot.input.Sensors;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.SPI.Port;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.command.Scheduler;

import java.time.Instant;

<
import org.usfirst.frc.team2537.robot.arm.ArmSubsystem;
import org.usfirst.frc.team2537.robot.input.Sensors;
import org.usfirst.frc.team2537.robot.shooter.angle.AngleSubsystem;
import org.usfirst.frc.team2537.robot.shooter.flywheel.FlywheelSubsystem;
import org.usfirst.frc.team2537.robot.shooter.actuator.ActuatorSubsystem;

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
	AutoChooser autoChooser;
	Command autoCommand;
	final String defaultAuto = "Default";
	final String customAuto = "My Auto";
	String autoSelected;
	public static DriveSubsystem driveSys;
//	private Controller contr = new Controller(Config.Controller.chn, Config.Controller.maxButtons, Config.Controller.linearity);
//    private CameraFeeds cameraFeeds = new CameraFeeds(contr);
    

	SendableChooser chooser;
	// My stuff
	public static Sensors sensorSys;
	public static FlywheelSubsystem shooterFlywheelSys;
	public static AngleSubsystem shooterAngleSys;
	public static ActuatorSubsystem shooterActuatorSys;
	public static ArmSubsystem armSys;

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
		sensorSys = new Sensors();
		sensorSys.init();
		
		driveSys = new DriveSubsystem();
		driveSys.registerButtons();
		driveSys.initDefaultCommand();
		
		shooterFlywheelSys = new FlywheelSubsystem();
		shooterAngleSys = new AngleSubsystem();
		shooterActuatorSys = new ActuatorSubsystem();
		armSys = new ArmSubsystem();
		armSys.initDefaultCommand();
		armSys.registerButtons();
		sensorSys.registerListener(armSys);
		autoChooser = new AutoChooser();
		
		System.out.println("  _________     ___.                   __________        __   ");
		System.out.println(" /   _____/____ \\_ |_________   ____   \\______   \\ _____/  |_ ");
		System.out.println(" \\_____  \\\\__  \\ | __ \\_  __ \\_/ __ \\   |    |  _//  _ \\   __\\");
		System.out.println(" /        \\/ __ \\| \\_\\ \\  | \\/\\  ___/   |    |   (  <_> )  |  ");
		System.out.println("/_______  (____  /___  /__|    \\___  >  |______  /\\____/|__|  ");
		System.out.println("        \\/     \\/    \\/            \\/          \\/             ");
		// Sensors
		sensorSys.init();
		// sensorSys.registerListener(armSys);
		sensorSys.registerListener(shooterAngleSys);
		sensorSys.registerListener(shooterFlywheelSys);
		// Shooter Flywheel
		shooterFlywheelSys.initDefaultCommand();
		shooterFlywheelSys.registerButtons();
		// Shooter Angle
		shooterAngleSys.initDefaultCommand();
		shooterAngleSys.registerButtons();
		// Shooter Actuator
		shooterActuatorSys.initDefaultCommand();
		shooterActuatorSys.registerButtons();
		// Arm
		// armSys.initDefaultCommand();
		// armSys.registerButtons();
		// Scheduler.getInstance().add(new TestCommand());
		// START_TIME = System.currentTimeMillis();
		
	}

	@Override
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
		AHRS ahrs = new AHRS(Port.kMXP);
		double angle;
		angle = ahrs.getAngle();
		autoCommand = autoChooser.getAutoChoice();
		Scheduler.getInstance().add(autoCommand);		
		autoSelected = (String) chooser.getSelected();
		autoSelected = SmartDashboard.getString("Auto Selector", defaultAuto);
		System.out.println("Auto selected: " + autoSelected);
	}

	@Override
	/**
	 * This function is called periodically during autonomous
	 */
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}
	
	public void teleopInit(){
		System.out.println("Teleop init");
		if(autoCommand != null)
			autoCommand.cancel();
		}
	/**
	 * This function is called periodically during operator control
	 */
	public void teleopPeriodic() {
		sensorSys.handleEvents();
		Scheduler.getInstance().run();
//		contr.update();
//		cameraFeeds.run();
		SmartDashboard.putNumber("Lidar Val", sensorSys.sensorVals.get(Sensor.SHOOTER_LIDAR));
		SmartDashboard.putNumber("Left Encoder Value", shooterFlywheelSys.getLeftSpeed());
		SmartDashboard.putNumber("Right Encoder Value", shooterFlywheelSys.getRightSpeed());
		SmartDashboard.putNumber("Right Error", shooterFlywheelSys.rightFlywheelMotor.getError());
		SmartDashboard.putNumber("Right Setpoint", shooterFlywheelSys.rightFlywheelMotor.getSetpoint());
		SmartDashboard.putNumber("Left Error", shooterFlywheelSys.leftFlywheelMotor.getError());
		SmartDashboard.putNumber("Left Setpoint", shooterFlywheelSys.leftFlywheelMotor.getSetpoint());
		SmartDashboard.putNumber("Lidar Value", sensorSys.lidar.input.getPeriod());
		SmartDashboard.putNumber("Tilt Sensor Value", sensorSys.tilt.input.getPeriod());
		//TODO get proximity value
//		SmartDashboard.putNumber("Proximity Sensor", );
		SmartDashboard.putNumber("Actuator Position", shooterActuatorSys.getAngle());
		SmartDashboard.putBoolean("Shooter Proximity Value", shooterFlywheelSys.isBallPresent());
		SmartDashboard.putBoolean("Raw Proximity Value", sensorSys.prox.input.get());
		// System.out.println("Actuator Position: " +
		// shooterActuatorSys.getAngle());
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
		// System.out.println(System.currentTimeMillis()-START_TIME);
	}

}
