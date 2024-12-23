package frc.robot.subsystems.Elevator;

import static frc.robot.Constants.ROBOT_MODE;

import org.littletonrobotics.junction.Logger;
import frc.robot.pioneersLib.subsystem.Subsystem;

public class Elevator extends Subsystem<ElevatorStates> {
    private static Elevator instance;

    private ElevatorIO io;
    private ElevatorIOInputsAutoLogged inputs;

    private Elevator() {
        super("Elevator", ElevatorStates.IDLE);
        this.io = switch (ROBOT_MODE) {
            case SIM -> new ElevatorIOSim();
            default -> new ElevatorIOSim();
        };
        inputs = new ElevatorIOInputsAutoLogged();
    }

    public static Elevator getInstance() {
        if (instance == null) {
            instance = new Elevator();
        }
        return instance;
    }

    @Override
    protected void runState() {
        io.setHeightSetpoint(getState().getTargetHeight());
        io.runElevator();
        
        io.updateInputs(inputs);
        Logger.processInputs("Elevator", inputs);
    }
    
}
