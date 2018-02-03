package org.usfirst.frc.team1572.robot.subsystems;

import org.usfirst.frc.team1572.robot.RobotMap;

import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class PlatformRelease extends Subsystem {

	Relay platform = RobotMap.platform;
	
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	public void deploy() {
		platform.set(Relay.Value.kOn);
		//opens relay
	}
	public void undeploy() {
		platform.set(Relay.Value.kOff);
		//closes relay
	}
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

