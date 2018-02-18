package org.usfirst.frc.team1572.robot.commands.main;

import org.usfirst.frc.team1572.robot.JoystickController;
import org.usfirst.frc.team1572.robot.Robot;
import org.usfirst.frc.team1572.robot.RobotMap;
import org.usfirst.frc.team1572.robot.subsystems.Forklift;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class ForkliftManual extends Command {

	JoystickController stick = JoystickController.COPILOT_JOYSTICK;
	Forklift forklift = Robot.forklift;
	//double targetPostion;
	
    public ForkliftManual() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(Robot.forklift);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
 
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double currentPosition = forklift.bottomPosition() + forklift.topPosition();
    	currentPosition -= RobotMap.bottomLowLimit;
    	currentPosition -= RobotMap.topLowLimit;
    	SmartDashboard.putNumber("current position", currentPosition);
    	/*if(Math.abs(stick.getRightStickY()) < 0.2) {
    		forklift.moveToPosition(currentPosition, 1);
    	}
    	else{*/
    		//forklift.moveManually(stick.getRightStickY() * -0.4);
    	if(Math.abs(stick.getRightTrigger()) < 0.2) {
    		forklift.bottomToPosition(forklift.bottomBrakePosition(), 1);
    	}
    	else if(forklift.bottomPosition() >= 72000 && forklift.bottomPosition() <= 79000 && stick.getRightTrigger() < 0) {
    		forklift.moveManually(stick.getRightTrigger() * -0.2);
    		forklift.changeBottomBrakePosition(forklift.bottomPosition());
    	}
    	else if(forklift.bottomPosition() <= 0 && stick.getRightTrigger() > 0 || forklift.bottomPosition() >= 79000 && stick.getRightTrigger() < 0) {
    		forklift.moveManually(0);
    		forklift.changeBottomBrakePosition(forklift.bottomPosition());
    	}
    	else {
    		forklift.moveManually(stick.getRightTrigger() * -0.4);
    		forklift.changeBottomBrakePosition(forklift.bottomPosition());
    	}
    	forklift.topToPosition(forklift.topBrakePosition(), 0.5);
    	//}
    	
    	//forklift.moveToPosition(GetNextPos(), 1);
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
