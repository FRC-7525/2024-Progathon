package frc.robot.subsystems.Manager;

import frc.robot.pioneersLib.subsystem.SubsystemStates;
import frc.robot.subsystems.Elevator.ElevatorStates;
import frc.robot.subsystems.Intake.IntakeStates;

public enum ManagerStates implements SubsystemStates {
    IDLE("Idle", ElevatorStates.IDLE, IntakeStates.IDLE),
    INTAKING("INTAKING", ElevatorStates.IDLE, IntakeStates.INTAKING),
    SCORING_HIGH("SCORING HIGH", ElevatorStates.HIGH, IntakeStates.IDLE);
    
    ManagerStates(String stateString, ElevatorStates elevatorState, IntakeStates intakeState) {
        this.stateString = stateString;
        this.elevatorState = elevatorState;
        this.intakeState = intakeState;
    }

    private String stateString;
    private ElevatorStates elevatorState;
    private IntakeStates intakeState;

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
}
