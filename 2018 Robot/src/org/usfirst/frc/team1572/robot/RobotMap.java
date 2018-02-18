/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team1572.robot;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Solenoid;
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
	public static VictorSPX leftDriveSlave;
	public static TalonSRX rightDriveMaster;
	public static VictorSPX rightDriveSlave;
	public static TalonSRX bottomForklift;
	public static TalonSRX topForklift;
	public static TalonSRX leftTestDriveMaster;
	public static TalonSRX rightTestDriveMaster;
	public static VictorSPX leftTestDriveSlave;
	public static VictorSPX rightTestDriveSlave;
	public static double bottomLowLimit = 20000;
	public static double bottomHighLimit = 80000;
	public static double topLowLimit = 1200;
	public static double topHighLimit = 29000;
	public static double totalTravel = (bottomHighLimit - bottomLowLimit) + (topHighLimit - topLowLimit);
	public static Spark leftIntake;
	public static Spark rightIntake;
	public static Victor leftClimb;
	public static Victor rightClimb;
	//Victors for practice robot
	public static Victor leftDrive;
	public static Victor rightDrive;
	public static Encoder leftEncoder;
	public static Encoder rightEncoder;
	public static Relay platform;
	public static Relay climbReleaser;
	public static Solenoid climbRelease;
	public static Solenoid platformRelease;
	public static DigitalInput forkliftReset;
	
	public static void init() {
		leftDriveMaster = new TalonSRX(1);
		leftDriveSlave = new VictorSPX(2);
		rightDriveMaster = new TalonSRX(3);
		rightDriveSlave = new VictorSPX(4);
		bottomForklift = new TalonSRX(5);
		topForklift = new TalonSRX(6);
		leftTestDriveMaster = new TalonSRX(7);
		rightTestDriveMaster = new TalonSRX(8);
		leftTestDriveSlave = new VictorSPX(10);
		rightTestDriveSlave = new VictorSPX(11);
		platform = new Relay(0);
		climbReleaser = new Relay(1);
		leftIntake = new Spark(2);
		rightIntake = new Spark(3);
		leftClimb = new Victor(0);
		leftClimb.setInverted(true);
		rightClimb = new Victor(1);
		leftDrive = new Victor(8);
		leftDrive.setInverted(true);
		rightDrive = new Victor(9);
		rightDrive.setInverted(true);
		leftDriveMaster.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 0);
		leftDriveMaster.setSelectedSensorPosition(0, 0, 0);
		leftDriveMaster.setInverted(true);
		leftDriveSlave.setInverted(true);
		leftDriveSlave.follow(leftDriveMaster);
		rightDriveMaster.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 0);
		rightDriveMaster.setSelectedSensorPosition(0, 0, 0);
		rightDriveSlave.follow(rightDriveMaster);
		leftTestDriveMaster.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 0);
		leftTestDriveMaster.setSelectedSensorPosition(0, 0, 0);
		leftTestDriveMaster.setInverted(true);
		leftTestDriveMaster.setNeutralMode(NeutralMode.Brake);
		leftTestDriveSlave.setInverted(true);
		leftTestDriveSlave.setNeutralMode(NeutralMode.Brake);
		leftTestDriveSlave.follow(leftTestDriveMaster);
		rightTestDriveMaster.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 0);
		rightTestDriveMaster.setNeutralMode(NeutralMode.Brake);
		rightTestDriveMaster.setSelectedSensorPosition(0, 0, 0);
		rightTestDriveSlave.setNeutralMode(NeutralMode.Brake);
		rightTestDriveSlave.follow(rightTestDriveMaster);
		//add PIDF configurations for drivetrain speed control
		//add setup for encoders
		bottomForklift.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 0);
		bottomForklift.setSelectedSensorPosition(0, 0, 0);
		topForklift.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Absolute, 0, 0);
		topForklift.setSelectedSensorPosition(0, 0, 0);
		climbRelease = new Solenoid(0);
		platformRelease = new Solenoid(1);
		forkliftReset = new DigitalInput(0);
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
