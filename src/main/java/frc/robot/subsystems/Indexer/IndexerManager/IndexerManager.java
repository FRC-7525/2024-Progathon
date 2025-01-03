package frc.robot.subsystems.Indexer.IndexerManager;

import frc.robot.pioneersLib.subsystem.Subsystem;
import frc.robot.subsystems.Indexer.Indexer;
import frc.robot.subsystems.Indexer.IndexerConstants;
import frc.robot.subsystems.Indexer.IndexerStates;
import frc.robot.subsystems.Intake.Intake;

public class IndexerManager extends Subsystem<IndexerManagerStates> {

	private static IndexerManager instance;
	private final Indexer indexer = Indexer.getInstance();

	// Those who know
	private IndexerManager() {
		super("Indexer Manager", IndexerManagerStates.OFF);
	}

	public static IndexerManager getInstance() {
		if (instance == null) {
			instance = new IndexerManager();
		}
		return instance;
	}

	public int getNumberOfPieces() {
		return Indexer.getInstance().getNumberOfPieces();
	}

	public boolean isEmpty() {
		return Indexer.getInstance().getNumberOfPieces() == 0;
	}

	public boolean isFull() {
		return Indexer.getInstance().getNumberOfPieces() >= IndexerConstants.MAX_GAME_PIECES;
	}

	@Override
	public void runState() {
		if (getState() == IndexerManagerStates.AUTONOMOUS) {
			if (indexer.getState() == IndexerStates.OFF && Intake.getInstance().hasGamepiece()) {
				indexer.setState(IndexerStates.INDEXING);
			} else if (
				indexer.getState() == IndexerStates.INDEXING && indexer.nextSensorTriggered()
			) {
				indexer.setState(IndexerStates.OFF);
			}
		} else {
			indexer.setState(getState().getIndexerState());
		}
	}
}
