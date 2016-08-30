package org.usfirst.frc.team2537.robot.climber;

import org.usfirst.frc.team2537.robot.Ports;
import org.usfirst.frc.team2537.robot.input.HumanInput;
import org.usfirst.frc.team2537.robot.input.XboxButtons;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class ClimberSubsystem extends Subsystem {
    
    private CANTalon climberMotor1;
    private CANTalon climberMotor2;
    private Servo servo;
    
    public ClimberSubsystem() {
    	climberMotor1 = new CANTalon(Ports.CLIMBER_MOTOR_1);
    	climberMotor2 = new CANTalon(Ports.CLIMBER_MOTOR_2);
    	servo = new Servo(Ports.CLIMBER_SERVO);
    	climberMotor2.changeControlMode(CANTalon.TalonControlMode.Follower);
    	climberMotor2.set(Ports.CLIMBER_MOTOR_1);
    }
    
    void setMotors(double value) {
    	climberMotor1.set(value);
    }

    double getLeftJoystick() {
    	return HumanInput.getXboxAxis(HumanInput.xboxController, XboxButtons.XBOX_LEFT_Y_AXIS);
    }
    
    void setServoPosition(double pos) {
    	servo.setAngle(pos);
    }
    
    double getServoPosition() {
    	return servo.getAngle();
    }
    
    public void initDefaultCommand() {
    	ManualClimberCommand MCC = new ManualClimberCommand();
    	this.setDefaultCommand(MCC);
    }
    
    public void registerButtons() {
    	HumanInput.registerPressedCommand(HumanInput.climbButton, new Climb());
    }
    
    void enable() {
    	climberMotor1.enableControl();
    	climberMotor1.changeControlMode(TalonControlMode.Position);
    	climberMotor1.reverseOutput(true);
    	climberMotor1.setPID(5, 0 ,0);
    }
}

