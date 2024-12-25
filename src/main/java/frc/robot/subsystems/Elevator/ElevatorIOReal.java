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

        leftConfigurations.MotorOutput.Inverted = Constants.Real.LEFT_INVERTED ? InvertedValue.Clockwise_Positive : InvertedValue.CounterClockwise_Positive;
        leftConfigurations.MotorOutput.NeutralMode = Constants.Real.LEFT_NEUTRAL_MODE;
        leftConfigurations.CurrentLimits.StatorCurrentLimitEnable = Constants.Real.LEFT_STRATOR_CURRENT_LIMIT_ENABLED;
        leftConfigurations.CurrentLimits.StatorCurrentLimit = Constants.Real.LEFT_STRATOR_CURRENT_LIMIT;
        leftConfigurator.apply(leftConfigurations);

        rightConfigurations.MotorOutput.Inverted = Constants.Real.RIGHT_INVERTED ? InvertedValue.Clockwise_Positive : InvertedValue.CounterClockwise_Positive;
        rightConfigurations.MotorOutput.NeutralMode = Constants.Real.RIGHT_NEUTRAL_MODE;
        rightConfigurations.CurrentLimits.StatorCurrentLimitEnable = Constants.Real.RIGHT_STRATOR_CURRENT_LIMIT_ENABLED;
        rightConfigurations.CurrentLimits.StatorCurrentLimit = Constants.Real.RIGHT_STRATOR_CURRENT_LIMIT;
        rightConfigurator.apply(rightConfigurations);

        //PID and FF controller setup
        pidController = new ProfiledPIDController(Constants.Real.PROFILLED_PID_CONSTANTS.kP, Constants.Real.PROFILLED_PID_CONSTANTS.kI,
        Constants.Real.PROFILLED_PID_CONSTANTS.kD, Constants.TRAPEZOID_PROFILE_CONSTRAINTS);
        pidController.setTolerance(Constants.POSITION_TOLERANCE, Constants.VELOCITY_TOLERANCE);
        pidController.setIZone(Constants.Real.PROFILLED_PID_CONSTANTS.iZone);

        ffcontroller = new ElevatorFeedforward(Constants.Real.FF_CONSTANTS.kS,
        Constants.Real.FF_CONSTANTS.kG, Constants.Real.FF_CONSTANTS.kV,
        Constants.Real.FF_CONSTANTS.kA);
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
