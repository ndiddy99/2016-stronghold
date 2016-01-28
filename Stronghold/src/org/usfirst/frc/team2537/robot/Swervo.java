package org.usfirst.frc.team2537.robot;
import edu.wpi.first.wpilibj.Servo;
public class Swervo { //i couldnt name this class servo without getting an error so you get "swervo" instead
	public static void setServo(double num, double angle) {
		Servo tomServo = new Servo(2);	//get it? it is a mst3k reference
		tomServo.set(num);
		tomServo.setAngle(angle);
	}
}
