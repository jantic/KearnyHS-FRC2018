/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team1572.robot;

import org.usfirst.frc.team1572.robot.commands.autonomous.AutoLine;
import org.usfirst.frc.team1572.robot.commands.autonomous.CenterAutoFullSwitch;
import org.usfirst.frc.team1572.robot.commands.autonomous.CenterAutoTwoBox;
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
import org.usfirst.frc.team1572.robot.commands.autonomous.autocommands.Testing;
import org.usfirst.frc.team1572.robot.commands.main.TeleopDrive;
import org.usfirst.frc.team1572.robot.subsystems.ClimbRelease;
import org.usfirst.frc.team1572.robot.subsystems.Climber;
import org.usfirst.frc.team1572.robot.subsystems.Drivetrain;
import org.usfirst.frc.team1572.robot.subsystems.Forklift;
import org.usfirst.frc.team1572.robot.subsystems.HeadingSubsystem;
import org.usfirst.frc.team1572.robot.subsystems.Intake;
import org.usfirst.frc.team1572.robot.subsystems.PlatformRelease;

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
	public static PlatformRelease platformRelease;
	public static ClimbRelease climbRelease;

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
		platformRelease = new PlatformRelease();
		climbRelease = new ClimbRelease();
		//m_chooser.addDefault("Default Auto", new ExampleCommand());
		// chooser.addObject("My Auto", new MyAutoCommand());
		chooser = new SendableChooser<Command>();
		chooser.addDefault("Auto line", new AutoLine());
		chooser.addDefault("Center auto 2 boxes", new CenterAutoTwoBox());
		chooser.addDefault("Center auto switch", new CenterAutoFullSwitch());
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
		chooser.addObject("testing", new Testing());
		
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
		if(colors.length() > 0) {
			ourSwitch = colors.charAt(0);
			String daSwitch = Character.toString(ourSwitch);
			SmartDashboard.putString("daSwitch", daSwitch);
			scale = colors.charAt(1);
			String daScale = Character.toString(scale);
			SmartDashboard.putString("daScale", daScale);
		}
		SmartDashboard.putString("game data", colors);
		autonomousCommand = chooser.getSelected();
		System.out.println(autonomousCommand.getName());
		//autonomousCommand.start();
		String autoSelected = chooser.getSelected().getName();
		SmartDashboard.putString("autoSelected", autoSelected);
		Command autonomousCommand2 = null;
		switch(autoSelected) {
		case "Testing":
			autonomousCommand2 = new Testing();
			break;
		case "AutoLine":
			autonomousCommand2 = new AutoLine();
			break;
		case "CenterAutoFullSwitch":
			autonomousCommand2 = new CenterAutoFullSwitch();
			break;
		case "CenterAutoTwoBox":
			autonomousCommand2 = new CenterAutoTwoBox();
			break;
		case "LeftAutoFullScale":
			autonomousCommand2 = new LeftAutoFullScale();
			break;
		case "LeftAutoFullSwitch":
			autonomousCommand2 = new LeftAutoFullSwitch();
			break;
		case "LeftAutoPreferScale":
			autonomousCommand2 = new LeftAutoPreferScale();
			break;
		case "LeftAutoPreferScaleCross":
			autonomousCommand2 = new LeftAutoPreferScaleCross();
			break;
		case "LeftAutoPreferSwitch":
			autonomousCommand2 = new LeftAutoPreferSwitch();
			break;
		case "LeftAutoPreferSwitchCross":
			autonomousCommand2 = new LeftAutoPreferSwitchCross();
			break;
		case "LeftAutoScale":
			autonomousCommand2 = new LeftAutoScale();
			break;
		case "LeftAutoSwitch":
			autonomousCommand2 = new LeftAutoSwitch();
			break;
		case "RightAutoFullScale":
			autonomousCommand2 = new RightAutoFullScale();
			break;
		case "RightAutoFullSwitch":
			autonomousCommand2 = new RightAutoFullSwitch();
			break;
		case "RightAutoPreferScale":
			autonomousCommand2 = new RightAutoPreferScale();
			break;
		case "RightAutoPrferScaleCross":
			autonomousCommand2 = new RightAutoPreferScaleCross();
			break;	
		case "RightAutoPreferSwitch":
			autonomousCommand2 = new RightAutoPreferSwitch();
			break;
		case "RightAutoPreferSwitchCross":
			autonomousCommand2 = new RightAutoPreferSwitchCross();
			break;
		case "RightAutoScale":
			autonomousCommand2 = new RightAutoScale();
			break;
		case "RightAutoSwitch":
			autonomousCommand2 = new RightAutoSwitch();
			break;
		}
		autonomousCommand2.start();
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
		SmartDashboard.putNumber("bot pos", forklift.bottomPosition());
		SmartDashboard.putNumber("top pos", forklift.topPosition());
		SmartDashboard.putNumber("position", forklift.getCurrentPos());
		SmartDashboard.putBoolean("switch activated", RobotMap.forkliftReset.get());
		//forklift.moveToPosition(30000, 1);
	}

	/**
	 * This function is called periodically during test mode.
	 */
	@Override
	public void testPeriodic() {
	}
}
