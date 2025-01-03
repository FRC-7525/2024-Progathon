package frc.robot.subsystems.Manager;

import frc.robot.pioneersLib.subsystem.SubsystemStates;
import frc.robot.subsystems.Elevator.ElevatorStates;
import frc.robot.subsystems.Indexer.IndexerManager.PublicIndexerInterfaceStates;
import frc.robot.subsystems.Intake.IntakeStates;

public enum ManagerStates implements SubsystemStates {
	IDLE("IDLE", ElevatorStates.IDLE, IntakeStates.IDLE, PublicIndexerInterfaceStates.OFF),
	INTAKING(
		"INTAKING",
		ElevatorStates.IDLE,
		IntakeStates.INTAKING,
		PublicIndexerInterfaceStates.OFF
	),
	OUTTAKING(
		"OUTTAKING",
		ElevatorStates.IDLE,
		IntakeStates.OUTTAKING,
		PublicIndexerInterfaceStates.OFF
	),
	GOING_MID("GOING MID", ElevatorStates.MID, IntakeStates.IDLE, PublicIndexerInterfaceStates.OFF),
	SCORING_MID(
		"SCORING MID",
		ElevatorStates.MID,
		IntakeStates.IDLE,
		PublicIndexerInterfaceStates.SCORING
	),
	GOING_HIGH(
		"GOING HIGH",
		ElevatorStates.HIGH,
		IntakeStates.INTAKING,
		PublicIndexerInterfaceStates.OFF
	),
	SCORING_HIGH(
		"SCORING HIGH",
		ElevatorStates.HIGH,
		IntakeStates.IDLE,
		PublicIndexerInterfaceStates.SCORING
	);

	ManagerStates(
		String stateString,
		ElevatorStates elevatorState,
		IntakeStates intakeState,
		PublicIndexerInterfaceStates indexerInterfaceState
	) {
		this.stateString = stateString;
		this.elevatorState = elevatorState;
		this.intakeState = intakeState;
		this.indexerInterfaceState = indexerInterfaceState;
	}

	private String stateString;
	private IntakeStates intakeState;
	private ElevatorStates elevatorState;
	private PublicIndexerInterfaceStates indexerInterfaceState;

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

	protected PublicIndexerInterfaceStates getIndexerInterfaceState() {
		return indexerInterfaceState;
	}
}
