package org.usfirst.frc.team1572.robot.commands.main;

import org.usfirst.frc.team1572.robot.JoystickController;
import org.usfirst.frc.team1572.robot.Robot;
import org.usfirst.frc.team1572.robot.RobotMap;
import org.usfirst.frc.team1572.robot.subsystems.Forklift;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ForkliftManual extends Command {

	JoystickController stick = JoystickController.MAIN_JOYSTICK;
	Forklift forklift = Robot.forklift;
	//double targetPostion;
	
    public ForkliftManual() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(forklift);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
 
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	forklift.moveToPosition(GetNextPos(), 1);
    	//mets the target position
    }

    public double GetNextPos() {
    	double currentPos = forklift.getCurrentPos();
    	double targetPos = stick.getRightStickY() * RobotMap.totalTravel / 2;
    	targetPos += currentPos;
    	return targetPos;
    	//find target position value based on position of joystick and the current position
    }
    // Called once after timeout
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}
}
