package org.usfirst.frc.team1572.robot.subsystems;

import org.usfirst.frc.team1572.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Forklift extends Subsystem {
	
	TalonSRX bottomForklift = RobotMap.bottomForklift;
	TalonSRX topForklift = RobotMap.topForklift;
	double bottomLowLimit = RobotMap.bottomLowLimit;
	double bottomHighLimit = RobotMap.bottomHighLimit;
	double topLowLimit = RobotMap.topLowLimit;
	double topHighLimit = RobotMap.topHighLimit;
	
	public void moveToPosition(double position, double maxSpeed) {
		double top;
		double bottom;
		if(position > (topHighLimit - topLowLimit)) {
			top = topHighLimit - topLowLimit;
		}
		else {
			top = position;
		}
		bottom = position - top;
		bottomToPosition(bottom, maxSpeed);
		topToPosition(top, maxSpeed);
	}
	public double currentPosition() {
		double currentPosition = 0;
		return currentPosition;
	}
	double bottomPosition() {
		double bottomPosition = 0;
		return bottomPosition;
	}
	double topPosition() {
		double topPosition = 0;
		return topPosition;
	}
	void bottomToPosition(double position, double maxSpeed) {
		
	}
	void topToPosition(double position, double maxSpeed) {
		
	}

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

