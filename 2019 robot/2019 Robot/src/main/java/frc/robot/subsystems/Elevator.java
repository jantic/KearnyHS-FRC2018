/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;


import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.Timer;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.commands.Guillotine;

/**
 * An example subsystem.  You can replace me with your own Subsystem.
 */
public class Elevator extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  Encoder encoder = RobotMap.elevatorEncoder;
  TalonSRX elevator = Robot.elevator;
  double position = 0;
  double level1 = 2;
  double level2 = 5;
  double level3 = 8;
  double encoderConverstion = 50;
  double lastTime;
  double currentTime;

  public void EncoderReset() {
    this.position = 0;
  }

  public void EncoderReset(double resetTo) {
    this.position = resetTo;
  }

  public double GetEncoderPos () {
		double rightVelocity = elevator.getSelectedSensorVelocity(0);
		this.currentTime = Timer.getFPGATimestamp();
		double timeDifference = this.currentTime - this.lastTime;
		timeDifference *= 10; //100 ms
		double positionish = rightVelocity * timeDifference;
		this.position += positionish;
		this.lastTime = this.currentTime;
		return this.position;
  }
  
  public double InchesToEncoder(double inches) {
		double encoderValue = inches * this.encoderConverstion;
		return encoderValue;
  }
  
  public void MoveElevator(double y) {
    elevator.set(ControlMode.PercentOutput, y);
  }
	
  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    setDefaultCommand(new Guillotine());
  }
}