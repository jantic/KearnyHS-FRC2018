/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;
import frc.robot.JoystickController;
import frc.robot.Robot;
import frc.robot.subsystems.Drivetrain;
//import frc.robot.subsystems.EncoderType;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

/**
 * An example command.  You can replace me with your own command.
 */
public class DriveTelop extends Command {
  JoystickController mainJoystick = JoystickController.MAIN_JOYSTICK;
  JoystickController coPilotJoystick = JoystickController.COPILOT_JOYSTICK;
  Drivetrain drivetrain = Robot.drivetrain;
  public DriveTelop() {
    // Use requires() here to declare subsystem dependencies
    requires(Robot.drivetrain);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run//
  @Override
  protected void execute() {

      double mainX = mainJoystick.getLeftStickY();
      double mainY = mainJoystick.getRightStickX();// + 0.1875;
      System.out.println(mainY);
    	double overdrive = mainJoystick.getRightTrigger();
    	double coY = coPilotJoystick.getLeftStickY();
    	double coX = coPilotJoystick.getLeftStickX();
    	double normalSpeed = 0.75;
    	double normalTurn = 0.75;
    	double overSpeed = 1;
    	double overTurn = 0.9;
    	double coSpeed = 0.4;//.25
    	double coTurn = 0.4;//.25
    	if(Math.abs(coX) > 0.2 || Math.abs(coY) > 0.2) {
    		drivetrain.arcadeDriveVoltage(coY, coX, coTurn, coSpeed);
    	}
    	else if(overdrive > 0.5) {
    		drivetrain.arcadeDriveVoltage(mainX, mainY, overTurn, overSpeed);
    	}
    	else {
    		drivetrain.arcadeDriveVoltage(mainX, mainY, normalTurn, normalSpeed);
    	}
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
