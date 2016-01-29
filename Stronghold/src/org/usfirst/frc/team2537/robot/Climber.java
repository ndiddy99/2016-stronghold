package org.usfirst.frc.team2537.robot;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.DigitalInput;
public class Climber extends Subsystem {
	DigitalInput switcho; //it wouldnt let me name the switch switch
	DigitalInput tapeswitcho;				//i've been informed that there's 2 motors instead of one, so i made the
	public void initDefaultCommand() {		//changes to fix this -nathan
		System.out.println("enabling climber (remove before competition)");
    }
	public static void extend() {
		CANTalon climber1;
		CANTalon climber2;
		climber1=new CANTalon(5);
		climber2=new CANTalon(6);
		climber1.set(1);
		climber2.set(-1);
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
		climber2.set(-1);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		climber1.set(0);
		climber2.set(0);
	}
	public boolean isMechanismExtended() {
		switcho=new DigitalInput(1);
		if (switcho.get()) {
			return true;
		}
		else {
			return false;
		}
	}
	public void extendServo() {
		Swervo.setServo(1.0, 0);
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Swervo.setServo(0, 0);
	}
	public void retractServo() {
		Swervo.setServo(1.0, 180); //i think this should make it go backwards probably
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Swervo.setServo(0, 0);
	}
	public boolean istapeExtended() {
		tapeswitcho=new DigitalInput(1);
		if (tapeswitcho.get()) {
			return true;
		}
		else {
			return false;
		}
	}
	
}