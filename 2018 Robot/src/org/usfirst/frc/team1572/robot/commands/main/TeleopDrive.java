package org.usfirst.frc.team1572.robot.commands.main;

import org.usfirst.frc.team1572.robot.JoystickController;
import org.usfirst.frc.team1572.robot.Robot;
import org.usfirst.frc.team1572.robot.subsystems.Drivetrain;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class TeleopDrive extends Command {
	JoystickController mainJoystick = JoystickController.MAIN_JOYSTICK;
    JoystickController coPilotJoystick = JoystickController.COPILOT_JOYSTICK;
    Drivetrain drivetrain = Robot.drivetrain;
    public TeleopDrive() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.drivetrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	double mainY = mainJoystick.getLeftStickY();
    	double mainX = mainJoystick.getRightStickX();
    	double overdrive = mainJoystick.getRightTrigger();
    	double coY = coPilotJoystick.getLeftStickY();
    	double coX = coPilotJoystick.getLeftStickX();
    	//double takeover = coPilotJoystick.getRightTrigger();
    	double normalSpeed = 0.75;
    	double normalTurn = 0.75;
    	double overSpeed = 1;
    	double overTurn = 0.9;
    	double coSpeed = 0.7;
    	double coTurn = 0.7;
    	//System.out.println("mainY" + mainY);
    	//System.out.println("mainX" + mainX);
    	if(Math.abs(coX) > 0.2 || Math.abs(coY) > 0.2) {
    		drivetrain.arcadeDriveVoltage(coX, coY, coTurn, coSpeed);
    	}
    	else if(overdrive > 0.5) {
    		drivetrain.arcadeDriveVoltage(mainX, mainY, overTurn, overSpeed);
    	}
    	else {
    		drivetrain.arcadeDriveVoltage(mainX, mainY, normalTurn, normalSpeed);
    	}
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
