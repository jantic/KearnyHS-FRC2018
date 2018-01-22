package org.usfirst.frc.team1572.robot.commands.autonomous;

import org.usfirst.frc.team1572.robot.Robot;
import org.usfirst.frc.team1572.robot.commands.autonomous.autocommands.DriveForward;
import org.usfirst.frc.team1572.robot.commands.autonomous.autocommands.TurnToAngle;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class RightAutoPreferSwitch extends CommandGroup {

    public RightAutoPreferSwitch() {
    	if(Robot.ourSwitch == 'R') {
    		//Place box in switch
    		addSequential(new DriveForward(25, 1));
    		addSequential(new TurnToAngle(3, 60, 1));
    		addSequential(new DriveForward(2, 1));
    	}
    	else if(Robot.scale == 'R') {
    		//Place box in scale
    		addSequential(new DriveForward(18, 1));
    		addSequential(new TurnToAngle(3, 90, 1));
    		addSequential(new DriveForward(2, 1));
    	}
    	else {
    		//Drive over auto line
    		addSequential(new DriveForward(14, 1));
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
