/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.subsystems.Pivot;
import frc.robot.JoystickController;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.command.Scheduler;

public class PivotManual extends Command {
  JoystickController coPilotJoystick = JoystickController.COPILOT_JOYSTICK;
  TalonSRX pivotM = Robot.pivotMotor;
  Pivot pivotSub = Robot.pivot;
  boolean endCycle = false;
  public boolean released;
  public PivotManual() {
    // Use requires() here to declare subsystem dependencies
   requires(pivotSub);
  }

  // Called just before this Command runs the first time

// Called just before this Command runs the first time
@Override
protected void initialize() {
}
//57 inches with revits
// Called repeatedly when this Command is scheduled to run
@Override
protected void execute() {
  //double elevatorUpSpeed = coPilotJoystick.;
 
double valueOfStick = coPilotJoystick.getRightStickY() + 0.015625;
 //System.out.println("valueOfStick " + valueOfStick);
 if (valueOfStick > .10) {
  pivotM.set(ControlMode.PercentOutput, -.4);
  this.released = true;
 }
 else if (valueOfStick < -.10) {
  pivotM.set(ControlMode.PercentOutput, .4);
  this.released = true;
  }
 else if (this.released == true) {
    pivotM.set(ControlMode.PercentOutput, 0); //0.15
   // System.out.println("here");
    this.released = false;
  }
  //System.out.println(released);
}

// Make this return true when this Command no longer needs to run execute()
@Override
protected boolean isFinished() {
  return false;
}

// Called once after isFinished returns true
@Override
protected void end() {
}


// Called when another command which requires one or more of the same
// subsystems is scheduled to run
@Override
protected void interrupted() {
  //Scheduler.getInstance().add(new Guillotine());
}
}