package org.usfirst.frc.team2537.robot;
//apparently i can't write raw data to the talons
import edu.wpi.first.wpilibj.Talon;
public class TalonCommand {
	Talon test = new Talon(0);
	public void talonSet(double value) {
		if (value >= -1 || value <= 1) {
			test.set(value);
		}
	}

}
