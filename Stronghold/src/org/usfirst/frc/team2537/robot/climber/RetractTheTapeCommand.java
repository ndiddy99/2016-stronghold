//command that retracts the tape
package org.usfirst.frc.team2537.robot.climber;

import org.usfirst.frc.team2537.robot.Robot;
import org.usfirst.frc.team2537.robot.input.HumanInput;
import edu.wpi.first.wpilibj.command.Command;

public class RetractTheTapeCommand extends Command {
	private boolean debug = false;
	int distance;
	int volt;

	public RetractTheTapeCommand(int Distance, int volts) {
		distance = Distance;
		requires(Robot.climberSys);
		volt = volts;
	}

	@Override
	protected void end() {
		// TODO Auto-generated method stub
		if (distance == 0) {
			Robot.climberSys.resetEncPos();
		}
		//Robot.climberSys.stop();
	}

	@Override /** lowers the climber when extending the climber */
	protected void execute() {

		if (Robot.climberSys.getTalonCurrent() < 10) {
			// new TurnTheTapeCommand(100,
			// Climber.RAMP_RATE_RAMP_RATE_SERVO).start();
		}
		// TODO Auto-generated method stub

		if (distance == 200) {
			System.out.println("retracting" + Robot.climberSys.getEncPos());
		} else {
			System.out.println("fake retracting" + Robot.climberSys.getEncPos());
		}
		// ExtendTheTapeCommand.turnoffgoddammit.set(0);

	}

	@Override
	protected void initialize() {
		// TODO Auto-generated method stub
		Robot.climberSys.enable();
		Robot.climberSys.setTalonVoltage(volt);
		Robot.climberSys.resetEncPos();
		Robot.climberSys.setPID(5, 0, 0);
		Robot.climberSys.move(distance);
	}

	@Override
	protected void interrupted() {
		// TODO Auto-generated method stub
		end();
	}

	@Override /**
				 * stawps lowering the climber when the limit swtich is pressed
				 */
	protected boolean isFinished() {
		// TODO Auto-generated method stu'
		System.out.println(Robot.climberSys.isLimitPressed());

		return Robot.climberSys.isLimitPressed() || Robot.climberSys.getEncPos() <= (distance * 80) + 1000;

	}
}
