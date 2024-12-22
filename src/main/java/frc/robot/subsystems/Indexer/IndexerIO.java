package frc.robot.subsystems.Indexer;

import org.littletonrobotics.junction.AutoLog;

import com.pathplanner.lib.config.PIDConstants;

import edu.wpi.first.math.geometry.Pose3d;

public interface IndexerIO {
    @AutoLog
	public static class IndexerIOInputs {
        public double wheelSpeed;
        public double wheelSetpoint;

        public boolean[] beamBreakArray = new boolean[6];
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
