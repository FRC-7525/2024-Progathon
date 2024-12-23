package frc.robot.subsystems.Intake;

import static frc.robot.GlobalConstants.ROBOT_MODE;

import frc.robot.pioneersLib.subsystem.Subsystem;
import frc.robot.subsystems.Intake.IntakeIO.IntakeIOInputs;

public class Intake extends Subsystem<IntakeStates>{
    IntakeIO io;
    IntakeIOInputs inputs;
    private static Intake instance;
    
    private Intake(IntakeIO io) {
        super("Intake", IntakeStates.IDLE);
        this.io = io;
        inputs = new IntakeIOInputs();
    }

    @Override
    protected void runState() {
        io.setPivotSetpoint(getState().getPivotSetpoint());
        io.setWheelSpeed(getState().getWheelSpeedSetpoint());
        io.updateInputs(inputs);
    }

    public static Intake getInstance() {
        if (instance == null) {
            IntakeIO intakeIO = switch(ROBOT_MODE) {
                case SIM -> new IntakeIOSim();
                default -> new IntakeIOSim();
            };
            instance = new Intake(intakeIO);
        }
        return instance;
    }
}
