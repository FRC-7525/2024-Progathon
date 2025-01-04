package frc.robot.subsystems.Manager;

import frc.robot.pioneersLib.subsystem.SubsystemStates;
import frc.robot.subsystems.Elevator.ElevatorStates;
import frc.robot.subsystems.Indexer.IndexerManager.IndexerInterfaceStates;
import frc.robot.subsystems.Intake.IntakeStates;

public enum ManagerStates implements SubsystemStates {
	IDLE("IDLE", ElevatorStates.IDLE, IntakeStates.IDLE, IndexerInterfaceStates.OFF),
	INTAKING(
		"INTAKING",
		ElevatorStates.IDLE,
		IntakeStates.INTAKING,
		IndexerInterfaceStates.AUTONOMOUS
	),
	OUTTAKING("OUTTAKING", ElevatorStates.IDLE, IntakeStates.OUTTAKING, IndexerInterfaceStates.OFF),
	GOING_MID("GOING MID", ElevatorStates.MID, IntakeStates.IDLE, IndexerInterfaceStates.OFF),
	SCORING_MID(
		"SCORING MID",
		ElevatorStates.MID,
		IntakeStates.IDLE,
		IndexerInterfaceStates.SCORING
	),
	GOING_HIGH(
		"GOING HIGH",
		ElevatorStates.HIGH,
		IntakeStates.INTAKING,
		IndexerInterfaceStates.OFF
	),
	SCORING_HIGH(
		"SCORING HIGH",
		ElevatorStates.HIGH,
		IntakeStates.IDLE,
		IndexerInterfaceStates.SCORING
	);

	ManagerStates(
		String stateString,
		ElevatorStates elevatorState,
		IntakeStates intakeState,
		IndexerInterfaceStates indexerInterfaceState
	) {
		this.stateString = stateString;
		this.elevatorState = elevatorState;
		this.intakeState = intakeState;
		this.indexerInterfaceState = indexerInterfaceState;
	}

	private String stateString;
	private IntakeStates intakeState;
	private ElevatorStates elevatorState;
	private IndexerInterfaceStates indexerInterfaceState;

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

	protected IndexerInterfaceStates getIndexerInterfaceState() {
		return indexerInterfaceState;
	}
}
