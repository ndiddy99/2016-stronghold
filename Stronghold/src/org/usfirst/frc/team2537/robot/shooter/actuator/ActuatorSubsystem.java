package org.usfirst.frc.team2537.robot.shooter.actuator;

import org.usfirst.frc.team2537.robot.input.Ports;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
/*
 * TODO this is just a stub.
 * It may be a good idea to make this a PIDSubsystem.
 */
public class ActuatorSubsystem extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	private static Solenoid ballPistonPusher;

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	ballPistonPusher = new Solenoid(Ports.SOLENOID_PORT);
    }
    
    public void registerButtons(){
    	//Set the needed buttons... if any.
    }
    
    public void actuateSolenoid() {
		ballPistonPusher.set(true);
	}
	public void retractSolenoid() {
		ballPistonPusher.set(false);
	}
	
	public void setSolenoid(boolean extended){
		ballPistonPusher.set(extended);
	}
}

