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
	//sets devices for drivetrain
	double maxRPM = 3200;
	double leftPosition = 0;
	double rightPosition = 0;
	double encoderConverstion = 50; //set 360 to however many pulses it takes to go one inch
	double lastTimeLeft;
	double currentTimeLeft;
	double lastTimeRight;
	double currentTimeRight;
	//Variables for the things
							//x direction, y direction, turn max speed, forward max speed
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
		leftMaster.set(ControlMode.PercentOutput, left);
		rightMaster.set(ControlMode.PercentOutput, right);
		//leftTestMaster.set(ControlMode.PercentOutput, left);
		//rightTestMaster.set(ControlMode.PercentOutput, right);
		//controls the direction of each side based in percentage 
		
		//SmartDashboard.putNumber("leftEncoder", leftTestMaster.getSelectedSensorPosition(1));
		//SmartDashboard.putNumber("rightEncoder", rightTestMaster.getSelectedSensorPosition(1));
		SmartDashboard.putNumber("leftEncoder", GetLeftEncoderPos());
		SmartDashboard.putNumber("rightEncoder", GetRightEncoderPos());
		SmartDashboard.putNumber("leftEncoderVelocity", leftMaster.getSelectedSensorVelocity(1));
		SmartDashboard.putNumber("rightEncoderVelocity", rightMaster.getSelectedSensorVelocity(1));
		//System.out.println("leftMaster output percent " + leftMaster.getMotorOutputPercent());
		//System.out.println("rightMaster output percent " + rightMaster.getMotorOutputPercent());
		leftDrive.set(left);
		rightDrive.set(right);
	}
						//x direction, y direction, turn max speed, forward max speed
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
		//sets the speed of each side based on RPM
		
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
		//directly sets the speed of side in percent
	}
	public void individualMotorDriveRPM(double left, double right, double MaxRPM) {
		leftMaster.set(ControlMode.Velocity, left * MaxRPM);
		rightMaster.set(ControlMode.Velocity, right * MaxRPM);
		System.out.println("leftMaster output percent " + leftMaster.getMotorOutputPercent());
		System.out.println("rightMaster output percent " + rightMaster.getMotorOutputPercent());
		//directly sets the speed of side in RPM
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
	//Gets the encoder values ^
	
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
		//sets the encoder to use based on enum
		}
	
	public double GetLeftEncoderPos () {
		double leftVelocity = 1 * leftMaster.getSelectedSensorVelocity(0);
		this.currentTimeLeft = Timer.getFPGATimestamp();
		double timeDifference = this.currentTimeLeft - this.lastTimeLeft;
		timeDifference *= 10; //100 ms
		double positionish = leftVelocity * timeDifference;
		this.leftPosition += positionish;
		this.lastTimeLeft = this.currentTimeLeft;
		return this.leftPosition;
		//not going to lie, don't know what this does. Gets the left Encoder Position?
	}
	
	public double GetRightEncoderPos () {
		double rightVelocity = rightMaster.getSelectedSensorVelocity(0);
		this.currentTimeRight = Timer.getFPGATimestamp();
		double timeDifference = this.currentTimeRight - this.lastTimeRight;
		timeDifference *= 10; //100 ms
		double positionish = rightVelocity * timeDifference;
		this.rightPosition += positionish;
		this.lastTimeRight = this.currentTimeRight;
		return this.rightPosition;
		//not going to lie, don't know what this does. Gets the right Encoder Position?
	}
	
	public void EncoderReset() {
		this.leftPosition = 0;
		this.rightPosition = 0;
		//resets the postions;
	}

	public double EncoderToInches(double encoderValue) {
		double inches = encoderValue/this.encoderConverstion;
		return  inches;
		//Converts encoder into inches
	}

	public double InchesToEncoder(double inches) {
		double encoderValue = inches * this.encoderConverstion;
		return encoderValue;
		//takes inches and converts into encoder pluses
	}
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

