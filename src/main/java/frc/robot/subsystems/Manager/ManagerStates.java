package frc.robot.subsystems.Manager;

import frc.robot.pioneersLib.subsystem.SubsystemStates;
import frc.robot.subsystems.Elevator.ElevatorStates;
import frc.robot.subsystems.Indexer.IndexerStates;
import frc.robot.subsystems.Intake.IntakeStates;

public enum ManagerStates implements SubsystemStates {
    IDLE("IDLE", ElevatorStates.IDLE, IntakeStates.IDLE, IndexerStates.OFF),
    INTAKING("INTAKING", ElevatorStates.IDLE, IntakeStates.INTAKING, IndexerStates.AUTONOMOUS_ON),
    OUTTAKING("OUTTAKING", ElevatorStates.IDLE, IntakeStates.OUTTAKING, IndexerStates.AUTONOMOUS_ON),
    GOING_MID("GOING MID", ElevatorStates.MID, IntakeStates.IDLE, IndexerStates.OFF),
    SCORING_MID("SCORING MID", ElevatorStates.MID, IntakeStates.IDLE, IndexerStates.SCORING),
    GOING_HIGH("GOING HIGH", ElevatorStates.HIGH, IntakeStates.INTAKING, IndexerStates.OFF),
    SCORING_HIGH("SCORING HIGH", ElevatorStates.HIGH, IntakeStates.IDLE, IndexerStates.SCORING);
    
    ManagerStates(String stateString, ElevatorStates elevatorState, IntakeStates intakeState, IndexerStates indexerState) {
        this.stateString = stateString;
        this.elevatorState = elevatorState;
        this.intakeState = intakeState;
        this.indexerState = indexerState;
    }

    private String stateString;
    private IntakeStates intakeState;
    private ElevatorStates elevatorState;
    private IndexerStates indexerState;

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
    protected IndexerStates getIndexerState() {
        return indexerState;
    }
}
