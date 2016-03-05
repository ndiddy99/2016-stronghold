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
		if(debug) System.out.println("[AutoShootCommand] キタ━━━(゜∀゜)━━━!!!!! ");
	}

	@Override
	protected void execute() {
		if(debug) System.out.println("[AutoShootCommand] ヽ(^o^)丿");
		System.out.println(offset);
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

	@Override
	protected void end() {
		if(debug) System.out.println("[AutoShootCommand] <コ:彡");
	}

	@Override
	protected void interrupted() {
		if(debug) System.out.println("[AutoShootCommand] (╯°□°）╯︵ ┻━┻");
	}

	@Override
	public void receivedValue(HashMap<Sensor, Double> sensorVals) {
		offset = sensorVals.get(Sensor.RPI);
	}

}
