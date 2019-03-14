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
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
public class PivotMove extends Command {
  double targetDistance;
	double topSpeed;
	double currentDistance;
  double distanceRemaining;
  Pivot pivotSub = Robot.pivot;
  public PivotMove(double targetDistance, double maxSpeed) {
    // Use requires() here to declare subsystem dependencies
    this.targetDistance = targetDistance;
    //System.out.println("target Distance " + this.targetDistance);
    this.topSpeed = maxSpeed;
    requires(pivotSub);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    this.currentDistance = pivotSub.GetPotPos();
    //System.out.println("command works" + " Pot Output" + this.currentDistance);
    double error = this.targetDistance - this.currentDistance;
    //SmartDashboard.putNumber("error", error);
    error/=Math.abs(this.targetDistance);
    error*= 1.15; //1.2 //1.3
    //error*= this.topSpeed;
    if (Math.abs(error) > this.topSpeed) {
      if (error > 0) {
        error = this.topSpeed;
      }
      else if (error < 0) {
        error = -this.topSpeed;
      }
    }
    //System.out.println("speed " + -error);
    this.pivotSub.MovePivot(-error);
    double currentDistance = this.currentDistance;
    double diff = (this.targetDistance - currentDistance);
    this.distanceRemaining = diff;
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    if(Math.abs(distanceRemaining) < 10) { //may need to reduce this number as this was orginally used for encoders
     System.out.println("distance remiainigng " + this.distanceRemaining);
      return true;
    }
      return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    //this.pivotSub.MovePivot(0);
    //this.pivotSub.PotReset();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
