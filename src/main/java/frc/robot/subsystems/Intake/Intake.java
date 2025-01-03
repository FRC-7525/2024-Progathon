package frc.robot.subsystems.Intake;

import static edu.wpi.first.units.Units.Seconds;
import static frc.robot.GlobalConstants.ROBOT_MODE;

import frc.robot.GlobalConstants;
import frc.robot.pioneersLib.subsystem.Subsystem;
import org.littletonrobotics.junction.Logger;

public class Intake extends Subsystem<IntakeStates> {

	private IntakeIO io;
	private IntakeIOInputsAutoLogged inputs;
	private static Intake instance;

	// Strictly for sim
	private double lastIntookSimulatedGampieceTime;

	private Intake(IntakeIO io) {
		super("Intake", IntakeStates.IDLE);
		this.io = io;
		inputs = new IntakeIOInputsAutoLogged();
		lastIntookSimulatedGampieceTime = 0.0;
	}

	@Override
	protected void runState() {
		// ehhhh, yeah idk if this will work, ig its in the same loop as the reset so it should theoretically work
		if (getStateTime() == 0) {
			lastIntookSimulatedGampieceTime = 0.0;
		}

		io.setPivotSetpoint(getState().getPivotSetpoint());
		io.setWheelSpeed(getState().getWheelSpeedSetpoint());

		io.updateInputs(inputs);
		Logger.processInputs("Intake", inputs);
	}

	public static Intake getInstance() {
		if (instance == null) {
			IntakeIO intakeIO =
				switch (ROBOT_MODE) {
					case SIM -> new IntakeIOSim();
					case REAL -> new IntakeIOReal();
					case TESTING -> new IntakeIOReal();
					case REPLAY -> new IntakeIOSim();
				};
			instance = new Intake(intakeIO);
		}
		return instance;
	}

	public boolean hasGamepiece() {
		return io.hasGamepiece();
	}

	public double getStateTime() {
		return getStateTime();
	}
}
