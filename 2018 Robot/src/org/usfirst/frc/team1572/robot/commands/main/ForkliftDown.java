package org.usfirst.frc.team1572.robot.commands.main;

import org.usfirst.frc.team1572.robot.JoystickController;
import org.usfirst.frc.team1572.robot.Robot;
import org.usfirst.frc.team1572.robot.subsystems.Forklift;

import edu.wpi.first.wpilibj.command.InstantCommand;

/**
 *
 */
public class ForkliftDown extends InstantCommand {
	Forklift forklift = Robot.forklift;
	JoystickController mainJoystick = JoystickController.MAIN_JOYSTICK;
    JoystickController coPilotJoystick = JoystickController.COPILOT_JOYSTICK;
    public ForkliftDown() {
        super();
        requires(Robot.forklift);
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called once when the command executes
    protected void initialize() {
    	
    }
    
    protected void execute() {
    	double leftTrigger = coPilotJoystick.getLeftTrigger();
    	forklift.moveToPosition(forklift.currentPosition() - leftTrigger, 0.5);
    }
    
}
