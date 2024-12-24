package frc.robot.subsystems.Manager;

import frc.robot.pioneersLib.subsystem.SubsystemStates;
import frc.robot.subsystems.Intake.IntakeStates;

public enum ManagerStates implements SubsystemStates {
    IDLE("Idle", IntakeStates.IDLE),
    INTAKING("INTAKING", IntakeStates.INTAKING);
    
    ManagerStates(String stateString, IntakeStates intakeState) {
        this.stateString = stateString;
        this.intakeState = intakeState;
    }

    private String stateString;
    private IntakeStates intakeState;

    @Override
    public String getStateString() {
        return stateString;
    }

    public IntakeStates getIntakeState() {
        return intakeState;
    }
}
