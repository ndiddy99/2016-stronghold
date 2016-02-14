package org.usfirst.frc.team2537.robot.input;

import org.usfirst.frc.team2537.robot.Robot;

import edu.wpi.first.wpilibj.DigitalInput;

public class ProximitySensor implements SensorInterface {
	private DigitalInput input;
	
	public ProximitySensor(int port){
		input = new DigitalInput(port);
	}

	@Override
	public void getValue() {
		//Ternary Operator
		//true -> 1
		//false-> 0
		Robot.sensorSys.addValue(Sensor.SHOOTER_BALL, input.get() ? 1 : 0);
	}

}
