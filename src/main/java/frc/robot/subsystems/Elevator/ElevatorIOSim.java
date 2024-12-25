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
        elevatorSim = new ElevatorSim(Constants.Sim.GEARBOX, Constants.Sim.GEARING,
        Constants.Sim.CARRIAGE_MASS, Constants.Sim.DRUM_RADIUS,
        Constants.Sim.MIN_HEIGHT, Constants.Sim.MAX_HEIGHT,
        Constants.Sim.SIMULATE_GRAVITY, Constants.Sim.STARTING_HEIGHT);

        pidController = new ProfiledPIDController(Constants.Sim.PROFILLED_PID_CONSTANTS.kP,
        Constants.Sim.PROFILLED_PID_CONSTANTS.kI,
        Constants.Sim.PROFILLED_PID_CONSTANTS.kD,
        Constants.TRAPEZOID_PROFILE_CONSTRAINTS);
        pidController.setTolerance(Constants.POSITION_TOLERANCE, Constants.VELOCITY_TOLERANCE);
        pidController.setIZone(Constants.Sim.PROFILLED_PID_CONSTANTS.iZone);

        ffcontroller = new ElevatorFeedforward(Constants.Sim.FF_CONSTANTS.kS,
        Constants.Sim.FF_CONSTANTS.kG,
        Constants.Sim.FF_CONSTANTS.kV,
        Constants.Sim.FF_CONSTANTS.kA);

    }

    public void updateInputs(ElevatorIOInputs inputs) {
        elevatorSim.update(0.02); // adding this to global constants once this exists.

        inputs.currentElevatorHeight = elevatorSim.getPositionMeters();
        inputs.elevatorHeightSetpoint = pidController.getSetpoint().position;
        inputs.elevatorHeightGoalpoint = pidController.getGoal().position;

        inputs.elevatorVelocity = elevatorSim.getVelocityMetersPerSecond();
        inputs.elevatorVelocitySetpoint = pidController.getSetpoint().velocity;
        inputs.elevatorVelocityGoalpoint = pidController.getGoal().velocity;

        inputs.leftMotorVoltInput = appliedVoltage;
        inputs.rightMotorVoltInput = appliedVoltage;
    }

    @Override
    public void setHeightGoalpoint(double height) {
        pidController.setGoal(height);
    }

    public void runElevator() {
        appliedVoltage = pidController.calculate(elevatorSim.getPositionMeters()) +
        ffcontroller.calculate(MetersPerSecond.of(pidController.getSetpoint().velocity)).magnitude();
        elevatorSim.setInputVoltage(appliedVoltage);
    }

    @Override
    public boolean nearTarget() {
        return pidController.atGoal();
    }
    
}
