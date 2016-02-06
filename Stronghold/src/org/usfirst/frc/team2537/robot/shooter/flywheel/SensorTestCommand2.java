package org.usfirst.frc.team2537.robot.shooter.flywheel;

import org.usfirst.frc.team2537.robot.Robot;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.FeedbackDevice;
import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Joystick.AxisType;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class SensorTestCommand2 extends Command {
	
	private CANTalon testMotor;
	private Joystick testJoystick;
	private StringBuffer sb = new StringBuffer();
	private byte loops = 0;
	

    public SensorTestCommand2(int motorPort, int joystickPort) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.shooterFlywheelSys);
    	testMotor = new CANTalon(motorPort);
    	testMotor.setFeedbackDevice(FeedbackDevice.QuadEncoder);
    	testMotor.configEncoderCodesPerRev(20);
    	testJoystick = new Joystick(joystickPort);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	testMotor.reverseSensor(false);
    	/* set the peak and nominal outputs, 12V means full */
    	testMotor.configNominalOutputVoltage(+0.0f, -0.0f);
    	testMotor.configPeakOutputVoltage(12.0f, -12.0f);
    	/* set closed loop gains in slot0 */
    	testMotor.setProfile(0);
    	testMotor.setF(0);
    	testMotor.setP(.5);
    	testMotor.setI(.01);
    	testMotor.setD(0);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	/* get gamepad axis */
    	double leftYstick = testJoystick.getAxis(AxisType.kY);
    	double motorOutput = testMotor.getOutputVoltage() / testMotor.getBusVoltage();
    	/* prepare to print it*/
    	sb.append("\tout:");
    	sb.append(motorOutput);
    	sb.append("\tspd:");
    	sb.append(testMotor.getSpeed());
    	
    	if (testJoystick.getRawButton(1)){
    		/* Speed Mode */
    		double targetSpeed = leftYstick * 1500;/* 1500 RPM in either direction */
    		testMotor.changeControlMode(TalonControlMode.Speed);
    		testMotor.set(targetSpeed);
    		
    		/* append more signals to print when in speed mode. */
    		sb.append("\terr:");
    		sb.append(testMotor.getClosedLoopError());
    		sb.append("\ttrg:");
    		sb.append(targetSpeed);
    	} else {
    		/* Percent Voltage Mode */
    		testMotor.changeControlMode(TalonControlMode.PercentVbus);
    		testMotor.set(leftYstick);
    	}
    	
    	if (++loops >= 10) {
    		loops = 0;
    		System.out.println(sb.toString());
    	}
    	
    	sb.setLength(0);
    	
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
