package frc.robot.subsystems.Indexer;

import com.pathplanner.lib.config.PIDConstants;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.system.plant.DCMotor;
import edu.wpi.first.wpilibj.simulation.DCMotorSim;

public class IndexerIOSim implements IndexerIO {
    private DCMotorSim wheelMotorSim;

    private PIDController speedController;

    private double wheelAppliedVoltage;
    private double wheelSpeedpoint;

    public IndexerIOSim() {
        wheelMotorSim = new DCMotorSim(
            DCMotor.getKrakenX60Foc(Constants.NUM_SPINNER_MOTORS),
            Constants.SPINNER_GEARING,
            Constants.SPINNER_MOI
        );

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

        wheelMotorSim.update(Constants.SIM_UPDATE_TIME);
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
}
