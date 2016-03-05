package org.usfirst.frc.team2537.robot.auto;

import java.util.HashMap;

import org.usfirst.frc.team2537.robot.input.Sensor;
import org.usfirst.frc.team2537.robot.input.SensorListener;

import edu.wpi.first.wpilibj.command.Command;

public class AutoShootCommand extends Command implements SensorListener{
	private static final boolean debug = false;
	private Double offset;
	
	/**
	 * Autonomous shot
	 */
	public AutoShootCommand() {

	}

	@Override
	protected void initialize() {
	}

	@Override
	protected void execute() {
		if (debug) System.out.println("[AutoShootCommand] PWM: " + offset);
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

	@Override
	protected void end() {
	}

	@Override
	protected void interrupted() {
	}

	@Override
	public void receivedValue(HashMap<Sensor, Double> sensorVals) {
		offset = sensorVals.get(Sensor.RPI);
	}

}
