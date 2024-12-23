package frc.robot.subsystems.Elevator;

import frc.robot.pioneersLib.subsystem.SubsystemStates;

public enum ElevatorStates implements SubsystemStates {
    HIGH(Constants.HIGH_POSITION_HEIGHT, "HIGH"),
    MID(Constants.MID_POSITION_HEIGHT, "MID"),
    IDLE(Constants.IDLE_POSITION_HEIGHT, "IDLE");

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
