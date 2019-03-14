/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.commands.PivotMove;
import frc.robot.commands.PivotUp;
import frc.robot.commands.PivotDown;
import frc.robot.commands.PushForward;
import frc.robot.commands.PushReset;
import frc.robot.commands.ElevatorStop;
import frc.robot.commands.ElevatorToPostion;
import frc.robot.commands.NewIntakeBlow;
import frc.robot.commands.IntakeSuck;
import frc.robot.commands.IntakeStop;
//import frc.robot.commands.AimForTargetAutonomously;
import frc.robot.commands.ResetDrive;
import frc.robot.commands.PivotStop;
import frc.robot.commands.JumpToPlatform;
import frc.robot.commands.JumpFinshed;
/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class JoystickController {
  private final Joystick joystick;
	public static JoystickController MAIN_JOYSTICK = generateMainJoystick();
  public static JoystickController COPILOT_JOYSTICK = generateCoPilotJoystick();
  public static double ballLevel2 = -3911;
  public static double ballLevel3 = -4600; //-4600
  public static double ballLevel1 = -1537;
  public static double hatchLevel2 = -2737;
  public static double hatchLevel3 = -4600;
  public static double hatchLevel1 = -361;
	
	private static JoystickController generateMainJoystick(){
		//change the speed to like .8
		final Joystick joystick = new Joystick(0);
		//setButtonPressBehavior(joystick, 1, new PivotUp(), new PivotStop());
		setButtonBehavior(joystick, 1, new ElevatorToPostion(1, .5));//, new ElevatorStop()); make elevator faster
		setButtonBehavior(joystick, 3, new ElevatorToPostion(-200, .35)); //361 .5
		setButtonBehavior(joystick, 2, new ElevatorToPostion(-2700, .5));
		setButtonBehavior(joystick, 4, new ElevatorToPostion(-4900, .6));
		//setButtonBehavior(joystick, 2, new ElevatorToPostion(-1, .4));
		setButtonPressBehavior(joystick, 5, new PushForward(), new PushReset());
		setButtonBehavior(joystick, 6, new PivotMove(150 + 30, .4));//, new PivotMove(1, .5));
		setButtonBehavior(joystick, 7, new PivotMove(80 + 30, .5)); // .4 //10, 1 upgrade to 90, . 45
		//, new PivotMove(1, .75));
		
		//remember guillotine
		//setButtonPressBehavior(joystick, 7, new AimForTargetAutonomously(), new ResetDrive());
		return new JoystickController(joystick);
	}

	private static JoystickController generateCoPilotJoystick(){
		final Joystick joystick = new Joystick(1);
		//change the speed to like .8
		setButtonBehavior(joystick, 1, new ElevatorToPostion(-2164, .5));
		setButtonBehavior(joystick, 3, new ElevatorToPostion(-4458, .5));
		setButtonBehavior(joystick, 4, new ElevatorToPostion(-5200, .65));
		//setButtonBehavior(joystick, 2, new PivotMove(95 + 30, .45));//, new PivotMove(1, .4));
		setButtonBehavior(joystick, 2, new ElevatorToPostion(-2950, .5));
		setButtonPressBehavior(joystick, 5, new IntakeSuck(), new IntakeStop());
		setButtonPressBehavior(joystick, 6, new NewIntakeBlow(), new IntakeStop());
		setButtonPressBehavior(joystick, 7, new JumpToPlatform(), new JumpFinshed());
		setButtonBehavior(joystick, 8, new PivotMove(150 + 30, .45));
		return new JoystickController(joystick);
	}
	
	private static void setButtonBehavior(final Joystick joystick, final int buttonNumber, final Command whileHeldCommand) {
		final Button button = new JoystickButton(joystick, buttonNumber);
		button.whenPressed(whileHeldCommand);
	}
	
	private static void setButtonHeldBehavior(final Joystick joystick, final int buttonNumber, 
										  final Command whileHeldCommand, final Command whenReleasedCommand) {
		final Button button = new JoystickButton(joystick, buttonNumber);
		button.whileHeld(whileHeldCommand);
		button.whenReleased(whenReleasedCommand);
	}
	private static void setButtonPressBehavior(final Joystick joystick, final int buttonNumber, final Command whenPressedCommand, final Command whenReleasedCommand) {
		final Button button = new JoystickButton(joystick, buttonNumber);
		button.whenPressed(whenPressedCommand);
		button.whenReleased(whenReleasedCommand);
	}

	private static void setButtonPressBehaviorSecondary(final Joystick joystick, final int buttonNumber, final Command whenPressedCommand) {
		final Button button = new JoystickButton(joystick, buttonNumber);
		button.whenPressed(whenPressedCommand);
	}
	
	JoystickController(final Joystick joystick){
		this.joystick = joystick;
	}
	
	public double getLeftStickX(){
		return this.joystick.getRawAxis(0);
	}
	
	public double getLeftStickY(){
		return this.joystick.getRawAxis(1);
	}
	
	public double getLeftTrigger(){
		return this.joystick.getRawAxis(2);
	}
	
	public double getRightTrigger(){
		return this.joystick.getRawAxis(3);
	}
	
	public double getRightStickX() {
		return this.joystick.getRawAxis(4);
	}
	
	public double getRightStickY() {
		return this.joystick.getRawAxis(5);
	}

	public boolean RightTriggeredPressed() {
		if (this.joystick.getRawAxis(3) > 0) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean LeftTriggeredPressed() {
		if (this.joystick.getRawAxis(2) > .25) {
			return true;
		}
		else {
			return false;
		}
	}

	public double GetPov() {
		double povValue = this.joystick.getPOV();
		return povValue;
	}
}
