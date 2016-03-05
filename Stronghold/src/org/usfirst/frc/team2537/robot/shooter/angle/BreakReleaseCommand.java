//package org.usfirst.frc.team2537.robot.shooter.angle;
//
//import org.usfirst.frc.team2537.robot.Robot;
//import edu.wpi.first.wpilibj.command.Command;
//
///**
// * A command to disengage the break on the window.
// * This is done by sending a jolt of 12 volts to the motor 
// * pushing the assembly slightly upwards. Afterward another command
// * should jump in, like a MoveToAngleCommand() and then that can move without the
// * need to overcome the break.
// * 
// * This will run for 50ms and then end. The motor will be set to 100% power during
// * this period.
// *
// */
//public class BreakReleaseCommand extends Command {
//	private static final boolean DEBUG = false;
//	private static final double TIMEOUT = .001;//It takes 50ms to turn the brake off.
//	private static final double POWER = -1.0;//Full power to take brake off. REVERESED
//	private final boolean GOING_UP;
//    public BreakReleaseCommand(boolean goingUp) {
//    	super(TIMEOUT);
//        requires(Robot.shooterAngleSys);    	
//        GOING_UP = goingUp;
//    }
//
//    @Override
//    protected void initialize() {
//    	if (DEBUG) System.out.println("BreakReleaseCommand started.");
//    	if(GOING_UP) {
//    	Robot.shooterAngleSys.setVoltagePercent(POWER);
//    	} else {
//    		Robot.shooterAngleSys.setVoltagePercent(-POWER);
//    	}
//    }
//
//    @Override
//    protected void execute() {
//    	
//    }
//    
//    @Override
//    protected boolean isFinished() {
//        return isTimedOut();
//    }
//    
//    @Override
//    protected void end() {
//    	if (DEBUG) System.out.println("BreakReleaseCommand ended.");
//    	Robot.shooterAngleSys.setVoltagePercent(0);//Power Down.
//    }
//
//    @Override
//    protected void interrupted() {
//    	if (DEBUG) System.out.println("BreakReleaseCommand was interrupted.");
//    	Robot.shooterAngleSys.setVoltagePercent(0);//Power Down.
//    }
//}
