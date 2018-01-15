package org.usfirst.frc.team1572.robot.subsystems;

import org.usfirst.frc.team1572.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Drivetrain extends Subsystem {
	
	TalonSRX leftMaster = RobotMap.leftDriveMaster;
	TalonSRX rightMaster = RobotMap.rightDriveMaster;
	Victor leftDrive = RobotMap.leftDrive;
	Victor rightDrive = RobotMap.rightDrive;
	Encoder leftEncoder = RobotMap.leftEncoder;
	Encoder rightEncoder = RobotMap.rightEncoder;
	double maxRPM = 3200;
	
	public void arcadeDriveVoltage(double x, double y, double maxX, double maxY) {
		x *= maxX;
		y *= maxY;
		x *= -1;
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
		//leftMaster.set(ControlMode.PercentOutput, left);
		//rightMaster.set(ControlMode.PercentOutput, right);
		//System.out.println("leftMaster output percent " + leftMaster.getMotorOutputPercent());
		//System.out.println("rightMaster output percent " + rightMaster.getMotorOutputPercent());
		leftDrive.set(left);
		rightDrive.set(right);
	}
	public void arcadeDriveRPM(double x, double y, double maxX, double maxY) {
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
		//make a rpm drive for Victors
		/*System.out.println("leftMaster output percent " + leftMaster.getMotorOutputPercent());
		System.out.println("rightMaster output percent " + rightMaster.getMotorOutputPercent());
		leftDrive.set(leftMaster.getMotorOutputPercent());
		rightDrive.set(rightMaster.getMotorOutputPercent());*/
	}
	public void individualMotorDriveVoltage(double left, double right) {
		leftMaster.set(ControlMode.PercentOutput, left);
		rightMaster.set(ControlMode.PercentOutput, right);
		System.out.println("leftMaster output percent " + leftMaster.getMotorOutputPercent());
		System.out.println("rightMaster output percent " + rightMaster.getMotorOutputPercent());
		leftDrive.set(left);
		rightDrive.set(right);
	}
	public void individualMotorDriveRPM(double left, double right, double MaxRPM) {
		leftMaster.set(ControlMode.Velocity, left * MaxRPM);
		rightMaster.set(ControlMode.Velocity, right * MaxRPM);
		System.out.println("leftMaster output percent " + leftMaster.getMotorOutputPercent());
		System.out.println("rightMaster output percent " + rightMaster.getMotorOutputPercent());
		//make a RPM drive for Victors
		/*leftDrive.set(leftMaster.getMotorOutputPercent());
		rightDrive.set(rightMaster.getMotorOutputPercent());*/
	}
	
	public void readLeftEncoder() {
		 System.out.println(leftEncoder.getDistance());
	}
	
	public void readRightEncoder() {
		System.out.println(rightEncoder.getDistance());
	}
		
	public void readAverageEncoder() {
		double averageDistance = leftEncoder.getDistance() + rightEncoder.getDistance();
		averageDistance /= 2;
		System.out.println(averageDistance);
	}
	
	public double EncoderValue(boolean left, boolean right, boolean average) {
		if (left) {
			return leftEncoder.getDistance();
		}
		
		else if (right) {
			return rightEncoder.getDistance();
		}
		
		double averageDistance = leftEncoder.getDistance() + rightEncoder.getDistance();
		averageDistance /= 2;
		return averageDistance;
		
		}
	
	public void EncoderResest() {
		rightEncoder.reset();
		leftEncoder.reset();
	}
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

