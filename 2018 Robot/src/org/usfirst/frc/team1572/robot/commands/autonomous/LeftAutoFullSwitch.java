package org.usfirst.frc.team1572.robot.commands.autonomous;

import org.usfirst.frc.team1572.robot.Robot;
import org.usfirst.frc.team1572.robot.commands.autonomous.autocommands.Delay;
import org.usfirst.frc.team1572.robot.commands.autonomous.autocommands.DriveForwardTimed;
import org.usfirst.frc.team1572.robot.commands.autonomous.autocommands.TurnToAngle;
import org.usfirst.frc.team1572.robot.commands.main.ForkliftSwitchHeight;
import org.usfirst.frc.team1572.robot.commands.main.ForwardIntake;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class LeftAutoFullSwitch extends CommandGroup {

    public LeftAutoFullSwitch() {
    	if(Robot.ourSwitch == 'L') {
    		//place box in switch left plate
    		//addSequential(new DriveForwardTimed(14, 0.5, 5));
    		//addSequential(new TurnToAngle(3, 90, 1));
    		//addSequential(new DriveForwardTimed(2, 0.5, 5));
    		
    		//place box in switch left plate
    		//addParallel(new ForwardIntake(2, 0.3));
    		//addSequential(new ForkliftSwitchHeight(2));
    		addSequential(new DriveForwardTimed(14-2.3, 0.7, 4));
    		addSequential(new Delay(0.25));
    		addSequential(new TurnToAngle(1, 90, 0.75));
    		//addSequential(new Delay(0.25));
    		addSequential(new DriveForwardTimed(0.5, 0.5, 4));
    	}
    	else {
    		//go around switch and place box in switch right plate
    		//addSequential(new DriveForwardTimed(18, 0.5, 5));
    		//addSequential(new TurnToAngle(3, 90, 1));
    		//addSequential(new DriveForwardTimed(14, 0.5, 5));
    		//addSequential(new TurnToAngle(3, 90, 1));
    		//addSequential(new DriveForwardTimed(2, 0.5, 5));
    		//addParallel(new ForwardIntake(2, 0.3));
    		//addSequential(new ForkliftSwitchHeight(2));
    		addSequential(new DriveForwardTimed(18, 0.5, 5));//add 1 to 2 feet to account for boxes
    		addSequential(new Delay(0.25));
    		addSequential(new TurnToAngle(3, 90, 0.73));
    		addSequential(new DriveForwardTimed(14, 0.5, 5));
    		addSequential(new Delay(0.25));
    		addSequential(new TurnToAngle(3, 90, 0.75));
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
