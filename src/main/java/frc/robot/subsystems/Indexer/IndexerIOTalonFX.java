package frc.robot.subsystems.Indexer;

import com.ctre.phoenix6.hardware.TalonFX;
import com.pathplanner.lib.config.PIDConstants;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.filter.Debouncer;
import edu.wpi.first.math.filter.Debouncer.DebounceType;
import edu.wpi.first.wpilibj.DigitalInput;

public class IndexerIOTalonFX implements IndexerIO {
    private TalonFX wheelMotor;

    private PIDController speedController;

    private double wheelAppliedVoltage;
    private double wheelSpeedpoint;

    private DigitalInput[] beamBreaks;
    private Debouncer[] beamBreakDebouncers;

    private int currentNumOfPieces;
    private int formerNumOfPieces;

    public IndexerIOTalonFX() {
        currentNumOfPieces = 0;        
        formerNumOfPieces = 0;

        wheelMotor = new TalonFX(IndexerConstants.SPINNER_ID);

        speedController = new PIDController(0, 0, 0);

        beamBreaks = new DigitalInput[IndexerConstants.NUM_BEAM_BREAK];
        for (int i = 0; i < beamBreaks.length; i++) {
            beamBreaks[i] = new DigitalInput(IndexerConstants.BEAM_BREAK_PORTS[i]);
            beamBreakDebouncers[i] = new Debouncer(
                IndexerConstants.DEBOUNCE_TIME,
                DebounceType.kBoth
            );
        }
    }

    @Override
    public void setSetpoint(double setpoint) {
        wheelAppliedVoltage = speedController.calculate(wheelMotor.getVelocity().getValueAsDouble(), setpoint);
        wheelSpeedpoint = setpoint;

        wheelMotor.setVoltage(wheelAppliedVoltage);
    } 

    @Override
    public void updateInputs(IndexerIOInputs inputs) {
        inputs.wheelSetpoint = wheelSpeedpoint;
        inputs.wheelSpeed = wheelMotor.getVelocity().getValueAsDouble();

        for (int i = 0; i < beamBreakDebouncers.length; i++) {
            inputs.beamBreakArray[i] = !beamBreakDebouncers[i].calculate(beamBreaks[i].get());
        }
    }

    @Override
    public void stop() {
        wheelAppliedVoltage = 0;
        wheelMotor.setVoltage(0);
    }

    @Override
    public double getSpeed() {
        return wheelMotor.getVelocity().getValueAsDouble();
    }

    @Override
    public void configurePID(PIDConstants constants) {
        speedController = new PIDController(constants.kP, constants.kI, constants.kD);
    }

    @Override
    public int getNumberOfPieces() {
        for (int i = 0; i < beamBreakDebouncers.length; i++) {
            if (beamBreakDebouncers[i].calculate(beamBreaks[i].get())) {
                currentNumOfPieces = i;
                return i;
            }
        }

        return 0;
    }

    @Override
    public boolean nextSensorTriggered() {
        boolean triggered = formerNumOfPieces != currentNumOfPieces;
        formerNumOfPieces = currentNumOfPieces;

        return triggered;
    }
}
