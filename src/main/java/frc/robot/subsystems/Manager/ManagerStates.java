package frc.robot.subsystems.Manager;

import frc.robot.pioneersLib.subsystem.SubsystemStates;
import frc.robot.subsystems.Elevator.ElevatorStates;
import frc.robot.subsystems.Indexer.IndexerStates;
import frc.robot.subsystems.Indexer.IndexerManager.IndexerManagerStates;
import frc.robot.subsystems.Intake.IntakeStates;

public enum ManagerStates implements SubsystemStates {
	IDLE("IDLE", ElevatorStates.IDLE, IntakeStates.IDLE, IndexerManagerStates.OFF),
	INTAKING("INTAKING", ElevatorStates.IDLE, IntakeStates.INTAKING, IndexerManagerStates.OFF),
	OUTTAKING(
		"OUTTAKING",
		ElevatorStates.IDLE,
		IntakeStates.OUTTAKING,
		IndexerManagerStates.OFF
	),
	GOING_MID("GOING MID", ElevatorStates.MID, IntakeStates.IDLE, IndexerManagerStates.OFF),
	SCORING_MID("SCORING MID", ElevatorStates.MID, IntakeStates.IDLE, IndexerManagerStates.SCORING),
	GOING_HIGH("GOING HIGH", ElevatorStates.HIGH, IntakeStates.INTAKING, IndexerManagerStates.OFF),
	SCORING_HIGH("SCORING HIGH", ElevatorStates.HIGH, IntakeStates.IDLE, IndexerManagerStates.SCORING);

	ManagerStates(
		String stateString,
		ElevatorStates elevatorState,
		IntakeStates intakeState,
		IndexerManagerStates indexerManagerState
	) {
		this.stateString = stateString;
		this.elevatorState = elevatorState;
		this.intakeState = intakeState;
		this.indexerManagerState = indexerManagerState;
	}

	private String stateString;
	private IntakeStates intakeState;
	private ElevatorStates elevatorState;
	private IndexerManagerStates indexerManagerState;

	@Override
	public String getStateString() {
		return stateString;
	}

	protected ElevatorStates getElevatorState() {
		return elevatorState;
	}

	protected IntakeStates getIntakeState() {
		return intakeState;
	}

	protected IndexerManagerStates getIndexerManagerState() {
		return indexerManagerState;
	}
}
