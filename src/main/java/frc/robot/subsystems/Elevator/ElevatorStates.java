package frc.robot.subsystems.Elevator;

import frc.robot.pioneersLib.subsystem.SubsystemStates;

public enum ElevatorStates implements SubsystemStates {
    HIGH(ElevatorConstants.HIGH_POSITION_HEIGHT.magnitude(), "HIGH"),
    MID(ElevatorConstants.MID_POSITION_HEIGHT.magnitude(), "MID"),
    IDLE(ElevatorConstants.IDLE_POSITION_HEIGHT.magnitude(), "IDLE");

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
