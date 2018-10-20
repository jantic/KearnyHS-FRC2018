package org.usfirst.frc.team1572.robot.commands.autonomous;

import org.usfirst.frc.team1572.robot.Robot;
import org.usfirst.frc.team1572.robot.commands.autonomous.autocommands.Delay;
import org.usfirst.frc.team1572.robot.commands.autonomous.autocommands.DriveForwardTimed;
import org.usfirst.frc.team1572.robot.commands.autonomous.autocommands.TurnToAngle;
import org.usfirst.frc.team1572.robot.commands.main.ForkliftSwitchHeight;
import org.usfirst.frc.team1572.robot.commands.main.ForwardIntake;
import org.usfirst.frc.team1572.robot.commands.main.ReverseIntake;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class LeftAutoSwitch extends CommandGroup {

    public LeftAutoSwitch() {
    	if(Robot.ourSwitch == 'R'){
    		//drive past auto line
    		addSequential(new DriveForwardTimed(10, 0.7, 5));
    	}
    	else {
    		//place box in switch
    		//addParallel(new ForwardIntake(2, 0.3));
    		//addSequential(new ForkliftSwitchHeight(2));
    		addParallel(new ForwardIntake(1, 0.1));
    		addSequential(new ForkliftSwitchHeight(1));
    		addSequential(new DriveForwardTimed(10.5, 0.7, 4));
    		addSequential(new Delay(0.25));
    		addSequential(new TurnToAngle(1, 90, 0.65));
    		addSequential(new Delay(0.25));
    		addSequential(new DriveForwardTimed(1.5, 0.5, 1));
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
