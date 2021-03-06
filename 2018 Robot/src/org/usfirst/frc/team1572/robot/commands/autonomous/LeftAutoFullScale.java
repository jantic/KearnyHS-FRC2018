package org.usfirst.frc.team1572.robot.commands.autonomous;

import org.usfirst.frc.team1572.robot.Robot;
import org.usfirst.frc.team1572.robot.commands.autonomous.autocommands.DriveForwardTimed;
import org.usfirst.frc.team1572.robot.commands.autonomous.autocommands.TurnToAngle;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class LeftAutoFullScale extends CommandGroup {
	
    public LeftAutoFullScale() {
    	if(Robot.scale == 'L') {
    		//place box in scale left plate
    		addSequential(new DriveForwardTimed(25, 0.5, 5));
    		addSequential(new TurnToAngle(3, 60, 1));
    		addSequential(new DriveForwardTimed(2, 0.5, 5));
    		
    	}
    	else {
    		//go around switch and place box in scale right plate
    		addSequential(new DriveForwardTimed(18, 0.5, 5));
    		addSequential(new TurnToAngle(3, 90, 1));
    		addSequential(new DriveForwardTimed(15, 0.5, 5));
    		addSequential(new TurnToAngle(3, -90, 1));
    		addSequential(new DriveForwardTimed(6, 0.5, 5));
    	}
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    }
}
