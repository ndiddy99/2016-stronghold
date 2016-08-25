package org.usfirst.frc.team2537.robot.climber;

import org.usfirst.frc.team2537.robot.Ports;
import org.usfirst.frc.team2537.robot.input.HumanInput;
import org.usfirst.frc.team2537.robot.input.XboxButtons;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class ClimberSubsystem extends Subsystem {
    
    public CANTalon climberMotor1;
    public CANTalon climberMotor2;
    
    public ClimberSubsystem() {
    	climberMotor1 = new CANTalon(Ports.CLIMBER_MOTOR_1);
    	climberMotor2 = new CANTalon(Ports.CLIMBER_MOTOR_2);
    	climberMotor2.changeControlMode(CANTalon.TalonControlMode.Follower);
    	climberMotor2.set(Ports.CLIMBER_MOTOR_1);
    }
    
    public void setMotors(double value) {
    	climberMotor1.set(value);
    }

    public double getLeftJoystick() {
    	return HumanInput.getXboxAxis(HumanInput.xboxController, XboxButtons.XBOX_LEFT_Y_AXIS);
    }
    
    public void initDefaultCommand() {
    	ManualClimberCommand MCC = new ManualClimberCommand();
    	this.setDefaultCommand(MCC);
    }
}

