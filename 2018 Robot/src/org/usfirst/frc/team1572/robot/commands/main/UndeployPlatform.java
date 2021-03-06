package org.usfirst.frc.team1572.robot.commands.main;

import org.usfirst.frc.team1572.robot.Robot;
import org.usfirst.frc.team1572.robot.subsystems.PlatformRelease;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class UndeployPlatform extends Command {
	PlatformRelease platformRelease = Robot.platformRelease;
    public UndeployPlatform() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.platformRelease);
    	
    	
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	platformRelease.undeploy();
    	//LETS GO of platform NOT UNRELEASES
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
