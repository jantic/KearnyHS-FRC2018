package org.usfirst.frc.team1572.robot.subsystems;

import org.usfirst.frc.team1572.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Drivetrain extends Subsystem {
	
	TalonSRX leftMaster = RobotMap.leftDriveMaster;
	TalonSRX rightMaster = RobotMap.rightDriveMaster;
	Victor leftDrive1 = RobotMap.leftDrive1;
	Victor leftDrive2 = RobotMap.leftDrive2;
	Victor rightDrive1 = RobotMap.rightDrive1;
	Victor rightDrive2 = RobotMap.rightDrive2;
	
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
		System.out.println("leftMaster output percent " + leftMaster.getMotorOutputPercent());
		System.out.println("rightMaster output percent " + rightMaster.getMotorOutputPercent());
		leftDrive1.set(leftMaster.getMotorOutputPercent());
		leftDrive2.set(leftMaster.getMotorOutputPercent());
		rightDrive1.set(rightMaster.getMotorOutputPercent());
		rightDrive2.set(rightMaster.getMotorOutputPercent());
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
		//make a rpm drive for Victors
		/*System.out.println("leftMaster output percent " + leftMaster.getMotorOutputPercent());
		System.out.println("rightMaster output percent " + rightMaster.getMotorOutputPercent());
		leftDrive1.set(leftMaster.getMotorOutputPercent());
		leftDrive2.set(leftMaster.getMotorOutputPercent());
		rightDrive1.set(rightMaster.getMotorOutputPercent());
		rightDrive2.set(rightMaster.getMotorOutputPercent());*/
	}
	public void individualMotorDriveVoltage(double left, double right) {
		leftMaster.set(ControlMode.PercentOutput, left);
		rightMaster.set(ControlMode.PercentOutput, right);
		System.out.println("leftMaster output percent " + leftMaster.getMotorOutputPercent());
		System.out.println("rightMaster output percent " + rightMaster.getMotorOutputPercent());
		leftDrive1.set(leftMaster.getMotorOutputPercent());
		leftDrive2.set(leftMaster.getMotorOutputPercent());
		rightDrive1.set(rightMaster.getMotorOutputPercent());
		rightDrive2.set(rightMaster.getMotorOutputPercent());
	}
	public void individualMotorDriveRPM(double left, double right, double MaxRPM) {
		leftMaster.set(ControlMode.Velocity, left * MaxRPM);
		rightMaster.set(ControlMode.Velocity, right * MaxRPM);
		System.out.println("leftMaster output percent " + leftMaster.getMotorOutputPercent());
		System.out.println("rightMaster output percent " + rightMaster.getMotorOutputPercent());
		//make a RPM drive for Victors
		/*leftDrive1.set(leftMaster.getMotorOutputPercent());
		leftDrive2.set(leftMaster.getMotorOutputPercent());
		rightDrive1.set(rightMaster.getMotorOutputPercent());
		rightDrive2.set(rightMaster.getMotorOutputPercent());*/
	}

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

