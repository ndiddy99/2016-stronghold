package org.usfirst.frc.team2537.robot.camera;

import java.util.LinkedList;
import java.util.Queue;

import org.usfirst.frc.team2537.robot.Ports;
import org.usfirst.frc.team2537.robot.Robot;
import org.usfirst.frc.team2537.robot.input.HumanInput;

import com.ni.vision.NIVision;
import com.ni.vision.NIVision.Image;
import com.ni.vision.NIVision.Point;

import edu.wpi.first.wpilibj.CameraServer;

public class CameraFeeds {
	private int camCenter;
	private int camRight;
	private int camLeft;
	private int curCam;
	private Image frame;
	private CameraServer server;
	private Queue<Integer> cameras;
	private final ThreadGroup cameraThreads;
	private final Thread updateCameras;
	private static final int CAMERA_THREADS_MAX_PRIORITY = 7;
	private final Thread CameraRotateThread;

	public CameraFeeds() {

		cameras = new LinkedList<Integer>();
		try {
			// Get camera ids by supplying camera name ex 'cam0', found on
			// roborio web interface
			camCenter = NIVision
					.IMAQdxOpenCamera(
							Ports.DOWNWARD_BREACHING_CAMERA,
							NIVision.IMAQdxCameraControlMode.CameraControlModeController);
			curCam = camCenter;
		} catch (Exception e) {
			System.out.println("cam0 failed (downward breaching)");
		}

		try {
			camRight = NIVision
					.IMAQdxOpenCamera(
							Ports.UPWARD_BREACHING_CAMERA,
							NIVision.IMAQdxCameraControlMode.CameraControlModeController);
			cameras.add(camRight);
		} catch (Exception e) {
			System.out.println("cam1 failed (upward breaching");
		}

		try {
			camLeft = NIVision
					.IMAQdxOpenCamera(
							Ports.SHOOTING_CAMERA,
							NIVision.IMAQdxCameraControlMode.CameraControlModeController);
			cameras.add(camLeft);
		} catch (Exception e) {
			System.out.println("cam2 failed (upward breaching");
		}

		frame = NIVision.imaqCreateImage(NIVision.ImageType.IMAGE_RGB, 0);
		server = CameraServer.getInstance();
		server.setQuality(Config.CameraFeeds.imgQuality);

		cameraThreads = new ThreadGroup("Cameras");
		// No threads contained within this group can go beyond this priority.
		cameraThreads.setMaxPriority(CAMERA_THREADS_MAX_PRIORITY);
		updateCameras = new Thread(cameraThreads, "Update Cameras") {
			@Override
			public void start() {
				Robot.feeds.updateCam();
			}
		};
		// updating the cameras is done constantly and we don't want it to get
		// in the way of other robot functions, so it is a relatively low
		// priority.
		// Condensed if else just in case we want the max priority to be lower
		// than 4.
		updateCameras
				.setPriority((cameraThreads.getMaxPriority() > 4) ? cameraThreads
						.getMaxPriority() - 4 : cameraThreads.getMaxPriority());

		CameraRotateThread = new Thread(Robot.feeds.getThreadGroup(),
				"Rotate Camera Thread") {
			@Override
			public void start() {
				Queue<Integer> cameras = Robot.feeds.getCameras();
				cameras.add(Robot.feeds.getCurCam());
				Robot.feeds.changeCam(cameras.poll());
			}
		};
		// We want this done quickly, about as soon as the drivers press the
		// button, but not so quickly that it can disrupt other operations.
		CameraRotateThread.setPriority(Robot.feeds.getThreadGroup()
				.getMaxPriority());

	}

	public void init() {
		HumanInput.registerPressedCommand(HumanInput.changeCameraButton,
				new RotateCamerasRightCommand());
		HumanInput.registerPressedCommand(HumanInput.changeCameraButton2,
				new RotateCamerasRightCommand());

		System.out.println("init");
		changeCam(curCam);
	}

	public void run() {
		updateCameras.start();
	}

	/**
	 * Stop aka close camera stream
	 */
	public void end() {
		try {
			NIVision.IMAQdxStopAcquisition(curCam);
		} catch (Exception e) {
			System.out.println("Camera end(): Stop failed");
		}
	}

	/**
	 * Change the camera to get imgs from to a different one
	 * 
	 * @param newId
	 *            for camera
	 */
	public void changeCam(int newId) {
		try {
			System.out.println(newId);
			NIVision.IMAQdxStopAcquisition(curCam);
			NIVision.IMAQdxConfigureGrab(newId);
			NIVision.IMAQdxStartAcquisition(newId);
			curCam = newId;
		} catch (Exception e) {
			System.out.println("changeCam() exception:" + e.getMessage());
		}
	}

	/**
	 * Get the img from current camera and give it to the server
	 */
	private void updateCam() {
		try {
			System.out.println();
			NIVision.IMAQdxGrab(curCam, frame, 0);

			// center line
			NIVision.imaqDrawLineOnImage(frame, frame,
					NIVision.DrawMode.DRAW_VALUE, new Point(320, 0), new Point(
							320, 480), 120);

			// skewed line
			NIVision.imaqDrawLineOnImage(frame, frame,
					NIVision.DrawMode.DRAW_VALUE, new Point(280, 0), new Point(
							280, 480), 120);

			server.setImage(frame);
		} catch (Exception e) {
			System.out.println("updateCam() exception: " + e.getMessage());
		}
	}

	public int getCurCam() {
		return curCam;
	}

	public void setCurCam(int curCam) {
		this.curCam = curCam;
	}

	public Queue<Integer> getCameras() {
		return cameras;
	}

	public ThreadGroup getThreadGroup() {
		return cameraThreads;
	}

	public Thread getCameraUpdateThread() {
		return updateCameras;
	}

	public Thread getCameraRotateThread() {
		return CameraRotateThread;
	}

}
