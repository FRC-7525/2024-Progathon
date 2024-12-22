package frc.robot.pioneersLib.subsystem;

public interface SubsystemStates {

	default String getStateString() {
		throw new UnsupportedOperationException("This method must be overridden by the implementing class");
	}

}