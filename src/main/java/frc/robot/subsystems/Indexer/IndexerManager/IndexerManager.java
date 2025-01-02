package frc.robot.subsystems.Indexer.IndexerManager;

import frc.robot.pioneersLib.subsystem.Subsystem;
import frc.robot.subsystems.Indexer.Indexer;
import frc.robot.subsystems.Indexer.IndexerConstants;

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
           indexer.setState(IndexerManagerStates.AUTONOMOUS.getIndexerState());
            if (Indexer.getInstance().)

       } else {
           indexer.setState(getState().getIndexerState());
       }
    }

    // addTrigger(IndexerStates.AUTONOMOUS_ON, IndexerStates.AUTONOMOUS_OFF, () -> io.nextSensorTriggered());
	// 	addTrigger(IndexerStates.AUTONOMOUS_OFF, IndexerStates.AUTONOMOUS_ON, () ->
	// 		Intake.getInstance().hasGamepiece()
	// 	);
}
