package org.usfirst.frc.team1572.robot.commands.autonomous;

import org.usfirst.frc.team1572.robot.Robot;
import org.usfirst.frc.team1572.robot.commands.autonomous.autocommands.Delay;
import org.usfirst.frc.team1572.robot.commands.autonomous.autocommands.DriveForwardTimed;
import org.usfirst.frc.team1572.robot.commands.autonomous.autocommands.TurnToAngle;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class LeftAutoSwitch extends CommandGroup {

    public LeftAutoSwitch() {
    	if(Robot.ourSwitch == 'R'){ //change to r later
    		//drive past auto line
    		addSequential(new DriveForwardTimed(14, 0.7, 5));
    	}
    	else {
    		//place box in switch
    		addSequential(new DriveForwardTimed(14-2.3, 0.7, 4));
    		addSequential(new Delay(0.25));
    		addSequential(new TurnToAngle(1, 90, 0.75));
    		//addSequential(new Delay(0.25));
    		addSequential(new DriveForwardTimed(0.5, 0.5, 4));
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
