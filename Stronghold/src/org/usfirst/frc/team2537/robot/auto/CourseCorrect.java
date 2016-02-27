//package org.usfirst.frc.team2537.robot.auto;
//
//import org.usfirst.frc.team2537.robot.Robot;
//
//import com.kauailabs.navx.frc.AHRS;
//
//import edu.wpi.first.wpilibj.DriverStation;
//import edu.wpi.first.wpilibj.Joystick;
//import edu.wpi.first.wpilibj.PIDController;
//import edu.wpi.first.wpilibj.PIDOutput;
//import edu.wpi.first.wpilibj.RobotDrive;
//import edu.wpi.first.wpilibj.SPI;
//import edu.wpi.first.wpilibj.Timer;
//import edu.wpi.first.wpilibj.command.Command;
//import edu.wpi.first.wpilibj.livewindow.LiveWindow;
//import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
//
//
//public class CourseCorrect extends Command implements PIDOutput{
//	private AHRS ahrs;
//	private RobotDrive myRobot;
//	private Joystick stick;
//	private PIDController turnController;
//	private double rotateToAngleRate;
//	
//	private double gangle;
//	private double angle;
//	private double speed;
//	private double distance;
//	private static final boolean debug = false;
//	private static final double DEFAULT_SPEED = 0.3;
//	
//	 static final double kP = 0.03;
//	  static final double kI = 0.00;
//	  static final double kD = 0.00;
//	  static final double kF = 0.00;
//	  
//	  static final double kToleranceDegrees = 2.0f;
//	  
//	public CourseCorrect() {
//		this(0);
//	}
//	public CourseCorrect(double distance) {
//		requires(Robot.driveSys);
//		this.distance = distance;
//		if (distance < 0)
//			speed = -DEFAULT_SPEED;
//		else
//			speed = DEFAULT_SPEED;
//	}
//	
//	public CourseCorrect(double distance, double speed) {
//		requires(Robot.driveSys);
//		this.distance = distance;
//		this.speed = speed;
//	}
//
//	@Override
//	protected void initialize() {
//		Robot.driveSys.setDriveMotors(speed);
//		Robot.driveSys.resetEncoders();
//		 angle = ahrs.getAngle();
//		  myRobot.setSafetyEnabled(false);
//	      myRobot.drive(0.0, 0.0);    // stop robot
//	      Timer.delay(2.0);		    //    for 2 seconds
//	      myRobot.drive(0.0, 0.0);	
//	}
//	
//	
//	@Override
//	protected void execute() {
//		myRobot = new RobotDrive(0, 1);
//	      myRobot.setExpiration(0.1);
//	      stick = new Joystick(0);
//	      try {
//	          /* Communicate w/navX-MXP via the MXP SPI Bus.                                     */
//	          /* Alternatively:  I2C.Port.kMXP, SerialPort.Port.kMXP or SerialPort.Port.kUSB     */
//	          /* See http://navx-mxp.kauailabs.com/guidance/selecting-an-interface/ for details. */
//	          ahrs = new AHRS(SPI.Port.kMXP); 
//	      } catch (RuntimeException ex ) {
//	          DriverStation.reportError("Error instantiating navX-MXP:  " + ex.getMessage(), true);
//	      }
//	      turnController = new PIDController(kP, kI, kD, kF, ahrs, this);
//	      turnController.setInputRange(-180.0f,  180.0f);
//	      turnController.setOutputRange(-1.0, 1.0);
//	      turnController.setAbsoluteTolerance(kToleranceDegrees);
//	      turnController.setContinuous(true);
//	      
//		Timer.delay(0.020); /* wait for one motor update time period (50Hz) */
//		
//		   myRobot.setSafetyEnabled(true);
//		     
//		          boolean rotateToAngle = false;
//		       
//		boolean zero_yaw_pressed = stick.getTrigger();
//		if (zero_yaw_pressed) {
//			ahrs.zeroYaw();
//			gangle = ahrs.getAngle();
//		}
//		if (gangle < angle ) {
//			turnController.setSetpoint(angle);
//			rotateToAngle = true;
//		}
//		else if (gangle > angle ){
//			turnController.setSetpoint(angle);
//			rotateToAngle = true;
//	
//		}
//		else{
//			rotateToAngle = false;
//		}
//		;
//		SmartDashboard.putBoolean("IMU_Connected", ahrs.isConnected());
//		SmartDashboard.putBoolean("IMU_IsCalibrating", ahrs.isCalibrating());
//		SmartDashboard.putNumber("IMU_Yaw", ahrs.getYaw());
//		SmartDashboard.putNumber("IMU_Pitch", ahrs.getPitch());
//		SmartDashboard.putNumber("IMU_Roll", ahrs.getRoll());
//
//		/* Display tilt-corrected, Magnetometer-based heading (requires */
//		/* magnetometer calibration to be useful) */
//
//		SmartDashboard
//				.putNumber("IMU_CompassHeading", ahrs.getCompassHeading());
//
//		/*
//		 * Display 9-axis Heading (requires magnetometer calibration to be
//		 * useful)
//		 */
//		SmartDashboard.putNumber("IMU_FusedHeading", ahrs.getFusedHeading());
//
//		/*
//		 * These functions are compatible w/the WPI Gyro Class, providing a
//		 * simple
//		 */
//		/* path for upgrading from the Kit-of-Parts gyro to the navx MXP */
//
//		SmartDashboard.putNumber("IMU_TotalYaw", ahrs.getAngle());
//		SmartDashboard.putNumber("IMU_YawRateDPS", ahrs.getRate());
//
//		/*
//		 * Display Processed Acceleration Data (Linear Acceleration, Motion
//		 * Detect)
//		 */
//
//		SmartDashboard.putNumber("IMU_Accel_X", ahrs.getWorldLinearAccelX());
//		SmartDashboard.putNumber("IMU_Accel_Y", ahrs.getWorldLinearAccelY());
//		SmartDashboard.putBoolean("IMU_IsMoving", ahrs.isMoving());
//		SmartDashboard.putBoolean("IMU_IsRotating", ahrs.isRotating());
//
//		/*
//		 * Display estimates of velocity/displacement. Note that these values
//		 * are
//		 */
//		/* not expected to be accurate enough for estimating robot position on a */
//		/*
//		 * FIRST FRC Robotics Field, due to accelerometer noise and the
//		 * compounding
//		 */
//		/* of these errors due to single (velocity) integration and especially */
//		/* double (displacement) integration. */
//
//		SmartDashboard.putNumber("Velocity_X", ahrs.getVelocityX());
//		SmartDashboard.putNumber("Velocity_Y", ahrs.getVelocityY());
//		SmartDashboard.putNumber("Displacement_X", ahrs.getDisplacementX());
//		SmartDashboard.putNumber("Displacement_Y", ahrs.getDisplacementY());
//
//		/* Display Raw Gyro/Accelerometer/Magnetometer Values */
//		/* NOTE: These values are not normally necessary, but are made available */
//		/* for advanced users. Before using this data, please consider whether */
//		/* the processed data (see above) will suit your needs. */
//
//		SmartDashboard.putNumber("RawGyro_X", ahrs.getRawGyroX());
//		SmartDashboard.putNumber("RawGyro_Y", ahrs.getRawGyroY());
//		SmartDashboard.putNumber("RawGyro_Z", ahrs.getRawGyroZ());
//		SmartDashboard.putNumber("RawAccel_X", ahrs.getRawAccelX());
//		SmartDashboard.putNumber("RawAccel_Y", ahrs.getRawAccelY());
//		SmartDashboard.putNumber("RawAccel_Z", ahrs.getRawAccelZ());
//		SmartDashboard.putNumber("RawMag_X", ahrs.getRawMagX());
//		SmartDashboard.putNumber("RawMag_Y", ahrs.getRawMagY());
//		SmartDashboard.putNumber("RawMag_Z", ahrs.getRawMagZ());
//		SmartDashboard.putNumber("IMU_Temp_C", ahrs.getTempC());
//
//		/* Omnimount Yaw Axis Information */
//		/*
//		 * For more info, see
//		 * http://navx-mxp.kauailabs.com/installation/omnimount
//		 */
//		AHRS.BoardYawAxis yaw_axis = ahrs.getBoardYawAxis();
//		SmartDashboard.putString("YawAxisDirection", yaw_axis.up ? "Up"
//				: "Down");
//		SmartDashboard.putNumber("YawAxis", yaw_axis.board_axis.getValue());
//
//		/* Sensor Board Information */
//		SmartDashboard.putString("FirmwareVersion", ahrs.getFirmwareVersion());
//
//		/* Quaternion Data */
//		/*
//		 * Quaternions are fascinating, and are the most compact representation
//		 * of
//		 */
//		/*
//		 * orientation data. All of the Yaw, Pitch and Roll Values can be
//		 * derived
//		 */
//		/*
//		 * from the Quaternions. If interested in motion processing, knowledge
//		 * of
//		 */
//		/* Quaternions is highly recommended. */
//		SmartDashboard.putNumber("QuaternionW", ahrs.getQuaternionW());
//		SmartDashboard.putNumber("QuaternionX", ahrs.getQuaternionX());
//		SmartDashboard.putNumber("QuaternionY", ahrs.getQuaternionY());
//		SmartDashboard.putNumber("QuaternionZ", ahrs.getQuaternionZ());
//
//		/* Connectivity Debugging Support */
//		SmartDashboard.putNumber("IMU_Byte_Count", ahrs.getByteCount());
//		SmartDashboard.putNumber("IMU_Update_Count", ahrs.getUpdateCount());
//		          }
//	
//
//	@Override
//	protected boolean isFinished() {
//		if (distance < 0) {
//			return (Robot.driveSys.getEncoderAverage() <= distance);
//		}
//		return (Robot.driveSys.getEncoderAverage() >= distance);
//	}
//
//	@Override
//	protected void end() {
//		if (debug){
//			System.out.println("[AutoDriveStraightCommand] good end");
//		}
//		Robot.driveSys.setDriveMotors(0);
//	}
//
//	@Override
//	protected void interrupted() {
//		if (debug){
//			System.out.println("[AutoDriveStraightCommand] bad end");
//		}
//		Robot.driveSys.setDriveMotors(0);
//	}
//	@Override
//	public void pidWrite(double output) {
//		// TODO Auto-generated method stub
//		rotateToAngleRate = output;
//		
//	}
//
//}
//
