package frc.robot.subsystems.Intake;

import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.util.Units;

public class IntakeIOReal implements IntakeIO {
    private TalonFX wheelMotor;
    private TalonFX pivotMotor;
    private PIDController pivotController;
    private PIDController wheelSpeedController;
    private double pivotPositionSetpoint;
    private double wheelSpeedSetpoint;

    public IntakeIOReal() {
        wheelMotor = new TalonFX(10);
        pivotMotor = new TalonFX(11);
        pivotController = new PIDController(0, 0, 0);
        wheelSpeedController = new PIDController(0, 0, 0);
    }

    @Override
    public void updateInputs(IntakeIOInputs inputs) {
        inputs.pivotPosition = Units.rotationsToDegrees(pivotMotor.getPosition().getValueAsDouble());
        inputs.pivotSetpoint = pivotPositionSetpoint;
        inputs.wheelSpeed = wheelMotor.getVelocity().getValueAsDouble();
        inputs.wheelSpeedSetpoint = wheelSpeedSetpoint;
    }

    @Override
    public void setPivotSetpoint(double pivotSetpoint) {
        this.pivotPositionSetpoint = pivotSetpoint;
        double voltage = pivotController.calculate(Units.rotationsToDegrees(pivotMotor.getPosition().getValueAsDouble()), pivotSetpoint);
        pivotMotor.setVoltage(voltage);
    }

    @Override
    public void setWheelSpeed(double wheelSpeed) {
        this.wheelSpeedSetpoint = wheelSpeed;
        double voltage = wheelSpeedController.calculate(Units.rotationsToDegrees(wheelMotor.getVelocity().getValueAsDouble()), wheelSpeed);
        wheelMotor.setVoltage(voltage);
    }
}
