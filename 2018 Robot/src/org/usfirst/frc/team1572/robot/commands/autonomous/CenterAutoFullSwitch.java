package org.usfirst.frc.team1572.robot.commands.autonomous;

import org.usfirst.frc.team1572.robot.Robot;
import org.usfirst.frc.team1572.robot.commands.autonomous.autocommands.Delay;
import org.usfirst.frc.team1572.robot.commands.autonomous.autocommands.DriveForward;
import org.usfirst.frc.team1572.robot.commands.autonomous.autocommands.DriveForwardTimed;
import org.usfirst.frc.team1572.robot.commands.autonomous.autocommands.TurnToAngle;
import org.usfirst.frc.team1572.robot.commands.main.ForkliftSwitchHeight;
import org.usfirst.frc.team1572.robot.commands.main.ForkliftUp;
import org.usfirst.frc.team1572.robot.commands.main.ForwardIntake;
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
    		addParallel(new ForwardIntake(1, 0.1));
    		addSequential(new ForkliftSwitchHeight(1));
    		addSequential(new DriveForwardTimed(1.5, 0.6, 3.5));
    		addSequential(new Delay(0.1));
    		addSequential(new TurnToAngle(1, -90, 0.55));
    		addSequential(new DriveForwardTimed(4, 0.7, 7.5));
    		addSequential(new Delay(0.1));
    		addSequential(new TurnToAngle(1, 90, 0.6));
    		addSequential(new DriveForwardTimed(5, 0.85, 12));
    		addSequential(new ReverseIntake(0.5));
    	}
    	else if (Robot.ourSwitch == 'R') {
    		//place box in switch right plate
    		addParallel(new ForwardIntake(1, 0.1));
    		addSequential(new ForkliftSwitchHeight(1));
    		addSequential(new DriveForwardTimed(1.5, 0.6, 3.5));
    		addSequential(new Delay(0.1));
    		addSequential(new TurnToAngle(1, 90, 0.6));
    		addSequential(new DriveForwardTimed(3, 0.7, 6.5));
    		addSequential(new Delay(0.1));
    		addSequential(new TurnToAngle(1, -90, 0.5)); //was 75
    		addSequential(new DriveForwardTimed(4.5, 0.85, 10));
    		addSequential(new ReverseIntake(0.5));
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
