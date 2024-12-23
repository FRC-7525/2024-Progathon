package frc.robot.subsystems.Intake;

import frc.robot.pioneersLib.subsystem.SubsystemStates;

public enum IntakeStates implements SubsystemStates {
    INTAKING(Constants.INTAKING_SPEED, Constants.INTAKING_PIVOT, "INTAKING"),
    OUTTAKING(Constants.OUTTAKING_SPEED, Constants.OUTTAKING_PIVOT, "OUTTAKING"),
    PASSING(Constants.PASSING_SPEED, Constants.PASSING_PIVOT, "PASSING"),
    IDLE(Constants.IDLE_SPEED, Constants.IDLE_PIVOT, "IDLE");

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
