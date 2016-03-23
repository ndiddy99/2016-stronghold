package org.usfirst.frc.team2537.robot.camera;

import java.util.Queue;

import org.usfirst.frc.team2537.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class RotateCamerasRightCommand extends Command {

	@Override
	protected void initialize() {
	    Queue<Integer> cameras = Robot.feeds.getCameras();
				
		cameras.add(Robot.feeds.getCurCam());	
		
		Robot.feeds.changeCam(cameras.poll());
	}

	@Override
	protected void execute() {
	}

	@Override
	protected boolean isFinished() {
		return true;
	}

	@Override
	protected void end() {
	}

	@Override
	protected void interrupted() {
	}

}