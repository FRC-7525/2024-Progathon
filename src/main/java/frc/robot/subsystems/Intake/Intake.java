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

	private Intake(IntakeIO io) {
		super("Intake", IntakeStates.IDLE);
		this.io = io;
		inputs = new IntakeIOInputsAutoLogged();
	}

	@Override
	protected void runState() {
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
        if (getState() == IntakeStates.INTAKING) {
            if (GlobalConstants.ROBOT_MODE == GlobalConstants.RobotMode.REAL || GlobalConstants.ROBOT_MODE == GlobalConstants.RobotMode.TESTING) {
                return io.hasGamepiece();
            } else {
                // TODO, happen more than once pls
                return getStateTime() > IntakeConstants.Sim.SIMULATED_INTAKING_TIME.in(Seconds);
            }
        }
        return false;
    }
}
