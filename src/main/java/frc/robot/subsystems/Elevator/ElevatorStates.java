package frc.robot.subsystems.Elevator;

import frc.robot.pioneersLib.subsystem.SubsystemStates;

public enum ElevatorStates implements SubsystemStates {
    HIGH(1, "HIGH"),
    MID(.5, "MID"),
    IDLE(0, "IDLE");

    ElevatorStates(double targetHeight, String stateString) {
        this.targetHeight = targetHeight;
        this.stateString = stateString;
    }

    private double targetHeight;
    private String stateString;

    public double getTargetHeight() {
        return targetHeight;
    }
    public String getStateString() {
        return stateString;
    }

}
