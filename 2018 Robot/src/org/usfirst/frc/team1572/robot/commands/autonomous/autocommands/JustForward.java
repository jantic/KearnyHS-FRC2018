package org.usfirst.frc.team1572.robot.commands.autonomous.autocommands;

import javax.management.timer.Timer;

import org.usfirst.frc.team1572.robot.Robot;
import org.usfirst.frc.team1572.robot.subsystems.Drivetrain;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class JustForward extends Command {
	Drivetrain drivetrain = Robot.drivetrain;
	
    public JustForward() {
    	//tester to see if robot drives forward
    	requires(drivetrain);
    	
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	this.drivetrain.arcadeDriveVoltage(0, 0.4, 1, 1);
    	edu.wpi.first.wpilibj.Timer.delay(1);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	this.drivetrain.arcadeDriveRPM(0, 0, 0, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
