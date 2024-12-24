package frc.robot.subsystems.Intake;

import org.littletonrobotics.junction.AutoLog;

public interface IntakeIO {
	
	@AutoLog
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
