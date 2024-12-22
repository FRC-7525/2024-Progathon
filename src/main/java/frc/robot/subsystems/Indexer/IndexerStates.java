package frc.robot.subsystems.Indexer;

import frc.robot.pioneersLib.subsystem.SubsystemStates;

public enum IndexerStates implements SubsystemStates {
    OFF("Off", Constants.OFF),
    AUTONOMOUS_ON("Autonomous On", Constants.ON),
    AUTONOMOUS_OFF("Autonomous Off", Constants.OFF),
    SCORING("Scoring", Constants.ON);

    private String stateString;
    private double wheelSetpoint;

    IndexerStates(String stateString, double wheelSpeedpoint) {
        this.stateString = stateString;
        this.wheelSetpoint = wheelSpeedpoint;
    }

    public String getStateString() {
        return stateString;
    }

    public double getSetPoint() {
        return wheelSetpoint;
    }
}
