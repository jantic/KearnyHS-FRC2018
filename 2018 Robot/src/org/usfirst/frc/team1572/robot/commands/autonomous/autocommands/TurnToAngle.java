package org.usfirst.frc.team1572.robot.commands.autonomous.autocommands;

import org.usfirst.frc.team1572.robot.Robot;
import org.usfirst.frc.team1572.robot.subsystems.HeadingSubsystem;
import org.usfirst.frc.team1572.robot.subsystems.Drivetrain;
import edu.wpi.first.wpilibj.command.TimedCommand;

/**
 *
 */
public class TurnToAngle extends TimedCommand {
	
	HeadingSubsystem headingSubsystem = Robot.headingSubsystem;	
	Drivetrain drivetrain = Robot.drivetrain;
	private double targetAngle;
	private final double angleTolerance = 1;
	private double turnSpeed;
	private double currentAngle;
	private boolean withinTolerance = false;
	private boolean lastWithinTolerance = false;
	private boolean done;
	
   public TurnToAngle(double timeout, double angle, double maxSpeed) {
        super(timeout);
        this.targetAngle = angle;
        this.turnSpeed = maxSpeed;
        if (this.turnSpeed > 1) {
        	this.turnSpeed = 1;
        }
        //sets parameters to private variables
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(Robot.headingSubsystem);
    }
   
   protected void initialize() {
	   headingSubsystem.reset();
	   //resets sensor systems
   }
  
   protected void execute() {
		final double joystickX = generateJoystickX();
		//creates value for x-axis 
		//final double joystickY = 0.0;
		this.drivetrain.arcadeDriveVoltage(joystickX, 0, 1, 0);
		//drives the drivetrain
		
		final double currentAngle = this.currentAngle;
		final double diff = (this.targetAngle - currentAngle);
		//checks angle remaining for debug?
		
		/*if(Math.abs(diff) < this.angleTolerance){
			withinTolerance = true;
		}
		if(withinTolerance && lastWithinTolerance) {
			done = true;
		}
		lastWithinTolerance = withinTolerance;
		*/
	}
	
	
	private double generateJoystickX(){
		this.currentAngle = headingSubsystem.getAngle();
		double error = this.targetAngle - this.currentAngle;
		error/=Math.abs(this.targetAngle);
		error*= 1.05; 
		error*= this.turnSpeed;
		if (Math.abs(error) > this.turnSpeed) {
			if (error > 0) {
				error = turnSpeed;
			}
			 
			else {
				error = -turnSpeed;
			}
		}
		//if(this.targetAngle < 0){
		//return -error;
		//}
		return error;
		//creates value for x axis based on angle remaining
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
		
		if(done) {
			return true;
		}
		
		return super.isFinished();
	}

	// Called once after isFinished returns true
	@Override
	protected void end() {
		this.drivetrain.arcadeDriveVoltage(0, 0, 0, 0);
		//stops robot once angle is met
	}

}

/*
// Called just before this Command runs the first time
protected void initialize() {
	headingSubsystem.reset();
	this.targetAngle = this.targetAngle + this.headingSubystem.getAngle();
}

// Called repeatedly when this Command is scheduled to run
protected void execute() {
	if (targetAngle = headingSubsystem.getAngle())
}

// Called once after timeout
protected void end() {
}

// Called when another command which requires one or more of the same
// subsystems is scheduled to run
protected void interrupted() {
}
}
 	*/