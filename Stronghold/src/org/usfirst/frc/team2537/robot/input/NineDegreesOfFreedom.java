package org.usfirst.frc.team2537.robot.input;

import java.util.HashMap;
import java.util.Map;

public class NineDegreesOfFreedom extends Sensor {
	
	private static NineDegreesOfFreedom INSTANCE = new NineDegreesOfFreedom();
	
	private static Map<SensorEnum, NDOF>NDOFs;
	
	private class NDOF {
		public double angle;
	}
	
	private NineDegreesOfFreedom() {
		NDOFs = new HashMap<SensorEnum, NDOF>();
		NDOFs.put(SensorEnum.ARM_NINE_DEGREES_OF_FREEDOM, new NDOF());	
		
		SerialCommunication.getInstance().register(this);
	}

	public static NineDegreesOfFreedom getInstance() {
		return INSTANCE;
	}

	public double getAngle(SensorEnum e) {
		// TODO Auto-generated method stub
		return NDOFs.get(e).angle;
	}
	
	@Override
	void handleEvents() {
		
	}
	
	public void receiveValue(SensorCommunicationEvent e) {
		NDOF nineDegreesOfFreedom = NDOFs.get(SensorEnum.ARM_NINE_DEGREES_OF_FREEDOM);
		nineDegreesOfFreedom.angle = e.getPair(Taglist.NDOF_ANGLE);
		NDOFs.put(SensorEnum.ARM_NINE_DEGREES_OF_FREEDOM, nineDegreesOfFreedom);
	}

}
