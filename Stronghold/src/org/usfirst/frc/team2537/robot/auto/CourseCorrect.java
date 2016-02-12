package org.usfirst.frc.team2537.robot.auto;

import org.usfirst.frc.team2537.robot.Robot;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

public class CourseCorrect extends AutoDriveToCommandGroup implements PIDOutput {
	  public CourseCorrect(double x, double y) {
		super(x, y);
		// TODO Auto-generated constructor stub
	}

	  AHRS ahrs;
	  RobotDrive myRobot;
	  Joystick stick;
	  PIDController turnController;
	  double rotateToAngleRate;
	  
	  static final double kP = 0.03;
	  static final double kI = 0.00;
	  static final double kD = 0.00;
	  static final double kF = 0.00;
		
	  static final double kToleranceDegrees = 2.0f;
	  
	  public void Robot() {
	      myRobot = new RobotDrive(0, 1);
	      myRobot.setExpiration(0.1);
	      stick = new Joystick(0);
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

	  /**
	   * Drive left & right motors for 2 seconds then stop
	   */
	  public void autonomous() {
	      myRobot.setSafetyEnabled(false);
	      myRobot.drive(0.0, 0.0);    // stop robot
	      Timer.delay(2.0);		    //    for 2 seconds
	      myRobot.drive(0.0, 0.0);	// stop robot
	  }

	  /**
	   * Runs the motors with onnidirectional drive steering.
	   * 
	   * Implements Field-centric drive control.
	   * 
	   * Also implements "rotate to angle", where the angle
	   * being rotated to is defined by one of four buttons.
	   * 
	   * Note that this "rotate to angle" approach can also 
	   * be used while driving to implement "straight-line
	   * driving".
	   */
	  public void operatorControl() {
	      myRobot.setSafetyEnabled(true);
	      while (isOperatorControl() && isEnabled()) {
	          boolean rotateToAngle = false;
	          if ( stick.getRawButton(1)) {
	              ahrs.reset();
	          }
	          if ( stick.getRawButton(2)) {
	              turnController.setSetpoint(0.0f);
	              rotateToAngle = true;
	          } else if ( stick.getRawButton(3)) {
	              turnController.setSetpoint(90.0f);
	              rotateToAngle = true;
	          } else if ( stick.getRawButton(4)) {
	              turnController.setSetpoint(179.9f);
	              rotateToAngle = true;
	          } else if ( stick.getRawButton(5)) {
	              turnController.setSetpoint(-90.0f);
	              rotateToAngle = true;
	          }
	          double currentRotationRate;
	          if ( rotateToAngle ) {
	              turnController.enable();
	              currentRotationRate = rotateToAngleRate;
	          } else {
	              turnController.disable();
	              currentRotationRate = stick.getTwist();
	          }
	          try {
	              /* Use the joystick X axis for lateral movement,          */
	              /* Y axis for forward movement, and the current           */
	              /* calculated rotation rate (or joystick Z axis),         */
	              /* depending upon whether "rotate to angle" is active.    */
	              myRobot.mecanumDrive_Cartesian(stick.getX(), stick.getY(), 
	                                             currentRotationRate, ahrs.getAngle());
	          } catch( RuntimeException ex ) {
	              DriverStation.reportError("Error communicating with drive system:  " + ex.getMessage(), true);
	          }
	          Timer.delay(0.005);		// wait for a motor update time
	      }
	  }
	@Override
	protected void initialize() {
		
		
	}

	/* This function is invoked periodically by the PID Controller, */
	  /* based upon navX-MXP yaw angle input and PID Coefficients.    */
	  public void pidWrite(double output) {
	      rotateToAngleRate = output;
	  }
	  
	@Override
	protected void execute() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected void end() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void interrupted() {
		// TODO Auto-generated method stub
		
	}


	  
	}




