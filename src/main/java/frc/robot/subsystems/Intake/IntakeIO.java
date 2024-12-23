package frc.robot.subsystems.Intake;

public interface IntakeIO {
    public static class IntakeIOInputs {
		// Pivot
		public double pivotPosition;
		public double pivotSetpoint;

		// Spinners
		public double wheelSpeed;
		public double wheelSpeedSetpoint;
	}

    public void updateInputs(IntakeIOInputs input);

    public void setPivotSetpoint(double pivotSetpoint);

    public void setWheelSpeed(double wheelSpeed);
    

}
