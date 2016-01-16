package org.usfirst.frc.team2537.robot;
import edu.wpi.first.wpilibj.Talon;
public class MotorTest {
	Talon test=new Talon(0);
	//ask karan to bring up possi
	public void dontuseinproduction() {
		int f=1;
		while (f==1) {  //seriously don't use this code
		for (double i=-1; i < 1; i=i+ 0.1) {
			test.set(i);
		}
		for (double j=1; j > -1; j=j- 0.1) {
			test.set(j);
		}
	 }
	}
}
