/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.subsystems.CrazyRidiculousBackLiftPlan;
import frc.robot.Robot;

public class JumpToPlatform extends Command {
  public JumpToPlatform() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.crazyRidiculousBackLiftPlan);
    requires(Robot.elevatorSub);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    Robot.crazyRidiculousBackLiftPlan.Up(.25);
    Robot.elevatorSub.MoveElevator(.25);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
     //Robot.elevatorSub.MoveElevator(0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
