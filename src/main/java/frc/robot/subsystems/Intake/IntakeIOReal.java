package frc.robot.subsystems.Intake;

import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.util.sendable.SendableRegistry;
import frc.robot.GlobalConstants;
import frc.robot.GlobalConstants.RobotMode;

public class IntakeIOReal implements IntakeIO {
    private TalonFX wheelMotor;
    private TalonFX pivotMotor;
    private PIDController pivotController;
    private PIDController wheelSpeedController;
    private double pivotPositionSetpoint;
    private double wheelSpeedSetpoint;
    
    public IntakeIOReal() {
        wheelMotor = new TalonFX(IntakeConstants.Real.WHEEL_MOTOR_CANID);
        pivotMotor = new TalonFX(IntakeConstants.Real.PIVOT_MOTOR_CANID);
        wheelMotor.setPosition(0);
        pivotMotor.setPosition(0);
        pivotController = new PIDController(IntakeConstants.Real.PIVOT_PID_CONSTANTS.kP,
        IntakeConstants.Real.PIVOT_PID_CONSTANTS.kI, IntakeConstants.Real.PIVOT_PID_CONSTANTS.kD);
        wheelSpeedController = new PIDController(IntakeConstants.Real.WHEEL_PID_CONSTANTS.kP,
        IntakeConstants.Real.WHEEL_PID_CONSTANTS.kI, IntakeConstants.Real.WHEEL_PID_CONSTANTS.kD);

        if (GlobalConstants.ROBOT_MODE == RobotMode.TESTING) {
            SendableRegistry.setName(pivotController, "Intake", "Pivot PIDController");
            SendableRegistry.setName(wheelSpeedController, "Intake", "Wheel Speed PIDController");
        }
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
