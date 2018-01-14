/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team1572.robot;

import org.usfirst.frc.team1572.robot.commands.autonomous.AutoLine;
import org.usfirst.frc.team1572.robot.commands.autonomous.LeftAutoFullScale;
import org.usfirst.frc.team1572.robot.commands.autonomous.LeftAutoFullSwitch;
import org.usfirst.frc.team1572.robot.commands.autonomous.LeftAutoPreferScale;
import org.usfirst.frc.team1572.robot.commands.autonomous.LeftAutoPreferScaleCross;
import org.usfirst.frc.team1572.robot.commands.autonomous.LeftAutoPreferSwitch;
import org.usfirst.frc.team1572.robot.commands.autonomous.LeftAutoPreferSwitchCross;
import org.usfirst.frc.team1572.robot.commands.autonomous.LeftAutoScale;
import org.usfirst.frc.team1572.robot.commands.autonomous.LeftAutoSwitch;
import org.usfirst.frc.team1572.robot.commands.autonomous.RightAutoFullScale;
import org.usfirst.frc.team1572.robot.commands.autonomous.RightAutoFullSwitch;
import org.usfirst.frc.team1572.robot.commands.autonomous.RightAutoPreferScale;
import org.usfirst.frc.team1572.robot.commands.autonomous.RightAutoPreferScaleCross;
import org.usfirst.frc.team1572.robot.commands.autonomous.RightAutoPreferSwitch;
import org.usfirst.frc.team1572.robot.commands.autonomous.RightAutoPreferSwitchCross;
import org.usfirst.frc.team1572.robot.commands.autonomous.RightAutoScale;
import org.usfirst.frc.team1572.robot.commands.autonomous.RightAutoSwitch;
import org.usfirst.frc.team1572.robot.commands.main.TeleopDrive;
import org.usfirst.frc.team1572.robot.subsystems.Climber;
import org.usfirst.frc.team1572.robot.subsystems.Drivetrain;
import org.usfirst.frc.team1572.robot.subsystems.Forklift;
import org.usfirst.frc.team1572.robot.subsystems.HeadingSubsystem;
import org.usfirst.frc.team1572.robot.subsystems.Intake;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.properties file in the
 * project.
 */
public class Robot extends TimedRobot {
	//public static final ExampleSubsystem kExampleSubsystem
	//		= new ExampleSubsystem();
	//public static OI oi;
	public static char ourSwitch;
	public static char scale;
	public static Drivetrain drivetrain;
	public static Forklift forklift;
	public static Intake intake;
	public static Climber climber;
	public static HeadingSubsystem headingSubsystem;

	Command autonomousCommand;
	SendableChooser<Command> chooser;

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		//oi = new OI();
		RobotMap.init();
		drivetrain = new Drivetrain();
		forklift = new Forklift();
		intake = new Intake();
		climber = new Climber();
		headingSubsystem = new HeadingSubsystem();
		//m_chooser.addDefault("Default Auto", new ExampleCommand());
		// chooser.addObject("My Auto", new MyAutoCommand());
		chooser = new SendableChooser<Command>();
		chooser.addDefault("Auto line", new AutoLine());
		chooser.addObject("Left auto switch", new LeftAutoSwitch());
		chooser.addObject("Right auto switch", new RightAutoSwitch());
		chooser.addObject("Left auto scale", new LeftAutoScale());
		chooser.addObject("Right auto scale", new RightAutoScale());
		chooser.addObject("Left auto prefer switch", new LeftAutoPreferSwitch());
		chooser.addObject("Right auto prefer switch", new RightAutoPreferSwitch());
		chooser.addObject("Left auto prefer scale", new LeftAutoPreferScale());
		chooser.addObject("Right auto prefer scale", new RightAutoPreferScale());
		chooser.addObject("Left auto prefer switch cross", new LeftAutoPreferSwitchCross());
		chooser.addObject("Right auto prefer switch cross", new RightAutoPreferSwitchCross());
		chooser.addObject("Left auto prefer scale cross", new LeftAutoPreferScaleCross());
		chooser.addObject("Right auto prefer scale cross", new RightAutoPreferScaleCross());
		chooser.addObject("Left auto full switch", new LeftAutoFullSwitch());
		chooser.addObject("Right auto full switch", new RightAutoFullSwitch());
		chooser.addObject("Left auto full scale", new LeftAutoFullScale());
		chooser.addObject("Right auto full scale", new RightAutoFullScale());
		SmartDashboard.putData("Auto chooser", chooser);
	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	@Override
	public void disabledInit() {

	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString code to get the auto name from the text box below the Gyro
	 *
	 * <p>You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons
	 * to the switch structure below with additional strings & commands.
	 */
	@Override
	public void autonomousInit() {
		
		String colors;
		colors = DriverStation.getInstance().getGameSpecificMessage();
		ourSwitch = colors.charAt(0);
		scale = colors.charAt(1);
		autonomousCommand = chooser.getSelected();
		System.out.println(autonomousCommand.getName());
		autonomousCommand.start();
		/*
		 * String autoSelected = SmartDashboard.getString("Auto Selector",
		 * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
		 * = new MyAutoCommand(); break; case "Default Auto": default:
		 * autonomousCommand = new ExampleCommand(); break; }
		 */

		// schedule the autonomous command (example)
	}

	/**
	 * This function is called periodically during autonomous.
	 */
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		if (autonomousCommand != null) {
			autonomousCommand.cancel();
		}
		Scheduler.getInstance().add(new TeleopDrive());
	}

	/**
	 * This function is called periodically during operator control.
	 */
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This function is called periodically during test mode.
	 */
	@Override
	public void testPeriodic() {
	}
}
