package org.usfirst.frc.team1572.robot.subsystems;

import org.usfirst.frc.team1572.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Drivetrain extends Subsystem {
	
	TalonSRX leftMaster = RobotMap.leftDriveMaster;
	TalonSRX rightMaster = RobotMap.rightDriveMaster;
	
	public void arcadeDriveVoltage(double x, double y, double maxX, double maxY) {
		x *= maxX;
		y *= maxY;
		double left = y + x;
		double right = y - x;
		if(Math.abs(left) > 1) {
			left /= Math.abs(left);
			right /= Math.abs(left);
		}
		else if(Math.abs(right) > 1) {
			left /= Math.abs(right);
			right /= Math.abs(right);
		}
		leftMaster.set(ControlMode.PercentOutput, left);
		rightMaster.set(ControlMode.PercentOutput, right);
	}
	public void arcadeDriveRPM(double x, double y, double maxX, double maxY, double maxRPM) {
		x *= maxX;
		y *= maxY;
		double left = y + x;
		double right = y - x;
		if(Math.abs(left) > 1) {
			left /= Math.abs(left);
			right /= Math.abs(left);
		}
		else if(Math.abs(right) > 1) {
			left /= Math.abs(right);
			right /= Math.abs(right);
		}
		left *= maxRPM;
		right *= maxRPM;
		leftMaster.set(ControlMode.Velocity, left);
		rightMaster.set(ControlMode.Velocity, right);
	}

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

