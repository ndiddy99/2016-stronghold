package org.usfirst.frc.team2537.robot;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.command.Subsystem;
public class Climber extends Subsystem {	//i've been informed that there's 2 motors instead of one, so i made the
	public void initDefaultCommand() {		//changes to fix this -nathan
		System.out.println("enabling climber (remove before competition)");
    }
	public static void extend() {
		CANTalon climber1;
		CANTalon climber2;
		climber1=new CANTalon(5);
		climber2=new CANTalon(6);
		climber1.set(1);
		climber2.set(1);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		climber1.set(0);
		climber2.set(0);
	}
	public static void retract() {
		CANTalon climber1;
		CANTalon climber2;
		climber1=new CANTalon(5);
		climber2=new CANTalon(6);
		climber1.set(1);
		climber2.set(1);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		climber1.set(0);
		climber2.set(0);
	}
	
}