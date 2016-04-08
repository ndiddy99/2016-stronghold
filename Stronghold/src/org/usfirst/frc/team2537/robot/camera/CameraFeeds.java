package org.usfirst.frc.team2537.robot.camera;

import java.util.LinkedList;
import java.util.Queue;

import org.usfirst.frc.team2537.robot.Ports;
import org.usfirst.frc.team2537.robot.input.HumanInput;

import com.ni.vision.NIVision;
import com.ni.vision.NIVision.Image;
import com.ni.vision.NIVision.Point;

import edu.wpi.first.wpilibj.CameraServer;

public class CameraFeeds {
	private int camCenter;
	private int camRight;
	private int camLeft;
	private final Image frame;
	private final CameraServer server;
	private final Queue<Integer> cameras;
	
	private int curCam;
	
	/**
	 * Constructor. Creates the list of cameras that have been found at the time
	 * of instantiation.
	 */
	public CameraFeeds() {
        cameras = new LinkedList<Integer>();

		try {
            // Get camera ids by supplying camera name ex 'cam0', found on roborio web interface
            camCenter = NIVision.IMAQdxOpenCamera(Ports.DOWNWARD_BREACHING_CAMERA, NIVision.IMAQdxCameraControlMode.CameraControlModeController);
            curCam = camCenter;
		} catch (Exception e) {
			System.out.println("cam0 failed (downward breaching)");
		}
		
		try {
            camRight = NIVision.IMAQdxOpenCamera(Ports.UPWARD_BREACHING_CAMERA, NIVision.IMAQdxCameraControlMode.CameraControlModeController);
            cameras.add(camRight);
		} catch (Exception e) {
			System.out.println("cam1 failed (upward breaching");
		}
		
		try {
            camLeft = NIVision.IMAQdxOpenCamera(Ports.SHOOTING_CAMERA, NIVision.IMAQdxCameraControlMode.CameraControlModeController);
            cameras.add(camLeft);
		} catch (Exception e) {
			System.out.println("cam2 failed (upward breaching");
		}
        
        frame = NIVision.imaqCreateImage(NIVision.ImageType.IMAGE_RGB, 0);
        server = CameraServer.getInstance();
        server.setQuality(Config.CameraFeeds.imgQuality);		
	}
	
	/**
	 * Initializes the cameras and camera switching capabilities. Starts the
	 * camera stream.
	 */
	public void init()
	{
		HumanInput.registerPressedCommand(HumanInput.changeCameraButton, new RotateCamerasRightCommand());
		HumanInput.registerPressedCommand(HumanInput.changeCameraButton2, new RotateCamerasRightCommand());

		System.out.println("init");
		changeCam(curCam);
	}
	
	/**
	 * Updates the camera view using the current camera selected.
	 */
	public void run()
	{
		updateCam();
	}
	
	/**
	 * Stops/closes camera stream.
	 */
	public void end()
	{
		try{
		    NIVision.IMAQdxStopAcquisition(curCam);
		} catch(Exception e){
			System.out.println("Camera end(): Stop failed");
		}
	}
	
	/**
	 * Change the camera to get images from the next camera on the list.
	 *
	 * @param newId
	 *          new id number for camera
	 */
	public void changeCam(int newId)
    {
		try {
		    System.out.println(newId);
		    NIVision.IMAQdxStopAcquisition(curCam);
    	    NIVision.IMAQdxConfigureGrab(newId);
    	    NIVision.IMAQdxStartAcquisition(newId);
    	    curCam = newId;}
		catch(Exception e) {
			System.out.println("changeCam() exception:"+e.getMessage());
		}
    }
    
	/**
	 * Gets the current image from the current camera selected (or the last 
	 * available image) and updates the camera view with the image. 
	 */
    private void updateCam() {
    	try {
    	    NIVision.IMAQdxGrab(curCam, frame, 0);
    	
    	    // center line
    	    NIVision.imaqDrawLineOnImage(frame, frame, NIVision.DrawMode.DRAW_VALUE, 
    	    		new Point(320, 0), new Point(320, 480), 120);
    	
    	    // skewed line
    	    NIVision.imaqDrawLineOnImage(frame, frame, NIVision.DrawMode.DRAW_VALUE, 
    	    		new Point(280, 0), new Point(280, 480), 120);
    	
    	    server.setImage(frame);}
    	catch(Exception e) {
			System.out.println("updateCam() exception: "+e.getMessage());
    	}
    }
    
    /**
     * Returns the id number of the current selected camera.
     */
	public int getCurCam() {
		return curCam;
	}

	public void setCurCam(int curCam) {
		this.curCam = curCam;
	}

    /**
     * Returns the queue containing the cameras available on the robot.
     */
	public Queue<Integer> getCameras() {
		return cameras;
	}

}
