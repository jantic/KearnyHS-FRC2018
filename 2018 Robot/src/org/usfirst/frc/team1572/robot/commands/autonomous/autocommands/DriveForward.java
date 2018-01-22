package org.usfirst.frc.team1572.robot.commands.autonomous.autocommands;

import org.usfirst.frc.team1572.robot.Robot;
import org.usfirst.frc.team1572.robot.subsystems.Drivetrain;
import org.usfirst.frc.team1572.robot.subsystems.EncoderType;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveForward extends Command {
	double targetDistance;
	double topSpeed;
	double currentDistance;
	Drivetrain drivetrain = Robot.drivetrain;
	
    public DriveForward(double targetDistance, double maxSpeed) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	this.targetDistance = targetDistance;
    	this.topSpeed =  maxSpeed;
    	
    	requires(Robot.drivetrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	drivetrain.EncoderResest();
    }

    protected void excute() {
	    // Called repeatedly when this Command is scheduled to run
	    final double joystickY = generateJoystickY();
		//final double joystickY = 0.0;
		//this.drivetrain.arcadeDriveVoltage(0, joystickY, 1, 0);
	    this.drivetrain.arcadeDriveRPM(0, joystickY, 1, 1);
		final double currentDistance = this.currentDistance;
		final double diff = (this.targetDistance - currentDistance);
		
		/*if(Math.abs(diff) < this.angleTolerance){
			withinTolerance = true;
		}
		if(withinTolerance && lastWithinTolerance) {
			done = true;
		}
		lastWithinTolerance = withinTolerance;
		*/
    }

private double generateJoystickY(){
	this.currentDistance = drivetrain.EncoderValue(EncoderType.average);
	double error = this.targetDistance - this.currentDistance;
	error/=this.targetDistance;
	error*= 1.16; 
	error*= this.topSpeed;
	if (Math.abs(error) > this.topSpeed) {
		error = topSpeed;
	}
	if(this.targetDistance < 0){
	return -error;
	}
	return error;
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
