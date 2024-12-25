package frc.robot.subsystems.Intake;

import frc.robot.pioneersLib.subsystem.SubsystemStates;

public enum IntakeStates implements SubsystemStates {
    INTAKING(IntakeConstants.INTAKING_SPEED.magnitude(), IntakeConstants.INTAKING_PIVOT.magnitude(), "INTAKING"),
    OUTTAKING(IntakeConstants.OUTTAKING_SPEED.magnitude(), IntakeConstants.OUTTAKING_PIVOT.magnitude(), "OUTTAKING"),
    PASSING(IntakeConstants.PASSING_SPEED.magnitude(), IntakeConstants.PASSING_PIVOT.magnitude(), "PASSING"),
    IDLE(IntakeConstants.IDLE_SPEED.magnitude(), IntakeConstants.IDLE_PIVOT.magnitude(), "IDLE");

    IntakeStates(Double wheelSpeed, double pivotSetpoint, String stateString) {
        this.wheelSpeed = wheelSpeed;
        this.pivotSetpoint = pivotSetpoint;
        this.stateString = stateString;
    }

    private double wheelSpeed;
    private double pivotSetpoint;
    private String stateString;

    public double getWheelSpeedSetpoint() {
        return wheelSpeed;
    }

    public double getPivotSetpoint() {
        return pivotSetpoint;
    }

    @Override
    public String getStateString() {
        return stateString;
    }
}
