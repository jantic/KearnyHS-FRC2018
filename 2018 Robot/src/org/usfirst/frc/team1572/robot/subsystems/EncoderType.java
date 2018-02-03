package org.usfirst.frc.team1572.robot.subsystems;

public enum EncoderType {
	left(0),
	right(1),
	average(2);
	//which encoder we use, assuming one is not working to standard
	private final int deviceNumber;
	
	private EncoderType(final int deviceNumber) {
		this.deviceNumber = deviceNumber;
	}
	public int getDeviceNumber() {
		return this.deviceNumber;
	}
}
