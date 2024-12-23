package frc.robot.subsystems.Intake;


import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.system.plant.DCMotor;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj.simulation.DCMotorSim;
import edu.wpi.first.wpilibj.simulation.SingleJointedArmSim;
import edu.wpi.first.math.system.plant.LinearSystemId;

public class IntakeIOSim implements IntakeIO {
    SingleJointedArmSim pivotSim;
    DCMotorSim wheelMotorSim;
    PIDController pivotController;
    PIDController wheelSpeedController;

    double wheelSpeedSetpoint;
    double pivotPositionSetpoint;

    IntakeIOSim() {
        pivotSim = new SingleJointedArmSim(DCMotor.getKrakenX60(Constants.NUM_PIVOT_MOTORS), Constants.PIVOT_GEARING, Constants.PIVOT_MOI, Constants.PIVOT_ARM_LENGTH, Units.degreesToRadians(Constants.MIN_PIVOT_ANGLE), Units.degreesToRadians(Constants.MAX_PIVOT_ANGLE), false, Units.degreesToRadians(Constants.STARTING_PIVOT_ANGLE));
        wheelMotorSim = new DCMotorSim(LinearSystemId.createDCMotorSystem(DCMotor.getKrakenX60(Constants.NUM_WHEEL_MOTORS), Constants.WHEEL_MOTOR_MOI, Constants.WHEEL_MOTOR_GEARING), DCMotor.getKrakenX60(Constants.NUM_WHEEL_MOTORS));
        pivotController = new PIDController(0, 0, 0);
        wheelSpeedController = new PIDController(0, 0, 0);

        wheelSpeedSetpoint = 0;
        pivotPositionSetpoint = 0;
    }

    @Override
    public void updateInputs(IntakeIOInputs input) {
        input.wheelSpeed = Units.radiansToDegrees(wheelMotorSim.getAngularVelocityRadPerSec());
        input.wheelSpeedSetpoint = wheelSpeedSetpoint;
        input.pivotPosition = Units.radiansToDegrees(pivotSim.getAngleRads());
        input.pivotSetpoint = pivotPositionSetpoint;

        pivotSim.update(0.02);
        wheelMotorSim.update(0.02);
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
