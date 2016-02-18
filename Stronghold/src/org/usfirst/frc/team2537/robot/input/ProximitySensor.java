package org.usfirst.frc.team2537.robot.input;

import org.usfirst.frc.team2537.robot.Robot;
import edu.wpi.first.wpilibj.DigitalInput;

public class ProximitySensor implements SensorInterface {
	private static final boolean DEBUG = false;
	
	private DigitalInput input;
	
	public ProximitySensor(int port){
		input = new DigitalInput(port);
	}

	@Override
	public void getValue() {
		double value = (input.get()) ? 
				1://true -> 1 
				0;//false-> 0
		
		if (DEBUG) {
			System.out.println("Ball is ");
			if (value==1) { 
				System.out.print("present");
			} else {
				System.out.print("absent");
			}
			System.out.println(" (val = " + value + ", raw = " + input.get() + ")");
		}
		
		Robot.sensorSys.addValue(Sensor.SHOOTER_BALL, value);
	}

}
