package org.usfirst.frc.team2537.robot;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.command.Subsystem;
public class Climber extends Subsystem {
	public void initDefaultCommand() {
		System.out.println("enabling climber (remove before competition)");
    }
	public static void extend() {
		CANTalon climber;
		climber=new CANTalon(5);
		climber.set(1);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		climber.set(0);
	}
	public static void retract() {
		CANTalon climber;
		climber=new CANTalon(5);
		climber.set(-1);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		climber.set(0);
	}
	
}