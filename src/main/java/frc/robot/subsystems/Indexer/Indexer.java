package frc.robot.subsystems.Indexer;

import frc.robot.GlobalConstants;
import frc.robot.pioneersLib.subsystem.Subsystem;
import frc.robot.subsystems.Indexer.IndexerManager.IndexerInterface;
import frc.robot.subsystems.Indexer.IndexerManager.IndexerInterfaceStates;
import frc.robot.subsystems.Intake.Intake;

import org.littletonrobotics.junction.Logger;

public class Indexer extends Subsystem<IndexerStates> {

	private static Indexer instance;
	private IndexerIO io;
	private IndexerIOInputsAutoLogged inputs;

	// This is a rogue subsystem 🥶🥶🥶, ngl if we were power scaling subsystems this would be comp for drive in terms of aura
	private Indexer() {
		super("Indexer", IndexerStates.OFF);
		this.io = switch (GlobalConstants.ROBOT_MODE) {
			case SIM -> new IndexerIOSim();
			case REAL -> new IndexerIOTalonFX();
			case TESTING -> new IndexerIOTalonFX();
			case REPLAY -> new IndexerIOSim();
		};
		this.inputs = new IndexerIOInputsAutoLogged();

		// State for scoring
		addTrigger(IndexerStates.OFF, IndexerStates.SCORING, () -> IndexerInterface.getInstance().getState() == IndexerInterfaceStates.SCORING);

		// Return to Off
		addTrigger(IndexerStates.SCORING, IndexerStates.OFF, () -> IndexerInterface.getInstance().getState() == IndexerInterfaceStates.OFF);
		addTrigger(IndexerStates.AUTO_OFF, IndexerStates.OFF, () -> IndexerInterface.getInstance().getState() == IndexerInterfaceStates.OFF);
		addTrigger(IndexerStates.AUTO_ON, IndexerStates.OFF, () -> IndexerInterface.getInstance().getState() == IndexerInterfaceStates.OFF);


		// Automaticall indexing
		addTrigger(IndexerStates.OFF, IndexerStates.AUTO_OFF, () -> IndexerInterface.getInstance().getState() == IndexerInterfaceStates.AUTONOMOUS);
		addTrigger(IndexerStates.AUTO_OFF, IndexerStates.AUTO_ON, () -> IndexerInterface.getInstance().getState() == IndexerInterfaceStates.AUTONOMOUS && Intake.getInstance().hasGamepiece());
		addTrigger(IndexerStates.AUTO_ON, IndexerStates.AUTO_OFF, () -> IndexerInterface.getInstance().getState() != IndexerInterfaceStates.AUTONOMOUS && io.nextSensorTriggered());
	}	

	public static Indexer getInstance() {
		if (instance == null) {
			instance = new Indexer();
		}
		return instance;
	}

	protected void runState() {
		io.setSetpoint(getState().getSetPoint());
	}

	public void stop() {
		io.stop();
	}

	@Override
	public void periodic() {
		super.periodic();

		Logger.processInputs("Indexer", inputs);
		io.updateInputs(inputs);
	}

	public int getNumberOfPieces() {
		return io.getNumberOfPieces();
	}

	public boolean nextSensorTriggered() {
		return io.nextSensorTriggered();
	}

	public double getStateTime() {
		return getStateTime();
	}
}
