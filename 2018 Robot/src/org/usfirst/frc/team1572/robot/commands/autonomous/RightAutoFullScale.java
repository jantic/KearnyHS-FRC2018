package org.usfirst.frc.team1572.robot.commands.autonomous;

import org.usfirst.frc.team1572.robot.Robot;
import org.usfirst.frc.team1572.robot.commands.autonomous.autocommands.Delay;
import org.usfirst.frc.team1572.robot.commands.autonomous.autocommands.DriveForwardTimed;
import org.usfirst.frc.team1572.robot.commands.autonomous.autocommands.TurnToAngle;
import org.usfirst.frc.team1572.robot.commands.main.ForkliftUp;
import org.usfirst.frc.team1572.robot.commands.main.ForwardIntake;
import org.usfirst.frc.team1572.robot.commands.main.ReverseIntake;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class RightAutoFullScale extends CommandGroup {

    public RightAutoFullScale() {
    	if(Robot.scale == 'R') {
    		addSequential(new DriveForwardTimed(22, 0.75, 4));
    		addParallel(new ForwardIntake(2, 0.3));
    		addSequential(new ForkliftUp(2));
    		addSequential(new Delay(0.1));
    		addSequential(new TurnToAngle(0.5, -40, 1.5));
    		addSequential(new ForkliftUp(0.5));
    		addSequential(new DriveForwardTimed(1, 0.5, 2));
    		addSequential(new ReverseIntake(0.5));
    		addSequential(new DriveForwardTimed(-2, 0.5, 2));
    	}
    	else {
    		//go around switch and place box in scale left plate
    		addSequential(new DriveForwardTimed(19.25, 0.75, 4.5));
    		addSequential(new Delay(0.1));
    		addSequential(new TurnToAngle(1.5, -90, 0.53));
    		addSequential(new Delay(0.1));
    		addSequential(new DriveForwardTimed(16, 0.75, 4));
    		addSequential(new Delay(0.1));
    		addSequential(new TurnToAngle(1.5, 90, 0.65));
    		addParallel(new ForwardIntake(1.25, 0.3));
    		addSequential(new ForkliftUp(1.25));
    		addSequential(new DriveForwardTimed(2, 0.5, 1.5));
    		addSequential(new ReverseIntake(0.5));
    		addSequential(new DriveForwardTimed(-2, 0.5, 2));
    		
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
