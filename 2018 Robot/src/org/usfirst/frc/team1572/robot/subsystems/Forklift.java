package org.usfirst.frc.team1572.robot.subsystems;

import org.usfirst.frc.team1572.robot.RobotMap;

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
	double bottomLowLimit = RobotMap.bottomLowLimit;
	double bottomHighLimit = RobotMap.bottomHighLimit;
	double topLowLimit = RobotMap.topLowLimit;
	double topHighLimit = RobotMap.topHighLimit;
	double x = 1;
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
	/*
	public double currentPosition() {
		double currentPosition = 0;
		return currentPosition;
	}
	*/
	double bottomPosition() {
		double bottomPosition = bottomForklift.getSelectedSensorPosition(0);
		SmartDashboard.putNumber("bottomPosition", bottomPosition);
		return bottomPosition;
	}
	double topPosition() {
		double topPosition = topForklift.getSelectedSensorPosition(0);
		SmartDashboard.putNumber("topPosition", topPosition);
		return topPosition;
	}
	void bottomToPosition(double position, double maxSpeed) {
		double difference = position - bottomPosition();
		double tolerance = 2;
		difference /= (bottomHighLimit / x);
		difference *= 1;
		difference *= maxSpeed;
		//if (position > bottomPosition()) {
			bottomForklift.set(ControlMode.PercentOutput, difference);
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
			topForklift.set(ControlMode.PercentOutput, difference);
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
	}
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

