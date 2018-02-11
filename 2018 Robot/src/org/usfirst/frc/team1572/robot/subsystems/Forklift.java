package org.usfirst.frc.team1572.robot.subsystems;

import org.usfirst.frc.team1572.robot.RobotMap;
import org.usfirst.frc.team1572.robot.commands.main.ForkliftManual;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

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
	//sets the limts of the stages
	double x = 1;
	public void moveToPosition(double position, double maxSpeed) {
		if(position < bottomLowLimit) {
			position = bottomLowLimit;
		}
		else if(position > totalTravel) {
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
		double bottomPosition = bottomForklift.getSelectedSensorPosition(0);
		SmartDashboard.putNumber("bottomPosition", bottomPosition);
		return bottomPosition;
		//gets the position of the bottom
	}
	public double topPosition() {
		double topPosition = topForklift.getSelectedSensorPosition(0);
		SmartDashboard.putNumber("topPosition", topPosition);
		return topPosition;
		//gets the position of the top
	}
	void bottomToPosition(double position, double maxSpeed) {
		double difference = position - bottomPosition();
		double tolerance = 2;
		difference /= (bottomHighLimit / x);
		difference *= 1;
		difference *= maxSpeed;
		//if (position > bottomPosition()) {
			//bottomForklift.set(ControlMode.PercentOutput, difference);
			//sends bottom part the forklift to a position
		//}
		/*else {
			bottomForklift.set(ControlMode.PercentOutput, 0);
		}*/
		SmartDashboard.putNumber("bottomPosition", bottomPosition());
		SmartDashboard.putNumber("bottomSpeed", difference);
	}

	void topToPosition(double position, double maxSpeed) {
		double difference = position - topPosition();
		double tolerance = 2;
		difference /= (topHighLimit / x);
		difference *= 1;
		difference *= maxSpeed;
		//if (position > topPosition()) {
			//topForklift.set(ControlMode.PercentOutput, difference);
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
		double bottomPos = bottomForklift.getSelectedSensorPosition(0); //add encoder
		double topPos = topForklift.getSelectedSensorPosition(0);
		double currentPos = bottomPos + topPos;
		return currentPos;
		//gets the current postion of the forklift
	}
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
    	//setDefaultCommand (new ForkliftManual());
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

