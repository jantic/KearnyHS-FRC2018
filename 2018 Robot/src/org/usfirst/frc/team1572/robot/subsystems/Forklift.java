package org.usfirst.frc.team1572.robot.subsystems;

import org.usfirst.frc.team1572.robot.RobotMap;
import org.usfirst.frc.team1572.robot.commands.main.ForkliftManual;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Forklift extends Subsystem {
	
	TalonSRX bottomForklift = RobotMap.bottomForklift;
	TalonSRX topForklift = RobotMap.topForklift;
	//call devices under new name
	double bottomLowLimit = RobotMap.bottomLowLimit;
	double bottomHighLimit = RobotMap.bottomHighLimit;
	double topLowLimit = RobotMap.topLowLimit;
	double topHighLimit = RobotMap.topHighLimit;
	double totalTravel = RobotMap.totalTravel;
	double currentTime = 0;
	double lastTime = 0;
	double bottomPositionRelative = 0;
	double brakeTopPosition = 0;
	double brakeBottomPosition = 0;
	//sets the limts of the stages
	double x = 1;
	
	public void moveManually(double speed) {
		double topSpeed = speed;
		double bottomSpeed = speed;
		if(speed > 0 && topPosition() > topHighLimit) {
			topSpeed = 0.2;
		}
		else if(speed < 0 && topPosition() < topLowLimit) {
			topSpeed = -0.1;
		}
		if(speed > 0 && bottomPosition() > bottomHighLimit) {
			bottomSpeed = 0;
		}
		/*else if(speed < 0 && bottomPosition() < bottomLowLimit) {
			bottomSpeed = -0.7;
		}*/
		//topForklift.set(ControlMode.PercentOutput, topSpeed);
		bottomForklift.set(ControlMode.PercentOutput, bottomSpeed);
	}
	
	public void moveToPosition(double position, double maxSpeed) {
		/*if(position < topLowLimit) {
			position = topLowLimit;
		}
		else */if(position > totalTravel) {
			position = totalTravel;
		}
		double top;
		double bottom;
		if(position > (topHighLimit - topLowLimit)) {
			top = topHighLimit - topLowLimit;
		}
		else {
			top = position;
		}
		//sets position limits so we don't break the forklift
		bottom = position - top;
		bottomToPosition(bottom + bottomLowLimit, maxSpeed);
		topToPosition(top + topLowLimit, maxSpeed);
	}
	/*
	public double currentPosition() {
		double currentPosition = 0;
		return currentPosition;
	}
	*/
	public double bottomPosition() {
		double leftVelocity = 1 * bottomForklift.getSelectedSensorVelocity(0);
		this.currentTime = Timer.getFPGATimestamp();
		double timeDifference = this.currentTime - this.lastTime;
		timeDifference *= 10; //100 ms
		double positionish = leftVelocity * timeDifference;
		this.bottomPositionRelative += positionish;
		this.lastTime = this.currentTime;
		//double bottomPosition = bottomForklift.getSelectedSensorPosition(0);
		//SmartDashboard.putNumber("bottomPosition", bottomPosition);
		return this.bottomPositionRelative;
		//gets the position of the bottom
	}
	public double topPosition() {
		double topPosition = topForklift.getSelectedSensorPosition(0);
		SmartDashboard.putNumber("topPosition", topPosition);
		return topPosition;
		//gets the position of the top
	}
	public void bottomToPosition(double position, double maxSpeed) {
		double difference = position - bottomPosition();
		double tolerance = 2;
		difference /= ((bottomHighLimit - bottomLowLimit) / x);
		difference *= 1;
		difference *= maxSpeed;
		if(difference > 1) {
			difference = 1;
		}
		else if(difference < -1 * 0.6) {
			difference = -1 * 0.6;
		}
		/*if(difference > maxSpeed) {
			difference = maxSpeed;
		}
		else if(difference < -1 * maxSpeed) {
			difference = -1 * maxSpeed;
		}*/
		//if (position > bottomPosition()) {
			bottomForklift.set(ControlMode.PercentOutput, difference);
			//sends bottom part the forklift to a position
		//}
		/*else {
			bottomForklift.set(ControlMode.PercentOutput, 0);
		}*/
		SmartDashboard.putNumber("bottomPosition", bottomPosition());
		SmartDashboard.putNumber("bottomSpeed", difference);
	}

	public void topToPosition(double position, double maxSpeed) {
		double difference = position - topPosition();
		double tolerance = 2;
		difference /= ((topHighLimit - topLowLimit) / x);
		difference *= 3.5;
		difference *= maxSpeed;
		if(difference > maxSpeed) {
			difference = maxSpeed;
		}
		else if(difference < -1 * maxSpeed) {
			difference = -1 * maxSpeed;
		}
		//if (position > topPosition()) {
			topForklift.set(ControlMode.PercentOutput, difference);
			//sets the top part of a forklift to a positon
		//}
		//else {
		//	topForklift.set(ControlMode.PercentOutput, 0);
		//}
		SmartDashboard.putNumber("topPosition", topPosition());
		SmartDashboard.putNumber("topSpeed", difference);
	}
	
	public void stopForklift() {
		topForklift.set(ControlMode.PercentOutput, 0);
		bottomForklift.set(ControlMode.PercentOutput, 0);
		//stops both stages
	}
	
	public double getCurrentPos() {
		double bottomPos = bottomPosition() - bottomLowLimit;
		double topPos = topPosition() - topLowLimit;
		double currentPos = bottomPos + topPos;
		return currentPos;
		//gets the current postion of the forklift
	}
	
	public void resetEncoders() {
		//topForklift.setSelectedSensorPosition(0, 0, 0);
		this.bottomPositionRelative = 0;
	}
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
    	setDefaultCommand (new ForkliftManual());
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public double topBrakePosition() {
    	return this.brakeTopPosition;
    }
    
    public void changeTopBrakePosition(double position) {
    	this.brakeTopPosition = position;
    }
    
    public double bottomBrakePosition() {
    	return this.brakeBottomPosition;
    }
    
    public void changeBottomBrakePosition(double position) {
    	this.brakeBottomPosition = position;
    }
}

