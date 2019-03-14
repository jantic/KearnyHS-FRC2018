/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
package frc.robot;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import edu.wpi.first.wpilibj.DigitalInput;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.Servo;
import frc.robot.subsystems.Elevator;



/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */


public class RobotMap {

  public static VictorSPX leftDriveMaster = new VictorSPX(1); //ID Completed
  public static VictorSPX leftDriveSlave = new VictorSPX(2);  //ID Completed
  public static VictorSPX rightDriveMaster = new VictorSPX(3);//ID Completed
  public static VictorSPX rightDriveSlave = new VictorSPX(4); //ID Completed
  public static TalonSRX elevator = new TalonSRX(5);          //ID Completed
  public static VictorSPX elevatorSlave = new VictorSPX(6);   //ID Completed
  public static TalonSRX pivotMotor = new TalonSRX(7);       //change this value to id of talon (7)
  public static VictorSPX ballIntakeMotor = new VictorSPX(8);//change this value to id of victor (8)
  public static Encoder elevatorEncoder;
  public static Servo dishPush1 = new Servo(0);
  public static Servo dishPush2 = new Servo(1);
  public static DigitalInput limitSwitch = new DigitalInput(0);
  public static DigitalInput backLiftBot = new DigitalInput(1); //add this to FinishJump and JumpToPlatform
  public static DigitalInput backLiftTop = new DigitalInput(2); //add this to FinishJump and JumpToPlatform
  public static Victor crazyRidiculousBackLiftPlanMaster = new Victor(2);
  public static Victor crazyRidiculousBackLiftPlanSlave = new Victor(3);
  // For example to map the left and right motors, you could define the
  // following variables to use with your drivetrain subsystem.
  // public static int leftMotor = 1;
  // public static int rightMotor = 2;

  // If you are using multiple modules, make sure to define both the port
  // number and the module. For example you with a rangefinder:
  // public static int rangefinderPort = 1;
  // public static int rangefinderModule = 1;

}
