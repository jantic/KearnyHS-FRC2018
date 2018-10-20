package org.usfirst.frc.team1572.robot.commands.main;

import org.usfirst.frc.team1572.robot.JoystickController;
import org.usfirst.frc.team1572.robot.Robot;
import org.usfirst.frc.team1572.robot.RobotMap;
import org.usfirst.frc.team1572.robot.subsystems.Forklift;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.InstantCommand;

/**
 *
 */
public class ForkliftUp extends Command {
	Forklift forklift = Robot.forklift;

	public ForkliftUp(double timeout) {
        super(timeout);
        requires(Robot.forklift);
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called once when the command executes
    protected void initialize() {
    	
    }
    
    protected void execute() {
    	forklift.moveToPosition(RobotMap.topHighLimit - RobotMap.topLowLimit + 88000 - RobotMap.bottomLowLimit, 1);
    	forklift.changeBottomBrakePosition(88000);
    	forklift.changeTopBrakePosition(RobotMap.topHighLimit);
    	//moves the forklift to this position
    	//forklift.currentPosition() + rightTrigger
    }

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return super.isTimedOut();
	}
	
	protected void end() {
		forklift.moveManually(0);
	}
    
}
