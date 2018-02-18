package org.usfirst.frc.team1572.robot;

import org.usfirst.frc.team1572.robot.commands.main.Climb;
import org.usfirst.frc.team1572.robot.commands.main.DeployClimber;
import org.usfirst.frc.team1572.robot.commands.main.DeployPlatform;
import org.usfirst.frc.team1572.robot.commands.main.ForkliftDown;
import org.usfirst.frc.team1572.robot.commands.main.ForkliftDownReset;
import org.usfirst.frc.team1572.robot.commands.main.ForkliftSwitchHeight;
import org.usfirst.frc.team1572.robot.commands.main.ForkliftToPos;
import org.usfirst.frc.team1572.robot.commands.main.ForkliftUp;
import org.usfirst.frc.team1572.robot.commands.main.ForwardIntake;
import org.usfirst.frc.team1572.robot.commands.main.ResetForkliftEncoders;
import org.usfirst.frc.team1572.robot.commands.main.ReverseIntake;
import org.usfirst.frc.team1572.robot.commands.main.StopClimb;
import org.usfirst.frc.team1572.robot.commands.main.StopForklift;
import org.usfirst.frc.team1572.robot.commands.main.StopIntake;
import org.usfirst.frc.team1572.robot.commands.main.TwistIntake;
import org.usfirst.frc.team1572.robot.commands.main.UndeployClimber;
import org.usfirst.frc.team1572.robot.commands.main.UndeployPlatform;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class JoystickController {
	private final Joystick joystick;
	public static JoystickController MAIN_JOYSTICK = generateMainJoystick();
	public static JoystickController COPILOT_JOYSTICK = generateCoPilotJoystick();
	//Int Main Joystick and Copilot Joystick
	
	private static JoystickController generateMainJoystick(){
		final Joystick joystick = new Joystick(0);
		//setButtonPressBehavior(joystick, 0, new Record(), new StopRecording());
		//setButtonBehavior(joystick, , new GearGrabOpen(), new GearGrabClose());
		setButtonPressBehavior(joystick, 3, new Climb(), new StopClimb());
		setButtonBehavior(joystick, 5, new ForwardIntake(20), new StopIntake());
		setButtonBehavior(joystick, 6, new ReverseIntake(20), new StopIntake());
		setButtonPressBehavior(joystick, 7, new DeployPlatform(), new UndeployPlatform());
		setButtonPressBehavior(joystick, 8, new DeployClimber(), new UndeployClimber());
		setButtonPressBehavior(joystick, 4, new ForkliftUp(2));
		setButtonPressBehavior(joystick, 1, new ForkliftDownReset(1));
		//setButtonPressBehavior(joystick, 2, new ForkliftToPos(12000, 2));
		setButtonPressBehavior(joystick, 2, new ForkliftSwitchHeight(2));
		return new JoystickController(joystick);
		
		//sets button controls for Main Controller
	}

	private static JoystickController generateCoPilotJoystick(){
		final Joystick joystick = new Joystick(1);
		setButtonPressBehavior(joystick, 1, new ResetForkliftEncoders());
		setButtonBehavior(joystick, 5, new TwistIntake(20, 0.75, -.5), new StopIntake());
		setButtonBehavior(joystick, 6, new TwistIntake(20, -.5, 0.75), new StopIntake());
		setButtonBehavior(joystick, 2, new TwistIntake(20, 0.75, 0.6), new StopIntake());
		setButtonPressBehavior(joystick, 3, new ForkliftDownReset(1));
		//setButtonBehavior(joystick, JoystickButtonMap.six, new ShootClose(), new StopShooting());
		//set buttom controls for Co controller
		return new JoystickController(joystick);
	}
	
	private static void setButtonBehavior(final Joystick joystick, final int buttonNumber, final Command whileHeldCommand) {
		final Button button = new JoystickButton(joystick, buttonNumber);
		button.whileHeld(whileHeldCommand);
	}
	
	private static void setButtonBehavior(final Joystick joystick, final int buttonNumber, final Command whileHeldCommand, final Command whenReleasedCommand) {
		final Button button = new JoystickButton(joystick, buttonNumber);
		button.whileHeld(whileHeldCommand);
		button.whenReleased(whenReleasedCommand);
	}
	private static void setButtonPressBehavior(final Joystick joystick, final int buttonNumber, final Command whenPressedCommand, final Command whenReleasedCommand) {
		final Button button = new JoystickButton(joystick, buttonNumber);
		button.whenPressed(whenPressedCommand);
		button.whenReleased(whenReleasedCommand);
	}
	private static void setButtonPressBehavior(final Joystick joystick, final int buttonNumber, final Command whenPressedCommand) {
		final Button button = new JoystickButton(joystick, buttonNumber);
		button.whenPressed(whenPressedCommand);
	}
	
	//Creates behaviors for buttons
	
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
	
	//renames getRawAxis to something that makes more sense
}
