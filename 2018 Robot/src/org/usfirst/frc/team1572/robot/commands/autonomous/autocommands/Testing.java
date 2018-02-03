package org.usfirst.frc.team1572.robot.commands.autonomous.autocommands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.usfirst.frc.team1572.robot.commands.autonomous.autocommands.DriveForward;
import org.usfirst.frc.team1572.robot.commands.autonomous.autocommands.DriveForwardTimed;
import org.usfirst.frc.team1572.robot.commands.main.ForkliftManual;
import org.usfirst.frc.team1572.robot.commands.main.ForkliftUp;
import org.usfirst.frc.team1572.robot.commands.main.ReverseIntake;
import org.usfirst.frc.team1572.robot.commands.main.StopIntake;
/**
 *
 */
public class Testing extends CommandGroup {

    public Testing() {
    	//tests different auto commands groups before commands enter the auto environment
    	
    	//addSequential(new Forward(0.5));
    	
    	//addSequential(new DriveForwardTimed(5, 0.5, 5));
    	
		addParallel(new ForkliftUp());
		addParallel(new Delay(3));
		//addParallel(new ForkliftStop());
		addSequential(new ReverseIntake());
		addSequential(new Delay(3));
		addSequential(new StopIntake());
       	
    	
    	//addSequential(new DriveForward(2, 0.5));
    	//addSequential(new TurnToAngle(3,90,1));
      
    	
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
