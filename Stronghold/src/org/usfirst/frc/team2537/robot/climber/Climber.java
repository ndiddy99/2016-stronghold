//The following code is licensed under the Microsoft Windows End User License Agreement.
//By using this code, you agree to either pay Nathan Misner (and Karan) $50 or else
//they'll break your legs.
package org.usfirst.frc.team2537.robot.climber;

import org.usfirst.frc.team2537.robot.input.HumanInput;
import org.usfirst.frc.team2537.robot.Ports;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.FeedbackDevice;
import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Climber extends Subsystem {
	private DigitalInput lineReader;
	private CANTalon climberForward;
	private CANTalon climberBackward;
	private Servo servo;
//	private Relay electromagnet;
	private DigitalInput retractButton;
	public static final double DISTANCE_TO_EXTEND = 360;// old value was 360
														// amounts of counts to
														// extend
	public static final double ANGLE_FOR_SERVO = 50;
	public static final double RAMP_RATE_RAMP_RATE_SERVO=.001;
	public static final double DISTANCE_TO_MOVE_BACK = -200;	//old value was -200
	public static final double CHANGE_SPEED_POINT = 30;
	public static final int PORT_FOR_LINE_READER = 6;
	public static final int DISTANCE_TO_BAR = 10;
	final static boolean debug = true;
	public int lineReaderCount = 0;
	double P;
	double I;
	double D;
	boolean lineReaderArchived;

	public Climber() {
		if (debug)
			System.out.println("enabling climber (remove before competition)");

		// TODO - Organize (haha like that's going to happen)
		climberForward = new CANTalon(Ports.CLIMBER_MOTOR_1);
		climberBackward = new CANTalon(Ports.CLIMBER_MOTOR_2);
		climberForward.enableForwardSoftLimit(false);
		climberBackward.enableReverseSoftLimit(false);
		climberForward.enableForwardSoftLimit(false);
		climberBackward.enableReverseSoftLimit(false);
		climberForward.changeControlMode(CANTalon.TalonControlMode.Position);
		climberForward.reverseOutput(true);
		//climberForward.configMaxOutputVoltage(4);
		//climberBackward.configMaxOutputVoltage(4);
		// TODO: do this
		// climberForward.changeControlMode(CANTalon.TalonControlMode.PercentVbus);
		// climberBackward.changeControlMode(CANTalon.TalonControlMode.PercentVbus);
		climberBackward.changeControlMode(CANTalon.TalonControlMode.Follower);
		climberBackward.set(1);
		//climberBackward.reverseOutput(true);
		servo = new Servo(Ports.CLIMBER_SERVO);

		climberForward.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		// climberB.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		climberForward.configEncoderCodesPerRev(20);
		// climberB.configEncoderCodesPerRev(20);
		climberForward.reverseSensor(false);
		climberBackward.reverseSensor(false);
//		electromagnet = new Relay(Ports.ELECTROMAGNET_RELAY_PORT);
//		electromagnet.set(Relay.Value.kOff);
		climberForward.configMaxOutputVoltage(9);
		climberBackward.configMaxOutputVoltage(9);
	}

	public void initDefaultCommand() {
	}
	public boolean isRetractButtonHeld() {
		return retractButton.get();
	}

	public double getAngle() {
		return servo.getAngle();
	}

	/**
	 * @return returns the value of limit switch
	 */
	public void enable() {
		climberForward.enableControl();
		climberForward.changeControlMode(TalonControlMode.Position);
		climberForward.reverseOutput(true);
	}

	public void positionMode() {
		climberForward.changeControlMode(TalonControlMode.Position);
		climberForward.setPID(P, I, D);
	}

	public void setPID(double P, int I, int D) {
		climberForward.setPID(P, I, D);
	}

	public boolean isLimitPressed() {
		if (debug && climberForward.isFwdLimitSwitchClosed()) {
			System.out.println("limit switch pressed");
		}
		return climberForward.isFwdLimitSwitchClosed();
	}
	public void setTalonVoltage(double voltage) {
		climberForward.configMaxOutputVoltage(voltage);
		climberBackward.configMaxOutputVoltage(voltage);
	}
	/**
	 * sets the values of the two motors to one, cAusing the two cims to move
	 * forwArd
	 * 
	 * @param num
	 *            - a double which the climberForward talon is set
	 *            to(ClimberForward is in position mode)
	 */

	public void move(double position) {
		climberForward.set(position);
		// climberB.set(-(num));
	}

	public void moveFFast() {
		climberForward.set(.8);
		climberBackward.set(-.8);
	}

	public void moveBFast() {
		climberForward.set(-.8);
		climberBackward.set(.8);
	}

	public void moveF() {
		climberForward.set(.4);
		climberBackward.set(-.4);
		// climberB.set(-(num));
	}

	public void moveFS() {
		climberForward.set(.2);
		climberBackward.set(-.2);
		// climberB.set(-(num));
	}

	public void moveB() {
		climberForward.set(-.4);
		climberBackward.set(.4);
		// climberForward.set(position);
		// climberB.set(-(num));
	}

	public void moveBS() {
		climberForward.set(-.2);
		climberBackward.set(.2);
		// climberB.set(-(num));
	}

	public void positionStop() {
		climberForward.set(0);
		climberBackward.set(0);
	}
	public void stop() {
		climberForward.disableControl();
	}
	public double getTalonCurrent() {
		return climberForward.getOutputCurrent();
	}
	/** Sets the angle of the servo to turn the climber */
	public void setAngle(double angle) {
		servo.setAngle(angle);
	}

	public void printAngle() {

		System.out.println(servo.getAngle());
	}

	/** @return the speed of the climber cim motors */
	public double getEncSpeed() {
		return climberForward.getEncVelocity();
	}

	/** @return the Counts of the Encoder */
	public int getEncPos() {
		System.out.println(climberForward.getEncPosition());
		return climberForward.getEncPosition();
	}

	public int getLineRead() {
		if (lineReader.get() != lineReaderArchived) {
			lineReaderCount++;
			lineReaderArchived = lineReader.get();
			if (debug)
				System.out.println("line reader: " + lineReaderCount);
		}
		return lineReaderCount;
	}

	/*
	 * public double getEncDist() { if (getEncVal() > 20) {
	 * encoderRetDiameterArchived += 0.00083; } double circumference = ((2 *
	 * 3.14 * (encoderRetDiameterArchived / 2)) / 20); return circumference; }
	 */
	public void resetEncPos() {
		climberForward.setEncPosition(0);
	}

	public void registerButtons() {
		// HumanInput.registerPressedCommand(HumanInput.climbButton, new
		// CommandTheClimber());
		//
		//TODO: add voltage parameter to extend the tape command
		HumanInput.registerPressedCommand(HumanInput.climberButton, new CommandTheClimber());
	}

//	public void electromagnetOff() {
//		electromagnet.set(Relay.Value.kForward);
//	}
//
//	public void electromagnetOn() {
//		electromagnet.set(Relay.Value.kReverse);
//	}
	public void PercentageMode() {
		climberForward.changeControlMode(CANTalon.TalonControlMode.PercentVbus);
		climberBackward.changeControlMode(CANTalon.TalonControlMode.PercentVbus);
	}

	// diameter with tape: 2 inches
	// diameter without: about 1.5 inches
	
	// tape thickness: 10/1000s of an inch (10 mils)
	// each inch of tape contributes 0.00083333... inches to the thickness of
	// the tape

	/*
	 * public double extendDist() { ======= /* public double extendDist() {
	 * >>>>>>> branch 'climbingtest' of
	 * http://71.121.167.52:9001/alex/2016-stronghold.git double diameter =
	 * encoderExtDiameterArchived; double distancePerPulse = ((2 * 3.14 *
	 * (diameter / 2)) / 20);
	 * 
	 * if ((climberF.getEncVelocity() / (System.currentTimeMillis() -
	 * ArchivedTime) - encoderExtendArchived) > 1) { diameter -= 0.00083;
	 * encoderExtendArchived = climberF.getEncVelocity() /
	 * (System.currentTimeMillis() - ArchivedTime); encoderExtDiameterArchived =
	 * diameter; } return climberF.getEncVelocity() /
	 * (System.currentTimeMillis() - ArchivedTime); }
	 * 
	 * public double retractDist() { double diameter =
	 * encoderRetDiameterArchived; double distancePerPulse = ((2 * 3.14 *
	 * (diameter / 2)) / 20); if ((climberF.getEncVelocity() /
	 * (System.currentTimeMillis() - ArchivedTime) - encoderRetractArchived) >
	 * 1) { diameter += 0.00083; encoderRetractArchived =
	 * climberF.getEncVelocity() / (System.currentTimeMillis() - ArchivedTime);
	 * encoderRetDiameterArchived = diameter; } return climberF.getEncVelocity()
	 * / (System.currentTimeMillis() - ArchivedTime); }
	 * 
	 * public void retractResetTape() { if (tapeswitcho.get()) {
	 * cimcoder.reset(); encoderRetractArchived=0;
	 * encoderRetDiameterArchived=1.5; } } public void resetEncoder() {
	 * timero.reset(); timero.start(); moveUp(); try { timero.wait(100); } catch
	 * (InterruptedException e) { // TODO Auto-generated catch block
	 * e.printStackTrace(); } moveDown(); try { timero.wait(105); } catch
	 * (InterruptedException e) { // TODO Auto-generated catch block
	 * e.printStackTrace(); } retractResetTape(); }
	 * 
	 * 
	 * public static void extend() { // Replace 15 with the limit for the tape
	 * measure can extend while (climberF.getEncPosition() < 15) { // with the
	 * 
	 * climberF.set(1); climberB.set(-1); try { Thread.sleep(1000); } catch
	 * (InterruptedException e) { // TODO Auto-generated catch block
	 * e.printStackTrace(); } climberF.set(0); climberB.set(0); } }
	 * 
	 * public static void retract() { while (climberB.getEncPosition() < 15) {
	 * // replace 15
	 * 
	 * climberF.set(1); climberB.set(-1); try { Thread.sleep(1000); } catch
	 * (InterruptedException e) { // TODO Auto-generated catch block
	 * e.printStackTrace(); } climberF.set(0); climberB.set(0);
	 * 
	 * try { Thread.sleep(1000); } catch (InterruptedException e) { // TODO
	 * Auto-generated catch block e.printStackTrace(); } } }
	 * 
	 * public boolean isMechanismExtended() { switcho = new DigitalInput(1); if
	 * (switcho.get()) { return true; } else { return false; } }
	 * 
	 * public void extendServo() { Swervo.setServo(1.0, 0); try {
	 * Thread.sleep(100); } catch (InterruptedException e) { // TODO
	 * Auto-generated catch block e.printStackTrace(); } Swervo.setServo(0, 0);
	 * }
	 * 
	 * public void retractServo() {
	 * 
	 * Swervo.setServo(1.0, 180); // i think this should make it go backwards //
	 * probably
	 * 
	 * Swervo.setServo(-1.0, 0); //i think this should make it go backwards
	 * probably
	 * 
	 * try { Thread.sleep(100); } catch (InterruptedException e) { // TODO
	 * Auto-generated catch block e.printStackTrace(); } Swervo.setServo(0, 0);
	 * }
	 * 
	 * public boolean istapeRetracted() { tapeswitcho=new DigitalInput(2); if
	 * (tapeswitcho.get()) { return true; } else { return false; } } public
	 * static double getEncValue(CANTalon t){ return t.getEncPosition(); }
	 * 
	 * public void climb() { this.extendServo(); while (getEncValue(climberF) >
	 * 100) { extend(); } while (istapeRetracted()==false) { retract(); }
	 * 
	 * }
	 */

}