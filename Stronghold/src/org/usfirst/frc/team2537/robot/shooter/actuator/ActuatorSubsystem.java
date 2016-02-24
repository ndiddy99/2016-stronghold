package org.usfirst.frc.team2537.robot.shooter.actuator;

import org.usfirst.frc.team2537.robot.Ports;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class ActuatorSubsystem extends Subsystem {
	//Const
	private static float extendedAngle = 45;
	private static float retractedAngle = 0;
	//Vars
	private Servo actuator;
	
	public ActuatorSubsystem() {
		actuator = new Servo(Ports.SERVO_PORT);
	}
	
	/**
	 * Find out if the arm is extended.
	 * @return If the arm is in the extended position.
	 */
	public boolean isExtended(){
		return actuator.getAngle() == extendedAngle;
	}
	
	/**
	 * Find out if  the kicker is retracted.
	 * Note: If isExtended and isRetracted return false, the actuator is moving.
	 * @return
	 */
	public boolean isRetracted(){
		return actuator.getAngle() == retractedAngle;
	}
	
	public void setPosition(boolean extended){
		//Set the position
		actuator.setAngle((extended) ? extendedAngle : retractedAngle);
	}
	
	public double getAngle() {
		//Get angle.
		return actuator.getAngle();
	}
	
	@Override
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	//No defualt command.
    }
	
	public void registerButtons() {
		//Needed but not used.
		//For testing, remove for competition.
		//HumanInput.registerPressedCommand(HumanInput.raiseArm, new ActuatorCommand(true));
		//HumanInput.registerPressedCommand(HumanInput.neutralArm, new ActuatorCommand(false));
		
	}
}

