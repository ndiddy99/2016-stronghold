package org.usfirst.frc.team2537.robot.input;

import org.usfirst.frc.team2537.robot.Robot;

import edu.wpi.first.wpilibj.Counter;

public class PiComms implements SensorInterface {
	
	Counter highCounter;
	Counter lowCounter;
	
	public PiComms(int inputPort) {
		highCounter = new Counter(inputPort);
		highCounter.setSemiPeriodMode(true);
		lowCounter = new Counter(inputPort);
		lowCounter.setSemiPeriodMode(false);
	}	
	
	@Override
	public void getValue() {
		double val;
		val = (highCounter.getPeriod() + lowCounter.getPeriod());
		val = highCounter.getPeriod()/val;
		Robot.sensorSys.addValue(Sensor.RPI, val);
	}
}
