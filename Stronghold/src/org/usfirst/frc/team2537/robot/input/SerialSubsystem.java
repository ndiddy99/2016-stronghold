package org.usfirst.frc.team2537.robot.input;

import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.SerialPort.Port;
import edu.wpi.first.wpilibj.command.Subsystem;

public class SerialSubsystem extends Subsystem {
	
	public static SerialPort serial;

	public void initDefaultCommand() {
		serial = new SerialPort(57600, Port.kMXP);
		try {
			serial.flush();
			setDefaultCommand(new SensorCommunicationCommand());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
