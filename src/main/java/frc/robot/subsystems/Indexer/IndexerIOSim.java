package frc.robot.subsystems.Indexer;

import com.pathplanner.lib.config.PIDConstants;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.system.plant.DCMotor;
import edu.wpi.first.math.system.plant.LinearSystemId;
import edu.wpi.first.wpilibj.simulation.DCMotorSim;

public class IndexerIOSim implements IndexerIO {
    private DCMotorSim wheelMotorSim;

    private PIDController speedController;

    private double wheelAppliedVoltage;
    private double wheelSpeedpoint;

    private boolean[] beamBreakArray = {false, false, false, false, false, false};

    public IndexerIOSim() {
        wheelMotorSim = new DCMotorSim(LinearSystemId.createDCMotorSystem(DCMotor.getKrakenX60(1), 0.000001, 1), DCMotor.getKrakenX60Foc(IndexerConstants.NUM_SPINNER_MOTORS));

        speedController = new PIDController(0, 0, 0);
    }

    @Override
    public void setSetpoint(double setpoint) {
        wheelAppliedVoltage = speedController.calculate(wheelMotorSim.getAngularVelocityRPM() / 60.0, setpoint);     
        wheelSpeedpoint = setpoint;

        wheelMotorSim.setInputVoltage(wheelAppliedVoltage);
    }

    @Override
    public void updateInputs(IndexerIOInputs inputs) {
        inputs.wheelSpeed = wheelMotorSim.getAngularVelocityRPM() / 60.0;
        inputs.wheelSetpoint = wheelSpeedpoint;
        inputs.beamBreakArray = beamBreakArray;
        
        wheelMotorSim.update(IndexerConstants.SIM_UPDATE_TIME);
    }

    @Override
    public void stop() {
        wheelAppliedVoltage = 0;
        wheelMotorSim.setInputVoltage(0);
    }

    @Override
    public double getSpeed() {
        return wheelMotorSim.getAngularVelocityRPM() / 60.0;
    }

    @Override
    public void configurePID(PIDConstants constants) {
        speedController = new PIDController(constants.kP, constants.kI, constants.kD);
    }

    public boolean[] getBeamBreakArray() {
        return beamBreakArray;
    }
}
