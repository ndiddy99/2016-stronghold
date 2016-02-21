package org.usfirst.frc.team2537.robot.input;

import org.usfirst.frc.team2537.robot.Robot;

import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.DigitalOutput;

public class LidarSensor implements SensorInterface {	
	private Counter input;
	private DigitalOutput trigger;
	private Sensor sensor;
	
	public LidarSensor(int triggerPort, int inputPort, Sensor sensor){
		this.sensor = sensor;
		input = new Counter(inputPort);
		input.setSemiPeriodMode(true);
		trigger = new DigitalOutput(triggerPort);
		trigger.set(false);
	}
	
	public void getValue() {
		trigger.set(false);
		double value = input.getPeriod() * 100000;
		Robot.sensorSys.addValue(sensor, value);
	}
}