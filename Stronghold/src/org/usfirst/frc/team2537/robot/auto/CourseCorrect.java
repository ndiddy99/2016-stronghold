package org.usfirst.frc.team2537.robot.auto;

import org.usfirst.frc.team2537.robot.Robot;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Command;

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
		
	@Override
	protected void initialize() {
		
		
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

	@Override
	public void pidWrite(double output) {
		// TODO Auto-generated method stub
		
	}
	
	



}

