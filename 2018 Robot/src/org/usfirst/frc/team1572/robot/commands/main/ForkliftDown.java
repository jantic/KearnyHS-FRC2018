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
public class ForkliftDown extends Command {
	Forklift forklift = Robot.forklift;
	JoystickController mainJoystick = JoystickController.MAIN_JOYSTICK;
    JoystickController coPilotJoystick = JoystickController.COPILOT_JOYSTICK;
    public ForkliftDown(double timeout) {
        super(timeout);
        requires(Robot.forklift);
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called once when the command executes
    protected void initialize() {
    	
    }
    
    protected void execute() {
    	//double leftTrigger = coPilotJoystick.getLeftTrigger();
    	forklift.moveToPosition(RobotMap.topLowLimit, 1);
    	
    	//forklift.currentPosition() - leftTrigger
    	//brings down the forklift to lowest position
    }

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		//return false;
		return super.isTimedOut();
	}
	
	protected void end() {
	}
    
}

