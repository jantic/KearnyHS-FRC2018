package org.usfirst.frc.team1572.robot.subsystems;

import org.usfirst.frc.team1572.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Drivetrain extends Subsystem {
	
	TalonSRX leftMaster = RobotMap.leftDriveMaster;
	TalonSRX rightMaster = RobotMap.rightDriveMaster;
	TalonSRX leftTestMaster = RobotMap.leftTestDriveMaster;
	TalonSRX rightTestMaster = RobotMap.rightTestDriveMaster;
	Victor leftDrive = RobotMap.leftDrive;
	Victor rightDrive = RobotMap.rightDrive;
	Encoder leftEncoder = RobotMap.leftEncoder;
	Encoder rightEncoder = RobotMap.rightEncoder;
	double maxRPM = 3200;
	double leftPosition = 0;
	double rightPosition = 0;
	double encoderConverstion = 50; //set 360 to however many pulses it takes to go one inch
	double lastTimeLeft;
	double currentTimeLeft;
	double lastTimeRight;
	double currentTimeRight;
	
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
		leftTestMaster.set(ControlMode.PercentOutput, left);
		rightTestMaster.set(ControlMode.PercentOutput, right);
		//SmartDashboard.putNumber("leftEncoder", leftTestMaster.getSelectedSensorPosition(1));
		//SmartDashboard.putNumber("rightEncoder", rightTestMaster.getSelectedSensorPosition(1));
		SmartDashboard.putNumber("leftEncoder", GetLeftEncoderPos());
		SmartDashboard.putNumber("rightEncoder", GetRightEncoderPos());
		SmartDashboard.putNumber("leftEncoderVelocity", leftTestMaster.getSelectedSensorVelocity(1));
		SmartDashboard.putNumber("rightEncoderVelocity", rightTestMaster.getSelectedSensorVelocity(1));
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
	
	public double EncoderValue(EncoderType encoder) {
		if (encoder.getDeviceNumber() == 0) {
			return leftEncoder.getDistance();
		}
		
		else if (encoder.getDeviceNumber() == 1) {
			return rightEncoder.getDistance();
		}
		
		double averageDistance = leftEncoder.getDistance() + rightEncoder.getDistance();
		averageDistance /= 2;
		return averageDistance;
		
		}
	
	public double GetLeftEncoderPos () {
		double leftVelocity = 1 * leftTestMaster.getSelectedSensorVelocity(0);
		this.currentTimeLeft = Timer.getFPGATimestamp();
		double timeDifference = this.currentTimeLeft - this.lastTimeLeft;
		timeDifference *= 10; //100 ms
		double positionish = leftVelocity * timeDifference;
		this.leftPosition += positionish;
		this.lastTimeLeft = this.currentTimeLeft;
		return this.leftPosition;
	}
	
	public double GetRightEncoderPos () {
		double rightVelocity = rightTestMaster.getSelectedSensorVelocity(0);
		this.currentTimeRight = Timer.getFPGATimestamp();
		double timeDifference = this.currentTimeRight - this.lastTimeRight;
		timeDifference *= 10; //100 ms
		double positionish = rightVelocity * timeDifference;
		this.rightPosition += positionish;
		this.lastTimeRight = this.currentTimeRight;
		return this.rightPosition;
	}
	
	public void EncoderReset() {
		this.leftPosition = 0;
		this.rightPosition = 0;
		//rightEncoder.reset();
		//leftEncoder.reset();
	}

	public double EncoderToInches(double encoderValue) {
		double inches = encoderValue/this.encoderConverstion;
		return  inches;
	}

	public double InchesToEncoder(double inches) {
		double encoderValue = inches * this.encoderConverstion;
		return encoderValue;
	}
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

