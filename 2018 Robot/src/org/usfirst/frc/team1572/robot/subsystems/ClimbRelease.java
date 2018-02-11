package org.usfirst.frc.team1572.robot.subsystems;

import org.usfirst.frc.team1572.robot.RobotMap;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class ClimbRelease extends Subsystem {

	Relay climbReleaser = RobotMap.climbReleaser;
	Solenoid climbRelease = RobotMap.climbRelease;
	
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	public void release() {
		climbReleaser.set(Relay.Value.kOn);
		climbRelease.set(true);
		//opens relay
	}
	public void unrelease() {
		climbReleaser.set(Relay.Value.kOff);
		climbRelease.set(false);
		//closes relay
	}
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

