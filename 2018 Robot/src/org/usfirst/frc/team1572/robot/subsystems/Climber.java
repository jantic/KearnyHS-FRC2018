package org.usfirst.frc.team1572.robot.subsystems;

import org.usfirst.frc.team1572.robot.RobotMap;

import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Climber extends Subsystem {
	
	Victor leftClimber = RobotMap.leftClimb;
	Victor rightClimber = RobotMap.rightClimb;
	
	public void climb(double speed) {
		//leftClimber.set(speed);
		//rightClimber.set(speed);
		//sets the speed of the climber
		//System.out.println(speed);
	}

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

