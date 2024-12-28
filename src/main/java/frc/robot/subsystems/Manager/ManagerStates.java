package frc.robot.subsystems.Manager;

import frc.robot.pioneersLib.subsystem.SubsystemStates;
import frc.robot.subsystems.Elevator.ElevatorStates;

public enum ManagerStates implements SubsystemStates {
    IDLE("Idle", ElevatorStates.IDLE),
    SCORING_HIGH("SCORING HIGH", ElevatorStates.HIGH);
    
    ManagerStates(String stateString, ElevatorStates elevatorState) {
        this.stateString = stateString;
        this.elevatorState = elevatorState;
    }

    private String stateString;
    private ElevatorStates elevatorState;

    @Override
    public String getStateString() {
        return stateString;
    }

    protected ElevatorStates getElevatorState() {
        return elevatorState;
    }
}
