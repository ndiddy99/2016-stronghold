package org.usfirst.frc.team2537.robot.shooter.flywheel;

import org.usfirst.frc.team2537.robot.input.HumanInput;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

import org.usfirst.frc.team2537.robot.input.Ports;
import org.usfirst.frc.team2537.robot.shooter.HarvestCommand;
import org.usfirst.frc.team2537.robot.shooter.ShootCommand;

public class ShooterSubsystem extends Subsystem {	
	//Motors
	private static final CANTalon leftFlywheelMotor = new CANTalon(Ports.TALON_LEFT_FLYWHEEL_PORT);
	private static final CANTalon rightFlywheelMotor = new CANTalon(Ports.TALON_RIGHT_FLYWHEEL_PORT);
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	private static final boolean CHECK_TEMP = true;
	private static final double MAX_TEMP = 100;
	private static final Solenoid ballPistonPusher = new Solenoid(Ports.SOLENOID_PORT);;
	
	public ShooterSubsystem() {
		//Starting motors.
		//Make sure the the mode to speed so we can modify it.
		//Everything will be in "position change / 10ms"
		leftFlywheelMotor.changeControlMode(CANTalon.TalonControlMode.Voltage);//Values will be 0-1
		rightFlywheelMotor.changeControlMode(CANTalon.TalonControlMode.Voltage);
	}
	
	@Override
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	//setDefaultCommand(new ShotBallCommand());
    	//There is not a defualt command.
    }
	
	public void registerButtons() {
		HumanInput.registerPressedCommand(HumanInput.ballShootTrigger, new ShootCommand());
		HumanInput.registerPressedCommand(HumanInput.harvestBallTrigger, new ShootCommand());
		
	}
	//Shooter Left Flywheel controls.
	public static double getLeftFlywheelSpeed() {
		return leftFlywheelMotor.getEncVelocity();
	}
	
	/**
	 * Set the speed of the left flywheel.
	 * 
	 * @param speed The voltage amount the wheel will be set to. 
	 * 				This should be [-1, 1].
	 */
	public static void setLeftFlywheelSpeed(double speed) {
		leftFlywheelMotor.set(speed);
	}
	
	//Shooter Right Flywheel controls.
	public static double getRightFlywheelSpeed() {
		return rightFlywheelMotor.getEncVelocity();
	}
	
	/**
	 * Set the speed of the right flywheel.
	 * 
	 * @param speed The voltage amount the wheel will be set to. 
	 * 				This should be [-1, 1].
	 */
	public static void setRightFlywheelSpeed(double speed) {
		rightFlywheelMotor.set(speed);
	}
	
	//Let the commands have assess to temperature readings.
	public boolean isTemperatureFault(){
		//Check to make sure I'm not on fire!!
		return CHECK_TEMP && (this.leftFlywheelMotor.getTemperature() >= MAX_TEMP ||this.rightFlywheelMotor.getTemperature() >= MAX_TEMP);
	}
}