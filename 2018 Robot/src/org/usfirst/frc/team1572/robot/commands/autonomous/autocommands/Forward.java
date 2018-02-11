package org.usfirst.frc.team1572.robot.commands.autonomous.autocommands;

import org.usfirst.frc.team1572.robot.Robot;
import org.usfirst.frc.team1572.robot.subsystems.Drivetrain;

import edu.wpi.first.wpilibj.command.TimedCommand;

/**
 *
 */
public class Forward extends TimedCommand {
	
	Drivetrain drivetrain = Robot.drivetrain;
	//Tester to see if robot can drive forward 
	
    public Forward(double timeout) {
        super(timeout);
        requires(Robot.drivetrain);
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	drivetrain.arcadeDriveVoltage(0, -0.95, 1, 1);
    	
    }

    // Called once after timeout
    protected void end() {
    	drivetrain.arcadeDriveVoltage(0, 0, 0, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
