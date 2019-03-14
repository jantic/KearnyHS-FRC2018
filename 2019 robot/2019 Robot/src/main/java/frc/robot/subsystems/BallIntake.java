/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.JoystickController;
/**
 * An example subsystem.  You can replace me with your own Subsystem.
 */
public class BallIntake extends Subsystem 
{
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  VictorSPX master = RobotMap.ballIntakeMotor;

  public void Suck()  {
    master.set(ControlMode.PercentOutput, -.95);
  }

  public void Blow() {
    master.set(ControlMode.PercentOutput, .75);//.75
  }
  
  public void stop() {
      master.set(ControlMode.PercentOutput, 0);
  }

  public void Move(double y) {
    master.set(ControlMode.PercentOutput, y);
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
