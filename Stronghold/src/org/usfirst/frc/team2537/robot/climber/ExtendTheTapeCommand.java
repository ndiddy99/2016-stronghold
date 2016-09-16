//command that extends the tape
package org.usfirst.frc.team2537.robot.climber;

import org.usfirst.frc.team2537.robot.Robot;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.command.Command;

/**
 * spoiler - Extends the tape for a certain amount of counts to be determined
 */
public class ExtendTheTapeCommand extends Command {
	// static CANTalon turnoffgoddammit = new
	// CANTalonyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyy
	double tolerance = 20;
	int distance;
	boolean debug = true;

	public ExtendTheTapeCommand(double distanceToExtend) {
		this.distance = (int) distanceToExtend;
		requires(Robot.climberSys);
	}

	@Override
	protected void end() {
		// TODO Auto-generated method stub
		//Robot.climberSys.stop();
	}

	/** when executing the climber moves up */
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		// System.out.println(Robot.climberSys.isLimitPressed());
		// System.out.println(Robot.climberSys.getEncPos() );
		System.out.println("extending");
		// turnoffgoddammit.set(0);

	}

	/** setting an angle to the servo befroe the climber extends */
	@Override
	protected void initialize() {
		if (debug) System.out.println("ExtendTheTape Command init");
		Robot.climberSys.enable();
		Robot.climberSys.setTalonVoltage(11);
		Robot.climberSys.resetEncPos();
		Robot.climberSys.setPID(5, 0, 0);
		Robot.climberSys.move(distance);

	}

	@Override
	protected void interrupted() {
		// TODO Auto-generated method stub
		end();
	}

	@Override
	/** stops extending the climber when it has reached DISTANCE_TO EXTEND */
	protected boolean isFinished() {
		System.out.println(Robot.climberSys.getEncPos());
		return (Robot.climberSys.getEncPos() + tolerance >= distance * 80
				&& Robot.climberSys.getEncPos() - tolerance <= distance * 80);

	}

}
