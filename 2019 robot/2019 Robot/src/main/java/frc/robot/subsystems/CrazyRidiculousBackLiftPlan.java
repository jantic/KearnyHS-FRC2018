/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import edu.wpi.first.wpilibj.Victor;
/**
 * Add your docs here.
 */
public class CrazyRidiculousBackLiftPlan extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  public static Victor Master = RobotMap.crazyRidiculousBackLiftPlanMaster;
  public static Victor rollers = RobotMap.crazyRidiculousBackLiftPlanSlave;

  public void Up(double y){ // moves robot up and rollers forward
    Master.set(y);
    rollers.set(.25);
  }

  public void Down(double y){ // lift moves back up and rollers stop
    Master.set(y);
    rollers.set(0);
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
