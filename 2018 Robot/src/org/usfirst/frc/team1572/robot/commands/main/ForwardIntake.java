package org.usfirst.frc.team1572.robot.commands.main;

import org.usfirst.frc.team1572.robot.Robot;
import org.usfirst.frc.team1572.robot.subsystems.Climber;
import org.usfirst.frc.team1572.robot.subsystems.Intake;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.TimedCommand;

/**
 *
 */
public class ForwardIntake extends Command {
	
	Intake intake = Robot.intake;
	double power;

    public ForwardIntake(double timeout, double speed) {
    	super(timeout);
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.intake);
    	this.power = speed;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	intake.runIntake(this.power);
    }
    //pulls box into claw
    
    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return super.isTimedOut();
    }

    // Called once after isFinished returns true
    protected void end() {
    	intake.runIntake(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
