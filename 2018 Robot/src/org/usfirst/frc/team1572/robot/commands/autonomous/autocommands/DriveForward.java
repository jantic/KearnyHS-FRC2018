package org.usfirst.frc.team1572.robot.commands.autonomous.autocommands;

import org.usfirst.frc.team1572.robot.Robot;
import org.usfirst.frc.team1572.robot.subsystems.Drivetrain;
import org.usfirst.frc.team1572.robot.subsystems.EncoderType;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DriveForward extends Command {
	double targetDistance;
	double topSpeed;
	double currentDistance;
	double distanceRemaining;
	Drivetrain drivetrain = Robot.drivetrain;
	
    public DriveForward(double targetDistance, double maxSpeed) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	//this.targetDistance = targetDistance;
    	double feetToInches = 12;
    	double inchesTarget = targetDistance * feetToInches;
    	this.targetDistance = drivetrain.InchesToEncoder(inchesTarget);
    	this.topSpeed = maxSpeed;
    	requires(Robot.drivetrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	drivetrain.EncoderReset();
    }

    protected void excute() {
	    // Called repeatedly when this Command is scheduled to run
	    final double joystickY = generateJoystickY();
		//final double joystickY = 0.0;
		//this.drivetrain.arcadeDriveVoltage(0, joystickY, 1, 0);
	    this.drivetrain.arcadeDriveVoltage(0, joystickY, 1, 1);
		final double currentDistance = this.currentDistance;
		final double diff = (this.targetDistance - currentDistance);
		this.distanceRemaining = diff;
		SmartDashboard.putNumber("targetDistance", this.targetDistance);
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
	//this.currentDistance = drivetrain.EncoderValue(EncoderType.left);
	this.currentDistance = -drivetrain.GetLeftEncoderPos();
	double error = this.targetDistance - this.currentDistance;
	SmartDashboard.putNumber("error", error);
	error/=this.targetDistance;
	error*= -1.16; 
	error*= this.topSpeed;
	if (Math.abs(error) > this.topSpeed) {
		error = topSpeed;
	}
	SmartDashboard.putNumber("speed", error);
	//if(this.targetDistance < 0){
	return error;
	//}
	//return -error;
}
    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if(Math.abs(distanceRemaining) < 20) {
    		return true;
    	}
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	drivetrain.arcadeDriveVoltage(0, 0, 0, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
