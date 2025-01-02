package frc.robot.subsystems.Indexer;

import frc.robot.pioneersLib.subsystem.SubsystemStates;

public enum IndexerStates implements SubsystemStates {
	OFF("Off", IndexerConstants.OFF),
	INDEXING("Indexing", IndexerConstants.ON),
	SCORING("Scoring", IndexerConstants.ON);

	private String stateString;
	private double wheelSetpoint;

	IndexerStates(String stateString, double wheelSpeedpoint) {
		this.stateString = stateString;
		this.wheelSetpoint = wheelSpeedpoint;
	}

	public String getStateString() {
		return stateString;
	}

	public double getSetPoint() {
		return wheelSetpoint;
	}
}
