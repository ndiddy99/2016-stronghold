package org.usfirst.frc.team2537.robot.camera;

import java.util.LinkedList;
import java.util.Queue;

import org.usfirst.frc.team2537.robot.Ports;
import org.usfirst.frc.team2537.robot.input.HumanInput;

import com.ni.vision.NIVision;
import com.ni.vision.NIVision.Image;
import com.ni.vision.NIVision.Point;

import edu.wpi.first.wpilibj.CameraServer;

public class CameraFeeds
{
	private int camCenter;
	private int camRight;
	private int camLeft;
	private int curCam;
	private Image frame;
	private CameraServer server;
	private Queue<Integer> cameras;
	
	public CameraFeeds()
	{
		try{
        // Get camera ids by supplying camera name ex 'cam0', found on roborio web interface
        camCenter = NIVision.IMAQdxOpenCamera(Ports.DOWNWARD_BREACHING_CAMERA, NIVision.IMAQdxCameraControlMode.CameraControlModeController);
        camRight = NIVision.IMAQdxOpenCamera(Ports.UPWARD_BREACHING_CAMERA, NIVision.IMAQdxCameraControlMode.CameraControlModeController);
        camLeft = NIVision.IMAQdxOpenCamera(Ports.SHOOTING_CAMERA, NIVision.IMAQdxCameraControlMode.CameraControlModeController);
        curCam = camCenter;
        cameras = new LinkedList<Integer>();
        cameras.add(camRight);
        cameras.add(camLeft);
        // Img that will contain camera img
        frame = NIVision.imaqCreateImage(NIVision.ImageType.IMAGE_RGB, 0);
        // Server that we'll give the img to
        server = CameraServer.getInstance();
        server.setQuality(Config.CameraFeeds.imgQuality);
		}
		catch(Exception e){}
		
	}
	
	public void init()
	{
		HumanInput.registerPressedCommand(HumanInput.changeCameraButton, new RotateCamerasRightCommand());
		HumanInput.registerPressedCommand(HumanInput.changeCameraButton2, new RotateCamerasRightCommand());

		System.out.println("init");
		changeCam(curCam);
	}
	
	public void run()
	{
		updateCam();
	}
	
	/**
	 * Stop aka close camera stream
	 */
	public void end()
	{
		try{
		NIVision.IMAQdxStopAcquisition(curCam);
		}
		catch(Exception e){
			
		}
	}
	
	/**
	 * Change the camera to get imgs from to a different one
	 * @param newId for camera
	 */
	public void changeCam(int newId)
    {
		try{
		System.out.println(newId);
		NIVision.IMAQdxStopAcquisition(curCam);
    	NIVision.IMAQdxConfigureGrab(newId);
    	NIVision.IMAQdxStartAcquisition(newId);
    	curCam = newId;}
		catch(Exception e){
			
		}
    }
    
	/**
	 * Get the img from current camera and give it to the server
	 */
    public void updateCam()
    {
    	try{
    	System.out.println();
    	NIVision.IMAQdxGrab(curCam, frame, 1);
    	if(curCam == camCenter){
    		NIVision.imaqDrawLineOnImage(frame, frame, NIVision.DrawMode.DRAW_VALUE, new Point(320, 0), new Point(320, 480), 120);
    	}
        server.setImage(frame);}
    	catch(Exception e){}
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

}
