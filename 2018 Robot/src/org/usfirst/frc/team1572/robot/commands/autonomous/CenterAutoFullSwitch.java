package org.usfirst.frc.team1572.robot.commands.autonomous;

import org.usfirst.frc.team1572.robot.Robot;
import org.usfirst.frc.team1572.robot.commands.autonomous.autocommands.Delay;
import org.usfirst.frc.team1572.robot.commands.autonomous.autocommands.DriveForward;
import org.usfirst.frc.team1572.robot.commands.autonomous.autocommands.DriveForwardTimed;
import org.usfirst.frc.team1572.robot.commands.autonomous.autocommands.TurnToAngle;
import org.usfirst.frc.team1572.robot.commands.main.ForkliftUp;
import org.usfirst.frc.team1572.robot.commands.main.ReverseIntake;
import org.usfirst.frc.team1572.robot.commands.main.StopIntake;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CenterAutoFullSwitch extends CommandGroup {

    public CenterAutoFullSwitch() {
    	if(Robot.ourSwitch == 'L') {
    		//place box in switch left plate
    		addSequential(new DriveForwardTimed(4, 0.5, 5));
    		addSequential(new Delay(0.1));
    		addSequential(new TurnToAngle(3, -90, 0.68));
    		addSequential(new DriveForwardTimed(3.5, 0.5, 5));
    		addSequential(new Delay(0.1));
    		addSequential(new TurnToAngle(3, 90, 0.68));
    		//addSequential(new DriveForwardTimed(2, 0.5, 5));
    	}
    	else if (Robot.ourSwitch == 'R') {
    		//place box in switch right plate
    		addSequential(new DriveForwardTimed(4, 0.5, 5));
    		addSequential(new Delay(0.1));
    		addSequential(new TurnToAngle(3, 90, 0.68));
    		addSequential(new DriveForwardTimed(4, 0.5, 5));
    		addSequential(new Delay(0.1));
    		addSequential(new TurnToAngle(3, -90, 0.68)); //was 75
    		addSequential(new DriveForwardTimed(2, 0.5, 5));
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
