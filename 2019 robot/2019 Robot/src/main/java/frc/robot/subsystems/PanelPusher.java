/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import frc.robot.RobotMap;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.Servo;

/**
 * An example subsystem.  You can replace me with your own Subsystem.
 */
public class PanelPusher extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  Servo panelPush1 = RobotMap.dishPush1;
  Servo panelPush2 = RobotMap.dishPush2;
public void PushThePanel() {
 panelPush1.set(1);
 panelPush2.set(1);
 //panelPush1.set
  //panelPush1.setAngle(90);
  //panelPush2.setAngle(9);
}
public void ResetPusher() {
panelPush1.set(.4);
 panelPush2.set(.4);
 // panelPush1.setAngle(-180);
 // panelPush2.setAngle(-180);
}
  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
