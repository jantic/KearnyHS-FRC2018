package org.usfirst.frc.team1572.robot.subsystems;

public enum EncoderType {
	left(0),
	right(1),
	average(2);
	
	private final int deviceNumber;
	
	private EncoderType(final int deviceNumber) {
		this.deviceNumber = deviceNumber;
	}
	public int getDeviceNumber() {
		return this.deviceNumber;
	}
}
