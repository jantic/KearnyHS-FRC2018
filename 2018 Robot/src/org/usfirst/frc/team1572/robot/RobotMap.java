/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team1572.robot;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.Victor;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	
	public static TalonSRX leftDriveMaster;
	public static TalonSRX leftDriveSlave;
	public static TalonSRX rightDriveMaster;
	public static TalonSRX rightDriveSlave;
	public static TalonSRX bottomForklift;
	public static TalonSRX topForklift;
	public static double bottomLowLimit = 0;
	public static double bottomHighLimit = 0;
	public static double topLowLimit = 0;
	public static double topHighLimit = 0;
	public static Spark intake;
	public static Victor climb;
	//Victors for practice robot
	public static Victor leftDrive;
	public static Victor rightDrive;
	public static Encoder leftEncoder;
	public static Encoder rightEncoder;
	
	public static void init() {
		leftDriveMaster = new TalonSRX(1);
		leftDriveSlave = new TalonSRX(2);
		rightDriveMaster = new TalonSRX(3);
		rightDriveSlave = new TalonSRX(4);
		bottomForklift = new TalonSRX(5);
		topForklift = new TalonSRX(6);
		intake = new Spark(2);
		climb = new Victor(1);
		leftDrive = new Victor(8);
		leftDrive.setInverted(true);
		rightDrive = new Victor(9);
		rightDrive.setInverted(true);
		leftDriveMaster.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Absolute, 0, 0);
		leftDriveSlave.follow(leftDriveMaster);
		rightDriveMaster.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Absolute, 0, 0);
		rightDriveSlave.follow(rightDriveMaster);
		//add PIDF configurations for drivetrain speed control
		//add setup for encoders
		bottomForklift.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Absolute, 0, 0);
		topForklift.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Absolute, 0, 0);
	}
	
	// For example to map the left and right motors, you could define the
	// following variables to use with your drivetrain subsystem.
	// public static int leftMotor = 1;
	// public static int rightMotor = 2;

	// If you are using multiple modules, make sure to define both the port
	// number and the module. For example you with a rangefinder:
	// public static int rangefinderPort = 1;
	// public static int rangefinderModule = 1;
}
