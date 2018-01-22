package org.usfirst.frc.team1572.robot.commands.main;

import org.usfirst.frc.team1572.robot.JoystickController;
import org.usfirst.frc.team1572.robot.Robot;
import org.usfirst.frc.team1572.robot.subsystems.Forklift;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.InstantCommand;

/**
 *
 */
public class ForkliftUp extends Command {
	Forklift forklift = Robot.forklift;

	public ForkliftUp() {
        super();
        requires(Robot.forklift);
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called once when the command executes
    protected void initialize() {
    	
    }
    
    protected void execute() {
    	forklift.moveToPosition(30000, 1);
    	//forklift.currentPosition() + rightTrigger
    }

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}
    
}
