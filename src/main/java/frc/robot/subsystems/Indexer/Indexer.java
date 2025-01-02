package frc.robot.subsystems.Indexer;

import frc.robot.GlobalConstants;
import frc.robot.GlobalConstants.RobotMode;
import frc.robot.pioneersLib.subsystem.Subsystem;
import frc.robot.subsystems.Intake.Intake;

import static edu.wpi.first.units.Units.Seconds;

import org.littletonrobotics.junction.Logger;

public class Indexer extends Subsystem<IndexerStates> {

	private static Indexer instance;
	private IndexerIO io;
	private IndexerIOInputsAutoLogged inputs;

	private Indexer() {
		super("Indexer", IndexerStates.OFF);

		addTrigger(IndexerStates.AUTONOMOUS_ON, IndexerStates.AUTONOMOUS_OFF, () ->
			{if (GlobalConstants.ROBOT_MODE == RobotMode.SIM) {
				return getStateTime() > IndexerConstants.SIMULATED_INDEXING_TIME.in(Seconds);
			} else {
				return io.nextSensorTriggered();
			}}
		);
		addTrigger(IndexerStates.AUTONOMOUS_OFF, IndexerStates.AUTONOMOUS_ON, () -> Intake.getInstance().hasGamepiece());

		this.io = switch (GlobalConstants.ROBOT_MODE) {
			case SIM -> new IndexerIOSim();
			case REAL -> new IndexerIOTalonFX();
			case TESTING -> new IndexerIOTalonFX();
			case REPLAY -> new IndexerIOSim();
		};
		this.inputs = new IndexerIOInputsAutoLogged();
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

		//TODO: Add output managing

		Logger.processInputs("Indexer", inputs);
		io.updateInputs(inputs);
	}

	public int getNumberOfPieces() {
		return io.getNumberOfPieces();
	}

	public boolean isEmpty() {
		return io.getNumberOfPieces() == 0;
	}

	// TODO: THIS IS ACTUALLY SO GOOFY
	@Override
	public void setState(IndexerStates state) {
		if (getState() != state) resetStateTimer();
		if (state == IndexerStates.AUTONOMOUS_OFF && getState() == IndexerStates.AUTONOMOUS_ON) {
			return;
		}
		manualSetState(state);
	}
}
