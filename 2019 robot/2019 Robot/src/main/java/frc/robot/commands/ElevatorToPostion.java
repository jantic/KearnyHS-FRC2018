/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.RobotMap;
import edu.wpi.first.wpilibj.command.Scheduler;
import frc.robot.Robot;
import frc.robot.subsystems.Elevator;
//import frc.robot.subsystems.EncoderType;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
public class ElevatorToPostion extends Command {
    double targetDistance;
    double topSpeed;
    double currentDistance;
    double distanceRemaining;
    Elevator elevatorSub = Robot.elevatorSub;
    DigitalInput lSwitch = RobotMap.limitSwitch;
    public ElevatorToPostion(double targetDistance, double maxSpeed) {
      // Use requires() here to declare subsystem dependencies
      this.targetDistance = targetDistance;
      //System.out.println("target Distance " + this.targetDistance);
      this.topSpeed = maxSpeed;
      requires(elevatorSub);
    }
  
    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
    }
  
    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
      this.currentDistance = elevatorSub.GetEncoderPos();
     // System.out.println("command works" + " Encoder output " + this.currentDistance);
      double error = this.targetDistance - this.currentDistance;
      //SmartDashboard.putNumber("error", error);
      error/=Math.abs(this.targetDistance);
      error*= 1.3; 
      //error*= this.topSpeed;
      if (Math.abs(error) > this.topSpeed) {
        if (error > 0) {
          error = this.topSpeed;
        }
        else if (error < 0) {
          error = -this.topSpeed;
        }
      }
      //System.out.println("speed " + error);
      this.elevatorSub.MoveElevator(error);
      double currentDistance = this.currentDistance;
      double diff = (this.targetDistance - currentDistance);
      this.distanceRemaining = diff;

      if(lSwitch.get()) {
        elevatorSub.EncoderReset(1);
      }
    }
  
    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
      if(Math.abs(distanceRemaining) < 40) { //reduce to 20 or 10
        //System.out.println("distance remiainigng " + this.distanceRemaining);
        return true;
      }
      /*else if(lSwitch.get()) {
        elevatorSub.EncoderReset(1);
        System.out.println("switch");
        //return true;
      }*/
        return false;
    }
  
    // Called once after isFinished returns true
    @Override
    protected void end() {
      this.elevatorSub.MoveElevator(0);
      //Scheduler.getInstance().add(new Guillotine());
    }
  
    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    @Override
    protected void interrupted() {
     // Scheduler.getInstance().add(new Guillotine());
    }
    
  }
  