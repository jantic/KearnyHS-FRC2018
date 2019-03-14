/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

/**
 * An example subsystem.  You can replace me with your own Subsystem.
 */
public class Drivetrain extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
	VictorSPX leftMaster = RobotMap.leftDriveMaster;
	VictorSPX rightMaster = RobotMap.rightDriveMaster;
	VictorSPX leftMaster2 = RobotMap.leftDriveSlave;
	VictorSPX rightMaster2 = RobotMap.rightDriveSlave;

	//TestDrive
	//public static TalonSRX TestLeftM = RobotMap.TestLeftMaster;
  //public static TalonSRX TestRightM = RobotMap.TestRightMaster;

 
  
	public void arcadeDriveVoltage(double x, double y, double maxX, double maxY) {
		x *= maxX;
		y *= maxY;
		x *= -1;
		double left = y + x;
		double right = y - x;
		if(Math.abs(left) > 1) {
			left /= Math.abs(left);
			right /= Math.abs(left);
		}
		else if(Math.abs(right) > 1) {
			left /= Math.abs(right);
			right /= Math.abs(right);
		}
		leftMaster.set(ControlMode.PercentOutput, left);
		rightMaster.set(ControlMode.PercentOutput, right);
		leftMaster2.set(ControlMode.PercentOutput, left);
		rightMaster2.set(ControlMode.PercentOutput, right);
		//TestLeftM.set(ControlMode.PercentOutput, left);
		//TestRightM.set(ControlMode.PercentOutput, right);
	}
  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
	}

	public void dontCrash(boolean aboutToCrash) {
		if (aboutToCrash) {
			dont();
		}
	}

	public void dont() {
		//just don't
	}
}
