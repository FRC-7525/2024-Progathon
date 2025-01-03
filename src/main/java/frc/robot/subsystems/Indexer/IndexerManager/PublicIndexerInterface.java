package frc.robot.subsystems.Indexer.IndexerManager;

import frc.robot.pioneersLib.subsystem.Subsystem;
import frc.robot.subsystems.Indexer.Indexer;
import frc.robot.subsystems.Indexer.IndexerConstants;

public class PublicIndexerInterface extends Subsystem<PublicIndexerInterfaceStates> {

	private static PublicIndexerInterface instance;

	private PublicIndexerInterface() {
		super("Indexer Manager", PublicIndexerInterfaceStates.OFF);
	}

	public static PublicIndexerInterface getInstance() {
		if (instance == null) {
			instance = new PublicIndexerInterface();
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
	public void runState() {}
}
