package org.usfirst.frc.team2537.robot;



public class AutoCommandGroup {
	public class AutoCommandGroup extends CommandGroup {
	    
	    public  AutoCommandGroup() {
	        addSequential(new AutoDrive(5));
	        // auto drive, auto vision thingy, auto turning, auto shooting
}
	
