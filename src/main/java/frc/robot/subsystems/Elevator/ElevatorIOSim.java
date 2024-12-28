package frc.robot.subsystems.Elevator;

import static edu.wpi.first.units.Units.MetersPerSecond;

import edu.wpi.first.math.controller.ElevatorFeedforward;
import edu.wpi.first.math.controller.ProfiledPIDController;
import edu.wpi.first.util.sendable.SendableRegistry;
import edu.wpi.first.wpilibj.simulation.ElevatorSim;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ElevatorIOSim implements ElevatorIO{

    private ProfiledPIDController pidController;
    private ElevatorFeedforward ffcontroller;

    private double appliedVoltage;

    private ElevatorSim elevatorSim;


    public ElevatorIOSim() {
        elevatorSim = new ElevatorSim(ElevatorConstants.Sim.GEARBOX, ElevatorConstants.Sim.GEARING,
        ElevatorConstants.Sim.CARRIAGE_MASS.magnitude(), ElevatorConstants.Sim.DRUM_RADIUS.magnitude(),
        ElevatorConstants.Sim.MIN_HEIGHT.magnitude(), ElevatorConstants.Sim.MAX_HEIGHT.magnitude(),
        ElevatorConstants.Sim.SIMULATE_GRAVITY, ElevatorConstants.Sim.STARTING_HEIGHT.magnitude());

        pidController = new ProfiledPIDController(ElevatorConstants.Sim.PROFILLED_PID_CONSTANTS.kP,
        ElevatorConstants.Sim.PROFILLED_PID_CONSTANTS.kI,
        ElevatorConstants.Sim.PROFILLED_PID_CONSTANTS.kD,
        ElevatorConstants.TRAPEZOID_PROFILE_CONSTRAINTS);
        pidController.setTolerance(ElevatorConstants.POSITION_TOLERANCE, ElevatorConstants.VELOCITY_TOLERANCE);
        pidController.setIZone(ElevatorConstants.Sim.PROFILLED_PID_CONSTANTS.iZone);

        ffcontroller = new ElevatorFeedforward(ElevatorConstants.Sim.FF_CONSTANTS.kS,
        ElevatorConstants.Sim.FF_CONSTANTS.kG,
        ElevatorConstants.Sim.FF_CONSTANTS.kV,
        ElevatorConstants.Sim.FF_CONSTANTS.kA);

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
        
        SmartDashboard.putData("Elevator PID", pidController);
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
