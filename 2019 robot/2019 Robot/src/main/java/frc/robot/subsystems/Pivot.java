/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;
import frc.robot.commands.PivotManual;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.Timer;
import frc.robot.RobotMap;

/**
 * An example subsystem.  You can replace me with your own Subsystem.
 */
public class Pivot extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  TalonSRX master = RobotMap.pivotMotor;
  double position = 0;
  double encoderConverstion = 50;
  double lastTime;
  double currentTime;

  public void PotReset() {
    this.position = 0;
  }

  public double GetPotPos () {
		double rightVelocity = master.getSelectedSensorVelocity(0); //may need to change for pot
		this.currentTime = Timer.getFPGATimestamp();
		double timeDifference = this.currentTime - this.lastTime;
		timeDifference *= 10; //100 ms
		double positionish = rightVelocity * timeDifference;
		this.position += positionish;
		this.lastTime = this.currentTime;
		return this.position;
  }
  public void MovePivot(double y) {
    master.set(ControlMode.PercentOutput, y);
  }
  public void MoveUp () 
  {
    master.set(ControlMode.PercentOutput, .3);
    //PotReset();
  }
  public void MoveDown ()
  {
    master.set(ControlMode.PercentOutput, -.3);
    //PotReset();
  }
  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    setDefaultCommand(new PivotManual());
  }
}
