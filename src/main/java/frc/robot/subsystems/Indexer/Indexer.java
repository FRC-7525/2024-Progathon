package frc.robot.subsystems.Indexer;

import org.littletonrobotics.junction.Logger;

import frc.robot.Constants;
import frc.robot.pioneersLib.subsystem.Subsystem;

public class Indexer extends Subsystem<IndexerStates> {
    private IndexerIO io;
    private IndexerIOInputsAutoLogged inputs;

    public Indexer(IndexerIO io) {
        super("Indexer", IndexerStates.OFF);

        this.io = io;

        addTrigger(IndexerStates.AUTONOMOUS_OFF, IndexerStates.AUTONOMOUS_ON, () -> io.nextSensorTriggered());
        addTrigger(IndexerStates.AUTONOMOUS_ON, IndexerStates.AUTONOMOUS_OFF, () -> true); //TODO: actually get intake beam break

        switch (Constants.ROBOT_MODE) {
            case REAL:
                break;
            case SIM:
                break;
            default:
                break;
        }
    }

    protected void runState() {
        io.setSetpoint(getState().getSetPoint());
    }

    public void stop() {
        io.stop();
    }

    @Override
    public void periodic() {
       super.periodic();

       //TODO: Add output managing

        Logger.processInputs("Indexer", inputs);
        io.updateInputs(inputs);
    }

    public int getNumberOfPieces() {
        return io.getNumberOfPieces();
    }
}
