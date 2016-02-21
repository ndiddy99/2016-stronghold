package org.usfirst.frc.team2537.robot.auto;

import org.usfirst.frc.team2537.robot.Robot;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.SPI.Port;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

public class AutoRotateCommand extends Command implements PIDOutput {
	private double speed;
	private double gangle;
	
	private static final boolean debug = true;
	private static final double DEFAULT_SPEED = 0.5;
	private AHRS ahrs;
	double angle;
	static final double kP = 0.03;
	static final double kI = 0.00;
	static final double kD = 0.00;
	static final double kF = 0.00;

	static final double kToleranceDegrees = 2.0f;

	RobotDrive myRobot;

	PIDController turnController;
	double rotateToAngleRate;
	boolean rotateToAngle;
	/**
	 * spins 10000000 degrees at default speed counterclockwise (untested) Don't
	 * know why anybody would want the robot to spin forever
	 */

	/**
	 * Spins [angle] at default speed
	 * 
	 * @param angle
	 */

	

	/**
	 * spins [angle] degrees at [speed] counterclockwise (untested)
	 * 
	 * @param angle]
	 * @param speed
	 */
	public AutoRotateCommand(double angle) {
		
		AHRS ahrs = new AHRS(Port.kMXP);
		requires(Robot.driveSys);
		double gangle = ahrs.getAngle();	
		if(gangle < angle)
			speed = -DEFAULT_SPEED;
		else
			speed = DEFAULT_SPEED;
		angle = ahrs.getAngle();
		  gangle = 90.0f;
	      myRobot = new RobotDrive(0, 1);
	      myRobot.setExpiration(0.1);
	 
	      try {
	          /* Communicate w/navX-MXP via the MXP SPI Bus.                                     */
	          /* Alternatively:  I2C.Port.kMXP, SerialPort.Port.kMXP or SerialPort.Port.kUSB     */
	          /* See http://navx-mxp.kauailabs.com/guidance/selecting-an-interface/ for details. */
	          ahrs = new AHRS(SPI.Port.kMXP); 
	      } catch (RuntimeException ex ) {
	          DriverStation.reportError("Error instantiating navX-MXP:  " + ex.getMessage(), true);
	      }
	      turnController = new PIDController(kP, kI, kD, kF, ahrs, this);
	      turnController.setInputRange(-180.0f,  180.0f);
	      turnController.setOutputRange(-1.0, 1.0);
	      turnController.setAbsoluteTolerance(kToleranceDegrees);
	      turnController.setContinuous(true);
	      
	      /* Add the PID Controller to the Test-mode dashboard, allowing manual  */
	      /* tuning of the Turn Controller's P, I and D coefficients.            */
	      /* Typically, only the P value needs to be modified.                   */
	      LiveWindow.addActuator("DriveSystem", "RotateController", turnController);
	}

	@Override
	protected void initialize() {
		myRobot.setSafetyEnabled(false);
	      myRobot.drive(0.0, 0.0);    // stop robot
	      Timer.delay(2.0);		    //    for 2 seconds
	      myRobot.drive(0.0, 0.0);	// stop robot
	}

	@Override
	protected void execute() {
		myRobot.setSafetyEnabled(true);
	      
	          boolean rotateToAngle = false;
	          if (angle < gangle){
	        	  turnController.setSetpoint(gangle);
	              rotateToAngle = true;
	          }
	          else if ( angle > gangle){
	        	  turnController.setSetpoint(gangle);
	              rotateToAngle = true;
	          }  else{
	        	  turnController.disable();
	              
	          }
	          while (rotateToAngle = true){
	        	  Robot.driveSys.setDriveMotors(-speed, speed);
	        	  rotateToAngle = false;
	          }
		}
		
		
	

	@Override
	protected boolean isFinished() {
		boolean val = false;
//		if (angle <= 0 && Robot.driveSys.getAhrs().getAngle() >= angle) {
//			val = true;
//		} else if (angle >= 0 && Robot.driveSys.getAhrs().getAngle() <= angle) {
//			val = true;
//			System.out.println("done");
//		}
		return val;
	}

	@Override
	protected void end() {
		if (debug)
			System.out.println("[AutoRotateCommand] good end");
		Robot.driveSys.setDriveMotors(0);
	}

	@Override
	protected void interrupted() {
		if (debug)
			System.out.println("[AutoRotateCommand] bad end");
		Robot.driveSys.setDriveMotors(0);
	}

	@Override
	public void pidWrite(double output) {
		rotateToAngleRate = output;
	}

}
