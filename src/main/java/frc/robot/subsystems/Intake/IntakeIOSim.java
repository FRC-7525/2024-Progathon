package frc.robot.subsystems.Intake;


import static frc.robot.GlobalConstants.SIM_DELTA_TIME;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.system.plant.DCMotor;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj.simulation.DCMotorSim;
import edu.wpi.first.wpilibj.simulation.SingleJointedArmSim;
import edu.wpi.first.math.system.plant.LinearSystemId;

public class IntakeIOSim implements IntakeIO {
    private SingleJointedArmSim pivotSim;
    private DCMotorSim wheelMotorSim;
    private PIDController pivotController;
    private PIDController wheelSpeedController;

    private double wheelSpeedSetpoint;
    private double pivotPositionSetpoint;

    IntakeIOSim() {
        pivotSim = new SingleJointedArmSim(DCMotor.getKrakenX60(IntakeConstants.Sim.NUM_PIVOT_MOTORS),
        IntakeConstants.Sim.PIVOT_GEARING, IntakeConstants.Sim.PIVOT_MOI.magnitude(), IntakeConstants.Sim.PIVOT_ARM_LENGTH.magnitude(),
        Units.degreesToRadians(IntakeConstants.Sim.MIN_PIVOT_ANGLE.magnitude()),
        Units.degreesToRadians(IntakeConstants.Sim.MAX_PIVOT_ANGLE.magnitude()),
        false, Units.degreesToRadians(IntakeConstants.Sim.STARTING_PIVOT_ANGLE.magnitude()));
        
        wheelMotorSim = new DCMotorSim(LinearSystemId.createDCMotorSystem(DCMotor.getKrakenX60(IntakeConstants.Sim.NUM_WHEEL_MOTORS),
        IntakeConstants.Sim.WHEEL_MOTOR_MOI.magnitude(), IntakeConstants.Sim.WHEEL_MOTOR_GEARING),
        DCMotor.getKrakenX60(IntakeConstants.Sim.NUM_WHEEL_MOTORS));
        
        pivotController = new PIDController(IntakeConstants.Sim.PIVOT_PID_CONSTANTS.kP,
        IntakeConstants.Sim.PIVOT_PID_CONSTANTS.kI, IntakeConstants.Sim.PIVOT_PID_CONSTANTS.kD);
        wheelSpeedController = new PIDController(IntakeConstants.Sim.WHEEL_PID_CONSTANTS.kP,
        IntakeConstants.Sim.WHEEL_PID_CONSTANTS.kI, IntakeConstants.Sim.WHEEL_PID_CONSTANTS.kD);

        wheelSpeedSetpoint = 0;
        pivotPositionSetpoint = 0;
    }

    @Override
    public void updateInputs(IntakeIOInputs input) {
        pivotSim.update(SIM_DELTA_TIME);
        wheelMotorSim.update(SIM_DELTA_TIME);

        input.wheelSpeed = Units.radiansToDegrees(wheelMotorSim.getAngularVelocityRadPerSec());
        input.wheelSpeedSetpoint = wheelSpeedSetpoint;
        input.pivotPosition = Units.radiansToDegrees(pivotSim.getAngleRads());
        input.pivotSetpoint = pivotPositionSetpoint;
    }

    @Override
    public void setPivotSetpoint(double pivotSetpoint) {
        this.pivotPositionSetpoint = pivotSetpoint;
        pivotSim.setInputVoltage(pivotController.calculate(Units.radiansToDegrees(pivotSim.getAngleRads()), pivotSetpoint));
    }

    @Override
    public void setWheelSpeed(double wheelSpeedSetpoint) {
        this.wheelSpeedSetpoint = wheelSpeedSetpoint;
        wheelMotorSim.setInputVoltage(wheelSpeedController.calculate(wheelMotorSim.getAngularVelocityRadPerSec(), wheelSpeedSetpoint));
    }


}
