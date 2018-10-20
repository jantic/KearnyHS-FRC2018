package org.usfirst.frc.team1572.robot.commands.autonomous.autocommands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team1572.robot.Robot;
import org.usfirst.frc.team1572.robot.RobotMap;
import org.usfirst.frc.team1572.robot.commands.autonomous.autocommands.DriveForward;
import org.usfirst.frc.team1572.robot.commands.autonomous.autocommands.DriveForwardTimed;
import org.usfirst.frc.team1572.robot.commands.main.ForkliftManual;
import org.usfirst.frc.team1572.robot.commands.main.ForkliftSwitchHeight;
import org.usfirst.frc.team1572.robot.commands.main.ForkliftToPos;
import org.usfirst.frc.team1572.robot.commands.main.ForkliftUp;
import org.usfirst.frc.team1572.robot.commands.main.ForwardIntake;
import org.usfirst.frc.team1572.robot.commands.main.ReverseIntake;
import org.usfirst.frc.team1572.robot.commands.main.StopIntake;
/**
 *
 */
public class Testing extends CommandGroup {

    public Testing() {
    	SmartDashboard.putString("daRealSwitch", Character.toString(Robot.ourSwitch));
    	if(Robot.ourSwitch == 'R') {
    		addSequential(new CubeVision(60));
    		//addSequential(new DriveForwardTimed(0.5, 0.5, 0.5));
    		//addParallel(new ForwardIntake(2, 0.3));
    		//addSequential(new ForkliftSwitchHeight(2));
    		//addSequential(new DriveForwardTimed(2, 0.5, 2));
    		//addSequential(new TurnToAngle(3, -90, 0.5));
    	}
    	else if(Robot.ourSwitch == 'L') {
    		//addSequential(new DriveForwardTimed(0.5, 0.5, 0.5));
    		//addSequential(new Delay(1));
    		addSequential(new DriveForwardTimed(19, 0.5, 4));//add 1 to 2 feet to account for boxes
    		addSequential(new Delay(0.25));
    		addSequential(new TurnToAngle(1.75, -90, 0.75));
    		addSequential(new DriveForwardTimed(20, 0.5, 4));//increase to pass switch
    		addSequential(new Delay(0.25));
    		addSequential(new TurnToAngle(2, -135, 0.75));
    	}
    	else {
    		addSequential(new DriveForwardTimed(-0.5, 0.5, 0.5));
    	}
    	//tests different auto commands groups before commands enter the auto environment
    	//addSequential(new Forward(5));
    	//addSequential(new DriveForwardTimed(10, 0.5, 10));
    	
    	//addSequential(new DriveForwardTimed(5, 0.5, 5));
    	
		//addParallel(new ForkliftToPos(RobotMap.bottomHighLimit));
		//addParallel(new Delay(1.5));
		//addParallel(new ForkliftStop());
		//addSequential(new ReverseIntake());
		//addSequential(new Delay(1.5));
		//addSequential(new StopIntake());
       	
    	
    	//addSequential(new DriveForward(2, 0.5));
    	
        

    	
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
