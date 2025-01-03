package frc.robot.subsystems.Indexer.IndexerManager;

import frc.robot.pioneersLib.subsystem.SubsystemStates;
import frc.robot.subsystems.Indexer.IndexerStates;

public enum IndexerManagerStates implements SubsystemStates {
	SCORING("SCORING", IndexerStates.SCORING),
	OFF("OFF", IndexerStates.OFF),
	AUTONOMOUS("Autonomous", IndexerStates.OFF);

	IndexerManagerStates(String stateString, IndexerStates indexerState) {
		this.stateString = stateString;
		this.indexerState = indexerState;
	}

	private String stateString;
	private IndexerStates indexerState;

	@Override
	public String getStateString() {
		return stateString;
	}

	protected IndexerStates getIndexerState() {
		return indexerState;
	}
}
