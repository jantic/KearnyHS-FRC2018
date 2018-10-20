package org.usfirst.frc.team1572.robot.commands.autonomous;

import org.usfirst.frc.team1572.robot.Robot;
import org.usfirst.frc.team1572.robot.commands.autonomous.autocommands.Delay;
import org.usfirst.frc.team1572.robot.commands.autonomous.autocommands.DriveForwardTimed;
import org.usfirst.frc.team1572.robot.commands.autonomous.autocommands.TurnToAngle;
import org.usfirst.frc.team1572.robot.commands.main.ForkliftSwitchHeight;
import org.usfirst.frc.team1572.robot.commands.main.ForkliftUp;
import org.usfirst.frc.team1572.robot.commands.main.ForwardIntake;
import org.usfirst.frc.team1572.robot.commands.main.ReverseIntake;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class RightAutoScale extends CommandGroup {

    public RightAutoScale() {
    	if(Robot.scale == 'L') {
    		//drive over auto line
    		addSequential(new DriveForwardTimed(10, 0.5, 5));
    	}
    	else {
    		//place box in scale
    		addSequential(new DriveForwardTimed(22, 0.75, 4));
    		addParallel(new ForwardIntake(2, 0.3));
    		addSequential(new ForkliftUp(2));
    		addSequential(new Delay(0.1));
    		addSequential(new TurnToAngle(0.5, -40, 1.5));
    		addSequential(new ForkliftUp(0.5));
    		addSequential(new DriveForwardTimed(1.5, 0.5, 2));
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
