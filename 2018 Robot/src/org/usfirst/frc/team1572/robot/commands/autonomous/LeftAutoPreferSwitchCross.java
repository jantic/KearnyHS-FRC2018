package org.usfirst.frc.team1572.robot.commands.autonomous;

import org.usfirst.frc.team1572.robot.Robot;
import org.usfirst.frc.team1572.robot.commands.autonomous.autocommands.Delay;
import org.usfirst.frc.team1572.robot.commands.autonomous.autocommands.DriveForwardTimed;
import org.usfirst.frc.team1572.robot.commands.autonomous.autocommands.TurnToAngle;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class LeftAutoPreferSwitchCross extends CommandGroup {

    public LeftAutoPreferSwitchCross() {
    	if(Robot.ourSwitch == 'L') {
    		//Place box in switch
    		//addSequential(new DriveForwardTimed(14, 0.5, 5));
    		//addSequential(new TurnToAngle(3, 90, 1));
    		//addSequential(new DriveForwardTimed(2, 0.5, 5));
    		
    		//place box in switch left plate
    		addSequential(new DriveForwardTimed(14-2.3, 0.7, 4));
    		addSequential(new Delay(0.25));
    		addSequential(new TurnToAngle(1, 90, 0.75));
    		//addSequential(new Delay(0.25));
    		addSequential(new DriveForwardTimed(0.5, 0.5, 4));
    	}
    	else if(Robot.scale == 'L') {
    		//Place box in scale
    		addSequential(new DriveForwardTimed(25, 0.5, 5));
    		addSequential(new TurnToAngle(3, 60, 1));
    		addSequential(new DriveForwardTimed(2, 0.5, 5));
    	}
    	else {
    		//Place box in switch right
    		addSequential(new DriveForwardTimed(18, 0.5, 5));//add 1 to 2 feet to account for boxes
    		addSequential(new Delay(0.25));
    		addSequential(new TurnToAngle(3, 90, 0.75));
    		addSequential(new DriveForwardTimed(14, 0.5, 5));
    		addSequential(new Delay(0.25));
    		addSequential(new TurnToAngle(3, 90, 0.75));
    		//addSequential(new DriveForwardTimed(2, 0.5, 5));
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
