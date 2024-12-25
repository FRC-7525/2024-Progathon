package frc.robot.subsystems.Elevator;

import static edu.wpi.first.units.Units.MetersPerSecond;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.configs.TalonFXConfigurator;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.InvertedValue;

import edu.wpi.first.math.controller.ElevatorFeedforward;
import edu.wpi.first.math.controller.ProfiledPIDController;


public class ElevatorIOReal implements ElevatorIO {

    private TalonFX leftMotor;
    private TalonFXConfigurator leftConfigurator;
    private TalonFXConfiguration leftConfigurations;
 
    private TalonFX rightMotor;
    private TalonFXConfigurator rightConfigurator;
    private TalonFXConfiguration rightConfigurations;
 
    private ProfiledPIDController pidController;
    private ElevatorFeedforward ffcontroller;

    private double metersPerRotation;
    private double leftMotorVoltage;
    private double rightMotorVoltage;

    public ElevatorIOReal() {
        leftMotor = new TalonFX(12);
        rightMotor = new TalonFX(13);

        leftMotorVoltage = 0;
        rightMotorVoltage = 0;

        //Motor configs
        leftConfigurator = leftMotor.getConfigurator();
        rightConfigurator = rightMotor.getConfigurator();

        leftConfigurations = new TalonFXConfiguration();
        rightConfigurations = new TalonFXConfiguration();

        leftConfigurations.MotorOutput.Inverted = ElevatorConstants.Real.LEFT_INVERTED ? InvertedValue.Clockwise_Positive : InvertedValue.CounterClockwise_Positive;
        leftConfigurations.MotorOutput.NeutralMode = ElevatorConstants.Real.LEFT_NEUTRAL_MODE;
        leftConfigurations.CurrentLimits.StatorCurrentLimitEnable = ElevatorConstants.Real.LEFT_STRATOR_CURRENT_LIMIT_ENABLED;
        leftConfigurations.CurrentLimits.StatorCurrentLimit = ElevatorConstants.Real.LEFT_STRATOR_CURRENT_LIMIT;
        leftConfigurator.apply(leftConfigurations);

        rightConfigurations.MotorOutput.Inverted = ElevatorConstants.Real.RIGHT_INVERTED ? InvertedValue.Clockwise_Positive : InvertedValue.CounterClockwise_Positive;
        rightConfigurations.MotorOutput.NeutralMode = ElevatorConstants.Real.RIGHT_NEUTRAL_MODE;
        rightConfigurations.CurrentLimits.StatorCurrentLimitEnable = ElevatorConstants.Real.RIGHT_STRATOR_CURRENT_LIMIT_ENABLED;
        rightConfigurations.CurrentLimits.StatorCurrentLimit = ElevatorConstants.Real.RIGHT_STRATOR_CURRENT_LIMIT;
        rightConfigurator.apply(rightConfigurations);

        //PID and FF controller setup
        pidController = new ProfiledPIDController(ElevatorConstants.Real.PROFILLED_PID_CONSTANTS.kP, ElevatorConstants.Real.PROFILLED_PID_CONSTANTS.kI,
        ElevatorConstants.Real.PROFILLED_PID_CONSTANTS.kD, ElevatorConstants.TRAPEZOID_PROFILE_CONSTRAINTS);
        pidController.setTolerance(ElevatorConstants.POSITION_TOLERANCE, ElevatorConstants.VELOCITY_TOLERANCE);
        pidController.setIZone(ElevatorConstants.Real.PROFILLED_PID_CONSTANTS.iZone);

        ffcontroller = new ElevatorFeedforward(ElevatorConstants.Real.FF_CONSTANTS.kS,
        ElevatorConstants.Real.FF_CONSTANTS.kG, ElevatorConstants.Real.FF_CONSTANTS.kV,
        ElevatorConstants.Real.FF_CONSTANTS.kA);
    }

    @Override
    public void setHeightGoalpoint(double height) {
        pidController.setGoal(height);
    }

    @Override
    public void updateInputs(ElevatorIOInputs inputs) {
        inputs.currentElevatorHeight = leftMotor.getPosition().getValueAsDouble() * metersPerRotation;
        inputs.elevatorHeightSetpoint = pidController.getSetpoint().position;
        inputs.elevatorHeightGoalpoint = pidController.getGoal().position;
        inputs.elevatorVelocity = leftMotor.getVelocity().getValueAsDouble();
        inputs.elevatorVelocitySetpoint = pidController.getSetpoint().velocity;
        inputs.elevatorHeightGoalpoint = pidController.getGoal().velocity;
        inputs.leftMotorVoltInput = leftMotorVoltage;
        inputs.rightMotorVoltInput = rightMotorVoltage;
    }

    @Override
    public void runElevator() {
        leftMotorVoltage = pidController.calculate(leftMotor.getPosition().getValueAsDouble() * metersPerRotation) +
        ffcontroller.calculate(MetersPerSecond.of(pidController.getSetpoint().velocity)).magnitude();
        rightMotorVoltage = pidController.calculate(rightMotor.getPosition().getValueAsDouble() * metersPerRotation) +
        ffcontroller.calculate(MetersPerSecond.of(pidController.getSetpoint().velocity)).magnitude();
        leftMotor.setVoltage(leftMotorVoltage);
        rightMotor.setVoltage(rightMotorVoltage);
    }

    @Override
    public boolean nearTarget() {
        return pidController.atGoal();
    }
    
}
