package org.usfirst.frc.team2537.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.command.Scheduler;
//import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
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
	final String defaultAuto = "Default";
	final String customAuto = "My Auto";
	String autoSelected;
	SendableChooser chooser;
	// My stuff
	public static Sensors sensorSys;//TO be done.
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
		//SaberMessage.printMessage();
		//Dashboard
		/*chooser = new SendableChooser();
		chooser.addDefault("Default Auto", defaultAuto);
		chooser.addObject("My Auto", customAuto);
		SmartDashboard.putData("Auto choices", chooser);*/
		
		//Initalize Everything
		sensorSys			= new Sensors();
		shooterFlywheelSys	= new FlywheelSubsystem();
		shooterAngleSys		= new AngleSubsystem();
		shooterActuatorSys	= new ActuatorSubsystem();
		armSys				= new ArmSubsystem();
		//Sensors
		//sensorSys.init();
		//sensorSys.registerListener(armSys);
		//sensorSys.registerListener(shooterAngleSys);
		//sensorSys.registerListener(shooterFlywheelSys);
		//Shooter Flywheel
		shooterFlywheelSys.initDefaultCommand();
		//shooterFlywheelSys.registerButtons();
		//Shooter Angle
		//shooterAngleSys.initDefaultCommand();
		//shooterAngleSys.registerButtons();
		//Shooter Actuator
		//shooterActuatorSys.initDefaultCommand();
		//shooterActuatorSys.registerButtons();
		//Arm
		//armSys.initDefaultCommand();
		//armSys.registerButtons();
	//	new FlywheelSubsystemTestCommand();
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
		autoSelected = (String) chooser.getSelected();
		// autoSelected = SmartDashboard.getString("Auto Selector", defaultAuto);
		System.out.println("Auto selected: " + autoSelected);
	}

	@Override
	/**
	 * This function is called periodically during autonomous
	 */
	public void autonomousPeriodic() {
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

	@Override
	/**
	 * This function is called periodically during operator control
	 */
	public void teleopPeriodic() {
		// System.out.println("Tele Hi!");
		Scheduler.getInstance().run();
		//System.out.println("Teleop is running");
		
	}
	
	@Override
	/**
	 * This function is called periodically during test mode
	 */
	public void testPeriodic() {

	}

}
