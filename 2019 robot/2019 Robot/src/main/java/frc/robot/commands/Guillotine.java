/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.subsystems.Elevator;
import frc.robot.JoystickController;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.command.Scheduler;

public class Guillotine extends Command {
  JoystickController coPilotJoystick = JoystickController.COPILOT_JOYSTICK;
  TalonSRX elevator = Robot.elevator;
  boolean endCycle = false;
  public boolean released;
  public Guillotine() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.elevatorSub);
    
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }
  //57 inches with revits
  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    //double elevatorUpSpeed = coPilotJoystick.;
   
  double valueOfPov = coPilotJoystick.GetPov();
   SmartDashboard.putNumber("valueOfPov ", valueOfPov);
   if (valueOfPov == 0 || valueOfPov == 315 || valueOfPov == 45) {
    elevator.set(ControlMode.PercentOutput, -.4);
    this.released = true;
   }
   else if (valueOfPov == 180 || valueOfPov == 135 || valueOfPov == 215) {
    elevator.set(ControlMode.PercentOutput, .4);
    this.released = true;
    }
   else if (this.released == true) {
      elevator.set(ControlMode.PercentOutput, 0);
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
