package frc.robot.subsystems.Indexer.IndexerManager;

import frc.robot.pioneersLib.subsystem.Subsystem;
import frc.robot.subsystems.Indexer.Indexer;
import frc.robot.subsystems.Indexer.IndexerConstants;

public class IndexerInterface extends Subsystem<IndexerInterfaceStates> {

	private static IndexerInterface instance;

	private IndexerInterface() {
		super("Indexer Manager", IndexerInterfaceStates.OFF);
	}

	public static IndexerInterface getInstance() {
		if (instance == null) {
			instance = new IndexerInterface();
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
	}
}
