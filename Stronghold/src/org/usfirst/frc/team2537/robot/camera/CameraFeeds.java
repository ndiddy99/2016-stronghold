package org.usfirst.frc.team2537.robot.camera;

import com.ni.vision.NIVision;
import com.ni.vision.NIVision.Image;
import com.ni.vision.NIVision.Point;

import edu.wpi.first.wpilibj.CameraServer;

public class CameraFeeds
{
	private final int camCenter;
	private final int camRight;
	private final int camLeft;
	private int curCam;
	private Image frame;
	private CameraServer server;
	private Controller contr;
	
	public CameraFeeds(Controller newContr)
	{
        // Get camera ids by supplying camera name ex 'cam0', found on roborio web interface
        camCenter = NIVision.IMAQdxOpenCamera(Config.CameraFeeds.camNameCenter, NIVision.IMAQdxCameraControlMode.CameraControlModeController);
        camRight = NIVision.IMAQdxOpenCamera(Config.CameraFeeds.camNameRight, NIVision.IMAQdxCameraControlMode.CameraControlModeController);
        camLeft = NIVision.IMAQdxOpenCamera(Config.CameraFeeds.camNameLeft, NIVision.IMAQdxCameraControlMode.CameraControlModeController);
        curCam = camCenter;
        // Img that will contain camera img
        frame = NIVision.imaqCreateImage(NIVision.ImageType.IMAGE_RGB, 0);
        // Server that we'll give the img to
        server = CameraServer.getInstance();
        server.setQuality(Config.CameraFeeds.imgQuality);
        contr = newContr;
	}
	
	public void init()
	{
		changeCam(camCenter);
	}
	
	public void run()
	{
		if(contr.getButton(Config.CameraFeeds.btCamCenter))
			changeCam(camCenter);
		
		if(contr.getButton(Config.CameraFeeds.btCamRight))
			changeCam(camRight);
		
		if(contr.getButton(Config.CameraFeeds.btCamLeft))
			changeCam(camLeft);
		
		updateCam();
	}
	
	/**
	 * Stop aka close camera stream
	 */
	public void end()
	{
		NIVision.IMAQdxStopAcquisition(curCam);
	}
	
	/**
	 * Change the camera to get imgs from to a different one
	 * @param newId for camera
	 */
	public void changeCam(int newId)
    {
		NIVision.IMAQdxStopAcquisition(curCam);
    	NIVision.IMAQdxConfigureGrab(newId);
    	NIVision.IMAQdxStartAcquisition(newId);
    	curCam = newId;
    }
    
	/**
	 * Get the img from current camera and give it to the server
	 */
    public void updateCam()
    {
    	NIVision.IMAQdxGrab(curCam, frame, 1);
    	if(curCam == camCenter){
    		NIVision.imaqDrawLineOnImage(frame, frame, NIVision.DrawMode.DRAW_VALUE, new Point(320, 0), new Point(320, 480), 120);
    	}
        server.setImage(frame);
    }
}