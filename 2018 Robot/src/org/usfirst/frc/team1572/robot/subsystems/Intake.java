package org.usfirst.frc.team1572.robot.subsystems;

import org.usfirst.frc.team1572.robot.RobotMap;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Intake extends Subsystem {
	
	Spark leftIntake = RobotMap.leftIntake;
	Spark rightIntake = RobotMap.rightIntake;
	
	public void runIntake(double speed) {
		leftIntake.set(speed);
		rightIntake.set(speed);
	}
	public void runIntakeSeparate(double leftSpeed, double rightSpeed) {
		leftIntake.set(leftSpeed);
		rightIntake.set(rightSpeed);
	}
	//sets speed of intake
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

