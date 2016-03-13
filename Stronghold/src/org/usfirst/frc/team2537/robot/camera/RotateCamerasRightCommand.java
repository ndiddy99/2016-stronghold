package org.usfirst.frc.team2537.robot.camera;

import java.util.Queue;

import org.usfirst.frc.team2537.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class RotateCamerasRightCommand extends Command {

	@Override
	protected void initialize() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void execute() {
		// TODO Auto-generated method stub
		Queue<Integer> cameras = Robot.feeds.getCameras();
				
		cameras.add(Robot.feeds.getCurCam());	
		
		Robot.feeds.changeCam(cameras.poll());

	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return true;
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