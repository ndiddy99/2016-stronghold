package org.usfirst.frc.team2537.robot.input;

import org.usfirst.frc.team2537.robot.Robot;
import edu.wpi.first.wpilibj.DigitalInput;

public class ProximitySensor implements SensorInterface {
	private static final boolean DEBUG = true;
	
	public DigitalInput input;
	private Sensor sensor;
	
	public ProximitySensor(int port, Sensor sensor){
		input = new DigitalInput(port);
		this.sensor = sensor;
	}

	@Override
	public void getValue() {
		double value = (input.get()) ? 
				0://true -> 0 
				1;//false-> 1
			
		
		if (DEBUG) {
			System.out.print("Ball is ");
			if (value==1) { 
				System.out.print("present");
			} else {
				System.out.print("absent");
			}
			System.out.println(" (val = " + value + ", raw = " + input.get() + ")");
		}
		
		Robot.sensorSys.addValue(sensor, value);
	}
	
	
}
