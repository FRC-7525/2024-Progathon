package frc.robot.subsystems.Indexer;

import com.pathplanner.lib.config.PIDConstants;
import org.littletonrobotics.junction.AutoLog;

public interface IndexerIO {
	@AutoLog
	public static class IndexerIOInputs {

		public boolean[] beamBreakArray = new boolean[IndexerConstants.NUM_BEAM_BREAK];
		public double wheelSpeed;
		public double wheelSetpoint;
	}

	public default void updateInputs(IndexerIOInputs inputs) {}

	public default void setSetpoint(double setpoint) {}

	public default void stop() {}

	public default double getSpeed() {
		return 0.0;
	}

	public default void configurePID(PIDConstants constants) {}

	public default int getNumberOfPieces() {
		return 0;
	}

	public default void updateCurrentSensor() {}

	public default boolean nextSensorTriggered() {
		return false;
	}
}
