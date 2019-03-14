/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.subsystems.PanelPusher;
import frc.robot.subsystems.Drivetrain;
import edu.wpi.first.wpilibj.command.Command;

public class PushForward extends Command {
  PanelPusher panelPush = Robot.panelPusher;
  Drivetrain drivetrain = Robot.drivetrain;
  public PushForward() {
    // Use requires() here to declare subsystem dependencies
    requires(panelPush);
    //requires(drivetrain);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    panelPush.PushThePanel();
    //drivetrain.arcadeDriveVoltage(0, 0.2, 0, 1);
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
  }
}
