package frc.robot.subsystems.Intake;

import static frc.robot.GlobalConstants.ROBOT_MODE;

import org.littletonrobotics.junction.Logger;

import frc.robot.pioneersLib.subsystem.Subsystem;

public class Intake extends Subsystem<IntakeStates>{
    IntakeIO io;
    IntakeIOInputsAutoLogged inputs;
    private static Intake instance;
    
    private Intake(IntakeIO io) {
        super("Intake", IntakeStates.IDLE);
        this.io = io;
        inputs = new IntakeIOInputsAutoLogged();
    }

    @Override
    protected void runState() {
        Logger.recordOutput("Intake State", getState());
        io.setPivotSetpoint(getState().getPivotSetpoint());
        io.setWheelSpeed(getState().getWheelSpeedSetpoint());

        io.updateInputs(inputs);
        Logger.processInputs("Intake", inputs);
    }

    public static Intake getInstance() {
        if (instance == null) {
            IntakeIO intakeIO = switch(ROBOT_MODE) {
                case SIM -> new IntakeIOSim();
                case REAL -> new IntakeIOReal();
                case TESTING -> new IntakeIOReal();
                case REPLAY -> new IntakeIOSim();
            };
            instance = new Intake(intakeIO);
        }
        return instance;
    }
}
