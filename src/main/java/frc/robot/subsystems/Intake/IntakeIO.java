package frc.robot.subsystems.Intake;

import org.littletonrobotics.junction.AutoLog;

import com.fasterxml.jackson.databind.ser.std.StdKeySerializers.Default;

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

	public default void updateInputs(IntakeIOInputs input) {};

	public default void setPivotSetpoint(double pivotSetpoint) {};

	public default void setWheelSpeed(double wheelSpeed) {};

	public default boolean hasGamepiece() {return false;};
}
