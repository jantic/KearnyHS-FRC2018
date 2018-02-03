package org.usfirst.frc.team1572.robot.commands.autonomous.autocommands;

import org.usfirst.frc.team1572.robot.Robot;
import org.usfirst.frc.team1572.robot.subsystems.Drivetrain;
import org.usfirst.frc.team1572.robot.subsystems.HeadingSubsystem;

import edu.wpi.first.wpilibj.command.TimedCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DriveForwardTimed extends TimedCommand {
	
	double targetDistance;
	double topSpeed;
	double topTurnSpeed = 0.4;
	double currentDistance;
	double distanceRemaining;
	double currentAngle;
	double targetAngle = 0;
	Drivetrain drivetrain = Robot.drivetrain;
	HeadingSubsystem headingSubsystem = Robot.headingSubsystem;

    public DriveForwardTimed(double distance, double maxSpeed, double timeout) {
    	super(timeout);
        requires(Robot.drivetrain);
        double feetToInches = 12;
    	double inchesTarget = -1 * distance * feetToInches;
    	this.targetDistance = drivetrain.InchesToEncoder(inchesTarget);
    	this.topSpeed = maxSpeed;
    	SmartDashboard.putNumber("targetDistance", drivetrain.EncoderToInches(targetDistance)/feetToInches);
    	SmartDashboard.putNumber("realTargetDistance", this.targetDistance);
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	generateJoystickY();
    	drivetrain.EncoderReset();
    	headingSubsystem.reset();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	final double joystickY = generateJoystickY();
    	final double joystickX = generateJoystickX();
		//final double joystickY = 0.0;
		//this.drivetrain.arcadeDriveVoltage(0, joystickY, 1, 0);
	    this.drivetrain.arcadeDriveVoltage(joystickX, joystickY, 1, 1);
		final double currentDistance = this.currentDistance;
		final double diff = (this.targetDistance - currentDistance);
		this.distanceRemaining = diff;
		SmartDashboard.putNumber("DistanceRemaining", this.distanceRemaining);
		/*if(Math.abs(diff) < this.angleTolerance){
			withinTolerance = true;
		}
		if(withinTolerance && lastWithinTolerance) {
			done = true;
		}
		lastWithinTolerance = withinTolerance;
		*/
    }
    private double generateJoystickX() {
    	this.currentAngle = headingSubsystem.getAngle();
    	SmartDashboard.putNumber("currentAngleError", this.currentAngle);
    	double error = this.targetAngle - this.currentAngle;
    	error /= Math.abs(10);
    	error *= 1;
    	error *= this.topTurnSpeed;
    	SmartDashboard.putNumber("angleError", error);
    	if (error > this.topTurnSpeed) {
    		error = topTurnSpeed;
    	}
    	else if(error < -1 * this.topTurnSpeed) {
    		error = this.topTurnSpeed * -1;
    	}
    	return error;
    }
    private double generateJoystickY(){
    	//this.currentDistance = drivetrain.EncoderValue(EncoderType.left);
    	this.currentDistance = drivetrain.GetLeftEncoderPos();
    	double error = this.targetDistance - this.currentDistance;
    	SmartDashboard.putNumber("error", error);
    	error /= Math.abs(this.targetDistance);
    	error *= 1.16; 
    	error *= this.topSpeed;
    	//SmartDashboard.putNumber("error2", error);
    	if (error > this.topSpeed) {
    		error = topSpeed;
    	}
    	else if(error < -1 * this.topSpeed) {
    		error = this.topSpeed * -1;
    	}
    	SmartDashboard.putNumber("speed", error);
    	//if(this.targetDistance < 0){
    	return error;
    	//}
    	//return -error;
    }

    protected boolean isFinished() {
    	if(Math.abs(distanceRemaining) < 150) {
    		return true;
    	}
		return super.isFinished();
    }
    
    protected void end() {
    	SmartDashboard.putNumber("endDistance", this.currentDistance);
    	drivetrain.arcadeDriveVoltage(0, 0, 0, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
