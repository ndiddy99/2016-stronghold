package org.usfirst.frc.team2537.robot.input;

import org.usfirst.frc.team2537.robot.Robot;

import edu.wpi.first.wpilibj.Counter;

public class PiComms implements SensorInterface {
	
	Counter input;
	
	public PiComms(int inputPort) {
		Counter input = new Counter(inputPort);
		input.setSemiPeriodMode(true);
	}	
	
	@Override
	public void getValue() {
		Robot.sensorSys.addValue(Sensor.PI, input.getPeriod());
	}
}
