package org.usfirst.frc.team2537.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team2537.robot.arm.ArmSubsystem;
import org.usfirst.frc.team2537.robot.input.Sensors;
import org.usfirst.frc.team2537.robot.shooter.angle.AngleSubsystem;
import org.usfirst.frc.team2537.robot.shooter.flywheel.FlywheelSubsystem;

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
	public static final Sensors sensorSys = new Sensors();//TO be done.
	public static final ArmSubsystem armSys = new ArmSubsystem();
	public static final FlywheelSubsystem shooterFlywheelSys = new FlywheelSubsystem();
	public static final AngleSubsystem shooterAngleSys = new AngleSubsystem();
	
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
		
		armSys.registerButtons();
		armSys.initDefaultCommand();
		//START Shooter
		shooterFlywheelSys.registerButtons();
		shooterFlywheelSys.initDefaultCommand();
		
		shooterAngleSys.registerButtons();
		shooterAngleSys.initDefaultCommand();
		
		
		sensorSys.init();
		//sensorSys.registerListener(armSys);
		sensorSys.registerListener(shooterAngleSys);
		sensorSys.registerListener(shooterFlywheelSys);
		
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
		// autoSelected = SmartDashboard.getString("Auto Selector",
		// defaultAuto);
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
		// Scheduler.getInstance().add(new driveCommand());
		// System.out.println("hi");

	}
	
	@Override
	/**
	 * This function is called periodically during test mode
	 */
	public void testPeriodic() {

	}

}
