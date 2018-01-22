package org.usfirst.frc.team1572.robot;

import org.usfirst.frc.team1572.robot.commands.main.Climb;
import org.usfirst.frc.team1572.robot.commands.main.DeployClimber;
import org.usfirst.frc.team1572.robot.commands.main.DeployPlatform;
import org.usfirst.frc.team1572.robot.commands.main.ForkliftDown;
import org.usfirst.frc.team1572.robot.commands.main.ForkliftUp;
import org.usfirst.frc.team1572.robot.commands.main.ForwardIntake;
import org.usfirst.frc.team1572.robot.commands.main.ReverseIntake;
import org.usfirst.frc.team1572.robot.commands.main.StopClimb;
import org.usfirst.frc.team1572.robot.commands.main.StopForklift;
import org.usfirst.frc.team1572.robot.commands.main.StopIntake;
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
	
	private static JoystickController generateMainJoystick(){
		final Joystick joystick = new Joystick(0);
		//setButtonPressBehavior(joystick, 0, new Record(), new StopRecording());
		//setButtonBehavior(joystick, , new GearGrabOpen(), new GearGrabClose());
		setButtonPressBehavior(joystick, 1, new Climb(), new StopClimb());
		setButtonPressBehavior(joystick, 2, new ForwardIntake(), new StopIntake());
		setButtonPressBehavior(joystick, 3, new ReverseIntake(), new StopIntake());
		setButtonPressBehavior(joystick, 4, new DeployPlatform(), new UndeployPlatform());
		setButtonPressBehavior(joystick, 5, new DeployClimber(), new UndeployClimber());
		setButtonPressBehavior(joystick, 6, new ForkliftUp(), new StopForklift());
		setButtonPressBehavior(joystick, 7, new ForkliftDown(), new StopForklift());
		return new JoystickController(joystick);
	}

	private static JoystickController generateCoPilotJoystick(){
		final Joystick joystick = new Joystick(1);
		//setButtonBehavior(joystick, JoystickButtonMap.six, new ShootClose(), new StopShooting());
		
		return new JoystickController(joystick);
	}
	
	private static void setButtonBehavior(final Joystick joystick, final int buttonNumber, final Command whileHeldCommand) {
		final Button button = new JoystickButton(joystick, buttonNumber);
		button.whileHeld(whileHeldCommand);
	}
	
	private static void setButtonBehavior(final Joystick joystick, final int buttonNumber, 
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
}
