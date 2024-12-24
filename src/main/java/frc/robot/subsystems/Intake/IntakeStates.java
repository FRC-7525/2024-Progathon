package frc.robot.subsystems.Intake;

import frc.robot.pioneersLib.subsystem.SubsystemStates;

public enum IntakeStates implements SubsystemStates {
    INTAKING(IntakeConstants.INTAKING_SPEED, IntakeConstants.INTAKING_PIVOT, "INTAKING"),
    OUTTAKING(IntakeConstants.OUTTAKING_SPEED, IntakeConstants.OUTTAKING_PIVOT, "OUTTAKING"),
    PASSING(IntakeConstants.PASSING_SPEED, IntakeConstants.PASSING_PIVOT, "PASSING"),
    IDLE(IntakeConstants.IDLE_SPEED, IntakeConstants.IDLE_PIVOT, "IDLE");

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
