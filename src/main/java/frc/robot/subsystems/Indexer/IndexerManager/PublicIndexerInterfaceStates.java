package frc.robot.subsystems.Indexer.IndexerManager;

import frc.robot.pioneersLib.subsystem.SubsystemStates;
import frc.robot.subsystems.Indexer.IndexerStates;

public enum PublicIndexerInterfaceStates implements SubsystemStates {
	SCORING("SCORING"),
	OFF("OFF"),
	AUTONOMOUS("Autonomous");

	PublicIndexerInterfaceStates(String stateString) {
		this.stateString = stateString;
	}

	private String stateString;

	@Override
	public String getStateString() {
		return stateString;
	}
}
