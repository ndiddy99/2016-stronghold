package org.usfirst.frc.team2537.robot;

import edu.wpi.first.wpilibj.command.Subsystem;

import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.Encoder;
public class CimCoder extends Subsystem {
	// TODO: A) read comments B) Figure out how to get distance

	Encoder enc;
	public CimCoder(int portA, int portB){
		enc = new Encoder(portA, portB, true, EncodingType.k4X);	
		enc.setDistancePerPulse(20);// idk what MinRate and other Commands are for  - halp-- Karan
	}
	public void reverse(){ // run when climber has to retract to lift the robot  -- Karan
		enc.reset();
		enc.setReverseDirection(true);
	}
	public int getCount(){
		return enc.get();
	}
	@Override
	protected void initDefaultCommand() { // srry but Im a noob here - whats this for ( does it replace the constructor)? -  Karan
	
	}
}
