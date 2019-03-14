/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Elevator;
import frc.robot.subsystems.BallIntake;
import frc.robot.subsystems.CrazyRidiculousBackLiftPlan;
import frc.robot.subsystems.Pivot;
//import jdk.javadoc.internal.doclets.toolkit.util.DocFinder.Output;
import frc.robot.subsystems.PanelPusher;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import frc.robot.commands.ElevatorToPostion;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.DriveTelop;
import frc.robot.commands.Guillotine;
import frc.robot.commands.PivotDown;
import frc.robot.commands.PivotMove;
import frc.robot.commands.PivotMoveAndReset;
import frc.robot.subsystems.ExampleSubsystem;
import frc.robot.commands.GriffinsIntakeSpecialAttack;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import edu.wpi.first.cameraserver.CameraServer; 
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.cscore.UsbCamera;
import org.opencv.videoio.VideoCapture;
import org.opencv.core.Mat;
import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.UsbCamera;
import org.opencv.imgproc.Imgproc;
import java.io.OutputStream;
//import edu.wpi.first.wpilibj.cameraServer.CameraServer;
import edu.wpi.first.wpilibj.DigitalInput;
 //edu.wpi.first.wpilibj.CameraServer;
/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  public static Drivetrain drivetrain;
  public static Pivot pivot;
  public static BallIntake intake;
  public static Elevator elevatorSub;
  public static VictorSPX elevator2 = RobotMap.elevatorSlave;
  public static TalonSRX elevator = RobotMap.elevator;
  public static TalonSRX pivotMotor = RobotMap.pivotMotor; 
  public static PanelPusher panelPusher;
  public static CrazyRidiculousBackLiftPlan crazyRidiculousBackLiftPlan = new CrazyRidiculousBackLiftPlan();
  public static DigitalInput lSwitch = RobotMap.limitSwitch;
  Thread visionThread;
  public static final double TURNING_SPEED = 0.7;
  public static UsbCamera camera;
  public static Mat img2 = new Mat();
  public CvSource outputStream = CameraServer.getInstance().putVideo("lol", 640, 480);

  

  //public static ExampleSubsystem m_subsystem = new ExampleSubsystem();
  //public static OI m_oi;
  
  Command m_autonomousCommand;
  //SendableChooser<Command> m_chooser = new SendableChooser<>();

  /**
   * This function is run when the robot is first started up and should be
   * used for any initialization code.
   */
  @Override
  public void robotInit() {
   CameraServer.getInstance().addAxisCamera("10.15.72.206");
    drivetrain = new Drivetrain();
    pivot = new Pivot();
    intake = new BallIntake();
    elevatorSub = new Elevator();
    panelPusher = new PanelPusher();

    elevator2.follow(elevator);
    elevator.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 0);
    elevator.setSelectedSensorPosition(0, 0, 0); 
    pivotMotor.configSelectedFeedbackSensor(FeedbackDevice.Analog, 0, 0);
    pivotMotor.setSelectedSensorPosition(0, 0, 0); 

  }
    
 


    //m_chooser.setDefaultOption("Default Auto", new ExampleCommand());
    // chooser.addOption("My Auto", new MyAutoCommand());

  /**
   * This function is called every robot packet, no matter the mode. Use
   * this for items like diagnostics that you want ran during disabled,
   * autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before
   * LiveWindow and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
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
   // m_autonomousCommand = m_chooser.getSelected();
    while (Math.abs(elevatorSub.GetEncoderPos()) > 1) {
      elevatorSub.EncoderReset();
    }
    
    while (Math.abs(pivot.GetPotPos()) > 1) {
      pivot.PotReset();
    }
    //Scheduler.getInstance().add(new PivotMove(40, 0.34));
    Scheduler.getInstance().add(new PivotMoveAndReset());
    
    SmartDashboard.putNumber("Pivot Values1", pivot.GetPotPos());
    Scheduler.getInstance().add(new DriveTelop());
   
    SmartDashboard.putNumber("Pivot Values", pivot.GetPotPos());
    /*
     * String autoSelected = SmartDashboard.getString("Auto Selector",
     * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
     * = new MyAutoCommand(); break; case "Default Auto": default:
     * autonomousCommand = new ExampleCommand(); break; }
     */
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
    Scheduler.getInstance().run();
     SmartDashboard.putNumber("pivot", pivot.GetPotPos());
     SmartDashboard.putNumber("elevator encoder", elevatorSub.GetEncoderPos());
  }

  @Override
  public void teleopInit() {
    // This makes sure that the autonomous stops running when
    // teleop starts running. If you want the autonomous to
    // continue until interrupted by another command, remove
    // this line or comment it out.
    //elevatorSub.MoveElevator(-.3);
    //pivot.MovePivot(-.2);
   /* while (Math.abs(elevatorSub.GetEncoderPos()) > 1) {
      elevatorSub.EncoderReset();
    }
    while (Math.abs(pivot.GetPotPos()) > 1) {
      pivot.PotReset();
    }*/
    Scheduler.getInstance().add(new DriveTelop());
    Scheduler.getInstance().add(new GriffinsIntakeSpecialAttack());
   // Scheduler.getInstance().add(new Guillotine());
   
    //Scheduler.getInstance().add(new ToElevatorPostionNew(-1500, -.2));
    
    //
    
    
  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {
    Scheduler.getInstance().run();
    SmartDashboard.putNumber("pivot", pivot.GetPotPos());
    //System.out.println("pivot " + pivot.GetPotPos());
    //System.out.println("elevator " + elevatorSub.GetEncoderPos());
    //panelPusher.PushThePanel();
    //System.out.println("switch" + lSwitch.get());
    //Scheduler.getInstance().add(ToElevatorPostion(10, 0.5));
   
    SmartDashboard.putNumber("elevator encoder", elevatorSub.GetEncoderPos());
    
  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
  }
}
