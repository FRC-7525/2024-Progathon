package frc.robot.subsystems.Elevator;

import static edu.wpi.first.units.Units.MetersPerSecond;

import edu.wpi.first.math.controller.ElevatorFeedforward;
import edu.wpi.first.math.controller.ProfiledPIDController;
import edu.wpi.first.wpilibj.simulation.ElevatorSim;

public class ElevatorIOSim implements ElevatorIO{

    private ProfiledPIDController pidController;
    private ElevatorFeedforward ffcontroller;

    private double appliedVoltage;

    private ElevatorSim elevatorSim;


    public ElevatorIOSim() {
        elevatorSim = new ElevatorSim(Constants.GEARBOX, Constants.GEARING,
        Constants.CARRIAGE_MASS, Constants.DRUM_RADIUS,
        Constants.MIN_HEIGHT, Constants.MAX_HEIGHT,
        Constants.SIMULATE_GRAVITY, Constants.STARTING_HEIGHT);

        pidController = new ProfiledPIDController(Constants.PROFILLED_PID_CONSTANTS.kP,
        Constants.PROFILLED_PID_CONSTANTS.kI,
        Constants.PROFILLED_PID_CONSTANTS.kD,
        Constants.TRAPEZOID_PROFILE_CONSTRAINTS);
        pidController.setTolerance(Constants.POSITION_TOLERANCE, Constants.VELOCITY_TOLERANCE);
        pidController.setIZone(Constants.I_ZONE);

        ffcontroller = new ElevatorFeedforward(Constants.FF_CONSTANTS.kS,
        Constants.FF_CONSTANTS.kG,
        Constants.FF_CONSTANTS.kV,
        Constants.FF_CONSTANTS.kA);

    }

    public void updateInputs(ElevatorIOInputs inputs) {
        elevatorSim.update(0.02); // adding this to global constants once this exists.

        inputs.currentElevatorHeight = elevatorSim.getPositionMeters();
        inputs.elevatorHeightSetpoint = pidController.getSetpoint().position;
        inputs.elevatorVelocity = elevatorSim.getVelocityMetersPerSecond();
        inputs.elevatorVelocitySetpoint = pidController.getSetpoint().velocity;
        inputs.leftMotorVoltInput = appliedVoltage;
        inputs.rightMotorVoltInput = appliedVoltage;
    }

    @Override
    public void setHeightSetpoint(double height) {
        pidController.setGoal(height);
    }

    public void runElevator() {
        appliedVoltage = pidController.calculate(elevatorSim.getPositionMeters()) +
        ffcontroller.calculate(MetersPerSecond.of(pidController.getSetpoint().velocity)).magnitude();
        elevatorSim.setInputVoltage(appliedVoltage);
    }

    @Override
    public boolean nearTarget() {
        return pidController.atSetpoint();
    }
    
}
